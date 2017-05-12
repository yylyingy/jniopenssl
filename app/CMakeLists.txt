
cmake_minimum_required(VERSION 3.4.1)

#符号表隐藏
#c:
set(CMAKE_C_VISIBILITY_PRESET hidden)
#c++
set(CMAKE_CXX_VISIBILITY_PRESET hidden)


INCLUDE_DIRECTORIES(
src/main/cpp/openssllib/include/openssl/
)
LINK_DIRECTORIES(src/main/cpp/openssllib/)


ADD_LIBRARY(
native SHARED
    src/main/cpp/native-lib.cpp
    src/main/cpp/MyRSA.cpp
    src/main/cpp/MyBASE64.cpp
    src/main/cpp/My3DES.cpp
    src/main/cpp/MyMD5.cpp
    src/main/cpp/MyAES.cpp)

find_library( log-lib log)

TARGET_LINK_LIBRARIES(  native optimized libcrypto.a libssl.a ${log-lib} ) # 链接动态库




