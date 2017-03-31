
import java.util.*;

public class Bag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bag problem = new Bag ();
		problem.run();

	}
	
	private void run () {
		Scanner sc = new Scanner (System.in);
		int numQ = sc.nextInt();
		int capacity = sc.nextInt();
		
		int[] items = new int[numQ];
		
		for (int i = 0 ; i < numQ ; i++) {
			items[i] = sc.nextInt();
		}
		
		System.out.println(Arrays.toString(items));	
		
		int results = getCount(items, capacity);
		
		System.out.println(results);
		
	}
	
	private int getCount (int[] items, int capacity) {
		return getCount (items, 0, capacity);
	}
	
	private int getCount(int[] items, int index, int capacity) {
		// base case 
		// 1) capacity is exceeded 
/*		if (capacity < 0) {
			return 0;
		}*/
		
		// 2) capacity is met ie. sum of combination exceeds the given capacity (not a solution!)
		if (capacity < 0) {
			return 0; 
		}
		
		else if (index >= items.length) {
			// no more items to take, but meets solution
			return 1;
		}
		
		// recursive case - when there are still items and capacity
		int currWeight = items[index];
		
		// output the total number of ways to pack the items 
		return getCount(items, index + 1, capacity - currWeight) + getCount (items, index + 1, capacity);
	}

}


