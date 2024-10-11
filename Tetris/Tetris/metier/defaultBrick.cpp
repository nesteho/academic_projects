#include "defaultBricks.h"

const Brick DefaultBricks::brickI({
                                      Position(1, 0),
                                      Position(0, 0),
                                      Position(2, 0),
                                      Position(3, 0)
                                  },1);

const Brick DefaultBricks::brickL({
                                      Position(1,0 ),
                                      Position(0, 0),
                                      Position(2, 0),
                                      Position(2, 1)
                                  },2);

const Brick DefaultBricks::brickZ({
                                      Position(0, 0),
                                      Position(0, -1),
                                      Position(1, 0),
                                      Position(1, 1)
                                  },3);

const Brick DefaultBricks::brickJ({
                                      Position(1,0 ),
                                      Position(0, 0),
                                      Position(2, 0),
                                      Position(2, -1)
                                  },4);

const Brick DefaultBricks::brickS({
                                      Position(0, 0),
                                      Position(0, 1),
                                      Position(1, 0),
                                      Position(1,- 1)
                                  },5);

const Brick DefaultBricks::brickT({
                                      Position(1, 0),
                                      Position(0, 0),
                                      Position(1, -1),
                                      Position(1, 1)
                                  },6);

const Brick DefaultBricks::brickSquare({
                                           Position(0, 0),
                                           Position(1, 0),
                                           Position(1, 1),
                                           Position(0, 1)
                                       },7);

