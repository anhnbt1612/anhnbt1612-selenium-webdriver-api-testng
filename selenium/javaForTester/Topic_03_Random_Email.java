package javaForTester;

import java.util.Random;

public class Topic_03_Random_Email {

	public static void main(String[] args) {
		Random rand = new Random();
		
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));
		
		System.out.println("rickyta" + rand.nextInt(999999) + "@gmail.com");
		System.out.println("rickyta" + rand.nextInt(999999) + "@yahoo.com");
		System.out.println("rickyta" + rand.nextInt(999999) + "@poki.vn");
		System.out.println("rickyta" + rand.nextInt(999999) + "@stemplus.vn");

	}

}
