---
title: "Quick Start"
date: 2020-06-14T20:53:02+03:00
weight: 2
---

Add following lines  to your `pom.xml` file; 

```xml
<repositories>

    <!-- yazilim.vip JAR repository -->
    <repository>
        <id>yvip-repo</id>
        <url>http://yazilim.vip:9090</url>
    </repository>

</repositories>

<dependencies>

    <!-- SpringCore Dependency -->
    <dependency>
        <groupId>vip.yazilim.libs</groupId>
        <artifactId>spring-core</artifactId>
        <version>{VERSION}</version>
    </dependency>
    
</dependencies>
```

*\* For now the library is only published to our maven repository. When it will be published to Maven Central Repo, this section will be updated.*
 

