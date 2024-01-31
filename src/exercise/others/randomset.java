package exercise.others;

import java.util.*;

/**
 * O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class randomset {
    List<Integer> list; // 变长数组存储元素
    Map<Integer, Integer> map; // map存储元素和下标
    Random rand;
    randomset () {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val); // 尾部插入元素
        map.put(val, list.size() - 1); // 记录元素和下标
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int len = list.size();
        int idx = map.get(val);
        int lastval = list.get(len - 1);
        list.set(idx, lastval); // 将最后一个元素替换要删除的元素
        list.remove(len - 1); // 删除最后一个元素
        map.put(lastval, idx); // 更新最后一个元素下标
        map.remove(val); // 删除待删除元素
        return true;
    }

    public int getRandom() {
        int randidx = rand.nextInt(list.size());
        return list.get(randidx);
    }
}
