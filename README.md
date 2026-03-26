// In CaseServiceControllerTest.java

@Test
@WithMockUser
void deleteCaseUploadById_success_returns200() throws Exception {
    doNothing().when(caseService).deleteFile(100, 1);

    mockMvc.perform(delete("/case/100/upload/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}

@Test
@WithMockUser
void deleteCaseUploadById_noAcceptHeader_returns501() throws Exception {
    mockMvc.perform(delete("/case/100/upload/1"))
            .andExpect(status().isNotImplemented());
}

@Test
@WithMockUser
void deleteCaseUploadById_serviceThrowsException_returns500() throws Exception {
    doThrow(new RuntimeException("unexpected error"))
            .when(caseService).deleteFile(100, 1);

    mockMvc.perform(delete("/case/100/upload/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
}
