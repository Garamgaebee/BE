#!/bin/sh
#ec2 명령어 백업용 (실제 사용 x)
sudo docker pull rookie3912/eureka
sudo docker pull rookie3912/gateway
sudo docker pull rookie3912/authservice
sudo docker pull rookie3912/imageservice
sudo docker pull rookie3912/memberservice
sudo docker pull rookie3912/teamservice
sudo docker pull rookie3912/threadservice
sudo docker pull redis

sudo nohup docker run -p 9000:9000 rookie3912/eureka --add-host=host.docker.internal:host-gateway > eureka.out 2>&1 &
sudo nohup docker run -p 8080:8080 rookie3912/gateway --add-host=host.docker.internal:host-gateway > gateway.out 2>&1 &
sudo nohup docker run -p 8081:8081 rookie3912/authservice --add-host=host.docker.internal:host-gateway > authservice.out 2>&1 &
sudo nohup docker run -p 9001:9001 rookie3912/imageservice --add-host=host.docker.internal:host-gateway > imageservice.out 2>&1 &
sudo nohup docker run -p 8082:8082 rookie3912/memberservice --add-host=host.docker.internal:host-gateway > memberservice.out 2>&1 &
sudo nohup docker run -p 8083:8083 rookie3912/teamservice --add-host=host.docker.internal:host-gateway > teamservice.out 2>&1 &
sudo nohup docker run -p 8084:8084 rookie3912/threadservice --add-host=host.docker.internal:host-gateway > threadservice.out 2>&1 &
sudo nohup docker run -p 6379:6379 redis > redis.out 2>&1 &

