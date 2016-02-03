REM Script for compiling and running the stand-alone
REM Java client program

REM set the required environment variables
REM (you'll need to change the setting of OC4J_HOME,
REM and probably CLASSPATH)
set OC4J_HOME=E:\Oracle\iSuites\j2ee\home
set ORIGINAL_CLASSPATH=%CLASSPATH%
set CLASSPATH=.;..;%OC4J_HOME%\ejb.jar;%OC4J_HOME%\jndi.jar;%OC4J_HOME%\oc4j.jar;%OC4J_HOME%\lib\classes12.jar

REM compile the client source files
javac client/Client.java

REM run the client program with different argument values for the
REM product id (including a non-existent product id of 20)
cd client
java Client 1
java Client 2
java Client 20
cd ..

REM set the classpath back to the original setting
set CLASSPATH=%ORIGINAL_CLASSPATH%