REM Script for compiling and deploying the example bean

REM set the required environment variables
REM (you'll need to change the setting of OC4J_HOME,
REM and probably CLASSPATH)
set OC4J_HOME=E:\Oracle\iSuites\j2ee\home
set ORIGINAL_CLASSPATH=%CLASSPATH%
set CLASSPATH=.;..;%OC4J_HOME%\ejb.jar;%OC4J_HOME%\jndi.jar;%OC4J_HOME%\lib\classes12.jar
set ADMIN_PASSWORD=welcome

REM compile the bean source files
javac server/*.java

REM build the JAR file containing the bean classes
jar -cf server.jar server/*.class META-INF/ejb-jar.xml

REM build the EAR file
jar -cf jdbcejb.ear server.jar META-INF/application.xml META-INF/orion-application.xml META-INF/principals.xml

REM deploy the EAR file to OC4J
copy jdbcejb.ear %OC4J_HOME%
java -jar %OC4J_HOME%\admin.jar ormi://localhost admin %ADMIN_PASSWORD% -deploy -file jdbcejb.ear -deploymentName JDBCEJB

REM set the classpath back to the original setting
set CLASSPATH=%ORIGINAL_CLASSPATH%