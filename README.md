 @ApiResponse(responseCode = "200", description = "Unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/reference/allDluOffices",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<DLUOffice>> getAllDLUOfficeList();
