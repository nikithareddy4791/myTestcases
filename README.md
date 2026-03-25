Get-Content "target\classes\org\nnnn\ddd\service\CacheService.class" | Select-String "clearReferenceDataCache"

javap -c "target\classes\org\nnnn\ddd\service\CacheService.class" | Select-String -A 20 "clearReferenceDataCache"

<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.12</version>
    <configuration>
        <excludes>
            <exclude>sun/**</exclude>
            <exclude>jdk/**</exclude>
        </excludes>
    </configuration>
</plugin>


<version>0.8.13</version>
