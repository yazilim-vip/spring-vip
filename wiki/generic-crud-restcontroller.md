[<<< back](./README.md)

* [Getting Started](./wiki/getting-started.md)

**Examples** 
* Generic CRUD Rest Controller
* [Generic CRUD Service](./wiki/generic-crud-service.md)

### Generic CRUD Rest Controller Example Usage
To use `Generic CRUD Rest Controller` feature you need following; \
Let's assume you have a table named as `User` on database. Then you need;
1. Entity for User
2. `Generic CRUD Service` for `User` entity. [see](/docs/examples/generic-crud-service/)


Then select `Generic Rest Controllr` depending on your needs. If;
1. you need only read: `ARestRead`
2. you need need create, read and update: `ARestCru`
3. you need need create, read and update, delete: `ARestCrud`

Sample:

```java
@Service
public class RestUser extends ARestCrud<User, Integer> {

    private final IUserService  Generic CRUD Service ExampleuserService;

    public UserServiceImpl(IUserService userService) {
        this.userService = userService
    }

    @Override
    protected ICrudService<User, Integer> getService() {
        return userService;
    }

    @Override
    protected Class<User> getClassOfEntity() {
        return User.class;
    }

    ....
}

```
