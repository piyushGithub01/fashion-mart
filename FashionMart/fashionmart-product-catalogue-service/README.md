# FashionMart retailer project
A FashionMart project to expose product catalogue microservice and pricing microservice.

fashionmart-product-catalogue-service module is developed using spring boot to expose restful services

This module contains-
-add product api
-retrieve product api
-remove product api


VM argument to start the service-
-Dspring.profiles.active=test


POST: http://localhost:8882/product/add

input-
{
  "name": "liril",
  "type": "soap",
  "quanity": 5,
  "price": 10.50,
  "maker": "hul"
}

output-
{
  "productId": "d408b3ce-d471-4402-9814-e392f953b716",
  "name": "liril",
  "type": "soap",
  "maker": "hul",
  "quantity": 0,
  "price": 10.5,
  "createdBy": "SYSTEM",
  "createdDate": 1494877675155,
  "updatedBy": null,
  "updatedDate": null,
  "version": 0
}


GET: http://localhost:8882/product/get-by-id/d408b3ce-d471-4402-9814-e392f953b716

output-
{
  "productId": "d408b3ce-d471-4402-9814-e392f953b716",
  "name": "liril",
  "type": "soap",
  "maker": "hul",
  "quantity": 0,
  "price": 10.5,
  "createdBy": "SYSTEM",
  "createdDate": 1494877675155,
  "updatedBy": null,
  "updatedDate": null,
  "version": 0
}


PUT: http://localhost:8882/product/get-by-criteria

input-
{
  "type": "food"
}

output-
[
  {
    "productId": "773b70c7-0365-46f8-9f68-61d3314d9d29",
    "name": "lays",
    "type": "food",
    "maker": "ITC",
    "quantity": 5,
    "price": 20.5,
    "createdBy": "SYSTEM",
    "createdDate": 1494877736512,
    "updatedBy": null,
    "updatedDate": null,
    "version": 0
  }
]


PUT: http://localhost:8882/product/remove

input-
{
  "productId": "773b70c7-0365-46f8-9f68-61d3314d9d29"
}

output-
true
