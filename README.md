Get-Content "src\test\java\org\nnnn\ddd\model\DddCaseStatsTest.java" | Select-Object -First 5

# Delete old file
Remove-Item "src\test\java\org\nnnn\ddd\model\DddCaseStatsTest.java" -Force

# Copy new file from downloads
Copy-Item "$env:USERPROFILE\Downloads\DddCaseStatsTest.java" "src\test\java\org\nnnn\ddd\model\DddCaseStatsTest.java"

Get-Content "src\test\java\org\nnnn\ddd\model\DddCaseStatsTest.java" | Select-String "toString|equals|hashCode|fluent"


mvn clean verify "-Dtest=DddCaseStatsTest" "-Dsurefire.useFile=false" 2>&1 | findstr "Tests run"
