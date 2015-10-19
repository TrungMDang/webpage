/*
 * main.c
 *
 *  Created on: May 19, 2015
 *      Author: Trung Dang
 *
 * Read words from 2 files and output 50 words sorted based on their largest difference of their occurrences.
 */
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

#define File_1 1
#define File_2 2

/*********************************************************************************************************************
 * Get a word from the specified string. Only alphabetical characters, hyphens, and apostrophe are considered.
 *
 * Param:	char *string 	A pointer to the string to be extracted.
 */
char *getWord(char *string) {
	char *temp = NULL;
	temp = (char *) malloc(sizeof(string) + 1);
	char *p = string;
	int i = 0;
	for (p = string; *p != '\0'; p++) {
		if (*p == '\"') {
			continue;
		}
		else if (isalpha((int)*p) || *p == '-' || (int) *p == 39 || (int) *p == 96) {
			if (*p >= 'A' && *p <= 'Z') {
						*p = *p + ('a' - 'A');
			}
			temp[i] = *p;
		}
		i++;
	}
	temp[i + 1] = '\0';
	return temp;
}
/*********************************************************************************************************************
 * Read 2 specified files and build an ordered linked list of all the words in those files.
 *
 * Param:	Node *myList 	A pointer to the linked list to be built.
 * 			int *size		A pointer to the size of the linked list.
 * 			FILE *input_1	A pointer to the first file.
 * 			FILE *input_2	A pointer to the second file.
 */
Node *readFile(Node *myList, int *size, FILE *input_1, FILE *input_2) {
	char *string;
	while (fscanf(input_1, "%s", string) != EOF) {
		string = getWord(string);
		myList = insert(myList, size, string, File_1);
	}
	while (fscanf(input_2, "%s", string) != EOF) {
			string = getWord(string);
			myList = insert(myList, size, string, File_2);
	}
	return myList;
}

/*********************************************************************************************************************
 * Perform reading from 2 files, building a linked list of all the words that appear, sorting the list based on
 * the difference of occurrences of each word in each file, and printing the 50 words with largest difference.
 */
int main(void) {
	Node *myList = NULL;
	int size = 0;

	FILE *input_1 = fopen("LittleRegiment.txt", "r");
	FILE *input_2 = fopen("RedBadge.txt", "r");

	myList = readFile(myList, &size, input_1, input_2);
	printf("Size of list: %d\n", size);

	myList = sortList(&myList, size);

	printList(myList);

	return 0;
}
