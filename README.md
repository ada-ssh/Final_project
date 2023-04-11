# Food delivery 
Project for TeachMeSkills

### Description
The project involves free delivery of food to anywhere in the city.

### DataBase
The PostgreSQL database is connected to the project. It contains 8 tables, 3 of which are link-table. Users - stores information about users (role, login, password, etc.). Buckets - stores data about the basket of products that the user has added. Orders - stores all information about the order (delivery address, order creation date, order status, etc.). Products - stores data about products (price, title) . Categories - stores data about the category of products.

### Registration
To register, you need to go to «localhost:8080/registration» and pass json (POST method:
{"name":...,"login":...,"password":...,"email":...}).

### Authentication
Authentication occurs when switching to «localhost:8080/auth» and pass json(POST method: {"login":..., "password"...}).

### Roles
Admin and Client Role

Available endpoints:
* "http://localhost:8080/products" - list of all products (GET method).
* "http://localhost:8080/products/id/{id}" - specific product by id (GET method) .
* "http://localhost:8080/products/title/{title}" - specific product by title(GET method).
* "http://localhost:8080/products" - adding a new product (POST method).
* "http://localhost:8080/products" - update product by id (PUT method).
* "http://localhost:8080/products" - delete product by id (DELETE method).
* "http://localhost:8080/buckets" - list of all buckets (GET method).
* "http://localhost:8080/buckets/id/{id}" - specific bucket by id (GET method).
* "http://localhost:8080/buckets" - adding a new bucket (POST method).
* "http://localhost:8080/buckets" - update bucket by id (PUT method).
* "http://localhost:8080/buckets" - delete bucket by id (DELETE method).
* "http://localhost:8080/users" - list of all users (GET method).
* "http://localhost:8080/users/id/{id}" - specific user by id (GET method).
* "http://localhost:8080/users/name/{name}" - specific user by name (GET method).
* "http://localhost:8080/users" - adding a new user (POST method).
* "http://localhost:8080/users" - update user by id (PUT method).
* "http://localhost:8080/users" - delete user by id (DELETE method).
* "http://localhost:8080/orders" - list of all orders (GET method).
* "http://localhost:8080/orders/id/{id}" - specific product by id (GET method).
* "http://localhost:8080/orders" - adding a new order (POST method).
* "http://localhost:8080/orders" - update product by id (PUT method).
* "http://localhost:8080/orders" - delete product by id (DELETE method).
