#!/bin/sh
#Apple Silicon Mac 버전입니다. 타 플렛폼 사용자는 파일을 복사하여 알맞게 변형하여 사용하시기 바랍니다.
#로컬에 도커가 깔려 있어야 합니다.
echo "start build and push docker image"
echo "login docker hub"
docker login
echo "start eureka"
(
cd ./eureka-server
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/eureka --platform linux/amd64 .
docker push tjrcjs0705/eureka
echo "finish eureka"
)
(
echo "start gateway"
cd ./api-gateway
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/gateway --platform linux/amd64 .
docker push tjrcjs0705/gateway
echo "finish gateway"
)
(
echo "start auth"
cd ./auth-service/auth-container
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/authservice --platform linux/amd64 .
docker push tjrcjs0705/authservice
echo "finish auth"
)
(
echo "start image"
cd ./image-service/image-container
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/imageservice --platform linux/amd64 .
docker push tjrcjs0705/imageservice
echo "finish image"
)
(
echo "start member"
cd ./member-service/member-container
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/memberservice --platform linux/amd64 .
docker push tjrcjs0705/memberservice
echo "finish member"
)
(
echo "start team"
cd ./team-service/team-container
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/teamservice --platform linux/amd64 .
docker push tjrcjs0705/teamservice
echo "finish team"
)
(
echo "start thread"
cd ./thread-service/thread-container
docker build --build-arg DEPENDENCY=build/dependency -t tjrcjs0705/threadservice --platform linux/amd64 .
docker push tjrcjs0705/threadservice
echo "image build finish!"
)