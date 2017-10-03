/************************************************************ 
 *  @file    	SLL.h
 *  @author  	Jonathan Bedard Schami
 *  @date    	02/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Singly Linked List Implementation
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains my implementation of a C++ sinlgy
 *	linked list ADT.
 ************************************************************/

#ifndef SLL_H
#define SLL_H

#include <stdexcept>
#include <iostream>

template<typename T>
struct node{
	T data;
	node *next;
};


//C++ Template for a singly linked list ADT
template<class T>
class SinglyLinkedList {
	private:
		node<T> *head;
		node<T> *tail;
		int size;
	public:
		SinglyLinkedList(){head = NULL; tail = NULL; size = 0;}

		bool isEmpty();
		int getSize();
		void display();
		
		void addFirst(T value);
		void addLast(T value);
		
		void removeFirst();
		void removeLast();

		void insertPosition(int pos, T value);
		void removePosition(int pos);

		T getFirst();
		T getLast();
};

//Checks if the SLL is empty
template<class T>
bool SinglyLinkedList<T>::isEmpty(){
	if(head == NULL)
		return true;
	else
		return false;
}

//Returns the size of the SLL
template<class T>
int SinglyLinkedList<T>::getSize(){
	return size;
}

//Outputs the nodes of the SLL
template<class T>
void SinglyLinkedList<T>::display(){
    node<T> *temp = new node<T>;
    temp = head;
    while(temp!=NULL)
    {
      std::cout<<temp->data<<"\t";
      temp=temp->next;
    }
    std::cout<<std::endl;
} 

//Adds a node at the head of the SLL
template<class T>
void SinglyLinkedList<T>::addFirst(T value){
	node<T> *temp = new node<T>;
	temp->data = value;
	if(isEmpty()){
		temp->next = NULL;
		head = temp;
		tail = temp;
		temp = NULL;
	}
	else{
	temp->next = head;
	head = temp;
	}
	size++;
}

//Adds a node at the tail of the SLL
template<class T>
void SinglyLinkedList<T>::addLast(T value){
	node<T> *temp = new node<T>;
	temp->data = value;
	temp->next = NULL;
	if(isEmpty()){
		std::cout<<"Adding"<<std::endl;
		head = temp;
		tail = temp;
		temp = NULL;
	}
	else{
		tail->next = temp;
		tail = temp;
	}
	size++;
}


//Removes the head of the SLL
template<class T>
void SinglyLinkedList<T>::removeFirst(){
	if(isEmpty()){
		//do nothing
	}
	else{
		node<T> *temp = head;
		head = head->next;
		delete temp;
		size--;
	}
}


//Removes the tail of the SLL
template<class T>
void SinglyLinkedList<T>::removeLast(){
	node<T> *current = new node<T>;
	node<T> *previous = new node<T>;
	current = head;
	while(current->next != NULL){
		previous = current;
		current = current->next;
	}
	tail = previous;
	previous->next = NULL;
	delete current;
	size--;
}


//Inserts a node at the specified index of the SLL
template<class T>
void SinglyLinkedList<T>::insertPosition(int pos, T value){
	if(pos < 0 || pos > (size-1))
		std::cout<<"Index out of range"<<std::endl;
	else{
		node<T> *previous = new node<T>;
		node<T> *current = new node<T>;
		node<T> *temp = new node<T>;
		current = head;
		if(pos == 0)
			addFirst(value);
		else{
			for(int i=0; i<pos; i++){
				previous = current;
				current = current->next;
			}
		}
		temp->data = value;
		previous->next = temp;
		temp->next = current;
		size++;
	}
}

//Removes a node at the specified index of the SLL
template<class T>
void SinglyLinkedList<T>::removePosition(int pos){
	if(pos<0 || pos > (size-1))
		std::cout<<"Index out of range"<<std::endl;
	else{
		node<T> *previous = new node<T>;
		node<T> *current = new node<T>;
		node<T> *temp = new node<T>;
		current = head;
		for(int i =0; i<pos; i++){
			previous = current;
			current = current->next;
		}
		temp = current;
		previous->next = current->next;
		delete temp;
		size--;
	}
}

//Returns the element at the head of the list
template<class T>
T SinglyLinkedList<T>::getFirst(){
	return head->data;
}

//Returns the element at the tail of the list
template<class T>
T SinglyLinkedList<T>::getLast(){
	return tail->data;
}


#endif //SLL_H