cmake_minimum_required(VERSION 3.10)

project (JPCAP)
set (JPCAP_VERSION_MAJOR 1)
set (JPCAP_VERSION_MINOR 0)

add_library(jpcap SHARED
    jpcap.c
)
target_link_libraries(jpcap pcap)
target_include_directories(jpcap 
	PUBLIC
	$ENV{JAVA_HOME}/include
	$ENV{JAVA_HOME}/include/linux
)
