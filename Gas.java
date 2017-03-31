import java.util.*;

public class Gas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gas problem = new Gas();
		problem.run();
	}
	
	private void run () {
		Scanner sc = new Scanner(System.in);
		int numQ = sc.nextInt();
		
		// List of stations
		Station[] listOfStations = new Station[numQ + 1];
		
		for (int i = 0; i <numQ; i++) {
			// create new gas stations 
			listOfStations[i] = new Station(sc.nextInt(), sc.nextInt());
		}
		
		listOfStations[numQ] = new Station(sc.nextInt(), 0);
		
		//System.out.println(Arrays.toString(listOfStations));
		
		int results = recurse(listOfStations);
		
		if (results >=0) {
		System.out.println(results);
		}
		
		else {
			System.out.println("can meh?");
		}
	}
	
	private int recurse (Station[] listOfStations) {
		return recurse(listOfStations, 0, 200);
	}
	
	private int recurse(Station[] listOfStations, int index, int fuel) {
		
		if (index >= listOfStations.length - 1) {
			if (fuel >= listOfStations[listOfStations.length - 1].getDistance()) {
				return 0; // can reach
			}
			
			else {
				// can't reach destination
				return -9999999;
			}
		}
		
		if (listOfStations[index].getDistance() > 200) {
			return -9999999;
		}

		
		else {
			fuel -= listOfStations[index].getDistance() ;
			int cost = (200 - fuel) * listOfStations[index].getCost();
			//System.out.println("The cost is " + cost);
			
			// CASE ONE: if fuel is less than what it takes to get to next distance, have to pump. 
			if (fuel < listOfStations[index + 1].getDistance()) {
			// full fuel
			return cost + recurse(listOfStations, index + 1, 200);	
			// cost refers to cost refilling at current station, but have to add on additional costs from the recursion!
			}
			
			// CASE 2: when you can reach this station, then you have to decide whether to pump or don't pump.
			else {
				return Math.min((cost + recurse(listOfStations, index + 1, 200)), recurse(listOfStations, index + 1, fuel));
			}
		}
		
		
	}

}

class Station {
private int index;
private int distance;
private int cost; 

	public Station (int distance, int cost) {
		this.distance = distance;
		this.cost = cost;
	}
	
	public int getDistance () {
		return distance;
	}
	
	public int getCost () {
		return cost; 
	}
}
