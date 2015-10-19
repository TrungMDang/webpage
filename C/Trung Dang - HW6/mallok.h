/*
 * mallok.h
 *
 *  Created on: May 28, 2015
 *      Author: Trung
 */

#ifndef MALLOK_H_
#define MALLOK_H_

#define TRUE 1
#define FALSE 0

typedef struct linked_node {
	char *address;
	int size;
	int free;
	struct linked_node *next;
} Node;

void create_pool(int size);
void *my_malloc(int size);
void my_free(void *block);
void printList(void);
void printListData(void);

#endif /* MALLOK_H_ */
