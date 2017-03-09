import java.util.*;

public class CircularLinkedList {
	boolean debug = false;
 
	public static void main (String[] args) {
		CircularLinkedList test = new CircularLinkedList();
		test.run();
	}
	
	public void run () {
	
		CDLL<Player> myList = new CDLL<Player>();
		
		myList.addFirst(new Player("steven"));
		myList.addLast(new Player("rais"));
		myList.addLast(new Player("khanh"));
		myList.addLast(new Player("john"));
		myList.addLast(new Player("gaby"));
	
		myList.printList();
		
		myList.reverse();
		
		myList.printList();
		
	}
}

class CDLL <E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int num_nodes;
	
	public CDLL () {
        this.head = null;
        this.tail = null;
        this.num_nodes = 0;
	}
	
	
    // Add item to front of list.
    public void addFirst(E item) {
        ListNode<E> newNode = new ListNode<E>(item, null, null); 
        num_nodes++;
        
        if (head == null) {
        newNode.setNext(newNode);
        newNode.setPrev(newNode);
        head = newNode;
        tail = newNode;
        }
        
        else {
        	// tail
    		newNode.setPrev(tail);
    		tail.setNext(newNode);
    		
    		// shifting the previous head
    		head.setPrev(newNode);
    		newNode.setNext(head);
    		
    		head = newNode;
    	}
        
    }
    
    // Add item to back of list.
    public void addLast(E item) {
    	
    	ListNode<E> newNode = new ListNode<E>(item, null, null);
    	num_nodes++;
    	
        if (head == null) {
        newNode.setNext(newNode);
        newNode.setPrev(newNode);
        head = newNode;
        tail = newNode;
        }
        
        else {
        	// tail
    		newNode.setPrev(tail);
    		tail.setNext(newNode);
    		
    		// shifting the previous head
    		head.setPrev(newNode);
    		newNode.setNext(head);
    		
    		tail = newNode;
    	}
    }
	
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
    	temp2.setPrev(newNode);

    	// point temp1 to newNode
    	temp1.next = newNode;
    	newNode.setPrev(temp1);
    	
    	num_nodes++;
    }
    
    // when a certain node is found, add the item after
    public void addAfter(ListNode<E> curr, E item) {
    	ListNode<E> newNode = new ListNode<E>(item, null, null);
    	
    	curr.next.setPrev(newNode);
    	newNode.setNext(curr.next);
    	
    	curr.setNext(newNode);
    	newNode.setPrev(curr);
    	
    	num_nodes++;
    }
    
    // Remove functions
    // // /// // /////// 
    
    // Removes the first Node
    // Need to conduct reassignment of head
    public ListNode<E> removeFirst () throws NoSuchElementException {
		ListNode<E> remover;
		
		if (head == null) { // an empty list
			throw new NoSuchElementException("Can't remove from empty list");
		} else {
			remover = head;
			head = head.next; // the reassignment of head to its next element
			head.setPrev(tail);
			tail.setNext(head);
			num_nodes--;
		}
    	
    	return remover;
    }
    
    // Removes the last node
    // Conduct reassignment of the tail 
    public ListNode<E> removeLast () throws NoSuchElementException {
    	ListNode<E> remover;
    	
    	if (tail == null) {
    		throw new NoSuchElementException("Can't remove from empty list");
    	} else {
    		// the reassignment of tail
    		remover = tail;
    		tail = tail.prev;
    		tail.setNext(head);
    		head.setPrev(tail);
    		num_nodes--;
    	}
    	return remover;
    }
    
    // Remove node at a particular index
    public ListNode<E> removeAt(int index) throws NoSuchElementException {
		ListNode<E> remover = head;
    	
    	if (head == null) { // an empty list
			throw new NoSuchElementException("Can't remove from empty list");
		} else { 
			while (--index != 0) {
				remover = remover.next;
			}
			
			// the remover node now will be the node to remove
			// 2 main cases: removal of head/tail; or removal of other nodes 
			
			// 1) Deletion of a node that is not head/tail
			// fixing the next pointer 
			if (remover != head) {
				remover.prev.next = remover.next;
			} else { // removal of a head : Same as removeFirst!
				head = remover.next;
				// updatee the prev pointer
				head.setPrev(tail);
				tail.setNext(head);
			}
			
			// fixing the prev pointer
			if (remover != tail) {
				remover.next.prev = remover.prev;
			} else { // removal of a tail : same as removeLast! 
				tail = remover.prev;
				// update the previous and next pointers
				tail.setNext(head);
				head.setPrev(tail);
			}
	
		}
    	
    	num_nodes--;
    	return remover;
    }
    
    // Remove the current node 
    public ListNode<E> remove (ListNode<E> curr) throws NoSuchElementException {
    	ListNode<E> remover = head; 
    	
    	if (remover == null) {
    		throw new NoSuchElementException("no such element to remove");
    	} else {
    		
    		while (remover != curr) {
    			remover = remover.next;
    		}
			// the remover node now will be the node to remove
			// 2 main cases: removal of head/tail; or removal of other nodes 
			
			// 1) Deletion of a node that is not head/tail
			// fixing the next pointer 
			if (remover != head) {
				remover.prev.next = remover.next;
			} else { // removal of a head : Same as removeFirst!
				head = remover.next;
				// updatee the prev pointer
				head.setPrev(tail);
				tail.setNext(head);
			}
			
			// fixing the prev pointer
			if (remover != tail) {
				remover.next.prev = remover.prev;
			} else { // removal of a tail : same as removeLast! 
				tail = remover.prev;
				// update the previous and next pointers
				tail.setNext(head);
				head.setPrev(tail);
			}
    	}
    	
    	num_nodes--;
    	return remover;
    }
    
    
    // Search functions
    // // // // // // 
    
    // getNodeAt(index)
    // Pre: A linked list has been initialised  
    // Post: Returns a node object at the index entered
    public ListNode<E> getNodeAt(int index) {
    	ListNode<E> curr = head;
    	
    	while (--index != 0) {
    		curr = curr.next;
    	}
    	
    	return curr;
    }
    
    // int whereIs (String string)
    // Searches through the linkedlist to see if there is a match 
    // If there is, return the position of the node
    // Else, return -1
    // Remember to manipulate the index if the list is one-based! 
    public int whereIs (String string) {
    	ListNode<E> curr = head;
    	
    	for (int i = 1; i <= num_nodes; i++) {
    		// need to check if curr matches the string
    		Player currPlayer = (Player) curr.getElement();
    		if (currPlayer.getName().equals(string)) {
    			return i; 
    		}
    		
    		curr = curr.next;
    	}
    	
    	// not found
    	return -1;
    }
	
	public void printList () {
		
		ListNode<E> current = head; 
		
		while (current != null && current != tail) {
			System.out.print(current.toString());
			current = current.getNext();
		}
		System.out.println(tail.toString());
	}
	
	// Special Operations
	// ///  // /  // / /  /
	// Swap (Value-Value)
	
    public void swap (ListNode<E> first, ListNode<E> second) {
    	first.swap(second);
    }
    
    // Swap (References)
    
    
    // Reverse
    public ListNode<E> reverse () {
    	ListNode<E> curr = head; 
    	ListNode<E> temp = null;
    	
    	while (curr != null) {
    		temp = curr.getPrev(); // swap the next and prev pointer 
    		curr.setPrev(curr.next);
    		curr.setNext(temp);
    		curr = curr.getPrev();
    	}
    	
    	return temp.prev;
    }
	
	
	
	// check
	public void point (int num) {
		ListNode<E> curr = head; 
		System.out.println("Started at the " + head.getElement());
		
		while (--num != 0) {
			curr = curr.next; 
			System.out.println("pointing at " + curr.getElement());
		}
		
	}
	
	public void pointBackwards (int num) {
		ListNode<E> curr = head; 
		System.out.println("Started at the " + head.getElement());
		
		while (--num != 0) {
			curr = curr.prev; 
			System.out.println("pointing at " + curr.getElement());
		}
		
	}
	
	public void pass(int passes, int limit) {
	    	ListNode<E> curr = head;
	    	
	    	while (passes != 0) {
	    		curr = curr.next;  
	    		passes--;
	    		//System.out.println("ball with " + curr.getElement());
	    	}
	    	
	    	Player currPlayer = (Player) curr.getElement();
	    	
	    	currPlayer.incNum();
	    	
	    	System.out.println(currPlayer.getName());
		
	    	if (currPlayer.getNum() != limit) {
	    	this.swap(curr, head);
	    	}
	    	
	    	else { // remove player
	    	System.out.println(currPlayer + " was removed");
	    	
	    	ListNode<E> oldHead = head;
	    	ListNode<E> newHead = curr.next;
	    	
	    	this.remove(curr);
	    	// starting point would be the player on the right
	    	
	    	head = newHead; 
	    	head.setPrev(oldHead);
	    	oldHead.setNext(head);
	    	
	    	tail.setNext(oldHead);
	    	oldHead.setPrev(tail);
	    	tail = oldHead;
	    
	    	} 
	    }
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
	public String toString() {
		return element.toString();
	}
	
	// Enable Swapping
	public void swap (ListNode<E> other) {
		E curr = element;
		this.setElement(other.getElement());
		other.setElement(curr);
	}
	
	
	
}

class Player {
	private String _name;
	private int _numReceived;
	
	public Player(String name) {
		_name = name;
		_numReceived = 0;
	}
	
	public void incNum() {
		_numReceived++;
	}
	
	public int getNum() {
		return _numReceived;
	}
	
	public String getName() {
		return _name;
	}
	
	@Override 
	public String toString() {
		return ("[ Name: " + _name + " | Num: " + _numReceived + " ] ");
	}
}