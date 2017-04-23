package interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by fenghao on 2017/4/21.
 */
public class RandLinked {
    static class Node {
        int data;
        Node next = null, randNext = null;

        Node(int data) {
            this.data = data;
        }
    }

    static Node copyWithHash(Node headNode) {
        Map<Node, Node> map = new HashMap<>();
        Node oldNode = headNode;
        // 建立新节点与原节点的映射
        while (oldNode != null) {
            map.put(oldNode, new Node(oldNode.data + 100));// 测试
            oldNode = oldNode.next;
        }
        oldNode = headNode;
        Node newHeadNode = null;
        Node newNode = null;
        // 利用映射将旧链接关系复制到新链接关系
        while (oldNode != null) {
            newNode = map.get(oldNode);
            newNode.next = map.get(oldNode.next);
            newNode.randNext = map.get(oldNode.randNext);// get(null)仍返回null
            if (newHeadNode == null) newHeadNode = newNode;
            oldNode = oldNode.next;
        }
        return newHeadNode;
    }

    static Node copyInsertion(Node headNode) {
        // 第一遍将新节点链接到旧节点next后，并链接到下一个旧节点
        Node oldNode = headNode;
        while (oldNode != null) {
            Node newNode = new Node(oldNode.data + 100);// 测试
            newNode.next = oldNode.next;
            oldNode.next = newNode;
            oldNode = newNode.next;
        }
        // 第二遍将旧节点randNext链接关系复制到新节点上
        oldNode = headNode;
        while (oldNode != null) {
            Node newNode = oldNode.next;
            if (oldNode.randNext != null){
                newNode.randNext = oldNode.randNext.next;
            }
            oldNode = newNode.next;
        }
        // 第三遍将新节点与旧节点分离开
        Node newHeadNode = null;
        oldNode = headNode;
        while (oldNode != null) {
            Node newNode = oldNode.next;
            oldNode.next = newNode.next;
            oldNode = oldNode.next;
            if (oldNode != null) newNode.next = oldNode.next;
            if (newHeadNode == null) newHeadNode = newNode;
        }
        return newHeadNode;
    }

    static Node buildRandLinked(int[] data) {
        int length = data.length;
        Map<Integer, Node> map = new HashMap<>(length);
        for (int i : data) {
            map.put(i, new Node(i));
        }
        int[] randIndex = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randIndex[i] = random.nextInt(length);
        }
        Node headNode = map.get(data[0]);
        Node tmpNode = null;
        for (int i = 0; i < length - 1; i++) {
            tmpNode = map.get(data[i]);
            tmpNode.next = map.get(data[i + 1]);
            if (randIndex[i] % 4 == 0)
                tmpNode.randNext = null;
            else
                tmpNode.randNext = map.get(data[randIndex[i]]);
        }
        return headNode;
    }

    static void printRandLinked(Node headNode) {
        Node tmpNode = headNode;
        while (tmpNode != null) {
            System.out.print(tmpNode.data + " -> ");
            System.out.println(tmpNode.randNext == null ? null : tmpNode.randNext.data);
            tmpNode = tmpNode.next;
        }
    }

    public static void main(String[] args) {
        Node headNode = buildRandLinked(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        printRandLinked(headNode);
        System.out.println("-----------");
        printRandLinked(copyInsertion(headNode));
    }
}
