/*
 * This code reads in data from input file hw4input.txt and prints appropriate information with necessary format
 * to 2 output files hw4time.txt and hw4money.txt.
 *
 *  Created on: Apr 28, 2015
 *      Author: Trung Dang
 */

#include <stdio.h>
#include <string.h>

#define MAX_CUSTOMERS 20
#define MAX_ITEMS 10
#define MAX_NAME_LENGTH 29

struct item_tag {
	char name[MAX_ITEMS + 1];
	int quantity;
	double price;
	double totalValue;
};

typedef struct item_tag Item;

struct customer_tag{
	char name[MAX_NAME_LENGTH + 1];
	Item items[MAX_ITEMS];
	int itemCount;
	double totalOrder;
};

typedef struct customer_tag Customer;

/**************************************************************************************************/
/*
 * Search for the specified customer in the list of customers.
 *
 * Param:	*customer 	A list of customers
 * 			*name 		The name of the customer being searched
 * Return:	A pointer that points to the matching customer or
 * 			NULL if there are no matching customer.
 */
Customer *searchCustomer(Customer *customer, char *name) {
	Customer *p = &customer[0];
	while (p < &customer[MAX_CUSTOMERS]) {
		if (strcmp(name, (*p).name) == 0) {
			return p;
		}
		p++;
	}
	return NULL;
}
/**************************************************************************************************/
/*
 * Search for the specified item in a customer's item list.
 *
 * Param:	customer 	A customer
 * 			*name 		The name of the item being searched
 * Return:	A pointer that points to the matching item or
 * 			NULL if there are no matching item.
 */
Item *searchItem(Customer customer, char *name) {
	Item *p = &customer.items[0];
	while (p < &customer.items[customer.itemCount]) {
		if (strcmp((*p).name, name) == 0) {
			return p;
		}
		p++;
	}
	return NULL;
}
/**************************************************************************************************/
/*
 * Create hw4time.txt output file with the necessary format.
 *
 * Param:	*output			The output file
 * 			*customers 		A list of customers
 * 			*customersCount	The current number of customers in the list.
 */
void createTimeOutput(FILE *output, Customer *customers, int *customersCount) {
	Customer *p = &customers[0];
	while (p < &customers[*customersCount]) {
		fprintf(output, "%s\n", (*p).name);
		int i;
		for (i = 0; i < (*p).itemCount; i++) {
			Item item = (*p).items[i];
			fprintf(output, "%s %d $%.2f\n", item.name, item.quantity, item.price);
		}
		p++;
		fprintf(output, "\n");
	}
}
/**************************************************************************************************/
/*
 * Create hw4money.txt output file with the necessary format.
 *
 * Param:	*output			The output file
 * 			*customers 		A list of customers
 * 			*customersCount	The current number of customers in the list.
 */
void createMoneyOutput(FILE *output, Customer *customers, int *customersCount) {
	int i, j;
	for (i = *customersCount - 1; i >= 0; i--) {
		Customer c = customers[i];
		fprintf(output, "%s, Total Order = $%.2f\n", c.name, c.totalOrder);
		for (j = c.itemCount - 1; j >= 0; j--) {
			Item item = c.items[j];
			fprintf(output, "%s %d $%.2f", item.name, item.quantity, item.price);
			fprintf(output, ", Item value = $%.2f\n", c.items[j].totalValue);
		}
		fprintf(output, "\n");
	}
}
/**************************************************************************************************/
/*
 * Read the input file and update number of customers or number of items if necessary.
 *
 * Param:	*input			The input file
 * 			*customers 		A list of customers
 * 			*customersCount	The current number of customers in the list.
 */
void readFile(FILE *input, Customer *customers, int *customersCount) {
	Customer c;
	Item item;
	while (fscanf(input, "%s %d %s %*c%lf", c.name, &item.quantity,
			item.name, &item.price) != EOF) {
		Customer *p = searchCustomer(customers, c.name);
		if (p != NULL) {
			Item *i = searchItem(*p, item.name);
			if (i != NULL) {
				(*i).quantity += item.quantity;
			} else {
				(*p).items[(*p).itemCount] = item;
				((*p).itemCount)++;
			}
		} else {
			c.itemCount = 0;
			c.items[0] = item;
			customers[*customersCount] = c;
			customers[*customersCount].itemCount++;
			(*customersCount)++;
		}
	}
}
/**************************************************************************************************/
/*
 * Calculate the total order and the total value of each item ordered of each customer.
 *
 * Param:	*customers 		A list of customers
 * 			*customersCount	The current number of customers in the list.
 */
void calculateTotal(Customer *customers, int *customersCount) {
	Customer *p = customers;
	int i;
	while (p < &customers[*customersCount]) {
		double total = 0;
		Customer c = *p;
		for(i = 0; i < c.itemCount; i++) {
			Item item = c.items[i];
			double temp = item.quantity * item.price;
			total += temp;
			(*p).items[i].totalValue = temp;
		}
		(*p).totalOrder = total;
		p++;
	}
}
/**************************************************************************************************/
/*
 * Swap 2 customers.
 *
 * Param:	*customers 	A list of customers
 * 			c1			The first customer
 * 			c2 			The second customer
 */
void swapCustomers(Customer *customers, int c1, int c2) {
	Customer temp = customers[c2];
	customers[c2] = customers[c1];
	customers[c1] = temp;
}
/**************************************************************************************************/
/*
 * Sort the items list of each customer
 * based on the items' total value (price x quantity).
 *
 * Param:	*items 		A list of items for a customer
 * 			*itemCount	The current number of items in the list.
 */
void sortItems(Item *items, int *itemCount) {
	int j, k;
	for (j = 0; j < *itemCount; j++) {
		for (k = 0; k < *itemCount - 1; k++) {
			if (items[k].totalValue > items[k + 1].totalValue) {
				Item temp = items[ k + 1];
				items[k + 1] = items[k];
				items[k] = temp;
			}
		}
	}
}
/**************************************************************************************************/
/*
 * Sort the customers list based on their total order and sort the items list of each customer
 * based on the items' total value (price x quantity).
 *
 * Param:	*customers 		A list of customers
 * 			*customersCount	The current number of customers in the list.
 */
void sortCustomers(Customer *customers, int *customersCount) {
	int i, j;
	for (i = 0; i < (*customersCount); i++) {
		for (j = 0; j < (*customersCount) - 1; j++) {
			sortItems(customers[i].items, &customers[i].itemCount);
			if (customers[j].totalOrder > customers[j + 1].totalOrder) {
				swapCustomers(customers, j, j + 1);
			}
		}
	}
}
/**************************************************************************************************/
int main(void) {
	setvbuf(stdout, NULL, _IONBF, 0);
	FILE* input = fopen("hw4input.txt", "r");
	if (input == NULL) {
		printf("Can't open file!\n");
		return 1;
	}
	FILE* timeOutput = fopen("hw4time.txt", "w");
	FILE* moneyOutput = fopen("hw4money.txt", "w");
	Customer customers[20];
	int customersCount = 0;

	readFile(input, customers, &customersCount);

	calculateTotal(customers, &customersCount);

	createTimeOutput(timeOutput, customers, &customersCount);

	sortCustomers(customers, &customersCount);

	createMoneyOutput(moneyOutput, customers, &customersCount);

	return 0;
}

