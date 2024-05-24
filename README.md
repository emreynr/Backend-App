## Simple Backend App for Employee CRUD

It is an app where CRUD operations can be performed according to defined roles. Users can add, remove, update or delete according to the roles defined for them. 
Each user can perform transactions within his or her own authority. For example, Employee can read but cannot delete. The admin role can do it all.

Defined these roles: 
- EMPLOYEE
- MANAGER
- ADMIN
  
  - An **EMPLOYEE** who has this role: Can do read an or all employees with `GET` method. But can't do any Delete, Update or Save actions.
  - An **MANAGER** who has this role: Can do read with `GET`, save `POST` and update `PUT` actions. But can't do delete `DELETE` action. 
  - An **ADMIN** who has this role: Can do every actions.


Url's and usable methods:
| Method | URL | Action | Role |
| :---: | --- | --- | :---: |
| `GET` | /api/employees | Read All  | **EMPLOYEE**    |
| `GET` | /api/employees/{employeeId}   | Read By Id | **EMPLOYEE** |
| `POST` | /api/employees | Save Employee | **MANAGER** |
| `PUT` | /api/employees | Update Employee | **MANAGER** |
| `DELETE` | /api/employees/{employeeId} | Delete Employee | **ADMIN** |


For Use:
  I defined three User profile for that. 


  ```java
// define role based users
UserDetails sarah = User.builder()
                .username("sarah")
                .password("{noop}21435342")
                .roles("EMPLOYEE")
                .build();

        UserDetails jack = User.builder()
                .username("jack")
                .password("{noop}5342152134")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails michael = User.builder()
                .username("michael")
                .password("{noop}87563345435")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
```
```java
// define user's roles
.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
```
*Note: {noop} meant password will be text format. Also if you want, you can use {bcrypt} format. 



# User based CRUD operations

<sub>Postman used for CRUD operations.</sub>


CRUD operations with Sarah:
** 
   Read:
   ```
/api/employees
```

```JSON
Status:200 OK
[
    {
        "id": 1,
        "firstName": "Onur",
        "lastName": "Tirpan",
        "email": "aga@gmail.com"
    },
    {
        "id": 3,
        "firstName": "jack",
        "lastName": "Gosee",
        "email": "bro3333@hotmail.com"
    },
    {
        "id": 4,
        "firstName": "Yuri",
        "lastName": "Gosee",
        "email": "russianboy10@gmail.com"
    },
    {
        "id": 5,
        "firstName": "sarah",
        "lastName": "vicky",
        "email": "twitchstreamer@gmail.com"
    },
    {
        "id": 6,
        "firstName": "baris",
        "lastName": "gorur",
        "email": "yozgatli66@gmail.com"
    },
    {
        "id": 7,
        "firstName": "Leslie",
        "lastName": "Allen",
        "email": "lesliea@hotmail.com"
    }
]
```
Create:
```
/api/employees
```

  ```JSON
{
    "firstName":"omer",
    "lastName":"atil",
    "email": "omer@gmail.com"
}
```
Output:
```JSON
Status: 403 Forbiddden

```

CRUD operations with jack:

Create:
```
/api/employees
```
```
{
    "firstName":"omer",
    "lastName":"atil",
    "email": "omer@gmail.com"
}
```
Output:
```JSON
Status: 200 OK
{
    "id": 8,
    "firstName": "omer",
    "lastName": "atil",
    "email": "omer@gmail.com"
}
```
Delete:
```
/api/employees/4
```
Output:
```JSON
Status: 403 Forbidden
```

CRUD operations with Michael:

Read:
```
http://localhost:8080/api/employees/1
```
Output:
```JSON
Status: 200 OK
{
    "id": 1,
    "firstName": "Onur",
    "lastName": "Tirpan",
    "email": "aga@gmail.com"
}
```

Delete:
```
http://localhost:8080/api/employees/7
```
Output:
```JSON
Status: 200 OK
```
```
Deleted Employee id : 7
```
Database values after delete action:
```JSON
[
    {
        "id": 1,
        "firstName": "Onur",
        "lastName": "Tirpan",
        "email": "aga@gmail.com"
    },
    {
        "id": 3,
        "firstName": "jack",
        "lastName": "Gosee",
        "email": "bro3333@hotmail.com"
    },
    {
        "id": 4,
        "firstName": "Yuri",
        "lastName": "Gosee",
        "email": "russianboy10@gmail.com"
    },
    {
        "id": 5,
        "firstName": "sarah",
        "lastName": "vicky",
        "email": "twitchstreamer@gmail.com"
    },
    {
        "id": 6,
        "firstName": "baris",
        "lastName": "gorur",
        "email": "yozgatli66@gmail.com"
    },
    {
        "id": 8,
        "firstName": "omer",
        "lastName": "atil",
        "email": "omer@gmail.com"
    }
]
```
