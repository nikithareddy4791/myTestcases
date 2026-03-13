


public ResponseEntity<String> loginUser(@Parameter(in = ParameterIn.QUERY, description = "The user name for login" ,schema=@Schema()) @Valid @RequestParam(value = "username", required = false) String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                auditService.auditAction(AppConstants.AUDIT_ACTION_LOGIN, null);
                return new ResponseEntity<String>(objectMapper.readValue("\"\"", String.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }



    public ResponseEntity<User> getUserByName(@Parameter(in = ParameterIn.PATH, description = "The name that needs to be fetched. Use user1 for testing", required=true, schema=@Schema()) @PathVariable("username") String username) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\r\n  \"firstName\" : \"John\",\r\n  \"lastName\" : \"James\",\r\n  \"roles\" : [ \"roles\", \"roles\" ],\r\n  \"mobile\" : \"mobile\",\r\n  \"rank\" : \"rank\",\r\n  \"tax\" : \"123456\",\r\n  \"title\" : \"title\",\r\n  \"cmdCode\" : \"cmdCode\",\r\n  \"email\" : \"john@email.com\",\r\n  \"username\" : \"theUser\"\r\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }
