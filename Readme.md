# Receipt application
An application that prints out of a given shopping basket

## Problem
* Apply 10% tax on all purchased items except books, food and medicals.
* Apply an additional 5% tax on all imported items.
* Calculate all taxes applied.
* Calculate the amount sum.

### Input example:
```
[{
  "quantity": 1,
  "name": "book",
  "imported": false,
  "price": 12.49,
  "type": "BOOK"
}, ... ]
```


### Output expected:
```
{
"items":[
  {"name":"book","quantity":1,"imported":false,"price":12.49,"type":"BOOK"},
  ...
],
"total":29.83,
"taxes":1.50
}
```

## Application
* The application is written in java. 
* Build with Maven.
* Linked to Travis CI at https://travis-ci.org/lucatode/mvn-repo


## Test
* Unit tests for logic and io components
* Integration tests on some given examples









