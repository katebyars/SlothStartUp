

# Sloth Start Up Conference Event Planner Dashboard

#### Event Planner Dash for Epicodus, July 2017

#### By Kate Byars

## Description
The Sloths are at it again! This year, a newly renovated conference schedule for the annual Sloth-A-Con caters specifically to Sloth Devs and like minded Bradypus tridactylus friendly community members! . It features slower speakers, longer meetings, and more tardy speakers!
This specially designed admin dashboard will help Sloth organizers keep track of events and attendees.

Created for Epicodus Java/Android Track, Summer 2017.

## About the Project
_The Sloth Event Planner is a customized website where Sloths can plan their next conference._
![The Sloth Event Planner is a customized website where Sloths can plan their next conference](https://github.com/katebyars/SlothStartUp/blob/master/src/main/resources/public/images/1.png)

_It contains an admin area where sloths can enter attendees and new events._
![You can sort the results via dropdown menu](resources/images/2.png)

_Sloth organizers can add a new event._
![You can sort the results via dropdown menu](resources/images/3.png)

_They can put in the details._
![You can sort the results via dropdown menu](resources/images/4.png)

_New events are displayed on the admin home page._
![You can sort the results via dropdown menu](resources/images/5.png)

_The details of each event are displayed on a separate page, along with an option to add attendees._
![You can sort the results via dropdown menu](resources/images/6.png)

_Users enter attendee details on the add attendees page._
![You can sort the results via dropdown menu](resources/images/7.png)

_Attendees are displayed on the admin homepage._
![You can sort the results via dropdown menu](resources/images/8.png)
## Setup/Installation Requirements

* Open project in IntelliJ and configure with a Gradle wrapper.
* Run the app and open in your local host.

## Known Bugs

* No known bugs.

## Specifications

| Behavior      | Example Input         | Example Output        |
| ------------- | ------------- | ------------- |
| Has Event | Event name  |  "Kate's Event"  |
| App has multiple events | Kate's Event, Bob's Event   | Kate's Event, Bob's Event  |
| Events have their own page | Kate's Event  | Kate's Event page |
| App has additional details and a list of attendees | "Kate's Event"  |  Kate's Event Description and Attendees. list.  |
|Attendees have a name| Gertrude| Gertrude|
|Attendees have other details such as age and home city| 31, Slowpokeville| 31, Slowpokeville|
|Attendees can be added to an event|Event : Best and Slowest Practices in Typing | Attendees : Billy and Sue|
|Attendees can be deleted from an event|[Billy, Sue] |[]|
|App has an area with attendee profile details|Billy |Billy : age 332, Food Preference : Bugaterian|
|Admin area has other functionalities - all attendees, all events, events with details + attendees |||
|All attendees sort alpha|Sue, Billy    | Billy, Sue|
|All events sort alpha|Sloths For Social Justice, A Slow Success|A Slow Success, Sloths for Social Justice|
|Sort attendees by age|Bill - 332, Sue - 233| Sue - 233, Bill 332|
|Sort attendees by city|Bill - Sloanoke VA,  Sue - Lollygagglopoli MD|Sue - Lollygagglopolis MD, Bill - Sloanoke VA,|
|Sort by attendees by event|Sluggish Mornings : A Slower Routine, A Happier You!, Listless In Seattle: How to be Tardy to the Top|Listless In Seattle: How to be Tardy to the Top, Sluggish Mornings : A Slower Routine, A Happier You!|

## Technologies Used

Java, Spark, Handlebars

### License

Copyright &copy; 2017 Kate Byars
