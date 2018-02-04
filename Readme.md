https://travis-ci.org/lucatode/mvn-repo

# Receipt application
An application that prints out of a given shopping basket

## Problem
Apply 10% tax on all purchased items except books, food and medicals.
Apply an additional 5% tax on all imported items.
Calculate all taxes applied.
Calculate the amount sum.


### INPUT EXAMPLE:
```
[{
  "quantity": 1,
  "name": "book",
  "imported": false,
  "price": 12.49,
  "type": "BOOK"
},{
  "quantity": 1,
  "name": "music CD",
  "imported": false,
  "price": 14.99,
  "type": "STANDARD"
},{
  "quantity": 1,
  "name": "chocolate bar",
  "imported": false,
  "price": 0.85,
  "type": "FOOD"
}]
```


### OUTPUT EXPECTED:
```
{
"items":[
  {"name":"book","quantity":1,"imported":true,"price":12.49,"type":"BOOK"},
  {"name":"music CD","quantity":1,"imported":false,"price":16.49,"type":"STANDARD"},
  {"name":"chocolate bar","quantity":1,"imported":false,"price":0.85,"type":"FOOD"}
],
"total":29.83,
"taxes":1.50
}
```

