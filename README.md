# Getting Started

### Just a sample project to test some GraphQL features

H2 console:
http://localhost:8082/h2-console

graphiql console:
http://localhost:8082/graphiql?path=/graphql#

Query sample:
```

 {
  findAllBooks {
    id
    title
    isbn
    rating
    published
    categories {
      name
    }
    authors  {
      id
      firstName
      lastName
    
  }
  }
} 
=====================================
{
  bookById(id: 2) {
    id
    title
    isbn
    rating
    published
    categories {
      name
    }
    authors  {
      id
      firstName
      lastName
    
  }
  }
}
```
Mutation sample:

```

mutation {
  createBook(
    book: {
            title: "Capoeira crashcourse", 
            isbn: "c1dfbj1d-9c4c-4a", 
            rating: 4.3, 
            published: "1986-12-18", 
            authors: [1, 2], 
            categories: [1]
           }
  ) {
    id
    title
    isbn
  }
}
=====================================

mutation {
  updateBook(
    book: {
       		 id: 2,
      	   title: "Capoeira na pratica", 
      		 isbn: "c1dfbj1d-9c4c-4a74-8a9f-6aqbghe3ae0007", 
      		 rating: 4.1, 
           published: "1986-12-18"
    }
  ) {
    id
    title
    isbn
  }
}

```
