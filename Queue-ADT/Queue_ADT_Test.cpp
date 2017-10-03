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

#include "Queue_ADT.h"
#include <iostream>

using namespace std;

int main (int argc, char* argv[]){
	Queue_ADT<string> *myQueue = new Queue_ADT<string>;

	myQueue->enqueue("Roger");
	cout << myQueue->first() << endl;
	myQueue->enqueue("Mike");
	myQueue->enqueue("Gen");
	myQueue->dequeue();
	cout << myQueue->first() << endl;
	myQueue->enqueue("Ivan");
	myQueue->enqueue("Melissa");
	myQueue->enqueue("Leon");
	myQueue->dequeue();
	cout << myQueue->first() << endl;
	myQueue->dequeue();
	cout << myQueue->first() << endl;
	myQueue->dequeue();
	cout << myQueue->first() << endl;
	myQueue->dequeue();
	cout << myQueue->first() << endl;
	myQueue->dequeue();
	cout << myQueue->first() << endl;
	myQueue->dequeue();
	cout << myQueue->first() << endl;

	delete myQueue;

}