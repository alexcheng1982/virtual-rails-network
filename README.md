Virtual Rails Network
=====================

Virtual Rails Network is a small project to demonstrate some basic ideas of graph theory. In this virtual rails network, stations are connected with routes. Each route has a distance associated with it. The network can be abstracted as a [directed acyclic graph (DAG)](http://en.wikipedia.org/wiki/Directed_acyclic_graph). Different operations can be performed on this network.

## Operations

### Travel

This is a simple operation which traverses the network from one station to another station following the routes. During the travel, the total distance of the routes will be calculated. 

Class `rails.service.DistanceCalculation` is used to perform this operation.

### Plan journeys

This operation accepts a start station and a end station and gives the possible journeys from the start station to the end station. There are three types of journey planning options:

* No constraints: List all possible journeys from start station to end station.
* Limit maximum stops: Specify the maximum number of stops in the result journeys.
* Limit exact stops: Specify the exact number of stops in the result journeys.

Class `rails.service.JourneyPlanning` is used to perform this operation.

### Calculate shortest route

This operation calculates the shorted route from one station to another station. This operation uses [A* search algorithm](http://en.wikipedia.org/wiki/A*_search_algorithm) to do the search. 

Class `rails.service.ShortestRoute` is used to perform this operation.


## Usage

This is a Maven based project. Use `mvn jetty:run` to start the Jetty server and point your brower to http://localhost:8080 to access web front-end.

## Frameworks and tools used

* Spring MVC
* Google Guava
* Maven
* Twitter Bootstrap
* Bower
* Jetty

