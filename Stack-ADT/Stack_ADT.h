/************************************************************ 
 *  @file    	Stack_ADT.h
 *  @author  	Jonathan Bedard Schami
 *  @date    	02/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Stack implementation
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains my implementation of a stack 
 *	in C++. It relies on the Singly Linked List class.
 ************************************************************/

#ifndef STACK_ADT_H
#define STACK_ADT_H

#include "../Singly-Linked-List/SLL.h"
#include <stdexcept>
#include <iostream>

//C++ Template for a Doubly linked list ADT
template<class T>
class Stack_ADT {
	private:
		SinglyLinkedList<T> Stack_Data;
	public:
		Stack_ADT(){};

		T top();
		int  getSize();
		bool isEmpty();
		void display();
		
		void push(T value);
		void pop();

};

//Returns the value at the top of the stack without removing it
template<class T>
T Stack_ADT<T>::top(){
	return Stack_Data.getFirst();
}

//Returns how many items are on the stack
template<class T>
int Stack_ADT<T>::getSize(){
	return Stack_Data.getSize();
}

//Checks if the Stack is empty
template<class T>
bool Stack_ADT<T>::isEmpty(){
	if(Stack_Data.isEmpty())
		return true;
	else
		return false;
}

template<class T>
void Stack_ADT<T>::display(){
	if(isEmpty()){
		std::cout << "Stack is Empty" << std::endl;
	}
	else{
		std::cout << top() << std::endl;
	}
}

//Adds a value to the top of the stack
template<class T>
void Stack_ADT<T>::push(T value){
	Stack_Data.addFirst(value);
}

//Removes the value at the top of the stack
template<class T>
void Stack_ADT<T>::pop(){
	if (isEmpty()){
		//do nothing
	}
	else{
		Stack_Data.removeFirst();
	}
}

#endif //STACK_ADT_H
