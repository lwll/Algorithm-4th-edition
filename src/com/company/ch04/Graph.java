package com.company.ch04;

import com.company.ch01.Bag;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lwsmilence
 */
public class Graph {
    /**
     * 顶点数目
     */
    private int V;

    /**
     * 边的数量
     */
    private int E;

    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(InputStream in) throws IOException {
        this(in.read());
        int E = in.read();
        for (int i = 0; i < E; i++) {
            int v = in.read();
            int w = in.read();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        // 将w添加到v的链表中
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
