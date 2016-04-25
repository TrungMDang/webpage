/*
 * list.h
 *
 *  Created on: May 19, 2015
 *      Author: Trung Dang
 *
 *  Define the necessary functions of implementation list.c.
 */

#ifndef LIST_H
#define LIST_H
#define MAX_LENGTH 50

typedef struct linked_node {
	char word[MAX_LENGTH + 1];
	int count_1;
	int count_2;
	int difference;
	struct linked_node *next;
} Node;

Node *insert(Node *front, int *size, char *string, int fileNum);

Node *sortList(Node **list, int size);

void printList(Node *front);

#endif /* LIST_H */
