package com.ddd.controller;

import com.ddd.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CaseController.class)
@DisplayName("CaseController Tests")
class CaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CaseService caseService;

    private DDDCase sampleCase;
    private DDDCaseStats sampleStats;
    private CreateCaseRequest createCaseRequest;
    private CaseFilter caseFilter;

    @BeforeEach
    void setUp() {
        // Build ArrestInfo
        ArrestInfo arrestInfo = new ArrestInfo();
        arrestInfo.setArrId("ARR-001");
        arrestInfo.setArrDt(LocalDate.of(2024, 1, 15));
        arrestInfo.setTopCharge("Robbery");
        arrestInfo.setDeftFrstNm("John");
        arrestInfo.setDeftLastNm("Doe");

        // Build DDDCase
        sampleCase = new DDDCase();
        sampleCase.setId(1);
        sampleCase.setArrId("ARR-001");
        sampleCase.setAssignedNm("Jane Smith");
        sampleCase.setRequestDt(LocalDate.of(2024, 1, 16));
        sampleCase.setDueDt(LocalDate.of(2024, 2, 16));
        sampleCase.setProactiveFlg(0);
        sampleCase.setActiveFlg(1);
        sampleCase.setArrest(arrestInfo);

        // Build DDDCaseStats
        sampleStats = new DDDCaseStats();
        sampleStats.setOverdueCount(3);
        sampleStats.setComingDueCount(5);

        // Build CreateCaseRequest
        createCaseRequest = new CreateCaseRequest();
        createCaseRequest.setArrId("ARR-002");
        createCaseRequest.setDddOfficeId(1);
        createCaseRequest.setProactiveFlg(0);

        // Build CaseFilter
        caseFilter = new CaseFilter();
        caseFilter.setPageSize(10);
        caseFilter.setPageNumber(0);
    }

    // =========================================================
    // GET /case/stats
    // =========================================================

    @Test
    @DisplayName("GET /case/stats - should return case stats with HTTP 200")
    void getCaseStats_Success() throws Exception {
        when(caseService.getCaseStats()).thenReturn(sampleStats);

        mockMvc.perform(get("/case/stats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.overdueCount", is(3)))
                .andExpect(jsonPath("$.comingDueCount", is(5)));

        verify(caseService, times(1)).getCaseStats();
    }

    @Test
    @DisplayName("GET /case/stats - should return 500 on service error")
    void getCaseStats_ServiceError() throws Exception {
        when(caseService.getCaseStats()).thenThrow(new RuntimeException("DB connection failed"));

        mockMvc.perform(get("/case/stats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    // =========================================================
    // GET /case/{caseId}
    // =========================================================

    @Test
    @DisplayName("GET /case/{caseId} - should return case when found")
    void getCaseById_Success() throws Exception {
        when(caseService.getCaseById(1)).thenReturn(sampleCase);

        mockMvc.perform(get("/case/{caseId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.arrId", is("ARR-001")))
                .andExpect(jsonPath("$.assignedNm", is("Jane Smith")));

        verify(caseService, times(1)).getCaseById(1);
    }

    @Test
    @DisplayName("GET /case/{caseId} - should return 404 when case not found")
    void getCaseById_NotFound() throws Exception {
        when(caseService.getCaseById(999)).thenThrow(new ResourceNotFoundException("Case not found"));

        mockMvc.perform(get("/case/{caseId}", 999)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /case/{caseId} - should return 400 for invalid (non-integer) caseId")
    void getCaseById_InvalidId() throws Exception {
        mockMvc.perform(get("/case/{caseId}", "abc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // =========================================================
    // PUT /case/{caseId}
    // =========================================================

    @Test
    @DisplayName("PUT /case/{caseId} - should update case and return updated case")
    void updateCase_Success() throws Exception {
        sampleCase.setAssignedNm("Updated Assignee");
        when(caseService.updateCase(anyInt(), any(DDDCase.class))).thenReturn(sampleCase);

        mockMvc.perform(put("/case/{caseId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleCase))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.assignedNm", is("Updated Assignee")));

        verify(caseService, times(1)).updateCase(eq(1), any(DDDCase.class));
    }

    @Test
    @DisplayName("PUT /case/{caseId} - should return 404 when case not found")
    void updateCase_NotFound() throws Exception {
        when(caseService.updateCase(anyInt(), any(DDDCase.class)))
                .thenThrow(new ResourceNotFoundException("Case not found"));

        mockMvc.perform(put("/case/{caseId}", 999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleCase))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /case/{caseId} - should return 400 when request body is missing")
    void updateCase_MissingBody() throws Exception {
        mockMvc.perform(put("/case/{caseId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // =========================================================
    // POST /case
    // =========================================================

    @Test
    @DisplayName("POST /case - should create new case and return HTTP 201")
    void createCase_Created() throws Exception {
        when(caseService.createCase(any(CreateCaseRequest.class))).thenReturn(sampleCase);

        mockMvc.perform(post("/case")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCaseRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.arrId", is("ARR-001")));

        verify(caseService, times(1)).createCase(any(CreateCaseRequest.class));
    }

    @Test
    @DisplayName("POST /case - should return HTTP 200 when case already exists")
    void createCase_AlreadyExists() throws Exception {
        when(caseService.createCase(any(CreateCaseRequest.class)))
                .thenThrow(new CaseAlreadyExistsException("Case already exists"));

        mockMvc.perform(post("/case")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCaseRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /case - should return 403 when user is forbidden")
    void createCase_Forbidden() throws Exception {
        when(caseService.createCase(any(CreateCaseRequest.class)))
                .thenThrow(new ForbiddenException("Access denied"));

        mockMvc.perform(post("/case")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCaseRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /case - should return 400 when required fields are missing")
    void createCase_MissingRequiredFields() throws Exception {
        CreateCaseRequest invalidRequest = new CreateCaseRequest(); // missing arrId, dddOfficeId, proactiveFlg

        mockMvc.perform(post("/case")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // =========================================================
    // POST /case/{caseId}/note
    // =========================================================

    @Test
    @DisplayName("POST /case/{caseId}/note - should create note without attachment and return 201")
    void createCaseNote_TextOnly_Success() throws Exception {
        when(caseService.createCaseNote(anyInt(), anyString(), any(), any()))
                .thenReturn(sampleCase);

        mockMvc.perform(multipart("/case/{caseId}/note", 1)
                .param("noteDesc", "This is a test note"))
                .andExpect(status().isCreated());

        verify(caseService, times(1)).createCaseNote(eq(1), eq("This is a test note"), isNull(), isNull());
    }

    @Test
    @DisplayName("POST /case/{caseId}/note - should create note with file attachment and return 201")
    void createCaseNote_WithAttachment_Success() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "fileContent", "evidence.pdf", "application/pdf",
                "PDF content".getBytes()
        );
        when(caseService.createCaseNote(anyInt(), anyString(), anyString(), any()))
                .thenReturn(sampleCase);

        mockMvc.perform(multipart("/case/{caseId}/note", 1)
                .file(mockFile)
                .param("noteDesc", "Evidence attached")
                .param("fileNm", "evidence.pdf"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /case/{caseId}/note - should return 404 when case not found")
    void createCaseNote_CaseNotFound() throws Exception {
        when(caseService.createCaseNote(anyInt(), any(), any(), any()))
                .thenThrow(new ResourceNotFoundException("Case not found"));

        mockMvc.perform(multipart("/case/{caseId}/note", 999)
                .param("noteDesc", "Test note"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    // POST /case/list
    // =========================================================

    @Test
    @DisplayName("POST /case/list - should return paginated case list")
    void getCaseList_Success() throws Exception {
        DDDCaseSummary summary = new DDDCaseSummary();
        summary.setId(1);
        summary.setArrId("ARR-001");

        CaseListResponse response = new CaseListResponse();
        response.setTotalElements(1L);
        response.setTotalPages(1);
        response.setPageSize(10);
        response.setPageNumber(0);
        response.setFirst(true);
        response.setLast(true);
        response.setCaseSummaries(Collections.singletonList(summary));
        response.setCaseStats(sampleStats);

        when(caseService.getCaseList(any(CaseFilter.class))).thenReturn(response);

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseFilter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.caseSummaries", hasSize(1)))
                .andExpect(jsonPath("$.caseSummaries[0].arrId", is("ARR-001")));
    }

    @Test
    @DisplayName("POST /case/list - should return empty list when no cases match filter")
    void getCaseList_EmptyResult() throws Exception {
        CaseListResponse emptyResponse = new CaseListResponse();
        emptyResponse.setTotalElements(0L);
        emptyResponse.setTotalPages(0);
        emptyResponse.setCaseSummaries(Collections.emptyList());

        when(caseService.getCaseList(any(CaseFilter.class))).thenReturn(emptyResponse);

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseFilter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(0)))
                .andExpect(jsonPath("$.caseSummaries", hasSize(0)));
    }

    @Test
    @DisplayName("POST /case/list - should filter by arrId")
    void getCaseList_FilterByArrId() throws Exception {
        caseFilter.setArrId("ARR-001");

        DDDCaseSummary summary = new DDDCaseSummary();
        summary.setId(1);
        summary.setArrId("ARR-001");

        CaseListResponse response = new CaseListResponse();
        response.setTotalElements(1L);
        response.setCaseSummaries(Collections.singletonList(summary));

        when(caseService.getCaseList(any(CaseFilter.class))).thenReturn(response);

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseFilter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.caseSummaries[0].arrId", is("ARR-001")));
    }

    @Test
    @DisplayName("POST /case/list - should filter by categoryIds and dddOfficeIds")
    void getCaseList_FilterByCategoryAndOffice() throws Exception {
        caseFilter.setCategoryIds(Arrays.asList(1, 2));
        caseFilter.setDddOfficeIds(Collections.singletonList(1));

        CaseListResponse response = new CaseListResponse();
        response.setTotalElements(2L);
        response.setCaseSummaries(Arrays.asList(new DDDCaseSummary(), new DDDCaseSummary()));

        when(caseService.getCaseList(any(CaseFilter.class))).thenReturn(response);

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(caseFilter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(2)));
    }
}
