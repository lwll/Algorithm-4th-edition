package com.company.ch03;


import com.company.ch01.Queue;

/**
 * 基于二叉查找树的有序符号表
 * 树由Node对象组成，每个对象都含有一堆键值，两条链接和一个结点计数器N。每个Node对象都是一棵含有N个结点的子树的根节点，
 * 它的左链接指向一棵由小于该结点的所有键组成的二叉查找树，右链接指向一棵由大于该结点的所有键组成的二叉查找树。
 * root变量指向二叉查找树的根节点Node对象
 * @author lwsmilence
 */
public class BST<Key extends Comparable<Key>, Value> {

    private class Node {
        /**
         * 键
         */
        private Key key;
        /**
         * 值
         */
        private Value value;
        /**
         * 指向左树
         */
        private Node left;
        /**
         * 指向右树
         */
        private Node right;
        /**
         * 以该节点为根节点的子树中的节点数
         */
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    /**
     * 二叉查找树的根节点
     */
    private Node root;

    /**
     *  查询指定节点的子树节点数量
     * @param n Node节点
     * @return 节点数量
     */
    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.N;
    }

    public int size() {
        return size(root);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void put(Key key, Value value) {
        put(root, key, value);
    }

    public Key min() {
        return min(root).key;
    }

    public Key max() {
        return max(root).key;
    }

    public Key floor(Key key) {
        return floor(root, key).key;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    /**
     * 在以node为根节点的子树中查找并返回key所对应的值
     * @param node 指定结点
     * @param key key
     * @return key对应的value
     */
    private Value get(Node node, Key key) {
        if (node == null) {
            // 未找到
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            return get(node.left, key);
        } else if (cmp < 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    /**
     * 如果key存在于以node为根节点的子树中，则更新它的值
     * 否则将以key和value为键值对的新结点插入到该子树中
     * @param node 待查找的根结点
     * @param key key
     * @param value value
     * @return 更新值后的根结点  （为了新增结点后, 指向能正确）
     */
    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 根据给定的结点查询该结点子树的最小键的结点
     * @param node  给定结点
     * @return 最小键
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    /**
     * 根据给定的结点查询小于等于该结点键的
     * @param node
     * @param key
     * @return
     */
    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            return floor(node.left, key);
        } else if (cmp == 0) {
            return node;
        } else {
            //
            Node n = floor(node.right, key);
            if (n != null) {
                return n;
            } else {
                return node;
            }
        }
    }

    /**
     * 从以给定结点为根节点的树中，查找排名为k的结点
     * @param node 根结点
     * @param k 排名
     * @return 结点
     */
    private Node select(Node node, int k) {
        if (node == null) {
            return null;
        }
        if (k == node.left.N) {
            return node;
        } else if (k > node.left.N) {
            return select(node.right, k - node.left.N - 1);
        } else {
            return select(node.left, k);
        }
    }

    /**
     * 给定根结点和键，查找键在二叉树的排名
     * @param node
     * @param key
     * @return
     */
    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            // 当前结点，当前结点的左分支以及在右分支中的排名
            return 1 + size(node.left) + rank(node.right, key);
        } else if (cmp < 0) {
            return rank(node.left, key);
        } else {
            return size(node.left);
        }
    }

    /**
     * 删除指定根结点二叉树的最小结点
     * @param node
     * @return 新的根结点
     */
     private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
     }

    /**
     * 删除指定结点二叉树中，键为key的结点
     * @param node
     * @param key
     * @return 删除之后的根结点
     */
     private Node delete(Node node, Key key) {
         if (node == null) {
             return null;
         }
         int cmp = key.compareTo(node.key);
         if (cmp > 0) {
             return delete(node.right, key);
         } else if (cmp < 0) {
             return delete(node.left, key);
         } else {
             if (node.right == null) {
                 return node.left;
             }
             if (node.left == null) {
                 return node.right;
             }
             Node t = node;
             // 右树的最小结点即为替代结点
             node = min(node.right);
             // 删除自己
             node.right = deleteMin(t.right);
             // 左树不变
             node.left = t.left;
         }
         node.N = size(node.left) + size(node.right) + 1;
         return node;
     }

     public Iterable<Key> keys() {
         return keys(min(), max());
     }

     public Iterable<Key> keys(Key lo, Key hi) {
         Queue<Key> queue = new Queue<Key>();
         keys(root, queue, lo ,hi);
         return queue;
     }

     private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
         if (x == null) {
             return;
         }
         int cmplo = lo.compareTo(x.key);
         int cmphi = hi.compareTo(x.key);
         if (cmplo < 0) {
             keys(x.left, queue, lo, hi);
         }
         if (cmplo <= 0 && cmphi >= 0) {
             queue.enqueue(x.key);
         }
         if (cmphi > 0) {
             keys(x.right, queue, lo ,hi);
         }
     }
}
