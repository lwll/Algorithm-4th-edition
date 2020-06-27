package com.company.ch04;

/**
 * 计算强连通分量的Kosaraju算法
 * @author lwsmilence
 */
public class KosarajuSCC {
    /**
     * 已访问过的顶点
     */
    private boolean[] marked;

    /**
     * 强连通分量的标识符
     */
    private int[] id;

    /**
     * 强连通分量的数量
     */
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
}
