# DatabaseUpdateFunction

## User story
As a front-end developer, I would like a Java program (to be used as an API) for updating current JSON data within a postgreSQL database field.

## Requirements
Produce a simple Java program for updating the existing data record of a postgreSQL database (of which the third database field's JSON data will be updated with the new data). The API will recieve an ID in the form of queryStringParameters, which will allow the program to update the correct, corresponding record that is specified. 

As the program will be used as an API (hosted by Amazon's AWS API gateway), the program will need to return the correct output (JSON data containing the necessary key-value pairs such as the header and body).

The program will also need to be an AWS lambda function (so it can run on the API gateway), which can be quickly setup using the default AWS Lambda package (which comes with Eclipse IDE's AWS plug-in). 

## Technical details
Example of JSON data to be added into the database record:
{"label": "Terrible","size": "3"}

Example of JSON data within the database that has been updated:
{"Project_ID": "010","Artwork_title": "Lessons","Artwork_ID": "01","User_Quote:": [{"label": "together","size": "3"},{"label": "intimate","size": "5"},{"label": "sharing","size ": "2"},{"label": "sympathetic","size": "1"},{"label": "grey","size": "2"},{"label": "dark","size": "7"},{"label": "sad","size": "9"},{"label": "timeless","size": "1"},{"label": "togetherness","size": "2"},{"label": "humanity","size": "1"},{"label": "Terrible","size": "3"}]}

## Acceptance criteria
The end result should be a Java program (hosted as an API) which will recieve JSON data as input, which will then be added onto an existing database JSON record (adding the words onto the existing word list, in JSON format). 
