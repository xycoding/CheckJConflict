CheckJConflict
==============

Check Jar or Class conflict for java

1. compile the jar: mvn clean package
2. java -Xmx1024m -XX:MaxPermSize=512m-jar check-j-0.1.jar <lib_path_1> <lib_path_2>
