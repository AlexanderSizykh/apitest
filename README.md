# apitest
This is my solution for the task from Natera:
https://qa-quiz.natera.com/


Text overview for the task:
------------------
Overview
Simple test service with some triangle calculations. You can save up to 10 triangles and request perimeter and area calculations for each one at any time you want. Nothing special.

Authentication
To authenticate your request custom header need to be added with personal token, like this one:

X-User : 3bda64c9-6439-4ad3-829c-a6fd7b9e55fe
If you have no idea where you can get your personal token, ask that guy who gave you this page's address.

Add a triangle
To add a new triangle just send the following request

POST /triangle
{"separator": ";", "input": "3;4;5"}
Field	Is Mandatory?	Default
input	Yes	
separator	No	;
Manipulations
GET	/triangle/{triangleId}
DELETE	/triangle/{triangleId}
GET	/triangle/all
GET	/triangle/{triangleId}/perimeter
GET	/triangle/{triangleId}/area
Possible HTTP codes
Some HTTP codes that could be returned as a response

200	OK	Nothing to say
401	Unauthorized	Check your token
404	Not found	Not found
422	Unprocessible	Typo in a request?
