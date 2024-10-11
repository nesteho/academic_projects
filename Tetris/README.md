# Tetris Game

This project is a C++ implementation of the classic Tetris game, featThis project is a C++ implementation of the
classic Tetris game, featuring both console and graphical interfaces. Players are challenged to manage falling blocks, 
complete lines, and navigate increasing difficulty in real-time gameplay.uring falling blocks, 
increasing difficulty, and real-time gameplay. It includes both a console version and a graphical interface,
 challenging players to complete lines and manage game dynamics.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Environment Configuration](#environment-configuration)
- [Running the Game](#running-the-game)
- [Contributing](#contributing)
- [Authors](#authors)
- [License](#license)

## Features

- Classic Tetris gameplay with falling blocks and line completion
- Randomly generated bag of bricks for dynamic gameplay
- Game board with line completion management
- Increasing difficulty with faster brick descent speed
- Brick rotation and movement control
- Real-time score and level management
- Game over condition handling

## Technologies Used
- **C++**: Main programming language
- **QtCreator**: IDE used for developing the game
- **Git**: Version control system for managing code

NOTE: ## Project Structure Choice:
- **CMake**

## Installation
### 1. Clone the Project
Clone the repository into your local environment:

```bash
git clone https://github.com/nesteho/tetris.git
cd tetris
```

### 2. Install Dependencies

Ensure that you have all necessary dependencies installed, including CMake for building the project.

###3 Build the Project

Navigate to the project directory and run the following command to build the project using CMake:

``` bash
cmake .
make
```

## Environment Configuration

Ensure you have a compatible C++ compiler and Qt libraries installed in your environment

## Running the Game
To run the Tetris game, follow these steps:

1. **Open your C++ IDE**: Launch your preferred C++ development environment (e.g., QtCreator)

2. **Load the Project**: Open the Tetris project file.

3. **Select the Module**: 
   - Locate the project structure in your IDE
   - Choose the module you want to run:
     - For the **Console Version**, select `console`
     - For the **Graphical Version**, select `gui`

4. **Run the Game**: Click the **Run** button (play icon) in your IDE to start the selected version of the game

Enjoy playing Tetris!

## Authors
This project was developed by Sarah Q. (Matricule: 61562) and I, Nesteho (Matricule: 60736).