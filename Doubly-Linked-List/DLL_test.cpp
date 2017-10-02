/************************************************************ 
 *  @file    	DLL_TEST.cpp
 *  @author  	Jonathan Bedard Schami
 *  @date    	02/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Doubly Linked List Test
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains is used to test my implementation of a
 *	Doubly linked list ADT.
 ************************************************************/

#include "DLL.h"
#include <iostream>
#include <string>

using namespace std;

int main(int argc, char* argv[]){

	DoublyLinkedList<int>* myList = new DoublyLinkedList<int>;

	myList->addFirst(1);
	myList->display();

	myList->addFirst(2);
	myList->display();

	myList->addFirst(3);
	myList->display();

	myList->addFirst(4);
	myList->display();

	myList->addFirst(5);
	myList->display();

	myList->addLast(99);
	myList->display();

	myList->insertPosition(1,36);
	myList->display();

	myList->insertPosition(6,22);
	myList->display();
	
	//myList->removeLast();
	myList->removeFirst();
	myList->display();

	myList->removePosition(2);
	myList->display();
	
	myList->removePosition(6);
	myList->display();

	myList->removeLast();
	myList->display();

	myList->addLast(21);
	myList->display();
}
