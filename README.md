# Spring Core Library
The Spring Core library is a generic auxiliary library for `Spring Web Applications`, `Spring RESTful Web Services`, which includes the implementation of generic services and auxiliary structures. It is a well designed library and allows developers to create services quickly. 

## Getting Started
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
 

## Quick Start
1. template proje oluştur
2. maven archtype tanımla
   * http://maven.apache.org/guides/mini/guide-creating-archetypes.html
3. erişim için gereeli açıklamları yap


   
# Getting Started

## Generic CRUD Service

You can manage the flow from the Entity level to the REST or View (MVC's View) level with the Spring Core Library. 
 
(diagram konacak)

`JPARepository`, `ICrudService` and `ACrudServiceImpl` components could be seen on the figure above already provided by library and Spring Framework to the developer.


* `JPARepository` which is a generic repository, already provided by `Sping Data`. 
* In additon to that `ICrudService` and `ACrudServiceImpl` provided by `Spring Core` library.


The core feature provided by `Spring Core` library is that  `Generic CRUD Service` implementation for service layer. `Generic CRUD Service` consists of two components.

* **ICrudService** provides definiitons of generic CRUD methods for entities 
* **ACrudServiceImpl** implements methods defined in `ICrudService`  in a generic manner.

Services extends to ACrudServiceImpl inherits methods from Spring Core Library, like create, read, update, delete methods and so on. Thus developers can use boilerplate service methods quickly.


### Example Usage
To use `Generic CRUD Service` feature you need following; \
Let's assume you have a table named as `User` on database

Create an Entity class

```java
@Entity
public class User {
    @Id
    private int id
    ....
}
```

Create a new repository 

```java
public interface IUserRepo<User, Integer> {
    ....
}
```

Create a new service interface

```java
public interface IUserService extends ICrudService <User, Integer> {
    ....
}
```

Then implment service created on previous step

```java
@Service
public interface UserServiceImpl extends ACrudServiceImpl <User, Integer> implements ICrudService <User, Integer> {
    ....
}
```


## Exceptions
 
## CRUD RestController



## Utility Classes

## AThread 
(....)
