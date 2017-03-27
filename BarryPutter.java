import java.util.*;

public class BarryPutter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BarryPutter problem = new BarryPutter();
		problem.run();
	}
	
	private void run () {
		// Array of chambers
		Scanner sc = new Scanner(System.in);
		
		int numQueries = sc.nextInt();
		
		Chamber[] allChambers = new Chamber[numQueries];
		
		
		int limit = sc.nextInt();
		
		for (int i = 0; i < numQueries; i++) {
			int chamberNo = sc.nextInt();
			int gold = sc.nextInt();
			int arraySize = sc.nextInt();
			
			int[] listOfChambers = new int[arraySize];
			
			for (int j = 0; j < arraySize; j++) {
				listOfChambers[j] = sc.nextInt();
			}
			
			// Create the chamber
			allChambers[i] = new Chamber(chamberNo, gold, listOfChambers);
		
		}
		
		int results = travel(allChambers, limit);
		
		System.out.println(results);
	}
	
	private int travel(Chamber[] allChambers, int limit){
		return travel(allChambers, 0, limit);
	}
	
	private int travel(Chamber[] allChambers, int index, int limit) {
		// base case - reached the limit 
		// base case 2 - far far away
		if (index >= allChambers.length || index < 0) { // always put the check first!! 
			return 0;
		}
		
		else if (limit == 0) {
			return allChambers[index].getGold();
		}
		
		// recurse 
		else {
//			System.out.println(index + " " + allChambers.length);
			int[] listOfChambers = allChambers[index].getListOfChambers();
			// everytime i recurse, i get a new list of chambers, so its ok
			//int numGold = 0;
			int numGold = recurse(0, allChambers, listOfChambers, index, limit);
			
			/*for (int i = 0; i < listOfChambers.length; i++) {
				// list of distance represents the distance 
				int distance = listOfChambers[i];
				
				// accumulate the amount of gold
				numGold = Math.max(numGold, travel(allChambers, (index + distance), (limit - 1)));
			}*/
			
//			System.out.println(allChambers[index].getGold() + " gold at this point");
			
			return allChambers[index].getGold() + numGold;
		}
	}
	
	
	private int recurse(int start, Chamber[] allChambers, int[] listOfChambers, int index, int limit) {
		// base case : when it ends 
		if (start >= listOfChambers.length) {
			return 0;
		}
		
		else {
			int current = travel(allChambers, (index + listOfChambers[start]), (limit - 1));
			int next = recurse(start + 1, allChambers, listOfChambers, index, limit);
			
			return Math.max(current, next);
		}
		
	// else keep recursing 
	}

}

class Chamber {
private int chamberNo;
private int gold;
private int[] chambers; // easier to access

	public Chamber (int chamber, int goldInside, int[] listOfChambers) {
		chamberNo = chamber;
		gold = goldInside;
		chambers = listOfChambers;
	}
	
	// getters and setters here
	public int getGold () {
		return gold;
	}
	
	public int getChamberNo () {
		return chamberNo;
	}
	
	public int[] getListOfChambers () {
		return chambers;
	}
}