package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		//System.out.println("1 head nazywa siê " + head);
		tail = new LLNode<E>(null);
		//System.out.println("2 tail nazywa siê " + tail);
		head.next = tail;
		head.prev = head;
		tail.prev = head;
		tail.next = tail;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		//System.out.println("3 mnastepnik heada to: " + head.next);
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		LLNode find = head;
		while (find.next != tail) {
			//System.out.println("4 a find wynosi" + find);
			find=find.next;
			//System.out.println("5 jest rozne tail a find.prev wynosi " + find.prev);
			}
		
			//System.out.println("6 tworzê nowego noda z find: " + find);
			new LLNode (element, find);
			size++;
			//System.out.println("7 dodaje " + size);

		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		if(index <0 || index >size-1 ) {
			throw new IndexOutOfBoundsException();
		}
		LLNode find= head.next;
	
		for (int i = 1; i<=index; i++) {
			find=find.next;
		}
		
		return (E) find.data;
		
		
		//return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		if(index <0 || index >size ) {
			throw new IndexOutOfBoundsException();
		}		
		if(element == null) {
			throw new NullPointerException();
		}
		LLNode find= head;
		for (int i = 1; i<=index; i++) {
			find=find.next;
		}
		new LLNode (element, find);
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index <0 || index >size-1 ) {
			throw new IndexOutOfBoundsException();
		}		
		
		LLNode find= head;
		for (int i = 0; i<=index; i++) {
			find=find.next;
		}
		(find.prev).next=find.next;
		(find.next).prev=find.prev;
		size--;
		E data = (E) find.data;
		find = null;
		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index <0 || index >size-1 ) {
			throw new IndexOutOfBoundsException();
		}		
		if(element == null) {
			throw new NullPointerException();
		}
		
		LLNode find= head;
		for (int i = 0; i<=index; i++) {
			find=find.next;
		}
		E data = (E) find.data;
		find.data=element;
		
		return data;
	}   
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prevNode) 
	{
		this.data = e;
		this.prev = prevNode;
		//System.out.println("8 W konsturktorze ustawiam this.prev " + this.prev);
		this.next = prevNode.next;
		(this.next).prev=this;
		//System.out.println("9 W konsturktorze ustawiam this.next " + this.next);
		prevNode.next = this;
		//System.out.println("10 W konstruktorze ustawiam prevNode.next" + prevNode.next);
		
		//System.out.println("11 nowa noda  " + this);
	}


}

