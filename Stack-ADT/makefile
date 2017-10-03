#************************************************************ 
#  	@file    	makefile
#  	@author  	Jonathan Bedard Schami
#  	@date    	02/10/2017  
#  	@version 	1.0 
#   
# 	@brief 		makefile for Stack ADT
# ************************************************************/

CPP = g++
CPP11 = -std=c++11
DEBUG = -g
OFLAG = -o
CFLAGS = -Wall -c
LFLAGS = -Wall
WALL = -Wall

#SLL Related
SLL_OBJS = Stack_ADT_Test.cpp Stack_ADT.h

#Clean related
#================================================================================================
CLEAN_OBJS = Stack_ADT_Test.exe

all: \
	Stack_ADT_Test \

SLL: \
	Stack_ADT_Test


#SLLdebug: CPP += -DDEBUG -g
#SLLdebug: SLL_Test

SLL_Test: $(SLL_OBJS)
	$(CPP) $(LFLAGS) $(SLL_OBJS) $(OFLAG) Stack_ADT_Test.exe

#===========
#To clean up
#===========
clean:
	rm $(CLEAN_OBJS)