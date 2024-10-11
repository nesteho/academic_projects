cmake_minimum_required(VERSION 3.5)

project(Tetris LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Inclure les sous-modules
add_subdirectory(metier)
add_subdirectory(console)
add_subdirectory(tests)
add_subdirectory(gui)

