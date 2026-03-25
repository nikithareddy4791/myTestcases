Remove-Item "src\test\java\org\nnnn\ddd\service\CacheServiceTest.java" -Force

Get-Content "src\test\java\org\nnnn\ddd\service\CacheServiceTest.java" | Select-Object -First 10
