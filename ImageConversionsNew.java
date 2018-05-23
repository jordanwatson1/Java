/*
 * Name : Jordan Watson
 * Date: 11/03/2017
 * FileName: ImageConversionsNew.java
 */
 
 /*
  *The class ImageConversionsNew contains a program
  *that takes a picture and manipulates it to flip horizontally, verticallly, rotate 90 degrees,
  *invert the grey scale, convert to ascii art and scale the image to be bigger or smaller.
  */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class ImageConversionsNew{
	
	//Flipping the image horizontally
	static int[][]	horizontalFlip(int[][] image){
		
		int rows = image.length;
		int cols = image[0].length;
		int[][] picture = new int[rows][cols];
		
		int horizontalpic = cols-1;
		
		for (int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				picture[j][i] = image[j][horizontalpic];
			}
			horizontalpic--;
		}
		return picture;
	}
	
	//inverting the image on grey scale 0-255, so 0 = 255, 1 = 254, and so on.
	static int[][] invert(int[][] image){
		
		int N = 255;
		int rows = image.length;
		int cols = image[0].length;
		int[][] result = new int[rows][cols];
		
		for ( int i = 0; i < rows; i++) {
			for ( int j = 0; j < cols; j++) {
				result[i][j] += (N - image[i][j]);
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		
		//If the args array has three elements, perform:
		if( args.length == 3) {
			
			args[0] = "Q.jpg";
			args[1] = "invertOuput.jpg";
			args[2] = "invert";
			int[][] resultArr = readGrayscaleImage(args[0]);
			writeGrayscaleImage(args[1], invert(resultArr));

			args[0] = "Q.jpg";
			args[1] = "verticalFlipOutput.jpg";
			args[2] = "verticalFlip";
			writeGrayscaleImage(args[1], verticalFlip(resultArr));

			args[0] = "Q.jpg";
			args[1] = "horizontalFlipOutput.jpg";
			args[2] = "horizontalFlip";
			writeGrayscaleImage(args[1], horizontalFlip(resultArr));

			args[0] = "Q.jpg";
			args[1] = "rotateOutput.jpg";
			args[2] = "rotate";
			writeGrayscaleImage(args[1], rotate(resultArr));

			args[0] = "Q.jpg";
			args[1] = "ascii.txt";
			args[2] = "makeAscii";
			makeAscii(resultArr, "ascii.txt");
		}
	
		//If the args array has four elements, perform:
		if(args.length == 4){
			int[][] arrValue = readGrayscaleImage(args[0]);
			double scalefactor;
			args[0]= "Q.jpg";
			args[1]= "scaleTest.jpg";
			args[2]= "scale";
			scalefactor = Double.parseDouble(args[3]);
			writeGrayscaleImage(args[1], scale(arrValue, scalefactor));
		}else{
			System.out.println("Check if args has four elements.");
			return;
		}
		
	}
	
	//This method was provided on connex which reads the greyscale image
	public static int[][] readGrayscaleImage(String filename) {
		int[][] result = null;
		File imageFile = new File(filename);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException ioe) {
			System.err.println("Problems reading file named " + filename);
			throw new RuntimeException("Please ensure the image file is saved in the same directory as your java file.");
		}
		
		int height = image.getHeight();
		int width  = image.getWidth();
		result = new int[height][width];
		int rgb;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				rgb = image.getRGB(x, y);
				result[y][x] = rgb & 0xff;
			}
		}
		return result;
	}
	
	//This method rotates the image
	static int[][] rotate(int[][] image){
		
		int rows = image.length;
		int cols = image[0].length;
		int[][] picture = new int[rows][cols];
		int rotatePic = rows-1;
		
		for (int i = 0; i<cols; i++){
			for(int j = rows-1; j>=0; j--){
				picture[i][j] = image[j][rotatePic];
			}
			rotatePic--;
		}
		return picture;
	}
	
	//This method takes an image and user will give a size they want the picture scaled to.
	static int[][] scale(int[][] image, double scalefactor){
		
		int cols = (int) (image[0].length * scalefactor);
		int rows  = (int) (image.length * scalefactor);
		int[][] result = new int[rows][cols];
    
		
		if(scalefactor < 1 && scalefactor > 0) {
			for(int i = 0; i < rows; i++) {
				int a = (int)(i / scalefactor);
				for(int j = 0; j < cols; j++) {
					int b = (int)(j / scalefactor);
					result[i][j] = image[a][b];
				}
  			
			}
		
		}else if (scalefactor >= 1) {
			for(int i = 0; i < rows; i++) {
				int a = (int)(i / scalefactor);
				for(int j = 0; j < cols; j++) {
					int b = (int)(j / scalefactor);
					result[i][j] = image[a][b];
				}
  			
			}
		
		} else { 
			System.out.println("not able to scalefactor");
		}
		return result;	
	}

	//This method flips the image vertically	
	static int[][] verticalFlip(int[][] image){
		
		int rows = image.length;
		int cols = image[0].length;
		int[][] picture = new int[rows][cols];
		int verticalPic = rows-1;
		
		for (int i = 0; i<rows; i++){
			for(int j = rows-1; j>=0; j--){
				picture[i][j] = image[verticalPic][j];
			}
			verticalPic--;
		}
		return picture;
	}
	
	//This method was given on connex that writes the greyscale image and is the main calling method for all methods excpt ascii
	public static void writeGrayscaleImage(String filename, int[][] array) {
		int width = array[0].length;
		int height = array.length;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

		int rgb;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				rgb = array[y][x];
				rgb |= rgb << 8;
				rgb |= rgb << 16;
				image.setRGB(x, y, rgb);
			}
		}
		File imageFile = new File(filename);
		try {
			ImageIO.write(image, "jpg", imageFile);
		} catch (IOException ioe) {
			System.err.println("The file could not be created " + filename);
			return;
		}
	}
}

