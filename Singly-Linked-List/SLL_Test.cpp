/************************************************************ 
 *  @file    	SLL_TEST.cpp
 *  @author  	Jonathan Bedard Schami
 *  @date    	02/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Singly Linked List Test
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains is used to test my implementation of a
 *	linked list ADT.
 ************************************************************/

#include "SLL.h"
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char* argv[]){

	SinglyLinkedList<int>* myList = new SinglyLinkedList<int>;

	//myList->addLast(25);
	myList->addFirst(12);
	myList->display();

	myList->addFirst(14);
	myList->display();

	myList->addFirst(26);
	myList->display();

	myList->addFirst(32);
	myList->display();

	myList->addFirst(11);
	myList->display();

	myList->addLast(99);
	myList->display();

	myList->insertPosition(1,36);
	myList->display();

	myList->insertPosition(5,22);
	myList->display();
	
	//myList->removeLast();
	myList->removeFirst();
	myList->display();

	myList->removePosition(2);
	myList->display();
	
	myList->removeLast();
	myList->display();

	myList->addLast(21);
	myList->display();
}
