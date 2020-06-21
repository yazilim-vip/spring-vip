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
There are some methods for basic CRUD operations are included in `Generic CRUD Service`. They are;

If there is an error occurred on `JPARepository` then an exception thrown

* GetAll: To get all records on the database
* GetById: To get record that matching id
  * Id passed as parameter to select **must not** be null
* Save:
  * Entity passed as parameter to save **must not** be null
  * If Entity not found after saving via `JPARepository` an exception thrown
* Create
  
* Update
  * If Entity passed as parameter to update not found on database an exception thrown
  
* Delete
  
* DeleteById
  



#### GetAll

Hepsini almaya yarar
JPA:findAll()


#### GetById
id ile alınır

id null ise DataBaseReadException atılır

#### Save
save yapılır

#### Create

#### Update

#### Delete

#### DeleteById



## Exceptions
There are some general exceptions can be thrown by generic CRUD service implementation (`ACrudServiceImpl`). 
* **DatabaseCreateException**: indicates there is an error on `Create` operation.
* **DatabaseDeleteException**: indicates there is an error on `Delete` operation.
* **DatabaseReadException**: indicates there is an error on `Read` operation.
* **DatabaseSaveException**: indicates there is an error on `Save` operation.
* **DatabaseUpdateException**: indicates there is an error on `Update` operation.



## Crud Rest Controller
crud rest


## Utility Classes



## Thread
threads


## Exception Handler

handler