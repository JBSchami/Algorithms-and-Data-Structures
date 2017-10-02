#************************************************************ 
#  	@file    	makefile
#  	@author  	Jonathan Bedard Schami
#  	@date    	02/10/2017  
#  	@version 	1.0 
#   
# 	@brief 		makefile for Doubly Linked List
# ************************************************************/

CPP = g++
CPP11 = -std=c++11
DEBUG = -g
OFLAG = -o
CFLAGS = -Wall -c
LFLAGS = -Wall
WALL = -Wall

#SLL Related
SLL_OBJS = DLL_Test.cpp DLL.h

#Clean related
#================================================================================================
CLEAN_OBJS = DLL_T.exe 

all: \
	DLL_Test \

DLL: \
	DLL_Test

DLL_Test: $(SLL_OBJS)
	$(CPP) $(LFLAGS) $(SLL_OBJS) -o DLL_T.exe


#===========
#To clean up
#===========
clean:
	rm $(CLEAN_OBJS)