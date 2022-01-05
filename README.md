# Project Management Site
Built a project management site using Java, HTML and JavaScript and connected it all using AWS tools.

## Back-end
We used Java to build all the back-end code. We created handlers to allow users to create and delete projects, add and remove tasks, add and remove teammates and numerous other capabilities. These handlers as well as the tests for the handlers are within the "final project" file. Additionally, within the "db" file it contains the classes that allow us to connect the functionality of the handlers into the database itself. Each class within the "db" file includes lines of SQL queries.

## Front-end
We used html to build the design of our application and JavaScript to connect the functionality of the handlers to actual buttons and labels.

## AWS
Lambda functions
- allow us to do actions within the application and have those actions stored in the cloud
S3 bucket
- uploaded javascript files into this bucket and created a URL from the html files
API Gateway
- deployed the API once all actions had a handler attached
RDS
- allowed us to store all data created and deleted in a database and have that database be stored in the cloud
