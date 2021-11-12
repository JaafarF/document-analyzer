# document-analyzer

This project was Spring boot - hibernate - and MySql

## Application Demo : [DEMO](http://173.249.32.142/)

## Development server

On Ide Run the main class of the project
or
Run the maven command on the root of the project :   'mvn spring-boot:run'.


## Database

The database name and credentials need to be configured in 'application.properties' file.
With hibernate ddl-auto=update, there is no need to execute sql script to create tables (they will be created from the entity classes).
If there is a need, there is sql script ot database and tables creation on the root of the project.

Here is the model of DB : 

![Screenshot from 2021-11-02 00-49-49](https://user-images.githubusercontent.com/12358940/139757008-fd2f6c61-effb-489b-822a-5b2b67465495.png)


## Expert Ai credentials

Don't forget to configure the nlp api credentials in the 'application.properties' file.
