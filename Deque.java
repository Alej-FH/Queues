import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size; // size of that stack
    private Node first; // top of stack
    private Node last; // bottom of stack

    private class Node { // Node class
        Item item; // item in a node
        Node next; // following node
        Node prev; // previous node

    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        // Adding the first itme to the deque
        if (size == 0) {
            Node firstNode = new Node();
            first = firstNode;
            last = firstNode;
            firstNode.item = item;
        }
        else {
            Node oldFirstNode = first;
            first = new Node();
            first.item = item;
            first.next = oldFirstNode;
            oldFirstNode.prev = first;
        }


        size++;


    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        // Adding the first item to the Deque

        if (size == 0) {
            last = new Node();
            first = last;
            last.item = item;
        }
        else {
            Node oldLastNode = last;
            last = new Node();
            last.item = item;
            last.prev = oldLastNode;
            oldLastNode.next = last;
        }


        size++;


    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty()) throw new NoSuchElementException();

        // Adjusts pointers of first and last if Deque becomes empty
        Item item = first.item;
        first = first.next;
        if (size == 1) {
            last = null;
        }
        else {
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) throw new NoSuchElementException();

        Item item = last.item;
        last = last.prev;
        if (size == 1) {
            first = null;
        }
        else {
            // Second to last Node links to null.
            last.next = null;
        }
        size--;
        return item;
    }

    // return iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current; // the current node

        // constructor for ListIterator
        public ListIterator() {
            current = first;
        }

        // returns false if no item follows the list
        public boolean hasNext() {
            return current != null;
        }


        // returns the current item and follows the following item.
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item x = current.item;
            current = current.next;
            return x;
        }
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        Deque<Integer> dq = new Deque<Integer>();


        // Initial State of the dq
        System.out.println("Is the Deque empty?: " + dq.isEmpty() + '\n');
        System.out.println("Initial Size of Deque: " + dq.size() + '\n');

        // Adds n items to dq
        for (int i = 0; i <= n; i++)
            if (i % 2 == 0) {
                dq.addFirst(i);
            }
            else dq.addLast(i);

        // Prints updated size of dq
        System.out.println("Size of Deque: " + dq.size() + '\n');


        // Returns an item from the front
        System.out.println("Front Item Removed: " + dq.removeFirst());

        // Returns an item from the back
        System.out.println("Back Item Removed: " + dq.removeLast());

        // Prints dq
        for (Integer name : dq) {
            System.out.print(name + '\n');
        }


    }

}
