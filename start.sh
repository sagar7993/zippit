#!/bin/bash

cd /home/ubuntu/zippit/
rm -rf target
mvn clean install
cd target
java -jar zippit-backend-0.0.1-SNAPSHOT.jar

