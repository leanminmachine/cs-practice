/**
 * Name         :
 * Matric No    :
 * Plab Acct.   :
 *
 */

import java.util.*;

public class Cards {
	boolean debug = false;
    
    
    public static void main(String[] args) {
        Cards myCards = new Cards();
        myCards.run();
    }
    
    public void run() {
        // implement your "main" method here
    	
    	Scanner sc = new Scanner (System.in);
    	
    	// first query : creating the LinkedList
    	int numCards = sc.nextInt();
    	
    	TailedLinkedList<Card> linkedList = new TailedLinkedList<Card> ();
    	
    	for (int i = 0; i < numCards; i++) {
    		linkedList.addLast(new Card(sc.next(), sc.nextInt()));
    	}
    	
    	if (debug)
    	linkedList.printList();
    	
    	// second query : number of queries
    	
    	int numQuery = sc.nextInt();
    	
    	for (int j = 0; j < numQuery; j++) {
    	
    	String query = sc.next();
    	
    	switch (query) {
    	case "swap":
    		// do something
    		swap(sc, linkedList);
    		System.out.println("swap has been performed");
    		break;
    	case "details":
    		// do something
    		details(sc, linkedList);
    		break;
    	case "position":
    		// do something
    		position(sc, linkedList);
    		break;
    	case "shuffle":
    		// do something
    		shuffle(sc, linkedList);
    		
    		if (debug)
    			linkedList.printList();
    		break;
    	case "print":
    		// print
    		print(linkedList);
    		break;
    		}
    	}
    }
    
    private void swap(Scanner sc, TailedLinkedList<Card> linkedList) {
    	int a = sc.nextInt();
    	int b = sc.nextInt();
    	int c = sc.nextInt();
    	int d = sc.nextInt();
    	
    	// need to traverse along the linkedlist, until you reach the correct index
    	// retrieve the index before the correct index
    	
    	ListNode<Card> cardA = linkedList.getNodeAt(linkedList, a);
    	//cardA.printNode();

    	
    	ListNode<Card> cardB = linkedList.getNodeAt(linkedList, b);
    	//cardB.printNode();
    	
    	ListNode<Card> cardC = linkedList.getNodeAt(linkedList, c);
    	//cardC.printNode();
    	
    	ListNode<Card> cardD = linkedList.getNodeAt(linkedList, d);
    	//cardD.printNode(); */
    	
    	linkedList.relink(a, b, c, d);
    	
    	if (debug)
    	linkedList.printList();
    
    	
    	
    }
    
    private void details (Scanner sc, TailedLinkedList<Card> linkedList) {
    	
    	int position = sc.nextInt();
    	ListNode<Card> node = linkedList.getNodeAt(linkedList, position);
    	
    	node.printNode();
    	System.out.println();
    }
    
    private void position (Scanner sc, TailedLinkedList<Card> linkedList) {
    	String name = sc.next();
    	
    	int position = 0;
    	
    	if (linkedList.containsString(name) != -1) {
    		position = linkedList.containsString(name);
    	}
    	
    	System.out.println(position);
    	
    
    }
    
    private void shuffle (Scanner sc, TailedLinkedList<Card> linkedList) {
    	TailedLinkedList<Card> newList = linkedList.splitList(linkedList);
    	//linkedList.printList();
    	//newList.printList();
    	
    	linkedList.merge(newList);
    	//linkedList.printList();
    	
    	System.out.println("shuffle has been performed");
    }
    
    private void print(TailedLinkedList<Card> linkedList) {
    	linkedList.printList();
    	System.out.println();
    }
    
    
}

class TailedLinkedList<E> {

    // Data attributes
    private ListNode<E> head;
    private ListNode<E> tail;
    private int num_nodes;

    public TailedLinkedList() {
        this.head = null;
        this.tail = null;
        this.num_nodes = 0;
    }

    // Return true if list is empty; otherwise return false.
    public boolean isEmpty() {
        return (num_nodes == 0);
    }

    // Return number of nodes in list.
    public int size() {
        return num_nodes;
    }

