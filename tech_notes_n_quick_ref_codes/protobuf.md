Download the protobuf compiler, there are two options to do so 1) download source and build 2) download the exe

http://code.google.com/p/protobuf/downloads/list

 

Add path to protoc.exe to PATH variable

 

 Write a sample .proto file.
 my User.proto
     
     option java_outer_classname="ProtoUser";
    message User {
       required int32  id = 1;  // DB record ID
       required string name = 2;
       required string firstname = 3;
       required string lastname = 4;
       required string ssn= 5;
     
     
       // Embedded Address message spec
        message Address {
          required int32 id = 1;
          required string country = 2 [default = "US"];;
          optional string state = 3;
          optional string city = 4;
          optional string street = 5;
          optional string zip = 6;
     
     
          enum Type {
             HOME = 0;
              WORK = 1;
            }
            optional Type addrType = 7 [default = HOME];
     }
       repeated Address addr = 16;
    }
    
Generate the java code from the proto file as

    C:\home\office\work\new-build\new-proto-test>protoc
     --java_out=C:\home\office\work\ws\ptp-erebus-read-only\com.mtk\src\main\java
      User.proto
      
 

The generated code is about a 3k, may go through this but not required.

Get the protobuf jar, easiet way is to create a maven project and add the dependency
    
    <dependency>
         <groupId>com.google.protobuf</groupId>
         <artifactId>protobuf-java</artifactId>
         <version>2.5.0</version>
    </dependency>
    
 

The driver program to read and write sample message 
Jar file can be directly created, but it doesn't include the class file, so generate the java code first and then compile it

    C:\home\office\work\new-build\new-proto-test\proto-file-tests>javac -cp C:\FAST\
    maven\repo\com\google\protobuf\protobuf-java\2.5.0\protobuf-java-2.5.0.jar  com\
    test\AddressUserProtos.java

Then create the jar
     C:\home\office\work\new-build\new-proto-test\proto-file-tests>jar cvf UserProto.jar com\test\
     
         Extensions can be defined at start or end of message type

extensions 100 to 200 ;

-Default values are not serialized and hence transferred over the wire.
-import public can be used so that the imported protoc file is available further
-option java_multiple_files = true; to generate multiple files
-jar file can be created directly and imported
>  C:\home\office\work\new-build\new-proto-test\proto-file-tests>protoc --java_out=User2.jar User2.proto
- -I=IMPORT_PATH can be used as a short form of --proto_path.to search for proto files being imported This option can exists multiple times in single command.

