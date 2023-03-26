# Library endpoints documentation

## Add book
### HTTP Request:
`POST /v1/library/addBook`

### Body:
```
{
    "title" : "J.K Rowling",
    "author" : "Harry Potter",
    "year" : 2000
}
```

### Returns:
**JSON** with added book

Example:
```
    {
        "bookId": 0,
        "bookCopyList": null,
        "title": "Harry Potter",
        "author": "Joanne",
        "year": 2000
    }
```
## Add book copy
### HTTP Request:
`POST /v1/library/addBookCopy`

### Body:
```
[
    {
        "bookCopyId": 0,
        "book": {
            "bookId": 1,
            "title": "Harry Potter",
            "author": "Joanne",
            "year": 2000
        },
        "borrowId": null,
        "status": "available"
    }
]
```

### Returns:
**JSON** with added book copy

Example:
```
[
    {
        "bookId": 0,
        "bookCopyList": null,
        "title": "Harry Potter",
        "author": "Joanne",
        "year": 2000
    }
]
```
## Show all books
### HTTP Request:
`GET /v1/library`

### Body:
empty

### Returns:
**JSON** with array of all products

Example:
```
[
    {
        "bookId": 0,
        "bookCopyList": null,
        "title": "Harry Potter",
        "author": "Joanne",
        "year": 2000
    }
]
```
## Add user
### HTTP Request:
`POST /v1//addUser
### Body:
Example
```
    {
        "firstName" : "userName",
        "lastName": "userLastName"
    }
```
### Returns:
empty **JSON**

