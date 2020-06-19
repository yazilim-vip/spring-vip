---
weight: 10
bookFlatSection: false
---

## Generic CRUD Service

You can manage the flow from the Entity level to the REST or View (MVC's View) level with the SpringVIP Library. 
 
(diagram konacak)

`JPARepository`, `ICrudService` and `ACrudServiceImpl` components could be seen on the figure above already provided by library and Spring Framework to the developer.


* `JPARepository` which is a generic repository, already provided by `Sping Data`. 
* In additon to that `ICrudService` and `ACrudServiceImpl` provided by `SpringVIP` library.


The core feature provided by `SpringVIP` library is that  `Generic CRUD Service` implementation for service layer. `Generic CRUD Service` consists of two components.

* **ICrudService** provides definiitons of generic CRUD methods for entities 
* **ACrudServiceImpl** implements methods defined in `ICrudService`  in a generic manner.

Services extends to ACrudServiceImpl inherits methods from SpringVIP Library, like create, read, update, delete methods and so on. Thus developers can use boilerplate service methods quickly.


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
