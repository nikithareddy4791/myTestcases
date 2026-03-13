// =========================================================================
// IOException coverage tests
// =========================================================================

@Test
@WithMockUser
void loginUser_objectMapperThrowsIOException_returns500() throws Exception {
    doNothing().when(auditService).auditAction(anyString(), any());
    when(objectMapper.readValue(anyString(), eq(String.class)))
            .thenThrow(new IOException("Serialization error"));

    mockMvc.perform(get("/user/login")
            .param("username", "jdoe")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
}

@Test
@WithMockUser
void getUserByName_objectMapperThrowsIOException_returns500() throws Exception {
    when(objectMapper.readValue(anyString(), eq(User.class)))
            .thenThrow(new IOException("Serialization error"));

    mockMvc.perform(get("/user/jdoe")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
}
