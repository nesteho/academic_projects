#include <iostream>
#include <catch2/catch_test_macros.hpp>
#include "../metier/defaultBricks.h"
#include "../metier/direction.h"
#include "gameTest.hpp"
#include "bagTest.hpp"
#include "plateTest.hpp"
#include "brickTest.hpp"


TEST_CASE("TestBagSize") {
    SECTION("Size of vector of brick") {
        BagTest bag;
        bool isSizeNotFour = (bag.getBrick().size() != 4);
        REQUIRE(isSizeNotFour == false);
    }

    SECTION("Size of vector of brick") {
        BagTest bag;
        bool isSizeFour = (bag.getBrick().size() == 4);
        REQUIRE(isSizeFour == true);
    }
}


TEST_CASE("Test rotation of DefaultBricks : I") {
    SECTION("Rotation of brickI clockwise") {

        BrickTest brickICopy(DefaultBricks::brickI.getPositions(), DefaultBricks::brickI.getNumero());

        brickICopy.rotate(true);

        auto rotatedPositions = brickICopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == 1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == -1);
        REQUIRE(rotatedPositions[3].getX() == 1);
        REQUIRE(rotatedPositions[3].getY() == -2);
    }

    SECTION("Rotation of brickI not clockwise") {
        BrickTest brickICopy(DefaultBricks::brickI.getPositions(), DefaultBricks::brickI.getNumero());

        brickICopy.rotate(false);

        auto rotatedPositions = brickICopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == -1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == 1);
        REQUIRE(rotatedPositions[3].getX() == 1);
        REQUIRE(rotatedPositions[3].getY() == 2);
    }

}

TEST_CASE("Test rotation of DefaultBricks : Z") {
    SECTION("Rotation of brickZ clockwise") {
        BrickTest brickZCopy(DefaultBricks::brickZ.getPositions(), DefaultBricks::brickZ.getNumero());

        brickZCopy.rotate(true);

        auto rotatedPositions = brickZCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 0);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == -1);
        REQUIRE(rotatedPositions[1].getY() == 0);
        REQUIRE(rotatedPositions[2].getX() == 0);
        REQUIRE(rotatedPositions[2].getY() == -1);
        REQUIRE(rotatedPositions[3].getX() == 1);
        REQUIRE(rotatedPositions[3].getY() == -1);
    }

    SECTION("Rotation of brickZ not clockwise") {
        BrickTest brickZCopy(DefaultBricks::brickZ.getPositions(), DefaultBricks::brickZ.getNumero());

        brickZCopy.rotate(false);

        auto rotatedPositions = brickZCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 0);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == 0);
        REQUIRE(rotatedPositions[2].getX() == 0);
        REQUIRE(rotatedPositions[2].getY() == 1);
        REQUIRE(rotatedPositions[3].getX() == -1);
        REQUIRE(rotatedPositions[3].getY() == 1);
    }
}

TEST_CASE("Test rotation of DefaultBricks : S") {
    SECTION("Rotation of brickS clockwise") {
        BrickTest brickSCopy(DefaultBricks::brickS.getPositions(), DefaultBricks::brickS.getNumero());

        brickSCopy.rotate(true);

        auto rotatedPositions = brickSCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 0);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == 0);
        REQUIRE(rotatedPositions[2].getX() == 0);
        REQUIRE(rotatedPositions[2].getY() == -1);
        REQUIRE(rotatedPositions[3].getX() == -1);
        REQUIRE(rotatedPositions[3].getY() == -1);
    }

    SECTION("Rotation of brickS not clockwise") {
        BrickTest brickSCopy(DefaultBricks::brickS.getPositions(), DefaultBricks::brickS.getNumero());

        brickSCopy.rotate(false);

        auto rotatedPositions = brickSCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 0);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == -1);
        REQUIRE(rotatedPositions[1].getY() == 0);
        REQUIRE(rotatedPositions[2].getX() == 0);
        REQUIRE(rotatedPositions[2].getY() == 1);
        REQUIRE(rotatedPositions[3].getX() == 1);
        REQUIRE(rotatedPositions[3].getY() == 1);
    }
}


