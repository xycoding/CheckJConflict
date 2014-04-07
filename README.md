CheckJConflict
==============

Check Jar or Class conflict for java

1. compile the jar: mvn clean package
2. java -Xmx1024m -XX:MaxPermSize=512m -jar check-j-0.1.jar [lib_path_1] [lib_path_2]

result:
trying to find conflict jars and classes, please wait…….
suggest -XX:MaxPermSize more than 512M …….
have found 486 conflict class…
have found 12 conflict jars…

Detail as Follow:
jar conflict NO.1: commons-collections-3.2.1.jar; commons-beanutils-core-1.8.3.jar; commons-beanutils-1.8.3.jar;
jar conflict NO.2: model-sgroup-0.0.5.jar; api-sgroup-0.0.5.jar;
jar conflict NO.3: stax-api-1.0.1.jar; stax-api-1.0-2.jar;
jar conflict NO.4: aspectjrt-1.7.3.jar; aspectjweaver-1.7.3.jar; aspectjtools-1.7.3.jar;
jar conflict NO.5: cglib-2.2.2.jar; cglib-nodep-2.2.jar;
jar conflict NO.6: jackson-core-asl-1.4.2.jar; jackson-0.9.1.jar;
jar conflict NO.7: api-counter-2.18.jar; remind-service-2.97.jar;
jar conflict NO.8: commons-beanutils-core-1.8.3.jar; commons-beanutils-1.8.3.jar;
jar conflict NO.9: jline-0.9.94.jar; jruby-complete-1.6.5.jar;
jar conflict NO.10: aspectjweaver-1.7.3.jar; aspectjtools-1.7.3.jar;
jar conflict NO.11: xom-1.1.jar; xml-apis-1.3.04.jar;
jar conflict NO.12: stax-api-1.0.1.jar; xml-apis-1.3.04.jar;
name conflict NO.1: net.sf.cglib.transform.AbstractClassFilterTransformer
	\target\web\WEB-INF\lib\cglib-2.2.2.jar
	\target\web\WEB-INF\lib\cglib-nodep-2.2.jar
……
……
