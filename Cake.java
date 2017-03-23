	/**
	 * Name          :
	 * Matric number :
	 */
	
	import java.util.*;
	
	public class Cake {
		public static void main(String[] args) {
			Cake myCake = new Cake();
			myCake.run();
		}
	
		public void run () {
			// main method implemented here
			Scanner sc = new Scanner(System.in);
	
			// my Constants
			int numOfSlices = sc.nextInt();
			int raisinLimit = sc.nextInt();
	
			// my Variables
			int currRaisin = 0;
			int currChoc = 0;
			int maxChoc = 0; // need to print out this value 
	
			QueueLL <CakeSlice> originalCake = new QueueLL<CakeSlice>();
			QueueLL <CakeSlice> takeHome = new QueueLL<CakeSlice>();
	
			for (int i = 0; i < numOfSlices; i++) {
				originalCake.offer(new CakeSlice(sc.next(), sc.nextInt()));
			}
	
			for (int i = 0; i < numOfSlices; i++) {
	
				if (raisinLimit != currRaisin) {
					CakeSlice slice = originalCake.poll();
	
					if (slice.getType().equals("R")) {
						currRaisin++;
					}
	
					currChoc += slice.getChocUnit();
					takeHome.offer(slice);
	
					//System.out.println(slice.getChocUnit() + " was added to take home slice");
					//System.out.println("Take Home Slice:");
					//takeHome.print();
					//System.out.println();
				}
	
				else if (raisinLimit == currRaisin) {
					CakeSlice slice = originalCake.poll();
	
					if (slice.getType().equals("C")) {
						currChoc += slice.getChocUnit(); 
						//System.out.println(slice.getChocUnit() + " was added to take home slice!");
						//System.out.println(currChoc + " is currChoc when chocolate is added despite raisin limit");
					}
	
					else {
						while (raisinLimit == currRaisin) {
							//System.out.println(takeHome.peek().getType());
							CakeSlice removed = takeHome.poll();
							currChoc -= removed.getChocUnit();
							//System.out.println(currChoc + " is current chocolate after remove " + removed.getChocUnit());
	
							if (removed.getType().equals("R")) {
								takeHome.push(slice);
								currChoc += slice.getChocUnit();
								//System.out.println(currChoc + " is current chocolate after slice is added!");
								break;
							}
						}
					}
	
				}
	
	
	
				// after each iteration, check the curr choc and max choc
				maxChoc = Math.max(maxChoc, currChoc);
				//System.out.println(maxChoc + " is maxChoc so far.");
	
			}
	
	
			System.out.println(maxChoc);
	
		}
	}
	
	
	class CakeSlice {
		private String type;
		private int chocUnit;
	
		public CakeSlice (String typeOfCake, int chocUnitNo) {
			type = typeOfCake;
			chocUnit = chocUnitNo;
		}
	
		public int getChocUnit() {
			return chocUnit;
		}
	
		public String getType() {
			return type;
		}
	
	}
	
	interface QueueADT <E> {
		public boolean isEmpty();
	
		// return front of the queue
		public E peek();
	
		// dequeue : remove and return front of the queue
		public E poll();
	
		// enqueue : add item to back of queue
		public boolean offer(E item);
	}
	
	class QueueLL <E> extends LinkedList<E> implements QueueADT<E> {
		public boolean offer (E o) {
			addLast(o);
			return true;
		}
	
		public E peek () {
			if (isEmpty()) return null;
			return getFirst();
		}
	
		public E poll () {
			E obj = peek();
			if (!isEmpty()) removeFirst();
			return obj;
		}
	
		/* public void print () {
			for (int i = 0; i < this.size(); i++) {
				E curr = this.get(i);
				System.out.print(((CakeSlice) curr).getChocUnit() + " ");
			}
		} */
	}
	
	class ListNode<E> {
		protected E element;
		protected ListNode<E> next; 
		protected ListNode<E> prev;
	
		// Constructors: Default 
		public ListNode (E item) {
			element = item;
			next = null;
			prev = null;
		}
	
		// Constructors
		public ListNode(E item, ListNode<E> n, ListNode<E> p) {
			element = item;
			next = n;
			prev = p;
		}
	
		// Getters
		public E getElement() {
			return element;
		}
	
		public ListNode<E> getNext() {
			return next;
		}
	
		public ListNode<E> getPrev() {
			return prev;
		}
	
		// Setters 
		public void setElement(E item) {
			this.element = item;
		}
	
		public void setNext(ListNode<E> item) {
			this.next = item;
		}
	
		public void setPrev(ListNode<E> item) {
			this.prev = item;
		}
	
		// Printing
		/* public void print () { 
			((Element) element).print();
		} */
	
		// Enable Swapping
		public void swap (ListNode<E> other) {
			E curr = element;
			this.setElement(other.getElement());
			other.setElement(curr);
		}
	}