TEST_CASE("Test rotation of DefaultBricks : Square") {
    SECTION("Rotation of brickSquare clockwise") {
        BrickTest brickSquareCopy(DefaultBricks::brickSquare.getPositions(), DefaultBricks::brickSquare.getNumero());

        brickSquareCopy.rotate(true);

        auto rotatedPositions = brickSquareCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 0);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 0);
        REQUIRE(rotatedPositions[1].getY() == -1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == -1);
        REQUIRE(rotatedPositions[3].getX() == 1);
        REQUIRE(rotatedPositions[3].getY() == 0);
    }

    SECTION("Rotation of brickSquare not clockwise") {
        BrickTest brickSquareCopy(DefaultBricks::brickSquare.getPositions(), DefaultBricks::brickSquare.getNumero());

        brickSquareCopy.rotate(false);

        auto rotatedPositions = brickSquareCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 0);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 0);
        REQUIRE(rotatedPositions[1].getY() == 1);
        REQUIRE(rotatedPositions[2].getX() == -1);
        REQUIRE(rotatedPositions[2].getY() == 1);
        REQUIRE(rotatedPositions[3].getX() == -1);
        REQUIRE(rotatedPositions[3].getY() == 0);
    }
 }


TEST_CASE("Test rotation of DefaultBricks : T") {
     SECTION("Rotation of brickT clockwise") {
        BrickTest brickTCopy(DefaultBricks::brickT.getPositions(), DefaultBricks::brickT.getNumero());

        brickTCopy.rotate(true);

        auto rotatedPositions = brickTCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == 1);
        REQUIRE(rotatedPositions[2].getX() == 0);
        REQUIRE(rotatedPositions[2].getY() == 0);
        REQUIRE(rotatedPositions[3].getX() == 2);
        REQUIRE(rotatedPositions[3].getY() == 0);
    }

    SECTION("Rotation of brickT not clockwise") {
        BrickTest brickTCopy(DefaultBricks::brickT.getPositions(), DefaultBricks::brickT.getNumero());

        brickTCopy.rotate(false);

        auto rotatedPositions = brickTCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == -1);
        REQUIRE(rotatedPositions[2].getX() == 2);
        REQUIRE(rotatedPositions[2].getY() == 0);
        REQUIRE(rotatedPositions[3].getX() == 0);
        REQUIRE(rotatedPositions[3].getY() == 0);
    }
}

TEST_CASE("Test rotation of DefaultBricks : J") {
    SECTION("Rotation of brickJ clockwise") {
        BrickTest brickJCopy(DefaultBricks::brickJ.getPositions(), DefaultBricks::brickJ.getNumero());

        brickJCopy.rotate(true);

        auto rotatedPositions = brickJCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == 1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == -1);
        REQUIRE(rotatedPositions[3].getX() == 0);
        REQUIRE(rotatedPositions[3].getY() == -1);
    }

    SECTION("Rotation of brickJ not clockwise") {
        BrickTest brickJCopy(DefaultBricks::brickJ.getPositions(), DefaultBricks::brickJ.getNumero());

        brickJCopy.rotate(false);

        auto rotatedPositions = brickJCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == -1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == 1);
        REQUIRE(rotatedPositions[3].getX() == 2);
        REQUIRE(rotatedPositions[3].getY() == 1);
    }
}

TEST_CASE("Test rotation of DefaultBricks : L") {
    SECTION("Rotation of brickL clockwise") {
        BrickTest brickLCopy(DefaultBricks::brickL.getPositions(), DefaultBricks::brickL.getNumero());

        brickLCopy.rotate(true);

        auto rotatedPositions = brickLCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == 1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == -1);
        REQUIRE(rotatedPositions[3].getX() == 2);
        REQUIRE(rotatedPositions[3].getY() == -1);
    }

    SECTION("Rotation of brickL not clockwise") {
        BrickTest brickLCopy(DefaultBricks::brickL.getPositions(), DefaultBricks::brickL.getNumero());

        brickLCopy.rotate(false);

        auto rotatedPositions = brickLCopy.getPositions();
        REQUIRE(rotatedPositions.size() == 4);
        REQUIRE(rotatedPositions[0].getX() == 1);
        REQUIRE(rotatedPositions[0].getY() == 0);
        REQUIRE(rotatedPositions[1].getX() == 1);
        REQUIRE(rotatedPositions[1].getY() == -1);
        REQUIRE(rotatedPositions[2].getX() == 1);
        REQUIRE(rotatedPositions[2].getY() == 1);
        REQUIRE(rotatedPositions[3].getX() == 0);
        REQUIRE(rotatedPositions[3].getY() == 1);
    }
}


