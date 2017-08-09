# cvrptenis
REST API on SpringBoot, ready to serve cvrptenis-client or other clients

This backend allow you to calculate and receive an image with the best route to pick up childrens into vans. 
Used for Tennis club Benicarló, they can optimize the distance and the time for picking and delivering the children.
Backend made with [Spring Boot](https://github.com/spring-projects/spring-boot) 
and a modified version of [JSprit](https://github.com/graphhopper/jsprit). I modified the JSprit version to not write files but
return instances of the files itselfs due to read-only policy on heroku. Modified version is attached on the jar located at 
[Modified JSprit jar] (https://github.com/albertjimenez/cvrptenis/blob/master/src/main/resources/static/jsprit-analysis-1.7.3-SNAPSHOT.jar)

Next you are going to see the methods available along with the JSON Data of the request:

## POST: http://cvrp-tenis.herokuapp.com/api/solve ##
You may send an array of objects such as vans and childre, just like this:
```json
{
    "vans": [
        {
            "capacity": 4,
            "id": "0",
            "x": 0.7859661518029643,
            "y": 0.08633777949607746,
            "endX": 0.7859661518029643,
            "endY": 0.08633777949607746,
            "weight_INDEX": 0
        }
    ],
    "children": [
        {
            "cost": 1,
            "id": "0",
            "x": 0.8465478279878939,
            "y": 0.35571055621449865,
            "weight_INDEX": 0
        },
    ]
}
```
Vans contains the data related to the Vehicles. The Backend can take account if some van are going to return to the initial
point or another point. Also, stores the capacity of maximum children on those van. Id identifies the Van and shall be unique.

Children has a cost, which means at the x and y points specified e.g. Google Maps coordinates, there will be more than 1 child.
It cannot be less than 1.

weight_Index applies just for vans, and represent and interval from [weight_index, capacity]. 
If is modified, it may force the van to carry a child, so optimum value is 0.

Once the POST request is completed, you shall receive a string containing the PNG image of the solution in BASE64.

Happy route optimization!

