---
id: exGenericCrudService1
title: Generic CRUD Service
sidebar_label: Generic CRUD Service
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
public class UserServiceImpl extends ACrudServiceImpl<User, Integer> implements IUserService {

    private final IUserRepo  Generic CRUD Service ExampleuserRepo;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    protected JpaRepository<User, Integer> getRepository() {
        return userRepo;
    }

    @Override
    protected Class<User> getClassOfEntity() {
        return User.class;
    }

    @Override
    protected Integer getId(User entity) {
        return entity.getId();
    }

    ....
}

```