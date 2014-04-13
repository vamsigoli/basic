package com.vamsi.util;

import java.util.Random;

public class RandomColorGenerator{
	
	private static  Random r = new Random();
	
	 public static String pickRandomColor(){   //notice that this method doesn't have to be  the same as the one that will apear in your tag 'randomColor'. This is only true for a class that implements a Custom El Function.
	  String [] colors = new String[4];
	  colors[0] = "Red";
	  colors[1] = "Blue";
	  colors[2] = "Yellow";
	  colors[3] = "Green";
	        return colors[Math.abs(r.nextInt()) % 4]; //return one of the colors in array at random
	    }
	}