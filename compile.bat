@echo off
set JAVAC="C:\Users\Abi Ahmad\.jdks\openjdk-26\bin\javac.exe"
set JAR="C:\My Apps\JavaProject\lib\mysql-connector-j.jar"
set SRC="C:\My Apps\JavaProject\src"
set OUT="C:\My Apps\JavaProject\out"
if not exist %OUT% mkdir %OUT%
%JAVAC% -cp %JAR% -d %OUT% %SRC%\*.java
echo Compilation done.
