Here's a summary of the 4 test files generated, covering all 14 API endpoints from your OpenAPI spec:
CaseControllerTest.java — 18 tests covering:
GET /case/stats — success + service error
GET /case/{caseId} — success, 404, invalid ID
PUT /case/{caseId} — success, 404, missing body
POST /case — 201 created, 200 already exists, 403 forbidden, missing required fields
POST /case/{caseId}/note — text-only, with attachment, case not found
POST /case/list — success, empty result, filter by arrId, filter by category+office

UserControllerTest.java — 13 tests covering:
GET /users — success, empty, AD unavailable, roles check
GET /user/login — success (with headers), bad credentials, optional param
GET /user/logout — success, service failure
GET /user/{username} — success, 404, invalid format, all fields

ReferenceControllerTest.java — 17 tests covering all 7 reference endpoints (/codes, /items, /adas, /categories, /tags, /dddOffices, /statuses) with success, empty list, and error scenarios. Also validates the required codeType query param.

DDDServiceIntegrationTest.java — Full Spring Boot context integration tests with @SpringBootTest, including a full case lifecycle test (create → retrieve → update) and structure validation for all endpoints.

Quick setup notes: The tests assume service classes named CaseService, UserService, and ReferenceService, and models matching the YAML schema. You'll need to add spring-boot-starter-test + mockito-core to your pom.xml, and an application-test.yml pointing to H2 for the integration tests.
