import java.util.*;

class Instruction {
	public static void main(String[] args) {
		Instruction instruction = new Instruction();
		instruction.run();
		}
		
		
		
	private void run () {
		ArrayList<Pair> listOfInstructions = new ArrayList<Pair> ();
		Scanner sc = new Scanner(System.in);
		
		int numOfCalculations = sc.nextInt();
		int targetNum = sc.nextInt();
		
		for (int i = 0; i < numOfCalculations; i++) {
			Pair instruction = new Pair (sc.next().charAt(0), sc.nextInt());
			listOfInstructions.add(instruction);
		}
		
		for (int j = 0; j < numOfCalculations; j++) {
			Pair currInstructions = listOfInstructions.get(j);
		}
		
		//System.out.println(listOfInstructions);
		
		int result = calculate(listOfInstructions, targetNum);
		
		System.out.println(result);
	}
		
	
	
	private int calculateValue (Pair nextInstruction, int currValue) {
		char operation = nextInstruction.getOperation();
		int number = nextInstruction.getNumber();
		
		switch(operation) {
		case '+':
			return currValue + number;
		case '-':
			return currValue - number;
		case '*':
			return currValue * number;
		default:
			return currValue / number;
		}
	}
	
	public int calculate (ArrayList<Pair> input, int target) {
	return calculate (input, 0, 0, target);	
	}
	
	private int calculate(ArrayList<Pair> input, int index, int currValue, int target) {
		// Base: Reached end of the input list ie. no more instructions to choose from
		if (index == input.size()) {
			return currValue;
		}
		
		// Recursive step: Calculate values of what happens when you take / don't take
		int take = calculate (input, index + 1, calculateValue (input.get(index), currValue), target);
		int dontTake = calculate (input, index + 1, currValue, target);
		
		int takeAbsDistance = Math.abs(target - take);
		int dontTakeAbsDistance = Math.abs(target - dontTake);
		
		if (takeAbsDistance == dontTakeAbsDistance) {
			return Math.min(take, dontTake);
		} else if (takeAbsDistance < dontTakeAbsDistance) {
			return take;
		} else { 
			return dontTake;
		}
		
		
	}
}



class Pair {
	private char operation;
	private int number;
	
	public Pair(char operation, int number) {
		this.operation = operation;
		this.number = number;
	}
	
	public char getOperation() {
		return operation;
	}
	
	public int getNumber () {
		return number;
	}
	
	public String toString () {
		return (operation + " " + number);
	}
}

