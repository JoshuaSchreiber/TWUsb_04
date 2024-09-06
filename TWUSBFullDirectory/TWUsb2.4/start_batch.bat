@echo off
echo Starte TWUsb Testprogramm

rem (c)2005-2007 by Thomas Wenzlaff
rem
rem Version 1.0

set LOG_JAR=log4j-1.2.13.jar
set TWUSB_JAR=TWUsb.jar

set LOCAL_CLASSPATH=%LOG_JAR%;%TWUSB_JAR%

if not exist %TWUSB_JAR% goto jarfehler
if not exist %LOG_JAR% goto jarlogfehler

java -classpath %LOCAL_CLASSPATH% test.de.wenzlaff.twusb.schnittstelle.TWUsbTest

goto ende


:jarfehler
echo Kann die Datei %TWUSB_JAR% nicht finden.
goto ende


:jarlogfehler
echo Kann die Datei %LOG.JAR% nicht finden.
goto ende

:ende