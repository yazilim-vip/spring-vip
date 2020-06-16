---
title: Introduction
type: docs
bookToc: false
---

# About

The Spring Core library is a generic auxiliary library for `Spring Web Applications`, `Spring RESTful Web Services`, which includes the implementation of generic services and auxiliary structures. It is a well designed library and allows developers to create services quickly.


# Quick Start
 
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
 