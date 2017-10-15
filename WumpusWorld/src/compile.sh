#!/bin/bash

#Delete old class files
rm Agent.class coordinate.class logic.class Map.class MapNode.class ScoringEngine.class WumpusWorld.class Wumpus Driver.class

#Recompile project
javac Agent.java coordinate.java Map.java MapNode.java ScoringEngine.java WumplusWorld.java WumpusDriver.java


#Run project
java WumpusDriver
