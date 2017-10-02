/************************************************************ 
 *  @file    	DLL.h
 *  @author  	Jonathan Bedard Schami
 *  @date    	02/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Doubly Linked List Implementation
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains my implementation of a C++ doubly
 *	linked list ADT.
 ************************************************************/

#ifndef DLL_H
#define DLL_H

#include <stdexcept>
#include <iostream>

template<typename T>
struct node{
	T data;
	node *next;
	node *previous;
};


//C++ Template for a Doubly linked list ADT
template<class T>
class DoublyLinkedList {
	private:
		node<T> headSentinel;
		node<T> tailSentinel;
		int size;
	public:
		DoublyLinkedList(){
			headSentinel.data = T();
			headSentinel.next = &tailSentinel;
			headSentinel.previous = NULL;
			
			tailSentinel.data = T();
			tailSentinel.previous = &headSentinel;
			tailSentinel.next = NULL;

			size = 2;
		}

		void addLast(T value);
		void addFirst(T value);
		
		void removeFirst();
		void removeLast();

		void insertPosition(int pos, T value);
		void removePosition(int pos);

		bool isEmpty();
		int  getSize();
		void display();
};

//Checks if the SLL is empty
template<class T>
bool DoublyLinkedList<T>::isEmpty(){
	if(headSentinel.next == &tailSentinel)
		return true;
	else
		return false;
}

//Returns the size of the SLL
template<class T>
int DoublyLinkedList<T>::getSize(){
	return size;
}

//Outputs the nodes of the SLL
template<class T>
void DoublyLinkedList<T>::display(){
    node<T> *temp = new node<T>;
    temp = &headSentinel;
    while(temp!=NULL)
    {
      std::cout<<temp->data<<"\t";
      temp=temp->next;
    }
    std::cout<<std::endl;
} 

//Inserts a node at the specified index of the SLL
template<class T>
void DoublyLinkedList<T>::insertPosition(int pos, T value){
	if(pos < 0 || pos > (size-1))
		std::cout<<"Index out of range"<<std::endl;
	else{
		node<T> *temp = new node<T>;
		temp->data = value;
		if(isEmpty()){
			temp->previous = &headSentinel;
			temp->next = &tailSentinel;
			headSentinel.next = temp;
			tailSentinel.previous = temp;
		}
		else{
			node<T> *previous = new node<T>;
			node<T> *current = new node<T>;
			if(pos <= (size-2)/2){
				current = &headSentinel;
				for(int i=0; i<=pos; i++){
					previous = current;
					current = current->next;
				}
				previous->next = temp;
				temp->next = current;
				temp->previous = previous;
				current->previous = temp;
			}
			else{
				node<T> *next = new node<T>;
				current = &tailSentinel;
				for(int i=size-2; i>=pos; i--){
					previous = current->previous;
					current = previous;
					previous = current->previous;
					next = current->next;
				}
				temp->next = current->next;
				current->next = temp;
				temp->previous = current;
				next->previous = temp;
			}

		}
		size++;
	}
}

//Adds a node at the head of the SLL
template<class T>
void DoublyLinkedList<T>::addFirst(T value){
	insertPosition(0, value);
}

//Adds a node at the tail of the SLL
template<class T>
void DoublyLinkedList<T>::addLast(T value){
	insertPosition(size-2, value);
}

//Removes a node at the specified index of the SLL
template<class T>
void DoublyLinkedList<T>::removePosition(int pos){
	if(pos<0 || pos > (size-1))
		std::cout<<"Index out of range"<<std::endl;
	else{
		if(isEmpty()){
			//do nothing
		}
		else{
			node<T> *temp = new node<T>;
			node<T> *previous = new node<T>;
			node<T> *current = new node<T>;
			if(pos < (size-2)/2){
				current = &headSentinel;
				for(int i=0; i<= pos; i++){
					previous = current;
					current = current->next;
					temp = current->next;
				}
				previous->next = temp;
				temp->previous = previous;
				delete current;
			}
			else{
				current = &tailSentinel;
				for(int i=0; i<=(size-pos-2); i++){
					previous = current->previous;
					current = previous;
					previous = previous->previous;
				}
				temp = current->next;
				previous->next = temp;
				temp->previous = previous;
				delete current;

			}
		size--;
		}

	}
}

//Removes the head of the SLL
template<class T>
void DoublyLinkedList<T>::removeFirst(){
	removePosition(0);
}

//Removes the tail of the SLL
template<class T>
void DoublyLinkedList<T>::removeLast(){
	removePosition(size-2);
}

#endif //SLL_H