@echo off
title %cd%
echo.
echo [��Ϣ] ʹ��Tomcat������й��̡�
echo.
rem pause
rem echo.

cd %~dp0
cd..

set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m
call mvn tomcat:run

cd bin
pause