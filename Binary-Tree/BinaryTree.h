/************************************************************ 
 *  @file    	BinaryTree.h
 *  @author  	Jonathan Bedard Schami
 *  @date    	08/11/2017
 *  @version 	0.5 
 *  
 *  @brief 		Basic Binary Tree Implementation
 *
 *  @section 	DESCRIPTION
 *  
 *	This file contains my implementation of a  basic C++ 
 *	binary tree.
 ************************************************************/

#include <iostream>
#include <string>

#ifndef BINARYTREE_H
#define BINARYTREE_H

template<typename T>
class BinaryTree{
public:
	struct Node{
		Node *parent;
		Node *lChild;
		Node *rChild;
		T data;
		int depth;

		Node();
		void print();
	};

	BinaryTree(){root = NULL;}

	bool isEmpty();

	void insert(T value, Node* currentNode, int currentDepth = 0);

	Node* getRoot();

	Node* binarySearch(T value, Node* currentNode);

private:
	Node* root;

};


template<typename T>
BinaryTree<T>::Node::Node(){parent = NULL; lChild = NULL; rChild = NULL; data = T(); depth = 0;}

template<typename T>
void BinaryTree<T>::Node::print(){
	if(this!=NULL){
	std::cout << "NODE: " 		<< this 
			  << " | data: " 	<< this->data 
			  << " | parent: " 	<< this->parent 
			  << " | lChild: " 	<< this->lChild 
			  << " | rChild: " 	<< this->rChild
			  << " | depth: "	<< this->depth 
			  << std::endl;
	}
	else{
		std::cout << "Does not exist in binary tree" << std::endl;
	}

}

template <typename T>
bool BinaryTree<T>::isEmpty(){
	if(root == NULL){
		return true;
	}
	else{return false;}
}

template <typename T>
typename BinaryTree<T>::Node* BinaryTree<T>::getRoot(){ return root; }

template <typename T>
void BinaryTree<T>::insert(T data, Node* currentNode, int currentDepth){
	if(isEmpty()){
		root = new Node;
		root->data = data;
	}
	else{
		if(data >= currentNode->data){
			if(currentNode->rChild == NULL){
				Node *newNode = new Node;
				newNode->data = data;
				newNode->depth = currentNode->depth + 1;
				newNode->parent = currentNode;
				currentNode->rChild = newNode;
			} 
			else { this->insert(data, currentNode->rChild, ++currentDepth); }
		}
		else{
			if(currentNode->lChild == NULL){
				Node *newNode = new Node;
				newNode->data = data;
				newNode->depth = currentNode->depth + 1;
				newNode->parent = currentNode;
				currentNode->lChild = newNode;
			}
			else{ this->insert(data, currentNode->lChild, ++currentDepth); }
		}
	}
}

template <typename T>
typename BinaryTree<T>::Node* BinaryTree<T>::binarySearch(T value, Node* currentNode){
	if(isEmpty()){
		return NULL;
	}
	else if(currentNode == NULL){
		return NULL;
	}
	else{
		if(currentNode->data == value){return currentNode;}
		else if(currentNode->data < value){return this->binarySearch(value, currentNode->rChild);}
		else {return this->binarySearch(value, currentNode->lChild);}
	}
}


#endif //BINARYTREE_H