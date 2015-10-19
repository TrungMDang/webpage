/*
 * main.c
 *
 *  Created on: Apr 21, 2015
 *      Author: Trung
 */

#include <stdio.h>
#include <string.h>

#define MAX_NAME_LENGTH 12
#define MAX_NUMBER_NAMES 10
#define MAX_NUMBER_YEARS 10

int searchName(char *name, char (*names)[MAX_NAME_LENGTH], int *namesCount) {
	int i;
	for (i = 0; i < *namesCount; i++) {
		if (strcmp(name, names[i]) == 0) {
			return i;
		}
	}
	return -1;
}

void clearArray(char *data) {
	char *p = &data[0];
	for (; p < &data[MAX_NAME_LENGTH + 1]; p++) {
		*p = 0;
	}
}

int findComma(char *data) {
	char *p = data;
	int index = 0;
	while (p < &data[MAX_NAME_LENGTH] && *p != ',') {
		index++;
		p++;
	}
	return index;
}
void printOutput(char (*names)[MAX_NAME_LENGTH], int *namesCount, int (*ranks)[MAX_NUMBER_YEARS]) {
	printf("Printing\n");
	FILE *summary = fopen("summary.csv", "w");
	if (summary != NULL) {
		int row, col1;
		char header[] = "Name, 1920, 1930, 1940, 1950, 1960, 1970, 1980, 1990, 2000, 2010";
		fprintf(summary, "%s\n", header);
		for (row = 0; row < *namesCount; row++) {
			fprintf(summary, "%s, ", names[row]);
			for (col1 = 0; col1 < MAX_NUMBER_YEARS; col1++) {
				int rank = ranks[row][col1];
				if (rank >= 1 && rank <= 100) {
					fprintf(summary, "%d, ", rank);
				} else {
					fprintf(summary, ", ");
				}
			}
			fprintf(summary, "\n");
		}
		fclose(summary);
	} else {
		printf("Error: Cannot read file!\n");
	}
}
void processOneName(char *name, char (*names)[MAX_NAME_LENGTH], int *namesCount, int (*ranks)[MAX_NUMBER_YEARS],
						int rank, int fileNumber) {

	int position = searchName(name, names, namesCount);
	//printf("Index of %s: %d\n", name, position);
	if (position < 0) {	//Does not have the name
		clearArray(names[*namesCount]);
		strncpy(names[*namesCount], name, strlen(name));

		printf("Name in names: %s\n", names[*namesCount]);

		ranks[*namesCount][fileNumber] = rank;
		printf("Rank: %d\n", ranks[*namesCount][fileNumber]);
		(*namesCount)++;
		printf("Names Count: %d\n", *namesCount);
	} else {									//Does have the name
		ranks[position][fileNumber] = rank;
		printf("HAS %s\n", names[position]);
	}
}
void readOneFile(FILE *input, char (*names)[MAX_NAME_LENGTH], int *namesCount, int (*ranks)[MAX_NUMBER_YEARS], int fileNumber) {
	char temp[MAX_NAME_LENGTH + 1];
	int rank, i;
	for (rank = 1; rank <= 100; rank++) {
		//printf("Ranks: %d ", rank);
		fgets(temp, 100, input);
		//printf("String: %s", temp);
		char name[MAX_NAME_LENGTH + 1];
		i = findComma(temp);
		//printf(" Comma at: %d", i);
		clearArray(name);
		strncpy(name, temp, i);
		//printf("Extracted name: %s\n", name);
		processOneName(name, names, namesCount, ranks, rank, fileNumber);
	}

}

void swap(char *names1, char *names2) {
	char *temp = names2;
	names2 = names1;
	names1 = temp;
}
void swapRank(int *rank1, int *rank2) {
	int *temp = rank1;
	rank1 = rank2;
	rank2 = temp;
}

void sort(char (*names)[MAX_NAME_LENGTH], int *namesCount, int (*ranks)[MAX_NUMBER_YEARS]) {
	int i, j;
	for (i = 0; i < *namesCount - 2; i++) {
		for (j = 0; i < *namesCount - 2 - i; j++) {
			if (strcmp(names[j+1], names[j]) < 0) {
				swap(names[j], names[j+1]);
				swapRank(ranks[j], ranks[j+1]);
			}
		}
	}


}
void readFiles(char (*names)[MAX_NAME_LENGTH], int *namesCount,
					int (*ranks)[MAX_NUMBER_YEARS]) {
	FILE *input1920 = fopen("yob1920.txt", "r");
	FILE *input1930 = fopen("yob1930.txt", "r");
	FILE *input1940 = fopen("yob1940.txt", "r");
	FILE *input1950 = fopen("yob1950.txt", "r");
	FILE *input1960 = fopen("yob1960.txt", "r");
	FILE *input1970 = fopen("yob1970.txt", "r");
	FILE *input1980 = fopen("yob1980.txt", "r");
	FILE *input1990 = fopen("yob1990.txt", "r");
	FILE *input2000 = fopen("yob2000.txt", "r");
	FILE *input2010 = fopen("yob2010.txt", "r");

	readOneFile(input1920, names, namesCount, ranks, 0);
	readOneFile(input1930, names, namesCount, ranks, 1);
	readOneFile(input1940, names, namesCount, ranks, 2);
	readOneFile(input1950, names, namesCount, ranks, 3);
	readOneFile(input1960, names, namesCount, ranks, 4);
	readOneFile(input1970, names, namesCount, ranks, 5);
	readOneFile(input1980, names, namesCount, ranks, 6);
	readOneFile(input1990, names, namesCount, ranks, 7);
	readOneFile(input2000, names, namesCount, ranks, 8);
	readOneFile(input2010, names, namesCount, ranks, 9);

	sort(names, namesCount, ranks);
	printOutput(names, namesCount, ranks);
}


int main(void) {

	setvbuf(stdout, NULL, _IONBF, 0);

	char names[MAX_NUMBER_NAMES][MAX_NAME_LENGTH];
	int ranks[MAX_NUMBER_NAMES][MAX_NUMBER_YEARS];

	int namesCount = 0;

	readFiles(names, &namesCount, ranks);

	return 0;
}
