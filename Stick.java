/**
 *	name	  :
 *	matric no.:
 */

import java.util.*;

public class Stick {
	
	public static void main(String[] args) {
		Stick problem = new Stick();
		problem.run(); 
	}
	
	private void run () {

		
		Scanner sc = new Scanner (System.in);
		
		int numOfSticks = sc.nextInt(); 
		int target = sc.nextInt();
		
		int[] length = new int[numOfSticks];
		
		// populate sticks array
		for (int i = 0; i < numOfSticks; i++) {
			length[i] = (sc.nextInt());
		}
		
		System.out.println(Arrays.toString(length));
		
		int results = getMinCount(length, target);
		
		if (results == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
		System.out.println(results);
		}
		
	}
	
	private int getMinCount (int[] lengths, int target) {
		return getMinCount(0, 0, target, lengths);
	}
	
	private int getMinCount (int index, int used, int length, int[] sticks) {
		// return the number of sticks used
		if (length == 0) {
			return used;
		}
		
		else if (length < 0) { // we have overshot the given length
			return Integer.MAX_VALUE;
		}
		
		else if (index >= sticks.length) { // nothing more to take 
			return Integer.MAX_VALUE;
		}
		
		else {
			return Math.min(getMinCount(index + 1, used + 1, length - sticks[index], sticks), getMinCount(index + 1, used, length, sticks));
		}
		
		
	}
	
	
	

		
}