    // Return value in the first node.
    public E getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("can't get from an empty list");
        else
            return head.getElement();
    }
    
    // Return node in index passed in 
    public ListNode<E> getNodeAt(TailedLinkedList<E> tailedLinkedList, int position) {
    	ListNode<E> curr = head; //start off by pointing curr reference to head node
    	ListNode<E> temp = null;
    	
    	int i = 1;
    	
    	while (i < position) {
    		temp = curr;
    		curr = curr.next;
    		curr.prev = temp;
    		i++;
    	}
    	
    	return curr;
    	
    }
    

    // Return true if list contains item, otherwise return false.
    public boolean contains(E item) {
        for (ListNode<E> n = head; n != null; n = n.getNext())
            if (n.getElement().equals(item))
                return true;

        return false;
    }
    
    public int containsString(String string) {
    	
    	int count = 1;
    	
    	 for (ListNode<E> n = head; n != null; n = n.getNext()) {
    		 if ( ((Card) n.getElement()).getName().equals(string) ){
    			 return count;
    		 }
    	 count++;
    	 }
    	 
    	 return -1;
    		 
    }



    // Add item to front of list.
    public void addFirst(E item) {
        head = new ListNode<E>(item, head, null); // point head to the new node created at front
        num_nodes++;
        if (num_nodes == 1) tail = head;
    }

    // Add item to k-th position of list
    public void addToIdx (E item, int k) {
    	ListNode<E> temp1 = head;
    	
    	// traverse through the linkedlist until the right index is found
    	while (--k != 0) {
    		temp1 = temp1.next;
    	}
    	
    	// AT this point, specified index is found
    	// tenp1 is before insertion point, temp2 is insertion point
    	ListNode<E> temp2 = temp1.next;
    	
    	// Creates a new listNode
    	ListNode<E> newNode = new ListNode<E> (item);
    	
    	// Point newNode to temp2
    	newNode.next = temp2;
    	
    	// point temp1 to newNode
    	temp1.next = newNode;
    	
    }
    
    public void relink (int a, int b, int c, int d) {
    	ListNode<E> temp1 = head;
    	ListNode<E> temp2 = head;
    	ListNode<E> temp3 = head;
    	ListNode<E> temp4 = head; 
    	
    	while (--a != 0) {
    		temp1 = temp1.next;
    	}
    	
    	while (--b != 0) {
    		temp2 = temp2.next;
    	}
    	
    	while (--c != 0) {
    		temp3 = temp3.next;
    	}
    	
    	while (--d != 0) {
    		temp4 = temp4.next;
    	}
    	
    	
    	ListNode<E> aPrev = temp1.getPrev();
    	ListNode<E> aNext = temp1.getNext();
    	
    	ListNode<E> bPrev = temp2.prev;
    	ListNode<E> bNext = temp2.next;
    	
    	ListNode<E> cPrev = temp3.getPrev();
    	ListNode<E> cNext = temp3.getNext();
    	
    	ListNode<E> dPrev = temp4.prev;
    	ListNode<E> dNext = temp4.next;
    	
    	
    	// Handles start of linkedList (head node)
    	if (head == temp1) {
    		head = temp3;
    	}
    	else {
    		aPrev.setNext(temp3);
    	}
    	
    	// Handle middle of linkedList
    	
    	ListNode<E> gapStart = temp2.next;
    	ListNode<E> gapEnd = temp3.prev;
    	
    	// there are no gaps 
    	if (gapStart == temp3 && gapEnd == temp2) {
       		
    		temp4.setNext(temp1);
    	
    		
    		temp1.setPrevious(temp4);
    	}
    	else {
    		temp4.setNext(gapStart);
    		gapEnd.setNext(temp1);
    		temp4.setPrevious(temp3);
    		temp3.setPrevious(aPrev);
    	}
    	
    	// Handle end of cardList (tail node)
    	if (tail == temp4) {
    		tail = temp2;
    		tail.setNext(null);
    	} 
    	else {
    		temp2.setNext(dNext);
    		
    	} 
    	
    }
    
    public TailedLinkedList<E> splitList(TailedLinkedList<E> linkedList) {
    	
    	ListNode<E> curr = head;
    	
    	ListNode<E> slow = head;
    	
    	ListNode<E> fast = head;
    	
    	TailedLinkedList<E> newList = new TailedLinkedList<E>();
    	
    	if (curr == null || curr.next == null) {
    		return linkedList;
    	}
    	
    	while (fast != null)  {

    		fast = fast.next;
    	
    		if (fast != null && fast.next != null) {
    			slow = slow.next;
    			fast = fast.next;
    		}
    	}
    
    	linkedList.head = head;
    	linkedList.tail = slow;
    	newList.head = slow.next; 
    	linkedList.tail.next = null;
    	// do i need to set newList.tail?
    		
    	return newList;
    	
    }
    
    public void merge (TailedLinkedList<E> newList) {
    	ListNode<E> firstNext, firstCurr = head;
    	ListNode<E> secondNext, secondCurr = newList.head;
    	// Start at each lists head.
    	// Curr refers to iterator node 
    	
    	while (firstCurr != null && secondCurr != null) {
    		
    		// Save next pointers 
    		firstNext = firstCurr.next;
    		secondNext = secondCurr.next;
    		
    		// point second list node to first list node next and vice versa
    		firstCurr.setNext(secondCurr);
    		secondCurr.setNext(firstNext);
    		secondCurr.setPrevious(firstCurr);
    		
    		
    		if (firstNext == null) {

        		firstCurr.setNext(secondCurr);
        		secondCurr.setNext(null);
        		tail = secondCurr;
        	}
    		
    		// update current pointers for the next iteration
    		firstCurr = firstNext;
    		secondCurr = secondNext;
    		
    		//System.out.println(firstCurr + "firstcurr");
    		//System.out.println(secondCurr + "secondcurr");
    		

        	
    	}
    	
    	
    	
    }
    
    
    // Return reference to first node.
    public ListNode<E> getHead() {
        return head;
    }

    // Return reference to last node of list.
    public ListNode<E> getTail() {
        return tail;
    }
    
   
    // Add item to end of list.
    public void addLast(E item) {
        if (head != null) {
            tail.setNext(new ListNode<E>(item));
            tail = tail.getNext();
        } else {
            tail = new ListNode<E>(item);
            head = tail;
        }
        num_nodes++;
    }

    // Remove node after node referenced by current
    public E removeAfter(ListNode<E> current) throws NoSuchElementException {
        E temp;
        if (current != null) {
            ListNode<E> nextPtr = current.getNext();
            if (nextPtr != null) {
                temp = nextPtr.getElement();
                current.setNext(nextPtr.getNext());
                num_nodes--;
                if (nextPtr.getNext() == null) // last node is removed
                    tail = current;
                return temp;
            } else
                throw new NoSuchElementException("No next node to remove");
        } else { // if current is null, we want to remove head
            if (head != null) {
                temp = head.getElement();
                head = head.getNext();
                num_nodes--;
                if (head == null)
                    tail = null;
                return temp;
            } else
                throw new NoSuchElementException("No next node to remove");
        }
    }

    // Remove first node of list.
    public E removeFirst() throws NoSuchElementException {
        return removeAfter(null);
    }

    // Remove item from list
    public E remove(E item) throws NoSuchElementException {
        ListNode<E> current = head;
        if (current == null) {
            throw new NoSuchElementException("No node to remove");
        }
        if (current.getElement().equals(item)) {
            return removeAfter(null);
        }
        while (current.getNext().getElement() != null) {
            if (current.getNext().getElement().equals(item)) {
                return removeAfter(current);
            }
            current = current.getNext();
        }
        throw new NoSuchElementException("No node to remove");
    }
    
    
    // Printing out LinkedList
    public String toString() {
    	return "LinkedList with" + size() + "elements";
    }
    
    public void printList () {
    	
    	//System.out.println("Contents of " + toString());

    	
    	ListNode<E> current = head;
    	
    	while (current != null && current != tail) {
    		System.out.print(current.toString() + " ");
    		current = current.getNext();
    	}
    	
    
    	System.out.print(tail.toString());
    }
}

