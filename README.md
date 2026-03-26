mvn clean test "-Dtest=SecurityConfigTest#debug_printClaimNames" "-Dsurefire.useFile=false" 2>&1

Get-Content "src\main\java\org\nnnn\ddd\SecurityConfig.java" | Select-String "Groups|getClaimAs"
