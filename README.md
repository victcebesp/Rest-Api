REST-API. The CRM Service.
---

## Introduction

With this API you will be able to manage customers data. This data can be managed by users. Depending on the user role, a user will be able to manage other users data. In other words:
- User with **USER** role can:
    - Manage customer data.
- User with **ADMIN** role can:
    - Manage customer data.
    - Manage other users data.
    
![Architecture](https://github.com/victcebesp/Rest-Api/blob/master/readmeImages/Architecture.png?raw=true)

## First steps
First things first. To user this API, you must be logged in. By default there is an admin user:
- **Username:** Admin
- **Password:** Admin

I recommend changing this user password or once there is another admin delete this one.

## Admin functions

As an admin user, you will be able to do the next actions:

#### Get all users
```
GET http://your.domain/users
```

#### Get a specific user
```
GET http://your.domain/users/{userdId}
```

#### Create a user
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

#### Delete a user
```
DELETE http://your.domain/users/{userId}
```

#### Update a user
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

#### Add a user role
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

#### Delete a user role
```
DELETE http://your.domain/users/{userId}/roles
```
Just as when adding a role, add to the request body the role you want to delete to the user specified in a json format.
An admin cannot change itself roles.

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

## User functions

Here you can find which actions a user with **USER** role can do. All this actions can be handled as well with the **ADMIN** role:

#### Get all customer
```
GET http://your.domain/customers
```
#### Get a specific customer
```
GET http://your.domain/customers/{customerId}
```

#### Create a customer
```
POST http://your.domain/customers
```
In order to create a customer, you will have to pass within the request body a JSON with the next user information:
- Name
- Surname

An example of this could be:

```json
{
  "name" : "Anakin",
  "surname" : "Skywalker"
}
```
What the server would do is create a customer with the specified information, but it also will add by default:
 - Photo URL
 - Customer Id
 - A reference to the use who created the customer
 - A reference to the last user that updated the customer (initially the same one that created it)
 
 #### Delete a customer
 ```
 DELETE http://your.domain/customers/{customerId}
 ```
 #### Update a customer
 ```
 PUT http://your.domain/customers
 ```
 To update a customer, you should send within the request body a json with the customer information to update. **You have to send in the json the customer ID to update**. See examples.
 
 **IMPORTANT**With this request you can only update the name and surname. The reference to the creator user will not change any more. The server will update automatically the last user that updated the customer. The photo URL mechanism will be explained below.
 
 So some examples of available json could be:
 
 ```json
 {
   "customerId" : 2,
   "username" : "Obi-One",
   "password" : "Kenobi"
 }
 ```
 ```json
 {
   "customerId" : 1,
   "username" : "Obi-One"
 }
 ```
 ```json
 {
   "customerId" : 2,
   "surname" : "Kenobi"
 }
 ```
 
### Upload a customer image
```
POST http://your.domain/customers/images
```
In order to upload an image for an specific customer, you should send in the request to things:
- The image ( as multipart file ) **IMPORTANT** The request parameter name MUST be "image".
- The customer ID to whom you want to upload the photo. **IMPORTANT** This request parameter MUST be named as "customerId".

Here is an example of a possible HTML form to easily upload a photo for an specific customer:
 
 ```html
 <html>
 <body>
 <div>
     <form method="POST" enctype="multipart/form-data" action="/customers/image">
         <table>
             <tr><td>File to upload:</td><td><input type="file" name="image" /></td></tr>
             <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
             <input type="hidden" name="customerId" value="1">
         </table>
     </form>
 </div>
 </body>
 </html>
 ```
