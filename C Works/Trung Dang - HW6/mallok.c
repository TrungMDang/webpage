/*
 * mallok.c
 *
 *  Created on: May 28, 2015
 *      Author: Trung Dang
 */

#include "mallok.h"
#include <stdio.h>
#include <stdlib.h>

static Node *myHeapList = NULL;
static char *myPrivateHeap = NULL;
static int mySize;
static int capacityLeft;


void printList() {
	Node *curr = myHeapList;
	while (curr != NULL) {
		printf("Node address    Size    Status    Heap address"
				"\n %p     %d      %d       %p \n", curr, curr->size, curr->free, curr->address);
		curr = curr->next;
	}
}

void printListData() {
	//printf("Size: %d", mySize);
	//printf("Capacity left: %d", capacityLeft);
	Node *curr = myHeapList;
	printf("List data at heap address: \n");
	while (curr != NULL) {
		char *p = curr->address;
		printf("Node at %p: ", curr);
		int i = 0;
		while (p != NULL && i < curr->size) {
			printf("%c", *p);
			p++;
			i++;
		}
		curr = curr->next;
		printf("\n");
	}
}

void create_pool(int size) {
	mySize = size;
	capacityLeft = size;
	myPrivateHeap = (char *) malloc(size);
	if (myPrivateHeap == NULL) {
		printf("Memory allocation fails!\n");
		exit(0);
	} else {
		Node *newNode = malloc(sizeof(Node));
		newNode->size = size;
		newNode->address = myPrivateHeap;
		newNode->free = TRUE;
		newNode->next = NULL;
		myHeapList = newNode;
	}
}

void split(Node **node, int size) {
	capacityLeft = capacityLeft - size;
	if ((*node)->size == size) {
		(*node)->free = FALSE;
	} else {
		Node *newNode = malloc(sizeof(Node));
		newNode->free = TRUE;
		newNode->size = (*node)->size - size;
		newNode->next = NULL;
		(*node)->size = size;
		(*node)->free = FALSE;
		char *p = (*node)->address;
		int i = 0;
		while (i < size) {
			p++;
			i++;
		}
		newNode->address = p;
		newNode->next = (*node)->next;
		(*node)->next = newNode;
	}
}

void *my_malloc(int size) {
	if (size > mySize || size > capacityLeft) {
		return NULL;
	} else {
		Node *curr, *prev;
		for (curr = myHeapList, prev = NULL; (curr != NULL && curr->free == FALSE) || curr->size < size ; prev = curr, curr = curr->next) {
		}

		if (curr == NULL) { //the end of list
			if (prev->free == TRUE) {
				split(&prev, size);
			} else {
				exit(EXIT_FAILURE);
			}
		} else if (curr == myHeapList) {
			split(&myHeapList, size);
		} else {
			split(&curr, size);
		}
//		Node *curr1 = myHeapList;
//		while (curr1 != NULL) {
//			curr1 = curr1->next;
//		}
		//printf("Return pointer: %p\n", curr);
		return curr->address;
	}
}

void checkList() {
	Node *curr = myHeapList, *next = curr->next;
	while (curr != NULL && curr->free == FALSE) {
		curr = next;
		next = next->next;
	}
	if (next != NULL && next->free == TRUE) { //curr and next nodes are free
		curr->next = next->next;
		curr->size = curr->size + next->size;
		capacityLeft += next->size;
		free(next);
	}
}

void my_free(void *block) {
	Node *curr = myHeapList;
	while (curr != NULL){
		if (curr->address == ((Node *) block)->address) {
			break;
		}
		curr = curr->next;
	}
	if (curr == NULL) {		//can't find the block address (outside of heap)
		printf("Illegal argument. Pointer outside of heap.\n");
	} else {
		curr->free = TRUE;
		capacityLeft += curr->size;
		checkList();
	}
}


