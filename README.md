@Test
@WithMockUser
void loginUser_objectMapperThrowsIOException_returns500() throws Exception {
    doNothing().when(auditService).auditAction(anyString(), any());
    doThrow(new IOException("Serialization error"))
            .when(objectMapper).readValue(anyString(), eq(String.class));

    mockMvc.perform(get("/user/login")
            .param("username", "jdoe")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
}

@Test
@WithMockUser
void getUserByName_objectMapperThrowsIOException_returns500() throws Exception {
    doThrow(new IOException("Serialization error"))
            .when(objectMapper).readValue(anyString(), eq(User.class));

    mockMvc.perform(get("/user/jdoe")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
}
