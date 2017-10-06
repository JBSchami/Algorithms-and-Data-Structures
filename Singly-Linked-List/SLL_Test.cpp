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

	myList->add(25);
	cout << "Index of 25: " << myList->indexOf(25) << endl;
	myList->addFirst(12);
	myList->add(85);
	myList->display();

	myList->addFirst(14);
	myList->display();

	myList->addFirst(26);
	myList->display();

	myList->addFirst(32);
	myList->display();

	cout << "Contains 26: " << myList->contains(26) << endl;
	cout << "Contains 11: " << myList->contains(11) << endl;

	cout << "Index of 32: " << myList->indexOf(32) << endl;
	cout << "Index of 26: " << myList->indexOf(26) << endl;
	cout << "Index of 85: " << myList->indexOf(85) << endl;
	cout << "Index of 25: " << myList->indexOf(25) << endl;

	myList->addFirst(11);
	myList->display();

	myList->clear();

	myList->add(99);
	myList->display();

	myList->add(54);

	myList->addAt(1,36);
	myList->display();

	myList->addAt(5,22);
	myList->display();

	myList->add(10);
	myList->add(19);

	myList->addAt(5, 55);
	
	//myList->remove();
	myList->removeFirst();
	myList->display();

	myList->removeAt(2);
	myList->display();
	
	myList->remove();
	myList->display();

	myList->add(21);
	myList->display();

	//myList->add(24);

	cout << myList->getHeadNode() << endl;
	cout << myList->getTailNode() << endl;

	myList->clear();
	myList->display();

	delete myList;
}
