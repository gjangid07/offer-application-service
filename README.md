# **Offer Service API Documentation**

## Introduction

Welcome to the Offer Service API documentation. This API provides endpoints to process and find all the best offers(payable items and discounted items list) for a list of products.

### Base URL

The base URL for all endpoints is:

[http://localhost:8098/api]()

### Endpoints
#### Process and Find All the Offers

Endpoint
* URL: [http://localhost:8098/api/offer]()
* Method: POST

##### Request Body
The request body should contain a JSON array of Product objects.

[
{
"price": 10
},
{
"price": 20
},
{
"price": 30
}
]

* `price` (integer, required): The price of the product.
* `isPurchased` (boolean, optional): Whether the product is purchased. Default is false.
* `isPurchasedInDiscount` (boolean, optional): Whether the product is purchased in discount. Default is false.

Response

##### Success Response:

* Code: 200 OK
* Content: JSON array of BestOffer objects.

[
{
"offerCaseName": String
"purchasedProducts": List[Product],
"discountedProducts": List[Product]
},
{
"offerCaseName": String
"purchasedProducts": List[Product],
"discountedProducts": List[Product]
}
]

##### Error Response:

* Code: 400 Bad Request
* Content: Error message indicating the reason for the failure. 

##### Example
Request:

POST /api/offer
Content-Type: application/json

`[
{"price": 10},
{"price": 20},
{"price": 30}
]`

Response:
`[
{
"offerCaseName": "First Offer Case"
"purchasedProducts": [ {"price": 30} ],
"discountedProducts": [ {"price": 20}, {"price": 10} ]
}
]`

