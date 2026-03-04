package com.ddd.integration;

import com.ddd.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Full-stack integration tests that load the entire Spring context.
 * Requires an H2 in-memory database configured in application-test.yml.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@DisplayName("DDD Service Integration Tests")
class DDDServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================================================
    // Case Integration Tests
    // =========================================================

    @Test
    @DisplayName("Integration: Create case → retrieve by ID → update → list")
    void caseLifecycle_CreateRetrieveUpdate() throws Exception {
        // 1. Create a new case
        CreateCaseRequest createRequest = new CreateCaseRequest();
        createRequest.setArrId("ARR-INT-001");
        createRequest.setDddOfficeId(1);
        createRequest.setProactiveFlg(0);
        createRequest.setRequestDt(LocalDate.now());
        createRequest.setDueDt(LocalDate.now().plusDays(30));

        String createResponse = mockMvc.perform(post("/case")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.arrId", is("ARR-INT-001")))
                .andReturn().getResponse().getContentAsString();

        DDDCase createdCase = objectMapper.readValue(createResponse, DDDCase.class);
        Integer caseId = createdCase.getId();

        // 2. Retrieve by ID
        mockMvc.perform(get("/case/{caseId}", caseId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(caseId)))
                .andExpect(jsonPath("$.arrId", is("ARR-INT-001")));

        // 3. Update the case
        createdCase.setAssignedNm("Integration Tester");
        mockMvc.perform(put("/case/{caseId}", caseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdCase))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assignedNm", is("Integration Tester")));
    }

    @Test
    @DisplayName("Integration: GET /case/stats returns valid stats structure")
    void getCaseStats_ReturnsValidStructure() throws Exception {
        mockMvc.perform(get("/case/stats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.overdueCount", notNullValue()))
                .andExpect(jsonPath("$.comingDueCount", notNullValue()));
    }

    @Test
    @DisplayName("Integration: POST /case/list with pagination returns correct structure")
    void getCaseList_PaginationStructure() throws Exception {
        CaseFilter filter = new CaseFilter();
        filter.setPageSize(5);
        filter.setPageNumber(0);

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", notNullValue()))
                .andExpect(jsonPath("$.totalPages", notNullValue()))
                .andExpect(jsonPath("$.pageSize", notNullValue()))
                .andExpect(jsonPath("$.pageNumber", notNullValue()))
                .andExpect(jsonPath("$.caseSummaries", notNullValue()));
    }

    @Test
    @DisplayName("Integration: POST /case/list with date range filter")
    void getCaseList_DateRangeFilter() throws Exception {
        CaseFilter filter = new CaseFilter();
        filter.setRequestDtFrom("2024-01-01");
        filter.setRequestDtTo("2024-12-31");
        filter.setPageSize(10);
        filter.setPageNumber(0);

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.caseSummaries", notNullValue()));
    }

    @Test
    @DisplayName("Integration: POST /case/list with sort params")
    void getCaseList_WithSorting() throws Exception {
        CaseFilter filter = new CaseFilter();
        filter.setPageSize(10);
        filter.setPageNumber(0);
        filter.setSortBy(Arrays.asList("requestDt", "assignedNm"));
        filter.setSortOrder(Arrays.asList(1, 0)); // 1=ASC, 0=DESC

        mockMvc.perform(post("/case/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // =========================================================
    // Reference Integration Tests
    // =========================================================

    @Test
    @DisplayName("Integration: GET /reference/codes with codeType returns list")
    void getReferenceCodeList_Integration() throws Exception {
        mockMvc.perform(get("/reference/codes")
                .param("codeType", "CHARGE_TYPE")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /reference/items returns item list")
    void getItemList_Integration() throws Exception {
        mockMvc.perform(get("/reference/items")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /reference/adas returns ADA list")
    void getADAList_Integration() throws Exception {
        mockMvc.perform(get("/reference/adas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /reference/categories returns category list")
    void getCategoryList_Integration() throws Exception {
        mockMvc.perform(get("/reference/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /reference/tags returns tag list")
    void getTagList_Integration() throws Exception {
        mockMvc.perform(get("/reference/tags")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /reference/dddOffices returns DDD office list")
    void getDDDOfficeList_Integration() throws Exception {
        mockMvc.perform(get("/reference/dddOffices")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /reference/statuses returns status list")
    void getStatusList_Integration() throws Exception {
        mockMvc.perform(get("/reference/statuses")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    // =========================================================
    // User Integration Tests
    // =========================================================

    @Test
    @DisplayName("Integration: GET /users returns user list")
    void getUserList_Integration() throws Exception {
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @DisplayName("Integration: GET /user/{username} for existing user")
    void getUserByName_Integration() throws Exception {
        mockMvc.perform(get("/user/{username}", "testuser")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", notNullValue()));
    }
}
