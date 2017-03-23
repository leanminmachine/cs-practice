/**
 * Name			:
 * Matric No.	:
 * PLab Acct.	:
 */

import java.util.*;

public class Radiation {

	public void run() {
		// implement your "main" method here...
		
		Scanner sc = new Scanner(System.in);
		int numQ = sc.nextInt();
		
		int globalMax = 0;
		// records the max decay for print output 
		
		Stack <Element> theStack = new Stack <> ();
		
		for (int i = 0; i < numQ; i++) {
			int currMax = 0; 
			int maxYears = 0;
			int strength = sc.nextInt();
			System.out.println(strength + " is str");
			

			// compare strength
			while (!theStack.isEmpty() && theStack.peek().getStrength() >= strength) {
				// need to remove .peek() - but we must still keep track of the strength.
				Element top = theStack.pop();
				//System.out.println(maxYears);
				maxYears = Math.max(maxYears, top.getYearsBeforeDecay());
			}
			
			// if the stack is empty
			if (theStack.isEmpty()) {
				currMax = 0;
			} else {
				currMax = maxYears + 1;
				System.out.println(currMax + " is curr max");
			}

			
			// with the determined currMax value, create a new element
			theStack.push(new Element(strength, currMax));
			
			globalMax = Math.max(currMax, globalMax);
			
		}
		
		sc.close();
		//System.out.println(maxYearsOfDecay);
		System.out.println(globalMax);
		
	}
		

	public static void main(String[] args) {
		Radiation myChemicalElements = new Radiation();
		myChemicalElements.run();
	}
}

// hint for O(N) solution...
class Element {
	private int strength;
	private int yearsBeforeDecay;
	private boolean remove;

	public Element(int strength, int yearsBeforeDecay) {
		this.strength = strength;
		this.yearsBeforeDecay = yearsBeforeDecay;
		this.remove = false; 
	}

	public int getStrength() {
		return this.strength;
	}

	public int getYearsBeforeDecay() {
		return this.yearsBeforeDecay;
	}
	
	public void setYearsBeforeDecay(int i) {
		yearsBeforeDecay = i;
	}
	
	public void remove () {
		remove = true; 
	}
	
	public void print () {
		System.out.println(this.getStrength()); 
	}
	
	public String toString () {
		return ("Strength " + this.getStrength());
	}
	
}
