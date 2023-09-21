#!/bin/sh
#ec2 명령어 백업용 (실제 사용 x)
sudo docker pull tjrcjs0705/eureka
sudo docker pull tjrcjs0705/gateway
sudo docker pull tjrcjs0705/authservice
sudo docker pull tjrcjs0705/imageservice
sudo docker pull tjrcjs0705/memberservice
sudo docker pull tjrcjs0705/teamservice
sudo docker pull tjrcjs0705/threadservice
sudo docker pull redis

sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 9000:9000 tjrcjs0705/eureka > eureka.out 2>&1 &
sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 8080:8080 tjrcjs0705/gateway > gateway.out 2>&1 &
sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 8081:8081 tjrcjs0705/authservice > authservice.out 2>&1 &
sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 9001:9001 tjrcjs0705/imageservice > imageservice.out 2>&1 &
sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 8082:8082 tjrcjs0705/memberservice > memberservice.out 2>&1 &
sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 8083:8083 tjrcjs0705/teamservice > teamservice.out 2>&1 &
sudo nohup docker run --add-host=host.docker.internal:host-gateway -p 8084:8084 tjrcjs0705/threadservice > threadservice.out 2>&1 &
sudo nohup docker run -p 6379:6379 redis > redis.out 2>&1 &