TEST_CASE("Test Game addBrick") {
    SECTION("Adding brick to empty plate") {
        GameTest  game(20, 10, 1, true);
        REQUIRE(!game.getEmptyForTest());
    }
}
TEST_CASE("Test Brick getNumero") {
    SECTION("Brick getNumero returns correct value") {
        std::vector<Position> positions = {Position(0, 0), Position(1, 0), Position(2, 0)};
        unsigned int numero = 5;
        BrickTest brick(positions, numero);
        REQUIRE(brick.getNumero() == numero);
    }
}

TEST_CASE("Test Brick getPositions") {
    SECTION("Brick getPositions returns correct positions") {
        std::vector<Position> positions = {Position(0, 0), Position(1, 0), Position(2, 0)};
        BrickTest brick(positions, 1);
        auto brickPositions = brick.getPositions();
        REQUIRE(brickPositions.size() == positions.size());
        for (size_t i = 0; i < positions.size(); ++i) {
            REQUIRE(brickPositions[i].getX() == positions[i].getX());
            REQUIRE(brickPositions[i].getY() == positions[i].getY());
        }
    }
}

TEST_CASE("Test Brick copy assignment operator") {
    SECTION("Brick copy assignment works correctly") {
        std::vector<Position> positions1 = {Position(0, 0), Position(1, 0), Position(2, 0)};
        std::vector<Position> positions2 = {Position(0, 1), Position(1, 1), Position(2, 1)};
        BrickTest brick1(positions1, 1);
        BrickTest brick2(positions2, 2);
        brick1 = brick2;
        auto brick1Positions = brick1.getPositions();
        REQUIRE(brick1Positions.size() == positions2.size());
        for (size_t i = 0; i < positions2.size(); ++i) {
            REQUIRE(brick1Positions[i].getX() == positions2[i].getX());
            REQUIRE(brick1Positions[i].getY() == positions2[i].getY());
        }
    }
}

TEST_CASE("Test DefaultBricks") {
    SECTION("Test brickI") {
        const Brick& brickI = DefaultBricks::brickI;
        const std::vector<Position>& positions = brickI.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 1);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 0);
        REQUIRE(positions[1].getY() == 0);
        REQUIRE(positions[2].getX() == 2);
        REQUIRE(positions[2].getY() == 0);
        REQUIRE(positions[3].getX() == 3);
        REQUIRE(positions[3].getY() == 0);
        REQUIRE(brickI.getNumero() == 1);
    }

    SECTION("Test brickL") {
        const Brick& brickL = DefaultBricks::brickL;
        const std::vector<Position>& positions = brickL.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 1);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 0);
        REQUIRE(positions[1].getY() == 0);
        REQUIRE(positions[2].getX() == 2);
        REQUIRE(positions[2].getY() == 0);
        REQUIRE(positions[3].getX() == 2);
        REQUIRE(positions[3].getY() == 1);
        REQUIRE(brickL.getNumero() == 2);
    }
    SECTION("Test brickZ") {
        const Brick& brickZ = DefaultBricks::brickZ;
        const std::vector<Position>& positions = brickZ.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 0);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 0);
        REQUIRE(positions[1].getY() == -1);
        REQUIRE(positions[2].getX() == 1);
        REQUIRE(positions[2].getY() == 0);
        REQUIRE(positions[3].getX() == 1);
        REQUIRE(positions[3].getY() == 1);
        REQUIRE(brickZ.getNumero() == 3);
    }
    SECTION("Test brickS") {
        const Brick& brickS = DefaultBricks::brickS;
        const std::vector<Position>& positions = brickS.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 0);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 0);
        REQUIRE(positions[1].getY() == 1);
        REQUIRE(positions[2].getX() == 1);
        REQUIRE(positions[2].getY() == 0);
        REQUIRE(positions[3].getX() == 1);
        REQUIRE(positions[3].getY() == -1);
        REQUIRE(brickS.getNumero() == 5);
    }

    SECTION("Test brickJ") {
        const Brick& brickJ = DefaultBricks::brickJ;
        const std::vector<Position>& positions = brickJ.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 1);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 0);
        REQUIRE(positions[1].getY() == 0);
        REQUIRE(positions[2].getX() == 2);
        REQUIRE(positions[2].getY() == 0);
        REQUIRE(positions[3].getX() == 2);
        REQUIRE(positions[3].getY() == -1);
        REQUIRE(brickJ.getNumero() == 4);
    }

    SECTION("Test brickT") {
        const Brick& brickT = DefaultBricks::brickT;
        const std::vector<Position>& positions = brickT.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 1);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 0);
        REQUIRE(positions[1].getY() == 0);
        REQUIRE(positions[2].getX() == 1);
        REQUIRE(positions[2].getY() == -1);
        REQUIRE(positions[3].getX() == 1);
        REQUIRE(positions[3].getY() == 1);
        REQUIRE(brickT.getNumero() == 6);
    }

    SECTION("Test brickSquare") {
        const Brick& brickSquare = DefaultBricks::brickSquare;
        const std::vector<Position>& positions = brickSquare.getPositions();
        REQUIRE(positions.size() == 4);
        REQUIRE(positions[0].getX() == 0);
        REQUIRE(positions[0].getY() == 0);
        REQUIRE(positions[1].getX() == 1);
        REQUIRE(positions[1].getY() == 0);
        REQUIRE(positions[2].getX() == 1);
        REQUIRE(positions[2].getY() == 1);
        REQUIRE(positions[3].getX() == 0);
        REQUIRE(positions[3].getY() == 1);
        REQUIRE(brickSquare.getNumero() == 7);
    }
}

