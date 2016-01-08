# verbose-dollop
Spring-boot JPA with postgre walk through.

1. What's your proudest achievement? It can be a personal project or something you've worked on professionally. Just a short paragraph is fine, but I'd love to know why you're proud of it.
 
2. Write some code, that will flatten an array of arbitrarily nested arrays of integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4]. 
 
3. We have some customer records in a text file (customers.json) -- one customer per line, JSON-encoded. We want to invite any customer within 100km of our Dublin office (GPS coordinates 53.3381985, -6.2592576) for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by user id (ascending).
 
You can use the first formula from this Wikipedia article to calculate distance: https://en.wikipedia.org/wiki/Great-circle_distance -- don't forget, you'll need to convert degrees to radians. Your program should be fully tested too.
 
Customer list is available here: https://gist.github.com/brianw/19896c50afa89ad4dec3 

# Solutions

2. heavy borrow from: https://gist.github.com/l-ray/11472207

3.1. Database layer, using PostgreSql with native GEO awareness feature;
3.2. JPA with JTS for POINT/POLYGON db functions storage;
3.3. JsonMapper for json file read and parse;
3.4. Spring-boot-data-jpa to apply repository save();
3.5. Spring-boot-test for jUnit-test;