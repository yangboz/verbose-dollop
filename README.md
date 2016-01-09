# verbose-dollop
Spring-boot JPA with postgre walk through.

1. What's your proudest achievement? It can be a personal project or something you've worked on professionally. Just a short paragraph is fine, but I'd love to know why you're proud of it.
 
2. Write some code, that will flatten an array of arbitrarily nested arrays of integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4]. 
 
3. We have some customer records in a text file (customers.json) -- one customer per line, JSON-encoded. We want to invite any customer within 100km of our Dublin office (GPS coordinates 53.3381985, -6.2592576) for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by user id (ascending).
 
You can use the first formula from this Wikipedia article to calculate distance: https://en.wikipedia.org/wiki/Great-circle_distance -- don't forget, you'll need to convert degrees to radians. Your program should be fully tested too.
 
Customer list is available here: https://gist.github.com/brianw/19896c50afa89ad4dec3 

# Solutions

1.see: https://github.com/yangboz/godpaper

2. heavy borrow from: https://gist.github.com/l-ray/11472207

3.1. Database layer, using PostgreSql with native GEO awareness feature;

3.2. JPA with JTS for POINT/POLYGON db functions storage;

3.3. JsonMapper for json file read and parse;

3.4. Spring-boot-data-jpa to apply repository save();

3.5. Spring-boot-test for jUnit-test;

# Installation

1.Install PostgreSql and Postgis;

brew install postgis

2.Start PostgreSql server:

postgres -D /usr/local/var/postgres


3.Create PostgreSql database:

createdb customers

4.Install postgis extension for Geometry data type:

CREATE EXTENSION postgis;

# How to run?

mvn clean test


# Verify

test=# SELECT * FROM customers;
 id |          geo_string           |                  geometry                  |  latitude  |  longitude  |        name        | user_id