TEST_CASE("Test DIRECTIONS") {
    SECTION("Test DOWN direction") {
        REQUIRE(DIRECTIONS::DOWN.first == 1);
        REQUIRE(DIRECTIONS::DOWN.second == 0);
    }

    SECTION("Test LEFT direction") {
        REQUIRE(DIRECTIONS::LEFT.first == 0);
        REQUIRE(DIRECTIONS::LEFT.second == -1);
    }

    SECTION("Test RIGHT direction") {
        REQUIRE(DIRECTIONS::RIGHT.first == 0);
        REQUIRE(DIRECTIONS::RIGHT.second == 1);
    }
}

TEST_CASE("Test Game initialization") {
    SECTION("Game initialization with valid parameters") {
        REQUIRE_NOTHROW(Game(20, 10, 1, true));
    }

    // SECTION("Game initialization with invalid parameters") {
    //     REQUIRE_THROWS_AS(Game(-1, 10, 1, true), std::invalid_argument);
    //     REQUIRE_THROWS_AS(Game(20, -1, 1, true), std::invalid_argument);
    //     REQUIRE_THROWS_AS(Game(20, 10, -1, true), std::invalid_argument);
    // }
}





// TEST_CASE("Test checkPlateDimensions") {
//     Game game(10, 10, 1, true);

//     SECTION("Valid dimensions") {
//         REQUIRE_NOTHROW(game.checkPlateDimensionsTest(5, 5),true);
//     }

//     SECTION("Negative height") {
//         REQUIRE_THROWS_AS(game.checkPlateDimensionsTest(-5, 5), std::invalid_argument);
//     }

//     SECTION("Negative width") {
//         REQUIRE_THROWS_AS(game.checkPlateDimensionsTest(5, -5), std::invalid_argument);
//     }
// }



// TEST_CASE("Test checkOver") {
//     SECTION("Game not over") {
//         Game game(10, 10, 1, true);
//         REQUIRE_FALSE(game.isOver());
//     }

//     SECTION("Game over by score") {
//         Game game(10, 10, 1, true);
//         game.increaseScoreTest(100001);
//         REQUIRE(game.isOver());
//     }
// }



// TEST_CASE("Test Game rotate") {
//     SECTION("Rotating a brick clockwise") {
//         Game game(10, 10, 1, true);
//         std::vector<std::vector<unsigned int>> initialCases = game.getCases();

//         // Perform a clockwise rotation
//         game.rotate(true);

//         // Check if the cases have changed
//         std::vector<std::vector<unsigned int>> updatedCases = game.getCases();
//         REQUIRE(initialCases != updatedCases); // Ensure cases are updated after rotation
//     }

//     SECTION("Rotating a brick counterclockwise") {
//         Game game(10, 10, 1, true);
//         std::vector<std::vector<unsigned int>> initialCases = game.getCases();

//         // Perform a counterclockwise rotation
//         game.rotate(false);


// TEST_CASE("Test Plate::clean") {
// SECTION("Cleaning empty plate") {
//     Plate plate(false, 10, 10);
//     plate.clean();
//     std::vector<std::vector<unsigned int>>cases= plate.getCases();
//     // Check if all cells are still empty
//     for (int row = 0; row < 10; ++row) {
//         for (int col = 0; col < 10; ++col) {
//             REQUIRE(cases[row][col] == 0);
//         }
//     }
// }

//     //CORRIGER
//     SECTION("Cleaning plate with occupied cells") {
//         Plate plate(false, 10, 10);
//         std::vector<std::vector<unsigned int>>cases= plate.getCases();
//         // Assume that some cells are occupied
//         cases[3][3] = 1;
//         cases[5][7] = 1;
//         plate.clean();

