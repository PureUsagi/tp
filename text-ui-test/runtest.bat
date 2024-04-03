@echo off
setlocal enableextensions
pushd %~dp0

if exist data del data\items.txt
if exist data rmdir data

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

REM redirects standard error as well
java -jar %jarloc% < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT 2>&1

cd ..\..\text-ui-test

REM as before the below only compares starting from line 2 onwards
FC /A /LB2 ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!
