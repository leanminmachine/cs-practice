/**
Example of using a stack for calculation in brackets
 */

import java.util.*;

public class Chemistry {
	public static void main(String[] args) {
        //implement your main method here
		
		Chemistry problem = new Chemistry();
		problem.run();

    }
	
	public void run() {
		
		HashMap<Character, Integer> massMapping = new HashMap<Character, Integer>();
		Stack<Integer> calculateFormula = new Stack<Integer>();
		
		Scanner sc = new Scanner (System.in);
		
		int n = sc.nextInt();
		sc.nextLine();

		massMapping = initializeMassMapping(sc, n); //implement it 

		//System.out.println(massMapping);
		
		sc.nextLine(); // need this to move the input stream, because initalise mapping uses nextInt
		
		String nextLine = sc.nextLine();
		char[] formula = nextLine.toCharArray();
		//System.out.println(Arrays.toString(formula) + " is the formula");
			
		Stack<Integer> finalStack = processInput(formula, massMapping);

		int total = accumulate(finalStack); 

		System.out.println(total);

		sc.close();

	}
	
	private HashMap<Character, Integer> initializeMassMapping(Scanner sc, int n) {
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		for (int i = 0 ; i < n; i++) {
		char element = sc.next().charAt(0);
		int mass = sc.nextInt();
		
		map.put(element, mass);
		} // end for loop 
		
		return map;
	} // end initialiseMassMapping
	
	private Stack<Integer> processInput (char[] formula, HashMap<Character, Integer> hash) {
		Stack<Integer> results = new Stack<Integer>();
		HashMap<Character, Integer> massMapping = hash;
		
		for (char c : formula) {
			if (c == '(') { //open bracket
			    results.push(-1); //start a new “session”
			} else if (c == ')') { //close bracket
			    int sum = accumulate(results); //acummulate is a “helper”
			    results.push(sum); //then push the sum into the stack
			} else if (Character.isDigit(c)) {//it’s a number
			    //multiply the top of the stack. 
				int v = results.pop();
				v *= Character.getNumericValue(c);

				results.push(v);

			} else { //then c must be a valid element!
			    //push the corresponding atom’s mass
				results.push(massMapping.get(c));;
			}

		}
		
		return results;
	} //end processInput 
	
	private int accumulate(Stack <Integer> results) {
		
	    int result = 0;
	    int top = results.pop();
	    
	    while (top != -1 || !results.isEmpty()) {
	    	result += top;
	    	
	    	if (!results.isEmpty()) {
	    		top = results.pop();
	    		
	    		if (top == -1) {
	    			break;
	    		}
	    	} else {
	    		break;
	    	}
	    }
	    
	    /* top is not -1 and stack is not empty */
		//add current top to result	    	
		//update top to be next element in stack
	    return result;
	}

}
