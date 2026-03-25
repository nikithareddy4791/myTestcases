mvn clean test -Dtest=CacheServiceTest -pl .

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

Get-ChildItem -Recurse -Filter "*.java" src\test\ | Select-String "CacheManager"
