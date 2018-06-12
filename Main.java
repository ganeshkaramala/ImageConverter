package com.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Main {

	public static void main(String[] args) {
		String fileLocation = "E:/Projects/MyProjects/Hackathon/Hackathon/resource/";
		String inputFile = "input.jpg";
		File file = new File(fileLocation + inputFile);
		File output;
		String outputName;
		BufferedImage image;
		
		ImageConverter imageConverter;
		
		
		imageConverter = new ImageConverter(file);
		outputName = "transport.jpg";
	    output = new File(fileLocation + outputName);
		image = imageConverter.getTranspose();

		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}

		imageConverter = new ImageConverter(file);
		outputName = "down.jpg";
	    output = new File(fileLocation + outputName);
		image = imageConverter.getDownImage();

		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		imageConverter = new ImageConverter(file);
		outputName = "right.jpg";
	    output = new File(fileLocation + outputName);
		image = imageConverter.getRightImage(imageConverter.getImage());

		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		imageConverter = new ImageConverter(file);
		outputName = "swap.jpg";
	    output = new File(fileLocation + outputName);
		image = imageConverter.getSwapImage();

		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		imageConverter = new ImageConverter(file);
		ArrayList<BufferedImage> lst = imageConverter.getCrop();
		
		
		outputName = "crop1.jpg";
	    output = new File(fileLocation + outputName);
		try {
			ImageIO.write(lst.get(1), "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		outputName = "crop2.jpg";
		output = new File(fileLocation + outputName);
		try {
			ImageIO.write(lst.get(0), "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		imageConverter = new ImageConverter(file);
		outputName = "shift.jpg";
	    output = new File(fileLocation + outputName);
		image = imageConverter.getShiftImage();

		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		imageConverter = new ImageConverter(file);
		outputName = "towards.jpg";
	    output = new File(fileLocation + outputName);
		image = imageConverter.getTowards();

		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		imageConverter = new ImageConverter(file);
		image = imageConverter.getInvertImage();
		outputName= "invert.jpg";
		output = new File(fileLocation + outputName);
		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		imageConverter = new ImageConverter(file);
		image = imageConverter.getGrayImage();
		outputName= "gray.jpg";
		output = new File(fileLocation + outputName);
		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
