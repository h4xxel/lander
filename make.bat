@echo off
REM lander
REM hooray for stupid bat-files and GOTOs.. -__-

if x%1==xall goto all
if x%1==xclean goto clean

:all
echo [JAVAC] *.java
javac *.java
echo [ JAR ] lander.jar
jar -cfm lander.jar lander.manifest *.class
goto end

:clean
echo [ RM  ] *.class
del *.class
echo [ RM  ] lander.jar
del lander.jar
goto end

:end
