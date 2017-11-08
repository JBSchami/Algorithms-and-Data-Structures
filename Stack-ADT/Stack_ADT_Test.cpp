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

#include "Stack_ADT.h"

using namespace std;

int main(int argc, char* argv[]){
	Stack_ADT<int> *myStack = new Stack_ADT<int>;

	myStack->push(12);
	myStack->display();
	myStack->push(15);
	myStack->display();
	myStack->push(34);
	myStack->display();

	cout << "Stack Size: " << myStack->getSize() << endl;
	int value = myStack->pop();
	cout << "popped node: " << value << endl;
	myStack->display();
	value = myStack->pop();
	cout << "popped node: " << value << endl;
	myStack->pop();
	myStack->display();
	myStack->pop();
	myStack->display();

	myStack->isEmpty();

}