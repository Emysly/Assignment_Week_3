# Assignment_Week_3

In an application, different configuration files could be used depending on the level of development. This project would primarily test your knowledge of file handling, string manipulation and basic programming construct in Java.

You are to create a Java application that would parse and read values from config files. The data in configuration file should be accessed with the following syntax.

com.emysilva.classes.ConfigParser config = new com.emysilva.classes.ConfigParser(“name_of_file”);

Once your applications start, the config file(s) should be read and values based on the environment(production, development, staging) should be stored in a Map.

Command Line arguments should be used to specify the environment in which the code would be executed in. Below is an illustration

Production java com.emysilva.classes.Main.java

Staging java com.emysilva.classes.Main.java staging

Development java com.emysilva.classes.Main.java development

There should be a default name if a name of the file is not passed to the constructor.

Here is a format of an example config file:

dbname=sq04_db

host=127.0.0.1

port=8080

[application]

name=fintek

port=8081

context-url=/api/v1

mode=dev

theme=yellow

pipeline=fast

[application]

name=fintrack

For each of this values i should be able to use the values by calling the get methods and passing the keys e.g

String dbName = config.get(‘dbname’);

String stagingDbname = config.get(‘application.name’);

Where there are multiple values for a key, the first one that appears in the config file should be used. For example

· If the get method for the key ‘application.name’ is called, based on the example file provided above, it should return the value fintek.

· If the get method for the key ‘dbname’ is called, it should return ‘sq04_db’.

All values should be returned as a string.

Use this boilerplate for the task
