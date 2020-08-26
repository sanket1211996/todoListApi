# ToDoList API

This project is a spring boot application

## Development server

Run on java env or spring sts . Navigate to `http://localhost:8080/`. The app will automatically reload if you change any of the source files.

## Endipoints Exposed:

Security:
  /login - For authentication and JWT Token Generation
  /auth/signUp - for creation of new user

TodoController :-
  GET: /api/todos - get list of all todos
  GET: /api/todos/user/{userID} -  Get todos of specific user.
  GET: /api/todos/{id} - Get todo based on ID
  POST: /api/todos - post request for creation of new todo.
  PUT: /api/todos/{id} - PUT request for update the existing todo based on ID.
  DELETE: /api/todos/{id} - DELETE Request for deletion of todo based on ID.
  
## Database
  H2-Inmemory



 
