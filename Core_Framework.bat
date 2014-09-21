cd c:
c:
set ANT_HOME=C:\apache-ant-1.9.3
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0
set PATH=%ANT_HOME%\bin;%JAVA_HOME%\bin
cd C:\Core_Framework

ant build 
ant compile
ant run