#!/bin/sh
CHECK_OS="`uname -s`"


case "$CHECK_OS" in
      Darwin*)    THIS_OS="MAC";;
      Linux*)     THIS_OS="LIN";;
      MINGW32*)   THIS_OS="WIN";;
    MINGW64*)   THIS_OS="WIN";;
    CYGWIN*)    THIS_OS="WIN";;
esac


echo "OS CHECK = ${CHECK_OS}"
echo "OS is ${THIS_OS}"

echo "jar file clean start"
if [ $THIS_OS = "MAC" ]
then
  (
    rm -rf ./api-gateway/build/libs
    rm -rf ./eureka-server/build/libs
    rm -rf ./auth-service/auth-container/build/libs
    rm -rf ./image-service/image-container/build/libs
    rm -rf ./member-service/member-container/build/libs
    rm -rf ./team-service/team-container/build/libs
    rm -rf ./thread-service/thread-container/build/libs
  )
else
  (
    rmdir ./api-gateway/build/libs
    rmdir ./eureka-server/build/libs
    rmdir ./auth-service/auth-container/build/libs
    rmdir ./image-service/image-container/build/libs
    rmdir ./member-service/member-container/build/libs
    rmdir ./team-service/team-container/build/libs
    rmdir ./thread-service/thread-container/build/libs
  )
fi
echo "bootJar를 통해 jar 파일 생성 후 deploy.sh를 실행해 주세요"