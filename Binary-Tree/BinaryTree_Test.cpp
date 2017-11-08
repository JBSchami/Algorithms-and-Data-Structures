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

#include <iostream>
#include "BinaryTree.h"

using namespace std;

int main(int argc, char* argv[]){
	BinaryTree<int> *myBTree = new BinaryTree<int>;

	myBTree->insert(23, myBTree->getRoot());
	myBTree->insert(45, myBTree->getRoot());
	myBTree->insert(11, myBTree->getRoot());
	myBTree->insert(18, myBTree->getRoot());
	myBTree->insert(78, myBTree->getRoot());
	myBTree->insert(65, myBTree->getRoot());

	BinaryTree<int>::Node* searchNode = myBTree->binarySearch(23, myBTree->getRoot());
	searchNode->print();
	searchNode = myBTree->binarySearch(45, myBTree->getRoot());
	searchNode->print();
	searchNode = myBTree->binarySearch(11, myBTree->getRoot());
	searchNode->print();
	searchNode = myBTree->binarySearch(18, myBTree->getRoot());
	searchNode->print();
	searchNode = myBTree->binarySearch(78, myBTree->getRoot());
	searchNode->print();
	searchNode = myBTree->binarySearch(65, myBTree->getRoot());
	searchNode->print();

}