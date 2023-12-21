### *This is just a made up REST project for practice <br> feel free to fork it [here](https://github.com/markpernia/library-management.git).*😉

## Task: Library Management System
This task involves creating two entities: Author and Book.<br>
The relationship between them is that an author can have multiple books, and a book can be written by one author.

### Entities
- Author
  - Attributes
    - name
    - email
- Book
  - Attributes
    - title
    - isbn
    - author

### Endpoints

#### 1. `GET` All Authors
- Endpoint: `/api/authors`
- Returns a list of all authors.

#### 2. `GET` Author by ID
- Endpoint: `/api/authors/{authorId}`
- Returns the details of a specific author.

#### 3. `POST` Create Author
- Endpoint: `/api/authors`
- Accepts a JSON request to create a new author.
- Example JSON Request:
```JSON
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

#### 4. `GET` All Books
- Endpoint: `/api/books`
- Return a list of all books with their details, including the author`s details.

#### 5. `GET` Book by ID
- Endpoint: `/api/books/{id}`
- Returns the details of a specific book, including the author's details

#### 6. `POST` Create Book
- Endpoint: `/api/books`
- Accepts a JSON request to create a new book.
- Example JSON Request:
```JSON
{
  "title": "Spring Boot Basics",
  "isbn": "978-1-234567-89-0",
  "authorId": 1
}
```
- Returns the details of the created book, including the author's details.

#### 7. `PUT` Update Book
- Endpoint: `/api/books/{bookId}`
- Accepts a JSON request to update the details of a specific book.
- Example JSON Request:
```JSON
{
  "title": "Updated Title",
  "isbn": "978-1-234567-89-0",
  "authorId": 1
}
```
- Returns the updated details of the book, including the author's details.

### Additional Notes
- Use DTOs to represent the data in the request and response bodies.
- Ensure proper validation for request payloads (e.g., required fields, valid email format).
- Use appropriate HTTP status codes for responses (e.g., 200 OK, 201 Created, 404 Not Found).
- Implement appropriate error handling for invalid requests or database errors.
