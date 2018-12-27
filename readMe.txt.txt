This is a work in progress. Made entirely for educational purposes.

In order to run the program I recomend using the Eclipse IDE.
Should it have an error related to the mysql connector jar being missing 
or unable to be resolved simply follow these steps.

(1) Right click on the main project folder & select properties.

(2) Select java Build path from the left side of the next window.

(3) Highlight the offending jar with the error message then select remove

(4) Select add jar from the right hand side, and simply point the program 
    to the connector jar, which can be found supplied int he project folder 
    (so the location will vary depending on where you have put the folder 
     on your local system).

(5) Once the jar has been added, expand the main project folder, then 
    expand the executables package folder, and select Basic Structure.java.
    This is the main class. You may either use thre 'run as' button supplied 
    on the upper toolbar displayed as a green arrow, or you may simply right 
    click this file and select run as.

TO DO LIST:

(1) Sanitization of Inputs

(2) We are still debating whether to convert this current iteration to a 
    functional web application or whether to start a whole new project based off
    of this one, when studies allow for it. Either way, some Http:Servlet based web 
    functionality will be implemented.