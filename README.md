 @Test
    @WithMockUser
    void getCaseById_caseSealed_notSupervisor_notInSealedGroup_returns403() throws Exception {
        when(caseService.loadCase(100))
                .thenThrow(new SealedAccessException("SEALED_ACCESS_PERM"));

        mockMvc.perform(get("/case/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
