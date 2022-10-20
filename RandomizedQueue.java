import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPCITY = 2; // initial capacity

    private Item[] rq; // an array of items
    private int size; // size of the array

    // construct an empty randomized queue
    public RandomizedQueue() {

        rq = (Item[]) new Object[INIT_CAPCITY];
        size = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the randomized queue
    public int size() {
        return size;


    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size() == rq.length) resize(rq.length, 2);

        rq[size] = item;

        size++;


    }

    // adjust size of array
    private void resize(int length, double a) {
        int newLength = (int) (length * a);
        Item[] copy = (Item[]) new Object[newLength];

        for (int i = 0; i < size(); i++) {
            copy[i] = rq[i];
        }

        rq = copy;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int itemIndex = StdRandom.uniformInt(size());
        Item item = rq[itemIndex];

        rq[itemIndex] = rq[size() - 1];
        rq[size() - 1] = null;

        size--;

        if (size > 0 && size() == rq.length / 4) resize(rq.length, 0.5);

        return item;


    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int itemIndex = StdRandom.uniformInt(size());

        return rq[itemIndex];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // iterator for RandomizedQueue
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] items; // array of items
        private int itemIndex; // the index for an item

        // contructor for RandomizedQueue
        public RandomizedQueueIterator() {
            items = (Item[]) new Object[size()];

            for (int i = 0; i < size(); i++) {
                items[i] = rq[i];
            }

            StdRandom.shuffle(items);
            itemIndex = 0;


        }

        // returns true if index is at capacity of array
        public boolean hasNext() {
            return itemIndex < size();
        }

        // returns
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = items[itemIndex];
            itemIndex++;

            return item;
        }
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        // creates a RandomizedQueue
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();


        // prints the initial state of rq
        System.out.println("Empty?: " + rq.isEmpty() + '\n');
        System.out.println("Initial Size: " + rq.size() + '\n');

        // enqueues n numbers into rq
        for (int i = 0; i <= n; i++) {
            rq.enqueue(i);

            if (i == n) {
                System.out.println("Size of n: " + rq.size() + '\n');
            }
        }

        // lists removed item
        System.out.println("Item Removed: " + rq.dequeue());
        System.out.println("Size: " + rq.size() + '\n');


        // lists random item
        System.out.println("Random Sample: " + rq.sample());
        System.out.println("Size: " + rq.size() + '\n');

        for (Integer name : rq) {
            System.out.print(name + " - ");
        }
        StdOut.println();

    }


}
