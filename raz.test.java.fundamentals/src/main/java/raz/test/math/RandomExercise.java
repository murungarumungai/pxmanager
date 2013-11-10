package raz.test.math;

import java.util.Random;

public class RandomExercise {
	
	public static void main(String[] args){
		
		double subUnitarRandom =  Math.random();
		System.out.println(subUnitarRandom);
		Random randomer =new Random();
		
		int randomColor = randomer.nextInt(255);
		
		System.out.println(randomColor);
	}

}