//         // Check if the cases have changed
//         std::vector<std::vector<unsigned int>> updatedCases = game.getCases();
//         REQUIRE(initialCases != updatedCases); // Ensure cases are updated after rotation
//     }
// }





///////////////      --- A CORRIGER ---     ///////////////////////
// /////////////////////////////////////////////////////////////////////
// TEST_CASE("Test increaseScore") {
//     GameTester game(10, 10, 1, true);

//     SECTION("Increase score with one line cleared") {

//         unsigned oldScore = game.getScore();
//         game.increaseScoreTest(1);
//         unsigned newScore = game.getScore();
//         unsigned expectedNewScore = oldScore + ( ( 40 * game.getPlateForTest().getLineBrick() +1 ) * game.getLevel());
//         REQUIRE( newScore == expectedNewScore);
//     }
//     SECTION("Increase score with multiple line cleared") {

//         unsigned oldScore = game.getScore();
//         game.increaseScoreTest(3);
//         unsigned newScore = game.getScore();
//         unsigned expectedNewScore = oldScore +( ( 300 * game.getPlateForTest().getLineBrick() +3 ) * game.getLevel());
//         REQUIRE( newScore == expectedNewScore);
//     }
// }

//         // Check if all cells are empty after cleaning
//         for (int row = 0; row < 10; ++row) {
//             for (int col = 0; col < 10; ++col) {
//                 REQUIRE(cases[row][col] == 0);
//             }
//         }
//     }
//  }
















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//       TEST_CASE("Test Game addBrick") {
//           SECTION("Adding brick to empty plate") {
//               Game game(20, 10, 1, true);
//               REQUIRE(!game.getPlateForTest().getEmptyForTest());
//           }
//       }


//       TEST_CASE("Translate brick down", "[Game]") {
//           Game game(20, 10, 1, true);
//          // Obtention des cases avant la translation
//           auto casesBefore = game.getCases();
//            //Appel de la méthode à tester avec la direction 'd' (vers le bas)
//           game.translateBrick('d');

//           // Obtention des cases après la translation
//           auto casesAfter = game.getCases();
//          //  Assertions
//           REQUIRE(casesBefore != casesAfter);  // Les cases doivent être différentes après la translation
//       }
//       TEST_CASE("Test Game translateBrick") {
//           Game game(10, 10, 1, true);
//           Plate& plate = game.getPlateForTest();
//           auto brick = DefaultBricks::brickI;
//           game.addBrickTest(brick);
//           auto oldCenter = plate.getCurBrickCenterForTest();
//           game.translateBrick('d');
//           auto newCenter = plate.getCurBrickCenterForTest();
//           auto positions = brick.getPositions();
//           for (const auto& pos : positions) {
//               int newX = newCenter->getX() + pos.getX() + DIRECTIONS::LEFT.first; // Calculate new X position
//               int newY = newCenter->getY() + pos.getY()+DIRECTIONS::LEFT.second; // Calculate new Y position
//               REQUIRE(plate.getCases()[newX][newY] == brick.getNumero()); // Check if the corresponding case is occupied
//           }
//       }


 TEST_CASE("Test Game drop") {
     SECTION("Dropping a brick") {
         Game game(10, 10, 1, true);
         std::vector<std::vector<unsigned int>> initialCases = game.getCases();

         // Perform a drop
         game.drop();

          // Check if at least one case in the last row has changed
         std::vector<std::vector<unsigned int>> updatedCases = game.getCases();
         bool lastRowNotEmpty = false;
         for (unsigned int col : updatedCases.back()) {
             if (col != 0) {
                 lastRowNotEmpty = true;
                 break;
             }
         }
         REQUIRE(lastRowNotEmpty);
     }

}



//     SECTION("Dropping a brick without available space") {
//         Plate plate(false, 10, 10);
//         unsigned initialLines = plate.getLines();

//         // Fill the plate to prevent dropping
//         for (int i = 0; i < 10; ++i) {
//             for (int j = 0; j < 10; ++j) {
//                 plate.cases[i][j] = 1;
//             }
//         }

//         // Assume that there is a current brick
//         plate.curBrick = std::make_shared<Brick>(std::vector<Position>{Position(0, 0), Position(0, 1), Position(0, 2), Position(0, 3)}, 1);

//         // Try dropping a brick
//         REQUIRE_THROWS_WITH(plate.drop(), "No current brick to drop.");
//     }
// }


