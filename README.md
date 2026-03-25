# Check what's actually in your test file around line 58-65
Get-Content src\test\java\org\nnnn\ddd\service\CacheServiceTest.java | Select-Object -Skip 55 -First 15
