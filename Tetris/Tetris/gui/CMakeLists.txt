cmake_minimum_required(VERSION 3.5)

project(gui LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_CXX_FLAGS "-Wall -pedantic-errors")

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

#target_sources(gui
#  PRIVATE
#    mainwindow.h mainwindow.cpp mainwindow.ui
#)
# Chemin d'accès aux en-têtes du module metier
include_directories(../metier)


add_executable(gui main.cpp


    welcomeview.h welcomeview.cpp welcomeview.ui
    playbuttonhandler.h
    playbuttonhandler.cpp
    controller.h
    controller.cpp
    playview.h playview.cpp playview.ui

)

find_package(QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets)
find_package(Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets)
target_link_libraries(gui PRIVATE Qt${QT_VERSION_MAJOR}::Widgets metier)  # Utilisation de la cible gui

include(GNUInstallDirs)
install(TARGETS gui
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)







