# Ethan Wood Review Ranking Project

This program ranks an arbitrary number of reviews by a set of criteria from least genuine to most genuine. The program
then outputs an arbitrary number of these to stdout. 

## Building
To build the program, you'll need to have Maven installed on your system. Once Maven is installed try using `mvn clean install`
to build the program. Once you've done that, the built jar file should be available in the `target` directory.

## Running
The program accepts 2 command line arguments, `-n` and `-l`. `-n` is numeric and specifies the number of reviews you want
to collect, and defaults to 50. `-l` is numeric as well and specifies the number of reviews you want to limit the output
to, and defaults to 3. 

To run the program with the defaults, try this: `java -jar .\target\review-ranking-0.0.1-SNAPSHOT-spring-boot.jar`

The program outputs JSON, and can be piped into a JSON viewer or parser like `jq`. If you have `jq` installed on your system, 
you can pipe the output into `jq` like this: `java -jar .\target\review-ranking-0.0.1-SNAPSHOT-spring-boot.jar | jq`
which will format the JSON nicely. 
