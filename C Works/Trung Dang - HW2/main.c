/*
 * main.c
 *
 *  Created on: Apr 14, 2015
 *  Author: Trung Dang
 *  Version: 1.0
 *
 *  This program reads in two bitmap image files and transforms them in two ways:
 *  	- blending them by computing the average of each component RGB of two files' pixels' data.
 *      - altenating two files to create a 8x8 checker board.
 *
 */

#include <stdio.h>

/*
 * Read the input file and appropriate sections of its header.
 * The header will be cut into 4 variables and 8 sections that contain unused header data.
 *
 * Param:	*input		The input file
 * 			header1		Store 2 bytes of bitmap ID
 * 			header2		Store 12 bytes of data
 *			header3		Store 8 bytes of data
 *			header4 	Store 16 bytes of data
 *			*file_size	The size of file (header + pixel data)
 *			*image_size	The size of pixel data (width * height * 3)
 *			*width		The width in pixels
 *			*height		The height in pixels
 */
void readInputFile(FILE *input, char header1[2], char header2[12],
		char header3[8], char header4[16], int *file_size, int *image_size,
		int *width, int *height) {
	fread(header1, 1, 2, input); 		//Identify bitmap
	fread(file_size, 4, 1, input);		//File size
	fread(header2, 1, 12, input);
	fread(width, 4, 1, input);			//width
	fread(height, 4, 1, input);			//height
	fread(header3, 1, 8, input);
	fread(image_size, sizeof(int), 1, input);	//image size
	fread(header4, 1, 16, input);

}

/*
 * Read the pixel data of the input file.
 *
 * Param:	*input		The input file
 *			*width		The width in pixels
 *			*height		The height in pixels
 *			pixels		Store the pixel data after function ends (Possible value: 0 to 255).
 */
void readPixels(FILE *input, int *width, int *height,
		unsigned char pixels[][*width * 3]) {
	fread(pixels, 1, *width * (*height) * 3, input);
}

/*
 * Print the data to the output file.
 *
 * Param:	*output		The output file
 * 			header1		Store 2 bytes of bitmap ID
 * 			header2		Store 12 bytes of data
 *			header3		Store 8 bytes of data
 *			header4 	Store 16 bytes of data
 *			*file_size	The size of file (header + pixel data)
 *			*image_size	The size of pixel data (width * height * 3)
 *			*width		The width in pixels
 *			*height		The height in pixels
 *			data		The data to be printed out. Possible value 0 to 255.
 */
void printOutput(FILE *output, char header1[2], char header2[12],
		char header3[8], char header4[16], int *file_size, int *image_size,
		int *width, int *height, unsigned char data[][*width * 3]) {

	fwrite(header1, 1, 2, output); 		//Identify bitmap
	fwrite(file_size, 4, 1, output);		//File size
	fwrite(header2, 1, 12, output);
	fwrite(width, 4, 1, output);			//width
	fwrite(height, 4, 1, output);			//height
	fwrite(header3, 1, 8, output);
	fwrite(image_size, sizeof(int), 1, output);	//image size
	fwrite(header2, 1, 16, output);
	fwrite(data, 1, *image_size, output);		//pixel data
}

/*
 * Blend two image files together by computing the average between each component of the pixels.
 *
 * Param:	*width		The width in pixels
 *			*height		The height in pixels
 *			pixels1		The first image pixel data
 *			pixels2		The second image pixel data
 *			data		Store the blended pixels data
 */
void blender(int *width, int *height, unsigned char pixels1[][*width * 3],
		unsigned char pixels2[][*width * 3], unsigned char data[][*width * 3]) {
	int row, col;
	for (row = 0; row < *height; row++) {
		for (col = 0; col < *width * 3; col++) {
			data[row][col] = (pixels1[row][col] + pixels2[row][col]) / 2;
		}
	}
}

/*
 * Transform two image files into a 8x8 checker board by alternating between the files pixel data.
 *
 * Param:	*width		The width in pixels
 *			*height		The height in pixels
 *			pixels1		The first image pixel data
 *			pixels2		The second image pixel data
 *			data		Store the blended pixels data. Possible value 0 to 255.
 */

void transformCheckerBoard(int *width, int *height, unsigned char pixels1[][*width * 3],
		unsigned char pixels2[][*width * 3], unsigned char data[][*width * 3]) {

	int grid_size = *width / 8;		//Size of each square of the checker board.
	int row, col;

	for (row = 0; row < *height; row++) {
		if (((row / grid_size) % grid_size) % 2 == 0) {	//Even row
			for (col = 0; col < *width * 3; col++) {
				if (((col / (grid_size * 3)) % (grid_size * 3)) % 2 == 0) {	//Even column
					data[row][col] = pixels1[row][col];
				} else {
					data[row][col] = pixels2[row][col];			//Odd column
				}
			}
		} else {										//Odd row
			for (col = 0; col < *width * 3; col++) {
				if (((col / (grid_size * 3)) % (grid_size * 3)) % 2 == 0) {	//Even column
					data[row][col] = pixels2[row][col];
				} else {										//Odd column
					data[row][col] = pixels1[row][col];
				}
			}
		}
	}
}

int main(void) {

	setvbuf(stdout, NULL, _IONBF, 0);

	FILE *input_file1 = fopen("in1.bmp", "rb");
	FILE *input_file2 = fopen("in2.bmp", "rb");
	FILE *blend = fopen("blend.bmp", "wb");
	FILE *checker = fopen("checker.bmp", "wb");

	char headerA_1[2], headerA_2[12], headerA_3[8], headerA_4[16];
	char headerB_1[2], headerB_2[12], headerB_3[8], headerB_4[16];

	int width1 = 0, height1 = 0, width2 = 0, height2 = 0;
	int file_size1 = 0, file_size2 = 0, image_size1 = 0, image_size2 = 0;

	readInputFile(input_file1, headerA_1, headerA_2, headerA_3, headerA_4,
			&file_size1, &image_size1, &width1, &height1);
	readInputFile(input_file2, headerB_1, headerB_2, headerB_3, headerB_4,
			&file_size2, &image_size2, &width2, &height2);

	unsigned char pixels1[height1][width1 * 3], pixels2[height2][width2 * 3];

	unsigned char checkerData[height1][width1 * 3];
	unsigned char blendData[height1][width1 * 3];

	readPixels(input_file1, &width1, &height1, pixels1);
	readPixels(input_file2, &width2, &height2, pixels2);

	blender(&width1, &height1, pixels1, pixels2, blendData);

	transformCheckerBoard(&width1, &height1, pixels1, pixels2, checkerData);

	printOutput(blend, headerA_1, headerA_2, headerA_3, headerA_4,
			&file_size1, &image_size1, &width1, &height1, blendData);

	printOutput(checker, headerB_1, headerB_2, headerB_3, headerB_4,
			&file_size2, &image_size2, &width2, &height2, checkerData);

	fclose(blend);
	fclose(checker);
	return 0;
}
