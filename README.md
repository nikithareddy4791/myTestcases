mvn clean test -Dtest=SecurityConfigTest#debug_printClaimNames -s
```

The output will show two things:

**1. What key is actually stored in the JWT:**
```
=== JWT Claims ===
  key=[nnnn Groups] value=[[SG-ddd-SUPERVISOR]]
```
If the key shows something different (like `nnnn_Groups` or truncated), that's the problem.

**2. Whether the converter finds any authorities:**
```
=== Authorities ===
  ROLE_SG-DDD-SUPERVISOR   ← good
(empty)                     ← means getClaimAsStringList("nnnn Groups") returned null




mvn clean test "-Dtest=SecurityConfigTest#debug_printClaimNames" | Select-String -Pattern "JWT Claims|Authorities|key=|ROLE_"

mvn clean test "-Dtest=SecurityConfigTest#debug_printClaimNames" 2>&1 | Tee-Object -FilePath "test-output.txt"
Get-Content "test-output.txt" | Select-String -Pattern "JWT|key=|Auth|ROLE_|==="
