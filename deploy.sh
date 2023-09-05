#!/bin/sh
#Apple Silicon Mac 버전입니다. 타 플렛폼 사용자는 파일을 복사하여 알맞게 변형하여 사용하시기 바랍니다.
#로컬에 도커가 깔려 있어야 합니다.
echo "start build and push docker image"
#echo "login docker hub"
#docker login
echo "start eureka"
(
cd ./eureka-server
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/eureka --platform linux/amd64 .
docker push rookie3912/eureka
echo "finish eureka"
)
(
echo "start gateway"
cd ./api-gateway
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/gateway --platform linux/amd64 .
docker push rookie3912/gateway
echo "finish gateway"
)
(
echo "start auth"
cd ./auth-service/auth-container
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/authservice --platform linux/amd64 .
docker push rookie3912/authservice
echo "finish auth"
)
(
echo "start image"
cd ./image-service/image-container
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/imageservice --platform linux/amd64 .
docker push rookie3912/imageservice
echo "finish image"
)
(
echo "start member"
cd ./member-service/member-container
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/memberservice --platform linux/amd64 .
docker push rookie3912/memberservice
echo "finish member"
)
(
echo "start team"
cd ./team-service/team-container
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/teamservice --platform linux/amd64 .
docker push rookie3912/teamservice
echo "finish team"
)
(
echo "start thread"
cd ./thread-service/thread-container
docker build --build-arg DEPENDENCY=build/dependency -t rookie3912/threadservice --platform linux/amd64 .
docker push rookie3912/threadservice
echo "image build finish!"
)