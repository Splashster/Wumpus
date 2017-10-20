#!/bin/bash
#Script to compile and run Wumplus World Program

#Delete old class files
rm Agent.class coordinate.class KnowledgeBase.class Map.class MapNode.class Menu.class ScoringEngine.class WumplusTitle.class WumpusWorld.class Wumpus Driver.class

#Recompile project
javac Agent.java coordinate.java KnowledgeBase.java Map.java MapNode.java Menu.java ScoringEngine.java WumplusTitle.java WumplusWorld.java WumpusDriver.java


#Run project
java WumpusDriver
