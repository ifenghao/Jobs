package interview;

import java.util.*;

/**
 * 有一个string 数组/集合，去掉中间相包含的结果，保留较长的
 * 例如输入 ["足球", "踢足球", "打篮球","投篮球","篮球","游泳"]
 * 输出 ["踢足球", "打篮球", "投篮球", "游泳"]
 */
public class removecontains {
    static List<String> solution(String[] strings) {
        List<String> resList = new ArrayList<>();
        boolean addflag; // 添加到结果的标记
        for (String s : strings) {
            addflag = true;
            Iterator<String> resIter = resList.iterator(); // 使用迭代器可以实现边遍历边删除
            while (resIter.hasNext()) {
                String rs = resIter.next();
                if (s.length() > rs.length() && s.contains(rs)) { // 满足长且包含，结果中短的全部删除
                    resIter.remove();
                }
                if (rs.contains(s)) { // 反过来长的已经在结果中，短的不再进入结果
                    addflag = false;
                    break;
                }
            }
            if (addflag) {
                resList.add(s);
            }
            System.out.println(s + ":" + resList);
        }
        return resList;
    }

    public static void main(String[] args) {
        String[] strings = {"足球", "踢足球", "打篮球", "投篮球", "篮球", "游泳", "篮球游泳"};
        System.out.println(solution(strings));
    }
}