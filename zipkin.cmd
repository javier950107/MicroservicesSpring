@echo off
set RABBIT_ADDRESSES=localhost:5672
set STORAGE_TYPE=mysql
set MYSQL_DB=zipkin
set MYSQL_USER=zipkin
set MYSQL_PASS=zipkin
java -jar ./Zipkin/zipkin-server-2.24.2-exec.jar