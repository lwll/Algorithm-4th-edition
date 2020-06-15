package com.company.ch03;


/**
 * 顺序查找，基于无序列表
 * @author lwsmilence
 */
public class SequentialSearchST<Key, Value> {
    public Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return node.val;
            }
        }
        return null;
    }

    public void set(Key key, Value value) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                node.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
    }


}
