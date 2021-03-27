package exercise.stackqueue;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
public class LRUCache {
    // 双向链表节点
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        Node() {
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return key + "," + value;
        }
    }

    private Map<Integer, Node> cacheMap; // 只记录key，value在节点记录
    private Node head, tail;
    private int capacity, count = 0;

    LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.head = new Node(); // 头尾节点单独建立方便添加和删除
        this.tail = new Node();
        this.head.next = tail;
        this.tail.prev = head;
    }

    void addNodeFirst(Node node) { // 头插
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    Node removeNodeLast() { // 尾删
        Node node = tail.prev;
        tail.prev = node.prev;
        node.prev.next = tail;
        return node;
    }

    void moveNodeFirst(Node node) { // 将访问节点移到头结点
        if (node.prev == head) return;
        // 断开访问节点，处理前后节点链接
        node.next.prev = node.prev;
        node.prev.next = node.next;
        // 处理访问节点链接
        node.prev = head;
        node.next = head.next;
        // 处理访问节点新位置前后链接
        head.next = node;
        node.next.prev = node;
    }

    int get(int key) {
        Node node = cacheMap.get(key);
        if (node == null) return -1;
        moveNodeFirst(node);
        return node.value;
    }

    void put(int key, int value) {
        Node node = cacheMap.get(key);
        if (node != null) { // 已存在需要更新，且移动到头部
            node.value = value;
            moveNodeFirst(node);
            return;
        }
        node = new Node(key, value); // 不存在需要新建，且插入到头部
        cacheMap.put(key, node);
        addNodeFirst(node);
        count++;
        if (count > capacity) { // 超过容量从尾部删除节点
            Node removeNode = removeNodeLast();
            cacheMap.remove(removeNode.key);
            count--;
        }
    }

    void printNode(Node node) {
        while (node != null) {
            System.out.print(node + "-");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}
