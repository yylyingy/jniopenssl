
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
    #src/main/cpp/MyRSA.cpp
    src/main/cpp/MyBASE64.cpp
    src/main/cpp/My3DES.cpp
    #src/main/cpp/MyMD5.cpp
    #src/main/cpp/MyAES.cpp
    )

ADD_LIBRARY(
    libcrypto
    SHARED
    IMPORTED
)
set_target_properties(
    libcrypto
    PROPERTIES IMPORTED_LOCATION
    ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libcrypto.so
)

find_library( log-lib log)

TARGET_LINK_LIBRARIES(
    native
    #libcrypto.a
    #libssl.a
    libcrypto
 ${log-lib}) # 链接动态库




