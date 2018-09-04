cmake_minimum_required (VERSION 3.10.0)
project (saked)

add_executable (saked saked.c)
target_link_libraries(saked pcre2-8)
 