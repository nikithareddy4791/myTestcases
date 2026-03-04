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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReferenceController.class)
@DisplayName("ReferenceController Tests")
class ReferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReferenceService referenceService;

    private List<ReferenceCode> referenceCodes;
    private List<Item> items;
    private List<ADA> adaList;
    private List<Category> categories;
    private List<Tag> tags;
    private List<DDDOffice> dddOffices;
    private List<Status> statuses;

    @BeforeEach
    void setUp() {
        // Reference Codes
        ReferenceCode code1 = new ReferenceCode();
        code1.setId(1);
        code1.setCodeType("CHARGE_TYPE");
        code1.setCodeVal("F");
        code1.setCodeDesc("Felony");

        ReferenceCode code2 = new ReferenceCode();
        code2.setId(2);
        code2.setCodeType("CHARGE_TYPE");
        code2.setCodeVal("M");
        code2.setCodeDesc("Misdemeanor");
        referenceCodes = Arrays.asList(code1, code2);

        // Items
        Item item1 = new Item();
        item1.setId(1);
        item1.setItemDesc("ECMS");
        item1.setInactiveFlg(0);

        Item item2 = new Item();
        item2.setId(2);
        item2.setItemDesc("Court Records");
        item2.setInactiveFlg(0);
        items = Arrays.asList(item1, item2);

        // ADAs
        ADA ada1 = new ADA();
        ada1.setId(1);
        ada1.setFrstNm("Sarah");
        ada1.setLastNm("Connor");
        ada1.setEmailAddrDesc("sconnor@da.gov");
        ada1.setBoroughNm("Manhattan");
        ada1.setInactiveFlg(0);

        ADA ada2 = new ADA();
        ada2.setId(2);
        ada2.setFrstNm("Michael");
        ada2.setLastNm("Banks");
        ada2.setEmailAddrDesc("mbanks@da.gov");
        ada2.setBoroughNm("Brooklyn");
        ada2.setInactiveFlg(0);
        adaList = Arrays.asList(ada1, ada2);

        // Categories
        Category cat1 = new Category();
        cat1.setId(1);
        cat1.setCategoryDesc("Shootings");
        cat1.setInactiveFlg(0);

        Category cat2 = new Category();
        cat2.setId(2);
        cat2.setCategoryDesc("Robbery");
        cat2.setInactiveFlg(0);
        categories = Arrays.asList(cat1, cat2);

        // Tags
        Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setTagDesc("Gun");
        tag1.setInactiveFlg(0);

        Tag tag2 = new Tag();
        tag2.setId(2);
        tag2.setTagDesc("Gang");
        tag2.setInactiveFlg(0);
        tags = Arrays.asList(tag1, tag2);

        // DDD Offices
        DDDOffice office1 = new DDDOffice();
        office1.setId(1);
        office1.setDddOfficeDesc("Manhattan");
        office1.setInactiveFlg(0);

        DDDOffice office2 = new DDDOffice();
        office2.setId(2);
        office2.setDddOfficeDesc("Brooklyn");
        office2.setInactiveFlg(0);
        dddOffices = Arrays.asList(office1, office2);

        // Statuses
        Status status1 = new Status();
        status1.setId(1);
        status1.setStatusDesc("Completed");
        status1.setInactiveFlg(0);

        Status status2 = new Status();
        status2.setId(2);
        status2.setStatusDesc("Pending");
        status2.setInactiveFlg(0);
        statuses = Arrays.asList(status1, status2);
    }

    // =========================================================
    // GET /reference/codes
    // =========================================================

    @Test
    @DisplayName("GET /reference/codes - should return list of codes for valid codeType")
    void getGeneralCodeList_Success() throws Exception {
        when(referenceService.getGeneralCodeList("CHARGE_TYPE")).thenReturn(referenceCodes);

        mockMvc.perform(get("/reference/codes")
                .param("codeType", "CHARGE_TYPE")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].codeType", is("CHARGE_TYPE")))
                .andExpect(jsonPath("$[0].codeVal", is("F")))
                .andExpect(jsonPath("$[0].codeDesc", is("Felony")))
                .andExpect(jsonPath("$[1].codeVal", is("M")));

        verify(referenceService, times(1)).getGeneralCodeList("CHARGE_TYPE");
    }

    @Test
    @DisplayName("GET /reference/codes - should return 400 when codeType param is missing")
    void getGeneralCodeList_MissingCodeType() throws Exception {
        mockMvc.perform(get("/reference/codes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(referenceService, never()).getGeneralCodeList(anyString());
    }

    @Test
    @DisplayName("GET /reference/codes - should return empty list when no codes found for type")
    void getGeneralCodeList_NoResults() throws Exception {
        when(referenceService.getGeneralCodeList("UNKNOWN_TYPE")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/codes")
                .param("codeType", "UNKNOWN_TYPE")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /reference/codes - should return 500 on service error")
    void getGeneralCodeList_ServiceError() throws Exception {
        when(referenceService.getGeneralCodeList(anyString()))
                .thenThrow(new RuntimeException("DB error"));

        mockMvc.perform(get("/reference/codes")
                .param("codeType", "CHARGE_TYPE")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    // =========================================================
    // GET /reference/items
    // =========================================================

    @Test
    @DisplayName("GET /reference/items - should return list of items")
    void getItemList_Success() throws Exception {
        when(referenceService.getItemList()).thenReturn(items);

        mockMvc.perform(get("/reference/items")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].itemDesc", is("ECMS")))
                .andExpect(jsonPath("$[0].inactiveFlg", is(0)))
                .andExpect(jsonPath("$[1].itemDesc", is("Court Records")));

        verify(referenceService, times(1)).getItemList();
    }

    @Test
    @DisplayName("GET /reference/items - should return empty list when no items found")
    void getItemList_Empty() throws Exception {
        when(referenceService.getItemList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/items")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /reference/items - should return 500 on service error")
    void getItemList_ServiceError() throws Exception {
        when(referenceService.getItemList()).thenThrow(new RuntimeException("DB error"));

        mockMvc.perform(get("/reference/items")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    // =========================================================
    // GET /reference/adas
    // =========================================================

    @Test
    @DisplayName("GET /reference/adas - should return list of ADAs")
    void getADAList_Success() throws Exception {
        when(referenceService.getADAList()).thenReturn(adaList);

        mockMvc.perform(get("/reference/adas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].frstNm", is("Sarah")))
                .andExpect(jsonPath("$[0].lastNm", is("Connor")))
                .andExpect(jsonPath("$[0].emailAddrDesc", is("sconnor@da.gov")))
                .andExpect(jsonPath("$[0].boroughNm", is("Manhattan")));

        verify(referenceService, times(1)).getADAList();
    }

    @Test
    @DisplayName("GET /reference/adas - should return empty list when no ADAs in DB")
    void getADAList_Empty() throws Exception {
        when(referenceService.getADAList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/adas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /reference/adas - should only return active ADAs (inactiveFlg = 0)")
    void getADAList_OnlyActive() throws Exception {
        when(referenceService.getADAList()).thenReturn(adaList);

        mockMvc.perform(get("/reference/adas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].inactiveFlg", is(0)))
                .andExpect(jsonPath("$[1].inactiveFlg", is(0)));
    }

    // =========================================================
    // GET /reference/categories
    // =========================================================

    @Test
    @DisplayName("GET /reference/categories - should return list of categories")
    void getCategoryList_Success() throws Exception {
        when(referenceService.getCategoryList()).thenReturn(categories);

        mockMvc.perform(get("/reference/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].categoryDesc", is("Shootings")))
                .andExpect(jsonPath("$[1].categoryDesc", is("Robbery")));

        verify(referenceService, times(1)).getCategoryList();
    }

    @Test
    @DisplayName("GET /reference/categories - should return empty list when no categories")
    void getCategoryList_Empty() throws Exception {
        when(referenceService.getCategoryList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // =========================================================
    // GET /reference/tags
    // =========================================================

    @Test
    @DisplayName("GET /reference/tags - should return list of tags")
    void getTagList_Success() throws Exception {
        when(referenceService.getTagList()).thenReturn(tags);

        mockMvc.perform(get("/reference/tags")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tagDesc", is("Gun")))
                .andExpect(jsonPath("$[1].tagDesc", is("Gang")));

        verify(referenceService, times(1)).getTagList();
    }

    @Test
    @DisplayName("GET /reference/tags - should return empty list when no tags")
    void getTagList_Empty() throws Exception {
        when(referenceService.getTagList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/tags")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // =========================================================
    // GET /reference/dddOffices
    // =========================================================

    @Test
    @DisplayName("GET /reference/dddOffices - should return list of DDD offices")
    void getDDDOfficeList_Success() throws Exception {
        when(referenceService.getDDDOfficeList()).thenReturn(dddOffices);

        mockMvc.perform(get("/reference/dddOffices")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dddOfficeDesc", is("Manhattan")))
                .andExpect(jsonPath("$[1].dddOfficeDesc", is("Brooklyn")));

        verify(referenceService, times(1)).getDDDOfficeList();
    }

    @Test
    @DisplayName("GET /reference/dddOffices - should return empty list when no offices")
    void getDDDOfficeList_Empty() throws Exception {
        when(referenceService.getDDDOfficeList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/dddOffices")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // =========================================================
    // GET /reference/statuses
    // =========================================================

    @Test
    @DisplayName("GET /reference/statuses - should return list of statuses")
    void getStatusList_Success() throws Exception {
        when(referenceService.getStatusList()).thenReturn(statuses);

        mockMvc.perform(get("/reference/statuses")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].statusDesc", is("Completed")))
                .andExpect(jsonPath("$[1].statusDesc", is("Pending")));

        verify(referenceService, times(1)).getStatusList();
    }

    @Test
    @DisplayName("GET /reference/statuses - should return empty list when no statuses")
    void getStatusList_Empty() throws Exception {
        when(referenceService.getStatusList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reference/statuses")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /reference/statuses - should return 500 on service error")
    void getStatusList_ServiceError() throws Exception {
        when(referenceService.getStatusList()).thenThrow(new RuntimeException("DB error"));

        mockMvc.perform(get("/reference/statuses")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
