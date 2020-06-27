package com.company.ch04;

import com.company.ch01.Queue;

import java.util.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 * @author lwsmilence
 */
public class DepthFirstOrder {
    private boolean[] marked;

    /**
     * 所有顶点的前序排序，即顶点被递归调用的顺序
     */
    private Queue<Integer> pre;

    /**
     * 所有顶点的后续排序，即顶点遍历完成的顺序
     */
    private Queue<Integer> post;

    /**
     * 所有顶点的逆后续排序
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
