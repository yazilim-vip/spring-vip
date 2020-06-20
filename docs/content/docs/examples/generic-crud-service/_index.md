---
weight: 2
bookFlatSection: false
bookToC: false
---

### Generic CRUD Service Example Usage
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
