package com.company.ch04;

import com.company.ch01.In;
import com.company.ch03.ST;

import java.io.InputStream;

/**
 * @author lwsmilence
 */
public class SymbolGraph {
    /**
     * 符号名 -> 索引
     */
    private ST<String, Integer> st;
    /**
     * 索引 -> 符号名
     */
    private String[] keys;

    private Graph G;

    public SymbolGraph(String stream, String sp) {
        st = new ST<String, Integer>();
        // 第一遍
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            // 为每个不同的字符串关联一个索引
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        // 用来获得定点名的反向索引是一个数组
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());
        // 第二遍
        in = new In(stream);
        // 构造图
        while (in.hasNextLine()) {
            // 将每一行的第一个顶点和该行的其他顶点相连
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }
}
