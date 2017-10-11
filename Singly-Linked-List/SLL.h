/************************************************************ 
 *  @file    	SLL.h
 *  @author  	Jonathan Bedard Schami
 *  @date    	06/10/2017  
 *  @version 	1.0 
 *  
 *  @brief 		Singly Linked List Implementation
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains my implementation of a C++ sinlgy
 *	linked list ADT.
 ************************************************************/

//Testing the Git branch feature before making changes

#ifndef SLL_H
#define SLL_H

#include <iostream>
#include <vector>

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
		SinglyLinkedList(){head = NULL; tail = NULL; size = 0;} //default constructor
		~SinglyLinkedList(){clear();} //destructor, ensures memory cleanup. 

		bool isEmpty();
		int getSize();
		void display();
		
		void add(T value); //change to add
		void addFirst(T value);	
		void addAt(int pos, T value); //change to insertAt
		//void addBefore(int pos, T value);
		//void addAfter(int pos, T value);
		
		void remove();
		void removeFirst();
		void removeAt(int pos); //change to removeAt

		//int find(T value);
		//T findAt(int pos);
		//void forEach(fn);

		T getHeadNode();
		T getTailNode();

		bool contains(T value);
		int indexOf(T value);

		std::vector<T> toVector();
		
		void clear();

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
int SinglyLinkedList<T>::getSize(){return size;}

//Outputs the nodes of the SLL
template<class T>
void SinglyLinkedList<T>::display(){
    if(isEmpty()){
    	std::cout << "Empty list" << std::endl;
    }
    else{
	    node<T> *temp = new node<T>;
	    temp = head;
	    while(temp!=NULL)
	    {
	      std::cout<<temp->data<<"\t";
	      temp=temp->next;
	    }
	    std::cout<<std::endl;
	}
} 

//Adds a node at the tail of the SLL
template<class T>
void SinglyLinkedList<T>::add(T value){
	node<T> *temp = new node<T>;
	temp->data = value;
	temp->next = NULL;
	if(isEmpty()){
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

//Inserts a node at the specified index of the SLL
template<class T>
void SinglyLinkedList<T>::addAt(int pos, T value){
	if((size-1) < pos){
		std::cout<<"Index out of range"<<std::endl;
	}
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

//Removes the tail of the SLL
template<class T>
void SinglyLinkedList<T>::remove(){
	if(isEmpty()){
		//do nothing
	}
	else if(head == tail){
		head = NULL;
		tail = NULL;
	}
	else{
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

//Removes a node at the specified index of the SLL
template<class T>
void SinglyLinkedList<T>::removeAt(int pos){
	if(isEmpty()){
		//do nothing
	}
	else if(pos<0 || pos > (size-1))
		std::cout<<"Index out of range" << std::endl;
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
T SinglyLinkedList<T>::getHeadNode(){return head->data;}

//Returns the element at the tail of the list
template<class T>
T SinglyLinkedList<T>::getTailNode(){return tail->data;}

template<class T>
bool SinglyLinkedList<T>::contains(T value){
	if(isEmpty()){
		return false;
	}
	else{
		node<T> *temp = new node<T>;
		temp = head;
		if(temp->data == value)
			return true;
		else{
			while(temp->next != NULL){
				if(temp->data == value)
					return true;
				else{
					temp = temp->next;
				}
			}
			return false;
		}
	}
}

template<class T>
int SinglyLinkedList<T>::indexOf(T value){
	node<T> *temp = new node<T>;
	int index = 0;
	if(isEmpty()){
		return -1;
	}
	else{
		temp = head;
		if(head->data == value){ //handles case where there is only one node
			return index;
		}
		else{
			while(temp->next != NULL){
				if (temp->data == value){
					return index;
				}
				else if(temp->next == tail){//handles case where the next node is the tail
					if(tail->data == value){
						return index + 1;
					}
					else
						temp = temp->next;
				}
				else{
					temp = temp->next;
					index++;
				}
			}
		}
	}
	return -1;
}

template<class T>
std::vector<T> SinglyLinkedList<T>::toVector(){
	std::vector<T> newVector;
	node<T> *temp = new node<T>;
	temp = head;
	for(int i = 0; i<size; i++){
		newVector.push_back(temp->data);
		temp = temp->next;
	}
	return newVector;
}

template<class T>
void SinglyLinkedList<T>::clear(){
	if(isEmpty()){
		//doNothing
	}
	else{
		while(head != tail){
			node<T> *temp = new node<T>;
			temp = head;
			head = head->next;
			delete temp;
			size--;
		}
		head = NULL;
		tail = NULL;
		size--;
	}
}

#endif //SLL_H