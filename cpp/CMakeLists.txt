cmake_minimum_required(VERSION 3.13)
project(cpp)

set(CMAKE_CXX_STANDARD 17)

include_directories(cpr)
include_directories(json)
include_directories(libcurl)


add_executable(cpp
        cpr/api.h
        cpr/auth.cpp
        cpr/auth.h
        cpr/body.h
        cpr/cookies.cpp
        cpr/cookies.h
        cpr/cpr.h
        cpr/cprtypes.cpp
        cpr/cprtypes.h
        cpr/curlholder.h
        cpr/defines.h
        cpr/digest.cpp
        cpr/digest.h
        cpr/multipart.cpp
        cpr/multipart.h
        cpr/parameters.cpp
        cpr/parameters.h
        cpr/payload.cpp
        cpr/payload.h
        cpr/proxies.cpp
        cpr/proxies.h
        cpr/response.h
        cpr/session.cpp
        cpr/session.h
        cpr/timeout.h
        cpr/util.cpp
        cpr/util.h
        json/json.hpp
        replay.cpp)


target_link_libraries(cpp curl)