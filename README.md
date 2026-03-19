public ResponseEntity<dddCase> getCaseById(@Parameter(in = ParameterIn.PATH, description = "Numeric ID of the case to get", required=true, schema=@Schema()) @PathVariable("caseId") Integer caseId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<dddCase>(caseService.loadCase(caseId), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<dddCase>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<dddCase>(HttpStatus.NOT_IMPLEMENTED);
    }
