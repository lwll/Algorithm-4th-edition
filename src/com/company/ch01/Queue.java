package com.company.ch01;

import java.util.Iterator;

/**
 * @author lwsmilence
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first;

    private Node last;

    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeque() {
        if (isEmpty()) {
            return null;
        }
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
