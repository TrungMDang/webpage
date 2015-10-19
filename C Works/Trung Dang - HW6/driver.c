/*
 * Driver.c
 *
 *  Created on: May 28, 2015
 *      Author: Trung Dang
 *
 * Performed tests with various schemes.
 * Function names correspond to test cases described in assignment specification.
 */

#include "mallok.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void test_1() {
	printf("Test 1------------------------------------------------------------\n");
	create_pool(1000);
	int i = 0;
	while (1) {
		Node *new = my_malloc(10);
		if (new == NULL) break;
		i++;
	}
	printf("Successfully requested %d blocks of size 10 bytes out of 1000 bytes\n", i);
	printf("End of Test 1------------------------------------------------------------\n");

}
int test_2Helper(Node *nodes[]) {
	int i = 0, success = 1;
	for (; i < 5; i++) {
		nodes[i] = (Node *) my_malloc(200);
		if (nodes[i] == NULL) {
			printf("Memory allocation failed.\n");
			success = FALSE;
		}
	}
	if (success == TRUE) {
		printf("Memory allocation succeeded...\n");
	}
	for (i = 0; i < 5; i++) {
		my_free(nodes[i]);
	}
	printf("Memory deallocated. List after:\n");
	printList();
	printf("\n");
	return success;
}
void test_2() {
	printf("Test 2------------------------------------------------------------\n");
	create_pool(1000);
	Node *nodePtrs[5];
	int success = 1;
	success = test_2Helper(nodePtrs);
	success = test_2Helper(nodePtrs);
	success = test_2Helper(nodePtrs);
	success = test_2Helper(nodePtrs);
	if (success == TRUE) {
		printf("Successfully allocated and deallocated 5 blocks of size 200 bytes.\n");
	} else {
		printf("Allocating and Deallocating 5 blocks of size 200 bytes failed.\n");
	}
	printf("End of Test 2------------------------------------------------------------\n");

}

void test_3() {
	printf("Test 3------------------------------------------------------------\n");
	create_pool(1000);
	Node *nodePtrs[5];
	int i, success = TRUE;
	for (i = 0; i < 5; i++) {
		Node *newNode = (Node *) my_malloc(200);
		nodePtrs[i] = newNode;
		if (nodePtrs[i] == NULL) {
			printf("Memory allocation failed for size 200.\n");
			success = FALSE;
		}
	}
	my_free(nodePtrs[2]);
	printf("Middle block deallocated...\n");
	success = TRUE;
	if (my_malloc(210) == NULL) {
		printf("Memory allocation failed for block of size 210 bytes (expected).\n");
	} else {
		printf("Memory allocation succeeded for block of size 210 bytes.\n");
		success = FALSE;
	}
	if (my_malloc(150) == NULL) {
		printf("Memory allocation failed for block of size 150 bytes.\n");
		success = FALSE;
	} else {
		printf("Memory allocation succeeded for block of size 150 bytes (expected).\n");

	}
	if (my_malloc(60) == NULL) {
		printf("Memory allocation failed for block of size 60 bytes (expected).\n");
	} else {
		printf("Memory allocation succeeded for block of size 60 bytes.\n");
		success = FALSE;
	}

	if (my_malloc(50) == NULL) {
		printf("Memory allocation failed for block of size 50 bytes.\n");
		success = FALSE;
	} else {
		printf("Memory allocation succeeded for block of size 50 bytes (expected).\n");
	}

	if (success == FALSE) {
		printf("Test 3 failed!\n");
	} else {
		printf("Successfully requested blocks of different sizes.\n");
	}
	printf("End of Test 3------------------------------------------------------------\n");

}
int verifyBlock(Node *node, char character) {
	char *p = node->address;
	int i = 0;
	while (p != NULL && i < node->size) {
		if (*p != character) {
			return FALSE;
		}
		p++;
		i++;
	}
	return TRUE;
}
void fillBlock(Node **node, char character) {
	char *p = (*node)->address;
	while (p != NULL) {
		*p = character;
		p++;
	}
}
void test_4() {
	printf("Test 4------------------------------------------------------------\n");
	create_pool(1000);
	Node *nodePtrs[5];
	char chars[] = {'A', 'B', 'C', 'D', 'E'};
	int i = 0, success = TRUE;
	for (; i < 5; i++) {
		nodePtrs[i] = (Node *) my_malloc(200);
		if (nodePtrs[i] == NULL) {
			printf("Memory allocation failed.\n");
			success = FALSE;
		}
	}
	for (i = 0; i < 5; i++) {
		char *p = nodePtrs[i]->address;
		int j = 0;
		while (p != NULL) {
			if (j < nodePtrs[i]->size) {
				*p = chars[i];
				p++;
				j++;
			} else break;
		}
	}
	printList();
	//printListData();
	for (i = 0; i < 5; i++) {
		success = verifyBlock(nodePtrs[i], chars[i]);
	}
	if (success == TRUE) {
		printf("Successfully filled blocks' contents and verified them.\n");
	} else {
		printf("Filling blocks' contents and verifying failed.\n");
	}
	printf("End of Test 4------------------------------------------------------------\n");

}

void test_5() {
	printf("Test 5------------------------------------------------------------\n");
	create_pool(1000);
	int success = TRUE, i = 0;
	Node *node = my_malloc(1000);
	//printList();
	if (node == NULL) {
		success = FALSE;
	}
	printf("%d\n", success);
	my_free(node);
	Node *nodePtrs[4];
	for (; i < 4; i++) {
		nodePtrs[i] = my_malloc(250);
		if (nodePtrs[i] == NULL) {
			success = FALSE;
		}
	}
	printList();
	printf("%d\n", success);

	for (i = 0; i < 4; i++) {
		my_free(nodePtrs[i]);
	}
	Node *nodePtrs1[10];
	for (i = 0; i < 10; i++) {
			nodePtrs1[i] = my_malloc(100);
			if (nodePtrs1[i] == NULL) {
				success = FALSE;
			}
		}
	printList();
	printf("%d\n", success);

		for (i = 0; i < 10; i++) {
			my_free(nodePtrs1[i]);
		}
	if (success == FALSE) {
		printf("Requesting and returning blocks of different sizes failed.\n");
	} else {
		printf("Successfully requested and returned blocks of different sizes.\n");
	}
	printf("End of Test 5------------------------------------------------------------\n");
	printf("\n");

}
//int main() {
//	printf("NOTE: %d for used block. %d for free blocks.\n", FALSE, TRUE);
//	printf("Begin testing...\n");
//	printf("\n");
//	test_1();
//	printf("\n");
//	test_2();
//	printf("\n");
//	test_3();
//	printf("\n");
//	test_4();
//	printf("\n");
//	test_5();
//	printf("End testing...\n");
//	return 0;
//}
