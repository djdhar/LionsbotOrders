#### First Create an Admin in `admin_tbl` of `newdatabase`
```
mysql> use newdatabase;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> insert into admin_tbl values('a086f7a6-6a81-11ed-966d-5ad6242f82ff','passadmin');
Query OK, 1 row affected (0.00 sec)
```


## API Reference

#### API to login Admin

```http
  POST http://localhost:9054/auth/login
```

JSON Request Body (sample)

```
{
    "userId" : "a086f7a6-6a81-11ed-966d-5ad6242f82fe",
    "password" : "passadmin",
    "isAdmin" : "true"
}
```


#### API to login Customer

```http
  POST http://localhost:9054/auth/login
```

JSON Request Body (sample)

```
{
    "userId" : "555a04be-33a6-44b0-83d2-077ed4e231cd",
    "password" : "passadmin"
}
```


#### Get All Customers (Restricted to Admin only)

```http
  GET http://localhost:9054/customers
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Admin.     |

#### Get All Orders (Restricted to Admin only)

```http
  GET http://localhost:9054/orders
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Admin.     |

#### Delete a particular Order (Restricted to Admin only)

```http
    DELETE http://localhost:9054/orders/{order_id}
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Admin.     |

#### Delete a particular Customer (Restricted to Admin only)

```http
  DELETE http://localhost:9054/customers/{customer_id}
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Admin.     |


#### Get All Orders for a Customer (Restricted to Admin and that Customer only)

```http
  GET http://localhost:9054/orders/{customer_id}
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login User.     |

#### Update Order details (Restricted to that Customer only)

```http
    PUT http://localhost:9054/orders/{order_id}
```

JSON Request Body (sample)

```
    {
        "orderDate": "2022-11-22T16:57:57.000+00:00",
        "totalPrice": 399994.0,
        "numberOfItems": 7469999
    }
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Customer.  |


#### Create a new Customer (Restricted to Admin only)

```http
  POST http://localhost:9054/customers
```

JSON Request Body (sample)
```
{
            "customerName": "DJ",
            "email": "dibyajyoti@gmail.com",
            "password": "mypass",
            "contactNumber": "9977654654"
}
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Admin.     |

#### Create a new Order (Restricted to Customer only)

```http
  POST http://localhost:9054/orders
```

JSON Request Body (sample)

```
{
    "totalPrice" : 786556.9869,
    "numberOfItems" : 45
}
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Customer.     |


#### Change password of a Customer (Restricted to that Customer only)

```http
  PUT http://localhost:9054/changepassword/{customer_id}
```

JSON Request Body (sample)

```
{
    "password" : "modifed_password"
}
```

| Header Key           | Header Value                      | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization`      | `Bearer <jwt_token>`              | JWT token of the login Customer.     |

