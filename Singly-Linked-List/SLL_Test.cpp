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
#include <vector>

using namespace std;

int main(int argc, char* argv[]){

	SinglyLinkedList<int>* myList = new SinglyLinkedList<int>;

	/* 	|| TEST CASES
	========================================================
	1	|| isEmpty() 		when list is empty
	12	|| isEmpty() 		when list is not empty
	2	|| getSize() 		when list is empty
	5	|| getSize()		when list is not empty
	23	|| display()		when list is empty
	6	|| display()		when list is not empty
	4	|| add(x)		
	13	|| addFirst(x)
	14	|| addAt(y, x)		when index is in range
	15	|| addAt(y, x)		when index is out of range
	19	|| remove()			when list has multiple nodes
	26	|| remove()			when list has single node
	27	|| remove()			when list is empty
	16	|| removeFirst()	when list has multiple nodes
	24	|| removeFirst()	when list has a single node
	25	|| removeFirst()	when list is empty
	17	|| removeAt(y)		when index is in range
	18	|| removeAt(y)		when index is out of range
	20	|| getHeadNode()
	21	|| getTailNode()
	8	|| contains(x)		when x is in the linked list
	9	|| contains(x)		when x is not in the linked list
	10	|| indexOf(x)		when x is in the linked list
	11	|| indexOf(x)		when x is not in the linked list
	7	|| toVector()
	22	|| clear()
	========================================================
	*/


//1
	cout << "myList->isEmpty(), should return 1 ...: " << myList->isEmpty() << endl;
//2
	cout << "myList->getSize(), should return 0 ...: "<< myList->getSize() << endl;
//3
	cout << "myList->add(25).......................: " << endl; myList->add(25);
	
//4
	cout << "myList->add(12).......................: " << endl; myList->add(12);
	cout << "myList->add(63).......................: " << endl; myList->add(63);
	cout << "myList->add(87).......................: " << endl; myList->add(87);
	cout << "myList->add(9)........................: " << endl; myList->add(9);

//5
	cout << "myList->getSize(), should return 5....: "<< myList->getSize() << endl;

//6
	cout << "myList->display().....................: "; myList->display();

//7
	cout << "myList->toVector(), when printed \n" 
			"should output 25 12 63 87 9 ..........: ";

	vector<int> myVector;
	myVector = myList->toVector();

	for(int i = 0; i < (int)myVector.size(); i++){
		cout<< myVector[i] << " ";
	}
	cout << endl;

//8
	cout << "myList->contains(26), should return 0 : " << myList->contains(26) << endl;
//9
	cout << "myList->contains(87), should return 1 : " << myList->contains(87) << endl;
//10
	cout << "myList->indexOf(63), should return 2 .: " << myList->indexOf(63) << endl;
//11
	cout << "myList->indexOf(99), should return -1 : " << myList->indexOf(99) << endl;	
//12
	cout << "myList->isEmpty(), should return 0 ...: " << myList->isEmpty() << endl;
//13
	cout << "myList->display().....................: "; myList->display();
	cout << "myList->addFirst(11), \n" 
			"should add 11 at front of list .......: "; myList->addFirst(11); myList->display();	
//14
	cout << "myList->addAt(2, 43), \n" 
			"should add 43 before 12 ..............: "; myList->addAt(2,43); myList->display();
//15
	cout << "myList->addAt(8, 10), \n"
			"outpus: Index out of range ...........: " ; myList->addAt(8,10);
//16
	cout << "myList->removeFirst() \n"
			"Should remove 11 .....................: "; myList->removeFirst(); myList->display();
//17
	cout << "myList->removeAt(4) \n"
			"Should remove 87 .....................: "; myList->removeAt(4); myList->display();
//18
	cout << "myList->removeAt(12) \n"
			"Index out of range ...................: "; myList->removeAt(12);
//19
	cout << "myList->remove() \n"
			"Should remove 9 ......................: "; myList->remove(); myList->display();
//20
	cout << "myList->getHeadNode() \n"
			"Should return 25 .....................: "; cout << myList->getHeadNode() << endl;
//21
	cout << "myList->getTailNode() \n"
			"Should return 63 .....................: ";	cout << myList->getTailNode() << endl;
//22
	cout << "myList->clear().......................: " << endl;	myList->clear();
//23	
	cout << "myList->display() \n"
			"Should output: Empty List ............: "; myList->display();
//24
	cout << "myList->add(25).......................: "; myList->add(25); myList->display();
	cout << "myList->removeFirst() \n"
			"Should remove 25 .....................: "; myList->removeFirst(); myList->display();
//25
	cout << "myList->removeFirst() \n"
			"nothing should hapen, empty list .....: "; myList->removeFirst(); myList->display();
//26
	cout << "myList->add(25).......................: "; myList->add(25); myList->display();
	cout << "myList->remove() \n"
			"Should remove 25 .....................: "; myList->removeFirst(); myList->display();
//27
	cout << "myList->remove() \n"
			"nothing should hapen, empty list .....: "; myList->removeFirst(); myList->display();

	delete myList;
}
