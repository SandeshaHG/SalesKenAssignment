# SalesKenAssignment

## Use Case

This is an assignment giving out the CRUD APIs to a Product UI

## Run the app
The application is Hosted on Heroku, and can be accessed through the below URL
```
https://saleskenassignment.herokuapp.com/
```


## Framework

The project is written using the Spring Boot Framework.

## ORM

The project uses Hibernate as the object-relational mapping tool

## Database

The project uses PostgreSQL database system.

## Tables

### processes table- 
#### containing the processes
### products table- 
#### containing the products
### product_processes table-
#### containing the many to many mapping of the 2 tables


## End Points

#### Get All products 
To get a json response of all the porducts present in the database;
```
/getAllProducts
```

#### Add a product 
Add a product to the Data Base
```
/AddProduct
```

#### Update a product 
Update a product in the database
```
/UpdateProduct/{id}
```

#### Delete a product 
Delete a product from the database
```
/DeleteProduct/{id}
```

#### Add a process to a product
Insert an already present process to an already present product
```
/AddProcess/{productId}/{processId}
```

#### Delete a process from a product
Delete an already present process from an already present product
```
/DeleteProcess/{productId}/{processId}
```

#### Get All processes 
To get a json response of all the processes present in the database
```
/getAllProcess
```

#### Add a Process
Add a process to the database
```
/addProcess
```

#### Update a process
Update a process present in the database
```
/UpdateProcess/{id}
```

#### Delete a process
Delete a process present in the database;
```
/DeleteProcess/{id}
```
