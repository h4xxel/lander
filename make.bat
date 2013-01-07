@echo off
REM lander
REM hooray for stupid bat-files and GOTOs.. -__-

if x%1==xall goto all
if x%1==xclean goto clean

:all
mkdir build
echo [JAVAC] *.java
javac -d build *.java
javac -d build test\TestLander.java
echo [ JAR ] lander.jar
cd build
jar -cfm ..\lander.jar ..\lander.manifest *.class
cd ..
goto end

:clean
echo [ RM  ] build
del build\*.class
rmdir build
echo [ RM  ] lander.jar
del lander.jar
goto end

:end
