# JsonReader
A Java Utitlity to flatten the nested Json Object. 

## Requirements
* Java 8 & above
* Maven 
  * if Maven is not already installed, please use the jar from the repo at the root directory

## Usage
To run the utility, 
* Compile & package maven the project
  * `mvn clean install`
* Pass the json input as a command line argument to the driver class `Main.class` 
  * `java -jar target/JsonReader-1.0-SNAPSHOT-jar-with-dependencies.jar {YOUR_JSON_INPUT_DOUBLE_QUOTES}`
  * ```
    Example:
    java -jar target/JsonReader-1.0-SNAPSHOT-jar-with-dependencies.jar "{\"a\": 1,  \"b\": true, \"c\": {\"d\": 3,\"e\": \"test\"  }  }"`
    ```

## Limitations:
* The input should be a JSON object.
* All keys named in the original object will be simple strings without ‘.’ characters
* The input JSON will not contain arrays.
