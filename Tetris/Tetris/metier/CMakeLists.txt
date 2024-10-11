cmake_minimum_required(VERSION 3.5)

project(metier LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_CXX_FLAGS "-Wall -pedantic-errors")

add_library(metier main.cpp

    bag.h
    brick.h
    position.h
    plate.h

    direction.h
    timeUnits.h
    defaultBricks.h


    brick.cpp
    game.h
    game.cpp
    plate.cpp
    bag.cpp
    brick.cpp
    direction.cpp
    timeUnits.cpp
    defaultBrick.cpp
    observer/observer.h
    observer/observable.h
    gamestate.h
    gametimer.h
    gametimer.cpp
)

include(GNUInstallDirs)
install(TARGETS metier
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)
