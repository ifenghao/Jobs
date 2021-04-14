package exercise.num;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class yanghuitriangle {
    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        List<Integer> newRow = new ArrayList<>();
        newRow.add(1);
        res.add(newRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> preRow = res.get(i - 1);
            newRow = new ArrayList<>();
            newRow.add(1);
            for (int j = 1; j < preRow.size(); j++) {
                newRow.add(preRow.get(j - 1) + preRow.get(j));
            }
            newRow.add(1);
            res.add(newRow);
        }
        return res;
    }
}
