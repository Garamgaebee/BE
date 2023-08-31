#!/bin/sh
#ec2 명령어 백업용 (실제 사용 x)
sudo docker pull rookie3912/eureka
sudo docker pull rookie3912/gateway
sudo docker pull rookie3912/authservice
sudo docker pull rookie3912/imageservice
sudo docker pull rookie3912/memberservice
sudo docker pull rookie3912/teamservice
sudo docker pull rookie3912/threadservice

sudo nohup docker run -p 9000:9000 rookie3912/eureka > eureka.out 2>&1 &
sudo nohup docker run -p 8080:8080 rookie3912/gateway > gateway.out 2>&1 &
sudo nohup docker run -p 8081:8081 rookie3912/authservice > authservice.out 2>&1 &
sudo nohup docker run -p 9001:9001 rookie3912/imageservice > imageservice.out 2>&1 &
sudo nohup docker run -p 8082:8082 rookie3912/memberservice > memberservice.out 2>&1 &
sudo nohup docker run -p 8083:8083 rookie3912/teamservice > teamservice.out 2>&1 &
sudo nohup docker run -p 8084:8084 rookie3912/threadservice > threadservice.out 2>&1 &