class ListNode<E> {
    protected E element;
    protected ListNode<E> next;
    protected ListNode<E> prev;

    /* constructors */
    public ListNode(E item) {
        this.element = item;
        this.next = null;
        this.prev = null;
    }

    public ListNode(E item, ListNode<E> n, ListNode<E> p) {
        element = item;
        next = n;
        prev = p;
    }

    /* get the next ListNode */
    public ListNode<E> getNext() {
        return this.next;
    }
    
    // Get previous listNode
    public ListNode<E> getPrev() {
    	return this.prev;
    }

    /* get the element of the ListNode */
    public E getElement() {
        return this.element;
    }

    public void setNext(ListNode<E> item) {
        this.next = item;
    }
    
    public void setPrevious (ListNode<E> item) {
    	this.prev = item;
    }

    public void setElement(E item) {
        this.element = item;
    }
    
    // prints out contents of the listNode
    public String toString() {
    	return (((Card) getElement()).getName());
    }
    
    public void printNode () {
    	System.out.print((((Card) getElement()).getName()) + " " + (((Card) getElement()).getAge()));
    }
}

class Card {
	private String _name;
	private int _age;
	
	public Card () {
		_name = "";
		_age = 0;
	}
	
	public Card (String name, int age) {
		_name = name;
		_age = age;
	}
	
	public String getName () {
		return _name;
	}
	
	public int getAge () {
		return _age;
	}
	
	
}

