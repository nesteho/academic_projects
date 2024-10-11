cmake_minimum_required(VERSION 3.5)

add_executable(tests
        ../metier/direction.cpp
        ../metier/defaultBrick.cpp
        ../metier/plate.cpp
        ../metier/brick.cpp
        ../metier/bag.cpp
        ../metier/game.cpp
         ../metier/gametimer.cpp
        ../Console/Controller.cpp
        ../Console/Vue.cpp
        ../metier/timeUnits.cpp
        main.cpp
        plateTest.hpp
        gameTest.hpp
        bagTest.hpp
        brickTest.hpp


)
target_include_directories(tests PRIVATE ../metier)

# add_executable(tests main.cpp)
Include(FetchContent)

FetchContent_Declare(
        Catch2
        GIT_REPOSITORY https://github.com/catchorg/Catch2.git
        GIT_TAG v3.3.1 # or a later release
)

FetchContent_MakeAvailable(Catch2)
#list(APPEND CMAKE_MODULE_PATH ${catch2_SOURCE_DIR}/extras)

target_link_libraries(tests Catch2::Catch2WithMain)
