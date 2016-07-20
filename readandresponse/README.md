This project implements RESTful services using Jersey Framework. The project exposes the below three services,

1. <b>retrieveCountOfGivenWords</b> - Retrieves the count of occurrence of the given word in the request.
2. <b>retrieveTopCounts</b> - Retrieves the counts of each word from the sample file and displays the counts of words in descending order.
3. <b>retrieveSampleFile</b> - Retrieves the sample file content in html format.

The project has been developed using Jersey and Spring framework built on JDK 1.8. The services are secured using Spring security which uses Basic authorization header, using Base64 format with UTF-8 CharSet.

To Build the application
-------------------------
Invoke the below Maven command to build the application,
        <br/> <b> mvn clean install </b>
