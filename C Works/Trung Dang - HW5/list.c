/*
 * list.c
 *
 *  Created on: May 19, 2015
 *      Author: Trung Dang
 *
 *  Contains necessary functions to insert, sort, and print a particular linked list.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

/*********************************************************************************************************************
 * Update the counts and the difference of the word  in struct pointed to by node.
 *
 * Param:	int fileNum 	The number of file. 1 for first file. 2 for second file.
 * 			Node **node			The pointer to the pointer that points to the node containing the word.
 */
void updateDifference(int fileNum, Node **node ) {
	if (fileNum == 1) {
		(*node)->count_1++;
	} else {
		(*node)->count_2++;
	}
	(*node)->difference = abs((*node)->count_1 - (*node)->count_2);
}
/*********************************************************************************************************************
 * Search for the word pointer to by string from the list beginning with front.
 *
 * Param:	Node *front 	A pointer to the first element of the linked list.
 * 			char *string	A pointer to the word to be searched.
 * Return:	The node that contains word that precedes the proper location of this word or if word is already
 * in the list the node that contains this word.
 */
Node *searchWord(Node *front, char *string) {
	Node *current, *previous;
	for (current = front, previous = NULL;
			current != NULL && (strcmp(current->word, string) < 0);
			previous = current, current = current->next) ;

	if (current != NULL && strcmp(current->word, string) == 0) {
		return current;
	}
	return previous;
}
/*
 * Insert the given word in the linked list in alphabetical order.
 *
 * Param:	Node *front 	A pointer to the first element of the linked list.
 * 			int *size		A pointer to the size of the linked list.
 * 			char *string	A pointer to the word to be inserted.
 * 			int fileNumt	Indicate the file. 1 for first file. 2 for second file.
 * Return:	A new pointer to the new linked list.
 */
Node *insert(Node *front, int *size, char *string, int fileNum) {
	Node *newNode = malloc(sizeof(Node));
	strcpy(newNode->word,string);
	newNode->count_1 = 0;
	newNode->count_2 = 0;
	newNode->difference = 0;
	newNode->next = NULL;
	if (front == NULL) {
		updateDifference(fileNum, &newNode);
		front = newNode;
		(*size)++;
	} else {
		Node *current, *previous;
		for (current = front, previous = NULL;
				current != NULL && (strcmp(newNode->word, current->word) > 0);
				previous = current, current = current->next) ;
		if (current != NULL && strcmp(current->word, newNode->word) == 0) { //Contains the word, update its counts
			updateDifference(fileNum, &current);
		} else {
			newNode->next = current;
			if (previous == NULL ){
				updateDifference(fileNum, &newNode);
				front = newNode;
			} else {
				previous->next = newNode;
				updateDifference(fileNum, &previous->next);
			}
			(*size)++;
		}
	}
	return front;
}
/*
 * Sort the linked list using bubble sort. Swapping data only.
 *
 * Param:	Node **list		An address of the pointer to the list to be sorted.
 * 			int size		The size of the linked list.
 * Return:	A pointer to the newly sorted list.
 */
Node *sortList(Node **list, int size) {
	Node *current;
	int i, j;
	for (i = 0; i < size; i++) {
		current = *list;
		for (j = 1; j < size; j++) {
			if (current->difference < current->next->difference) {
				int temp = current->difference;
				current->difference = current->next->difference;
				current->next->difference = temp;

				temp = current->count_1;
				current->count_1 = current->next->count_1;
				current->next->count_1 = temp;

				temp = current->count_2;
				current->count_2 = current->next->count_2;
				current->next->count_2 = temp;
				char *tempChar;
				strcpy(tempChar, current->word);
				strcpy(current->word, current->next->word);
				strcpy(current->next->word, tempChar);
			}
			current = current->next;
		}
	}
	return *list;
}
/*
 * Print 50 words that have the largest difference of their occurrences.
 *
 * Param:	Node *front		A pointer to the linked list to be printed.
 */
void printList(Node *front) {
	printf("List:\n");
	Node *current = front;
	int i = 1;
	while ( current != NULL && i <= 50  ) {
		printf("%d - %s | %d %d | %d\n", i, current->word, current->count_1, current->count_2, current->difference);
		current = current->next;
		i++;
	}
}

