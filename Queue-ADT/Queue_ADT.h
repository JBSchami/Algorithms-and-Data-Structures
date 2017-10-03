/************************************************************ 
 *  @file    	QUEUE_ADT.h
 *  @author  	Jonathan Bedard Schami
 *  @date    	02/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Queue implementation
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains my implementation of a Queue 
 *	in C++. It relies on the Singly Linked List class.
 ************************************************************/

#ifndef QUEUE_ADT_H
#define QUEUE_ADT_H

#include "../Singly-Linked-List/SLL.h"
#include <iostream>

template<class T>
class Queue_ADT{
	private:
		SinglyLinkedList<T> queue_Data;
		int size;
	public:
		Queue_ADT(){
			size = queue_Data.getSize();
		}

		~Queue_ADT(){

		}

		bool isEmpty();
		int  getSize();

		void enqueue(T value);	//Adds to the end of the queue
		void dequeue();			//Removes from the start of the queue
		T first();
};

template<class T>
bool Queue_ADT<T>::isEmpty(){
	if (queue_Data.isEmpty()){
		return true;
	}
	else
		return false;
}

template<class T>
int Queue_ADT<T>::getSize(){
	return queue_Data.getSize();
}

//Head of the singly linked list is considered end of the queue
//This is to respect the first in first out principle.
template<class T>
void Queue_ADT<T>::enqueue(T value){
	queue_Data.addFirst(value);
}

//Tail of the singly linked list is considered the start of the queue
//This is to respecte the first in first out principle.
template<class T>
void Queue_ADT<T>::dequeue(){
	queue_Data.removeLast();
}

template<class T>
T Queue_ADT<T>::first(){
	if (isEmpty()){
		std::cout << "Queue is empty" << std::endl;
		return T();
	}
	else
		return queue_Data.getLast();

}

#endif //QUEUE_ADT_H

