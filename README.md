# Game Store
<hr>

# Overview
<p>A simple backend REST inventory management web service for a video game store, developed and tested using agile techniques in a group setting. </p>

[Here is a video showcasing functionality](hhttps://link-url-here.org/).



# APIs
<hr>

## Games
## Features 


- List of features include:
    - Standard CRUD operations
    - Search for games by studio
    - Search for games by ESRB rating
    - Search for games by title
    

<p>
    Game GET Request Example:

    URI: /game
    
    Method: GET
    
    Request Body: None
    
    Response Body: List<Game>
</p>

<p>
    Game API Response:

    { 
        {
            "gameId": 1,
            "title": "Foo",
            "esrb_rating": "Foo" ,
            "description": "Foooo" ,
            "price": 1.00,
            "studio": "Foo",
            "quantity": 1
        }
    }

</p>

<p>
    Game POST Request Example:

    URI: /game
    
    Method: POST
    
    Request Body: Game
    
    Response Body: Game
</p>

<p>
    Game API Response:

    { 
        {
            "gameId": 1,
            "title": "Foo",
            "esrb_rating": "Foo" ,
            "description": "Foooo" ,
            "price": 1.00,
            "studio": "Foo",
            "quantity": 1
        }
    }

</p>

## T-shirts
## Features

- List of features include:
    - Standard CRUD operations
    - Search for t-shirts by color
    - Search for t-shirts by size

<p>
    T-Shirt GET Request Example:

    URI: /tshirts
    
    Method: GET
    
    Request Body: None
    
    Response Body: List<Tshirt>
</p>

<p>
    T-Shirt API Response:

    { 
        {
            "tshirtId": 1,
            "size": "Foo",
            "color": "Foo" ,
            "description": "Foooo" ,
            "price": 1.00,
            "quantity": 1
        }
    }

</p>

## Console
## Features

- List of features include:
    - Standard CRUD operations
    - Search for consoles by manufacturer

<p>
    Console GET Request Example:

    URI: /console
    
    Method: GET
    
    Request Body: None
    
    Response Body: List<Console>
</p>

<p>
    Console API Response:

    { 
        {
            "consoleId": 1,
            "model": "Foo",
            "manufacturer": "Foo" ,
            "memoryAmount": "Foooo" ,
            "price": 1.00,
            "quantity": 1
        }
    }

</p>

## Invoice
## Features 

- List of features include:
    - Create and return invoice after item purchase

<p>
    Invoice POST Request Example:

    URI: /invoice
    
    Method: POST
    
    Request Body: Invoice
    
    Response Body: Invoice
</p>

<p>
    Invoice API Response:

    { 
        {
            "invoiceId": 1,
            "name": "Foo",
            "street": "Foo" ,
            "city": "Foooo" ,
            "state": 1.00,
            "zipcode": "Foo",
            "itemType": "Foo",
            "itemId": "Foo",
            "unitPrice: 1.00,
            "quantity": 1,
            "subTotal": 1.00,
            "salesTaxRate": 1.00,
            "processingFee": 1.00,
            "total": 1.00
        }
    }

</p>

# Testing
<hr>

### Features:

- CONTROLLER
  - All routes tested using MockMVC
    - expected return and expected failures
- SERVICE
  - All service layer methods tested
    - 100% code coverage using mocks
- REPOSITORY 
  - Integration tests for repositories
    - tests all basic CRUD operations
    - tests all custom query methods 