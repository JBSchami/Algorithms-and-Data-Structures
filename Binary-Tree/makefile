#************************************************************ 
#  	@file    	makefile
#  	@author  	Jonathan Bedard Schami
#  	@date    	02/10/2017  
#  	@version 	1.0 
#   
# 	@brief 		makefile for Binary Tree Testing
# ************************************************************/

CPP = g++
CPP11 = -std=c++11
DEBUG = -g
OFLAG = -o
CFLAGS = -Wall -c
LFLAGS = -Wall
WALL = -Wall

#SLL Related
SLL_OBJS = BinaryTree_Test.cpp BinaryTree.h

#Clean related
#================================================================================================
CLEAN_OBJS = BTree_Test.exe

all: \
	BTree_Test \

BTree: \
	BTree_Test


BTreeDebug: CPP += -DDEBUG -g
BTreeDebug: BTree_Test

BTree_Test: $(SLL_OBJS)
	$(CPP) $(CPP11) $(LFLAGS) $(SLL_OBJS) $(OFLAG) Btree_Test.exe

#===========
#To clean up
#===========
clean:
	rm $(CLEAN_OBJS)