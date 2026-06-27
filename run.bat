@echo off
set JAVA="C:\Users\Abi Ahmad\.jdks\openjdk-26\bin\java.exe"
set JAR="C:\My Apps\JavaProject\lib\mysql-connector-j.jar"
set OUT="C:\My Apps\JavaProject\out"
%JAVA% -cp "%OUT%;%JAR%" Main
