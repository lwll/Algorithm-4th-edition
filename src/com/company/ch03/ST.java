package com.company.ch03;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>,Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<>();
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null key");
        }
        return st.get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (value == null) {
            st.remove(key);
        } else {
            st.put(key, value);
        }
    }

    public boolean contains(Key key) {
        return st.containsKey(key);
    }

    public int size() {
        return st.size();
    }

    public Set<Key> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }
}
