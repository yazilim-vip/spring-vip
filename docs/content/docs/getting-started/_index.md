---
weight: 1
bookFlatSection: false
---

## Generic CRUD Service

You can manage the flow from the Entity level to the REST or View (MVC's View) level with the SpringVIP Library. 
 
 ![res](/spring_core_diagram_rev3.svg)
 

`JPARepository`, `ICrudService` and `ACrudServiceImpl` components could be seen on the figure above already provided by library and Spring Framework to the developer.


* `JPARepository` which is a generic repository, already provided by `Sping Data`. 
* In additon to that `ICrudService` and `ACrudServiceImpl` provided by `SpringVIP` library.


The core feature provided by `SpringVIP` library is that  `Generic CRUD Service` implementation for service layer. `Generic CRUD Service` consists of two components.

* **ICrudService** provides definiitons of generic CRUD methods for entities
* **ACrudServiceImpl** implements methods defined in `ICrudService`  in a generic manner.

Services extends to ACrudServiceImpl inherits methods from SpringVIP Library, like create, read, update, delete methods and so on. Thus developers can use boilerplate service methods quickly.

### Methods
All DB operations are done via `JPARepository` in `Generic CRUD Service`. All exceptions occurred on `JPARepository` layer is handled by `Generic CRUD Service` then an exception thrown. There are some methods for basic CRUD operations are included in `Generic CRUD Service`. They are;
* GetAll: To get all records on the database
* GetById: To get record that matching id
  * Id passed as parameter to select **must not** be null
* Save: To save entity
  * Entity passed as parameter to save **must not** be null
* Create: To create a record by entity
* Update: To update a record by entity
  * Entity passed as parameter to update **must** be exists on db
* Delete: To delete a record by entity
  * Entity passed as parameter to save **must not** be null
* DeleteById: To delete record by Id
  * Id passed as parameter to select **must not** be null



## Exceptions
There are some general exceptions can be thrown by generic CRUD service implementation (`ACrudServiceImpl`). 
* **DatabaseCreateException**: indicates there is an error on `Create` operation.
* **DatabaseDeleteException**: indicates there is an error on `Delete` operation.
* **DatabaseReadException**: indicates there is an error on `Read` operation.
* **DatabaseSaveException**: indicates there is an error on `Save` operation.
* **DatabaseUpdateException**: indicates there is an error on `Update` operation.



## CRUD REST Controller
Generic CRUD operations defined in service layer can be used in REST layer too. This can be achieved by extending a `Rest Controller` to these classes:

* ARestRead: Read operation
* ARestCru: Create, Read and Update operations
* ARestCrud: Create, Read, Update and Delete operations

Note that default mappings 

### REST Responses
  * RestError
  * RestErrorResponse
  * RestResponse

## Utility Classes



## Thread
threads


## Exception Handler

handler
