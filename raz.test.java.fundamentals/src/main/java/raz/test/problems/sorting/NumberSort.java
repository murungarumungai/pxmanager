package raz.test.problems.sorting;

public class NumberSort {

	static int[] numbers = { 4, 3, 7, 8, 0, 9, 3, 7, 8, 2, 1, 5, 9, 6, 10, -2 };

	public static void main(String[] args) {
		System.out.println("Before sorting:"+numbers);
		printArray(numbers);
		sort(numbers, true);
		System.out.println("After sorting:"+numbers);
		printArray(numbers);
	}

	/**
	 * sorts the int[]
	 * */
	public static int[] sort(int[] intArray, boolean loopAgain) {

		if (loopAgain) {

			loopAgain = false;
			// bubble sort
			for (int i = 0; i <= (intArray.length - 1)-1; i++) {
				if (intArray[i] > intArray[i + 1]) {
					// swap them
					int tmp = intArray[i];
					intArray[i] = intArray[i + 1];
					intArray[i + 1] = tmp;
					loopAgain = true;
				}
			}
			return sort(intArray, loopAgain);
		} else {
			// array is sorted, end recursion, just return the array as is
			return intArray;
		}

	}
	
	public static void printArray(final int[] array ) {
		if(null==array){
			System.out.println("Array is null");
		} else if(array.length==0){
			System.out.println("Array is empty");
		} else{
			for(int currentNumber : array) {
				System.out.println(currentNumber);
			}
			
		}
	}

}
