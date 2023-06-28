@echo off
set RABBIT_ADDRESSES=localhost:5672
set STORAGE_TYPE=mysql
set MYSQL_DB=zipkin
set MYSQL_USER=root
set MYSQL_PASS=
java -jar ./Zipkin/zipkin-server-2.24.2-exec.jar