----+-------------------------------+--------------------------------------------+------------+-------------+--------------------+---------
  1 | POINT(52.986375 -6.043701)    | 0101000000C74B3789417E4A40C23577F4BF2C18C0 |  52.986375 |   -6.043701 | Christina McArdle  |      12
  2 | POINT(51.92893 -10.27699)     | 0101000000FB22A12DE7F6494059A31EA2D18D24C0 |   51.92893 |   -10.27699 | Alice Cahill       |       1
  3 | POINT(51.8856167 -10.4240951) | 01010000004EA555E35BF14940A7C931FE22D924C0 | 51.8856167 | -10.4240951 | Ian McArdle        |       2
  4 | POINT(52.3191841 -8.5072391)  | 01010000009C734B06DB284A404481E3D7B40321C0 | 52.3191841 |  -8.5072391 | Jack Enright       |       3
  5 | POINT(53.807778 -7.714444)    | 0101000000D236FE4465E74A40304B3B3597DB1EC0 |  53.807778 |   -7.714444 | Charlie Halligan   |      28
  6 | POINT(53.4692815 -9.436036)   | 0101000000158F8B6A11BC4A40DFC14F1C40DF22C0 | 53.4692815 |   -9.436036 | Frank Kehoe        |       7
  7 | POINT(54.0894797 -6.18671)    | 0101000000F4932012740B4B403A58FFE730BF18C0 | 54.0894797 |    -6.18671 | Eoin Ahearn        |       8
  8 | POINT(53.038056 -7.653889)    | 010100000052B5DD04DF844A4049DBF813959D1EC0 |  53.038056 |   -7.653889 | Stephen McArdle    |      26
  9 | POINT(54.1225 -8.143333)      | 010100000048E17A14AE0F4B4009E066F1624920C0 |    54.1225 |   -8.143333 | Enid Gallagher     |      27
 10 | POINT(53.1229599 -6.2705202)  | 0101000000169C6626BD8F4A4028A14F3F031519C0 | 53.1229599 |  -6.2705202 | Theresa Enright    |       6
 11 | POINT(52.2559432 -7.1048927)  | 010100000019D12CBFC2204A40475BF0FD686B1CC0 | 52.2559432 |  -7.1048927 | Jack Dempsey       |       9
 12 | POINT(52.240382 -6.972413)    | 010100000006465ED6C41E4A402DD2C43BC0E31BC0 |  52.240382 |   -6.972413 | Georgina Gallagher |      10
 13 | POINT(53.2451022 -6.238335)   | 0101000000BD9646825F9F4A40F9F719170EF418C0 | 53.2451022 |   -6.238335 | Ian Kehoe          |       4
 14 | POINT(53.1302756 -6.2397222)  | 0101000000BFBBF0DEAC904A402D8084BC79F518C0 | 53.1302756 |  -6.2397222 | Nora Dempsey       |       5
 15 | POINT(53.008769 -6.1056711)   | 0101000000FC1BB4571F814A4086877A0B356C18C0 |  53.008769 |  -6.1056711 | Richard Finnegan   |      11
 16 | POINT(53.1489345 -6.8422408)  | 0101000000815F234910934A40716D4D5F745E1BC0 | 53.1489345 |  -6.8422408 | Alan Behan         |      31
 17 | POINT(53 -7)                  | 01010000000000000000804A400000000000001CC0 |         53 |          -7 | Olive Ahearn       |      13
 18 | POINT(51.999447 -9.742744)    | 0101000000EC8A19E1EDFF4940B7990AF1487C23C0 |  51.999447 |   -9.742744 | Helen Cahill       |      14
 19 | POINT(52.966 -6.463)          | 0101000000CFF753E3A57B4A40273108AC1CDA19C0 |     52.966 |      -6.463 | Michael Ahearn     |      15
 20 | POINT(52.366037 -8.179118)    | 01010000001F10E84CDA2E4A40F73FC05AB55B20C0 |  52.366037 |   -8.179118 | Ian Larkin         |      16
 21 | POINT(54.180238 -5.920898)    | 01010000008CBFED0912174B40A7CEA3E2FFAE17C0 |  54.180238 |   -5.920898 | Patricia Cahill    |      17
 22 | POINT(53.0033946 -6.3877505)  | 0101000000D3FDF73B6F804A40D50792770E8D19C0 | 53.0033946 |  -6.3877505 | Lisa Ahearn        |      39
 23 | POINT(52.228056 -7.915833)    | 01010000000BD462F0301D4A40E9633E20D0A91FC0 |  52.228056 |   -7.915833 | Bob Larkin         |      18
 24 | POINT(54.133333 -6.433333)    | 0101000000213D450E11114B403B1C5DA5BBBB19C0 |  54.133333 |   -6.433333 | Rose Enright       |      24
 25 | POINT(55.033 -8.112)          | 01010000004E62105839844B40D34D6210583920C0 |     55.033 |      -8.112 | Enid Cahill        |      19
 26 | POINT(53.521111 -9.831111)    | 0101000000FD4AE7C3B3C24A4014B1886187A923C0 |  53.521111 |   -9.831111 | Enid Enright       |      20
 27 | POINT(51.802 -9.442)          | 01010000002DB29DEFA7E64940FCA9F1D24DE222C0 |     51.802 |      -9.442 | David Ahearn       |      21
 28 | POINT(54.374208 -8.371639)    | 010100000067F3380CE62F4B4017D68D7747BE20C0 |  54.374208 |   -8.371639 | Charlie McArdle    |      22
 29 | POINT(53.74452 -7.11167)      | 0101000000E59B6D6E4CDF4A40FCC6D79E59721CC0 |   53.74452 |    -7.11167 | Oliver Ahearn      |      29
 30 | POINT(53.761389 -7.2875)      | 01010000005F44DB3175E14A406666666666261DC0 |  53.761389 |     -7.2875 | Nick Enright       |      30
 31 | POINT(54.080556 -6.361944)    | 010100000090BFB4A84F0A4B4007EFAB72A17219C0 |  54.080556 |   -6.361944 | Eoin Gallagher     |      23
 32 | POINT(52.833502 -8.522366)    | 010100000047938B31B06A4A40AD156D8E730B21C0 |  52.833502 |   -8.522366 | David Behan        |      25
(32 rows)


# References

Spring-boot-data-jpa: https://spring.io/guides/gs/accessing-data-jpa/

JTS: http://www.hibernatespatial.org/

Sptial Queries: http://kofler.nonblocking.at/2014/03/unit-testing-of-spatial-queries/

Postgis: http://postgis.net/install/
