package com.app;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageConverter {

	private BufferedImage img;
	private Raster raster;
	private File file;
	private int[][] pixels;

	public ImageConverter(File file) {
		this.file = file;
		this.pixels = createArray();
	}

	private int[][] createArray() {
		try {
			this.img = ImageIO.read(file);
			this.raster = this.img.getData();
			int w = this.img.getWidth(), h = this.img.getHeight();
			int pixels[][] = new int[w][h];
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					pixels[x][y] = raster.getSample(x, y, 0);
				}
			}
			return pixels;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BufferedImage getGrayImage() {
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Color c = new Color(getImage().getRGB(j, i));
				int red = (int) (c.getRed() * 0.299);
				int green = (int) (c.getGreen() * 0.587);
				int blue = (int) (c.getBlue() * 0.114);
				Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
				getImage().setRGB(j, i, newColor.getRGB());
			}
		}
		return getImage();
	}

	public BufferedImage getInvertImage() {
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgba = getImage().getRGB(x, y);
				Color col = new Color(rgba, true);
				col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
				getImage().setRGB(x, y, col.getRGB());
			}
		}
		return getImage();
	}

	public BufferedImage getSwapImage() {
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		BufferedImage simg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < h; y++) {
			for (int l = 0, r = w - 1; l < w; l++, r--) {
				int p = getImage().getRGB(l, y);
				simg.setRGB(r, y, p);
			}
		}

		int temp = 0;
		for (int y = 0; y < h; y++) {
			for (int l = 0, r2 = w / 2; l < w; l++, r2++) {
				if (l >= w / 2) {
					int p = getImage().getRGB(l, y);
					simg.setRGB(temp, y, p);
					temp++;
				}
				if (l < w / 2) {
					int p = getImage().getRGB(l, y);
					simg.setRGB(r2, y, p);
				}
			}
			temp = 0;

		}
		return simg;
	}

	public void generateColorImage() {
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Color c = new Color(getImage().getRGB(j, i));
				int red = (int) (c.getRed());
				int green = (int) (c.getGreen());
				int blue = (int) (c.getBlue());
				Color newColor = new Color(red, green, blue);
				getImage().setRGB(j, i, newColor.getRGB());
			}
		}

	}

	public BufferedImage getRightImage(BufferedImage img) {
		BufferedImage myImage = img;
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		BufferedImage mimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < h; y++) {
			for (int l = 0, r = w - 1; l < w; l++, r--) {
				int p = myImage.getRGB(l, y);
				mimg.setRGB(r, y, p);
			}
		}
		return mimg;
	}

	public BufferedImage getTranspose() {
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int y = h - 1, y2 = 0; y >= 0; y--, y2++) {
			for (int l = w - 1, l2 = 0; l >= 0; l--, l2++) {
				int p = getImage().getRGB(l, y);
				dimg.setRGB(l2, y2, p);
			}
		}
		return dimg;
	}

	public ArrayList<BufferedImage> getCrop() {

		ArrayList<BufferedImage> list = new ArrayList<>();
		int[][] pixelArray = getPixelArray();
		int w = pixelArray.length;
		int h = pixelArray[0].length;
		int temp = 0;
		BufferedImage mimg1 = new BufferedImage(w / 2, h, BufferedImage.TYPE_INT_RGB);
		BufferedImage mimg2 = new BufferedImage(w / 2, h, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < h; y++) {
			for (int l = 0; l < w; l++) {

				if (l < w / 2) {
					int p = getImage().getRGB(l, y);
					mimg2.setRGB(l, y, p);
				}
				if (l >= w / 2) {
					int p = getImage().getRGB(l, y);
					mimg1.setRGB(temp, y, p);
					temp++;
				}
			}
			temp = 0;
		}
		list.add(mimg1);
		list.add(mimg2);
		return list;
	}

	public BufferedImage getDownImage() {
		return getRightImage(getTranspose());
	}

	public BufferedImage getShiftImage() {

		BufferedImage myImage = getImage();
		int width = myImage.getWidth();
		int height = myImage.getHeight();
		int width2 = myImage.getHeight();
		int height2 = myImage.getWidth();

		int temp = width2 - 1;

		BufferedImage mimg = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);

		for (int y = width - 1; y >= 0; y--)
		{
			for (int l = 0; l < height; l++)
			{
				int p = myImage.getRGB(y, l);
				mimg.setRGB(temp, y, p);
				temp--;
			}
			temp = width2 - 1;
		}

		return mimg;
	}

	public BufferedImage getTowards() {

		BufferedImage myImage = getImage();
		int width = myImage.getWidth();
		int height = myImage.getHeight();

		BufferedImage mimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int l = 0, r = width - 1, r2 = width / 2; l < width; l++, r--, r2++) {

				if (l >= width / 2) {
					int p = myImage.getRGB(l, y);
					mimg.setRGB(r, y, p);
				}
				if (l < width / 2) {
					int p = myImage.getRGB(l, y);
					mimg.setRGB(r2, y, p);
				}

			}

		}
		return mimg;
	}

	public int[][] getPixelArray() {
		return this.pixels;
	}

	public BufferedImage getImage() {
		return this.img;
	}

}
