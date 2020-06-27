package com.company.ch01;

import java.util.Iterator;

/**
 * @author lwsmilence
 */
public class Bag<Item> implements Iterable<Item> {

    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

         @Override
         public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
         }
    }
}
