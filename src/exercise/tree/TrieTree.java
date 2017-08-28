package exercise.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Trie树，即字典树，又称单词查找树或键树，是一种树形结构，是一种哈希树的变种。
 * 典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
 * 它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。
 * Trie的核心思想是空间换时间。利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。
 * 它有3个基本性质：
 * 根节点不包含字符，除根节点外每一个节点都只包含一个字符。
 * 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串。
 * 每个节点的所有子节点包含的字符都不相同。
 */
public class TrieTree {
    static final int SIZE = 10;
    static final char BASE = '0';

    static class Node {
        char ch;
        int cnt;
        boolean end;
        Node[] child;

        Node(char ch) {
            this.ch = ch; // 节点字符
            this.cnt = 0; // 该节点字符出现次数
            this.end = false;
            this.child = new Node[SIZE];
        }
    }

    static void insert(Node root, String str) {
        if (str == null || str.length() == 0) return;
        int n = str.length();
        int pos;
        for (int i = 0; i < n; i++) {
            pos = str.charAt(i) - BASE;
            if (root.child[pos] == null) {
                root.child[pos] = new Node(str.charAt(i));
            }
            root.child[pos].cnt++;
            root = root.child[pos];
        }
        root.end = true;
    }

    static Node create(String[] strs) {
        Node root = new Node((char) 0);
        for (int i = 0; i < strs.length; i++) {
            insert(root, strs[i]);
        }
        return root;
    }

    static boolean search(Node root, String str) {
        if (str == null || str.length() == 0) return false;
        int n = str.length();
        int pos;
        for (int i = 0; i < n; i++) {
            pos = str.charAt(i) - BASE;
            if (root.child[pos] == null) return false;
            root = root.child[pos];
        }
        return root.end;
    }

    static void bfs(Node root) {
        Deque<Node> deque = new ArrayDeque<>();
        for (int i = 0; i < SIZE; i++) {
            if (root.child[i] != null)
                deque.addLast(root.child[i]);
        }
        Node cur;
        while (!deque.isEmpty()) {
            cur = deque.removeFirst();
            System.out.print(cur.ch + "(" + cur.cnt + ") ");
            for (int i = 0; i < SIZE; i++) {
                if (cur.child[i] != null)
                    deque.addLast(cur.child[i]);
            }
        }
    }

    public static void main(String[] args) {
        String[] strs = {"123", "12", "345", "135"};
        Node root = create(strs);
        System.out.println(search(root, "135"));
        bfs(root);
    }
}
