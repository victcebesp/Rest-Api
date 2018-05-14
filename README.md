REST-API. The CRM Service.
---

##Introduction

With this API you will be able to manage customers data. This data can be managed by users. Depending on the user role, a user will be able to manage other users data. In other words:
- User with **USER** role can:
    - Manage customer data.
- User with **ADMIN** role can:
    - Manage customer data.
    - Manage other users data.
    
![Architecture](https://github.com/victcebesp/Rest-Api/blob/master/readmeImages/Architecture.png?raw=true)

##First steps
First things first. To user this API, you must be logged in. By default there is an admin user:
- **Username:** Admin
- **Password:** Admin

I recommend changing this user password or once there is another admin delete this one.

##Admin functions

As an admin user, you will be able to do the next actions:

####Get all users
```
GET http://your.domain/users
```

####Get a specific user
```
GET http://your.domain/users/{userdId}
```

####Create a user
```
POST http://your.domain/users
```

In order to create a user, you will have to pass within the request body a JSON with the next user information:
- Username
- Password
- List of roles

An example of this could be:

```json
{
  "username" : "Han solo",
  "password" : "Chewaka",
  "roles" : [
    {
      "roleId" : 1,
      "role" : "ADMIN"
    },{
      "roleId" : 2,
      "role" : "USER"
    }
  ]
}
```

By the way, the unique roles available are the once showed above. 

####Delete a user
```
DELETE http://your.domain/users/{userId}
```

####Update a user
```
PUT http://your.domain/users
```
To update a user, you should send within the request body a json with the user information to update. **You have to send in the json the user id to update**. See examples.

**IMPORTANT**With this request you can only update the username and password. Roles are will be covered below.

So some examples of available json could be:

```json
{
  "userId" : 1,
  "username" : "Han solo",
  "password" : "Chewaka"
}
```
```json
{
  "userId" : 2,
  "username" : "Han solo"
}
```
```json
{
  "userId" : 1,
  "password" : "Chewaka"
}
```

####Add a user role
```
POST http://your.domain/users/{userId}/roles
```
To add a user some role, just send in the request body a role object in json format:

```json
{
  "roleId" : 2,
  "role" : "USER"
}
```
or
```json
{
  "roleId" : 1,
  "role" : "ADMIN"
}
```

####Delete a user role
```
DELETE http://your.domain/users/{userId}/roles
```
Just as when adding a role, add to the request body the role you want to delete to the user specified in a json format.

```json
{
  "roleId" : 2,
  "role" : "USER"
}
```
or
```json
{
  "roleId" : 1,
  "role" : "ADMIN"
}
```
