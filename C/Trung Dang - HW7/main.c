///*
// * main.c
// *
// *  Created on: Jun 4, 2015
// *      Author: Trung Dang
// */
//
#include <stdio.h>
#include <string.h>
//
//typedef union union_tag {
//	float f;
//	unsigned int i;
//} Union;
//
//void print32Bits(Union u, char **exponent, char **fraction, int *value, int *sign) {
//	int i;
//	printf("Your float in 32 bits: ");
//		for (i = 0; i < 32; i++) {
//			if (u.i & 0x80000000) {
//				if (i == 0) *sign = 1;
//				else if (i == 1) {
//					*value *= 2;				//Part of conversion from binary to float
//					*value += 1;
//					strcpy(*exponent, "1");	//eliminate garbage in the uninitialized string
//				} else if (i > 1 && i < 9) {
//					strcat(*exponent, "1");
//					*value *= 2;
//					*value += 1;
//				} else {
//					if (i == 9) strcpy(*fraction, "1");
//					else strcat(*fraction, "1");
//				}
//				printf("1");
//			} else {
//				if (i == 0) *sign = 0;
//				else if (i == 1) {
//					*value *= 2;				//Part of conversion from binary to float
//					strcpy(*exponent, "0");	//eliminate garbage in the uninitialized string
//				} else if ( i < 9 && i > 1) {
//					strcat(*exponent, "0");
//					*value *= 2;
//				} else {
//					if (i == 9) strcpy(*fraction, "0");
//					else strcat(*fraction, "0");
//				}
//				printf("0");
//			}
//			u.i = u.i << 1;
//		}
//}
//void createFraction(Union u, float *base) {
//	int i;
//	float powerOf2 = 0.5;
//	printf("Creating the fraction: \n");
//	*base = 1.0;
//		for (i = 0; i < 32; i++) {
//			if (i < 9) {
//				if (i == 0) printf("fraction = %f (the implicit 1)\n", *base);
//				u.i = u.i << 1;
//			} else {
//				if (u.i & 0x80000000) {
//					*base += powerOf2;
//					printf("fraction = %f, after adding %f\n", *base, powerOf2);
//				} else printf("fraction = %f, after skipping %f\n", *base, powerOf2);
//				u.i = u.i << 1;
//				powerOf2 /= 2;
//			}
//		}
//		printf("\n");
//}
//
//void createExponent(Union u, int *value, float *base) {
//	int j = 0, unbiasedExpo = *value - 127;
//	printf("Applying the exponent:\n"
//			"Unbiased exponent = %d\n", unbiasedExpo);
//	while (j != unbiasedExpo) {
//		if (unbiasedExpo >= 0) {
//			*base *= 2;
//			printf("times 2 = %f\n", *base);
//		}
//		else {
//			*base /= 2;
//			printf("divided by 2 = %f\n", *base);
//		}
//		if (unbiasedExpo < 0) j--;
//		else
//			j++;
//	}
//}
//int main(void) {
//	setvbuf(stdout, NULL, _IONBF, 0);
//	Union u;
//	printf("Enter a float: \n");
//	scanf("%f", &(u.f));
//	printf("Your float was read as: %f\n", u.f);
//	int sign, value = 0;
//	char *exponent, *fraction;
//	char string[50], string1[50];
//	exponent = string;
//	fraction = string1;
//
//	print32Bits(u, &exponent, &fraction, &value, &sign);
//
//	printf("\nSign: %d\n", sign);
//	printf("Exponent: %s\n", exponent);
//	printf("Fraction: %s\n\n", fraction);
//
//	float base;
//
//	createFraction(u, &base);
//
//	createExponent(u, &value, &base);
//
//	if (u.f < 0)
//		printf("\nFinal Answer:\t-%f\n", base);
//	else printf("\nFinal Answer:\t %f\n", base);
//	return 0;
//}
//
//

int main(void) {


    int i =0xF0F0F0F0;
    printf("%d\n", i);
    printf("%x\n", i);
    // turn on 5th bit from the left
    i = i | 0x08000000 ;
    printf("%x\n", i);

    i = i & 0xFFFFFF9F ;
    printf("%x\n", i);
    if (i & 0x00008000   ) {
       printf("ON\n");
    } else {
       printf("OFF\n");
    }

    int k;
    int savedi = i;
    for (k = 0; k < 32; k++) {
       if (i & 0x80000000   ) {
          printf("1");
       } else {
          printf("0");
       }
       i = i << 1;
    }
    printf("\n");
    printf("Revi:\n");
    int revi=0;
    for (k = 0; k < 32; k++) {
           if (revi & 0x80000000   ) {
              printf("1");
           } else {
              printf("0");
           }
           revi = revi << 1;
        }
     printf("\n");
    i = savedi;
    for (k = 0; k < 32; k++) {
       revi = revi >> 1;
       revi = revi & 0x7FFFFFFF;
       if (i & 0x80000000   ) {
          // it's a 1!
	   revi = revi | 0x80000000;
       }
       i = i << 1;

    }
    printf("\n");
    i = revi;
    for (k = 0; k < 32; k++) {
       if (i & 0x80000000   ) {
          printf("1");
       } else {
          printf("0");
       }
       i = i << 1;
    }
    printf("\n");

}

