// =========================================================================
// GET /reference/alldddOffices
// =========================================================================

@Test
@WithMockUser
void getAlldddOfficeList_returnsOkWithList() throws Exception {
    when(referenceDataService.getAlldddOffices()).thenReturn(Arrays.asList(mockOffice));

    mockMvc.perform(get("/reference/alldddOffices")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].dddOfficeDesc").value("Manhattan"));

    verify(referenceDataService, times(1)).getAlldddOffices();
}

@Test
@WithMockUser
void getAlldddOfficeList_returnsEmptyList() throws Exception {
    when(referenceDataService.getAlldddOffices()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/reference/alldddOffices")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
}

@Test
@WithMockUser
void getAlldddOfficeList_serviceThrowsException_returns500() throws Exception {
    when(referenceDataService.getAlldddOffices())
            .thenThrow(new RuntimeException("DB error"));

    mockMvc.perform(get("/reference/alldddOffices")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
}

@Test
void getAlldddOfficeList_noAuth_returns401() throws Exception {
    mockMvc.perform(get("/reference/alldddOffices")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
}

@Test
@WithMockUser
void getAlldddOfficeList_noAcceptHeader_returns501() throws Exception {
    mockMvc.perform(get("/reference/alldddOffices"))
            .andExpect(status().isNotImplemented());
}
