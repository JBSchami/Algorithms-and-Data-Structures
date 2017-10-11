#************************************************************ 
#  	@file    	makefile
#  	@author  	Jonathan Bedard Schami
#  	@date    	02/10/2017  
#  	@version 	1.0 
#   
# 	@brief 		makefile for Sinlgy Linked List
# ************************************************************/

CPP = g++
CPP11 = -std=c++11
DEBUG = -g
OFLAG = -o
CFLAGS = -Wall -c
LFLAGS = -Wall
WALL = -Wall

#SLL Related
SLL_OBJS = Queue_ADT_Test.cpp Queue_ADT.h

#Clean related
#================================================================================================
CLEAN_OBJS = Queue_Test.exe

all: \
	Queue \

Queue: \
	Queue_ADT_Test


Qdebug: CPP += -DDEBUG -g
Qdebug: Queue_ADT_Test

Queue_ADT_Test: $(SLL_OBJS)
	$(CPP) $(LFLAGS) $(SLL_OBJS) $(OFLAG) Queue_Test.exe

#===========
#To clean up
#===========
clean:
	rm $(CLEAN_OBJS)