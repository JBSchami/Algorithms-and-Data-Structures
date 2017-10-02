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
SLL_OBJS = SLL_Test.cpp SLL.h

#Clean related
#================================================================================================
CLEAN_OBJS = SLL_Test.exe

all: \
	SLL \

SLL: \
	SLL_Test


#SLLdebug: CPP += -DDEBUG -g
#SLLdebug: SLL_Test

SLL_Test: $(SLL_OBJS)
	$(CPP) $(LFLAGS) $(SLL_OBJS) $(OFLAG) SLL_Test.exe

#===========
#To clean up
#===========
clean:
	rm $(CLEAN_OBJS)