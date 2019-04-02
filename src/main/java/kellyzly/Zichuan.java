package kellyzly;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;


public class Zichuan {


    public static void main(String[] args) {
        String s = "qrsvbspk";
        //baolifa(s);
        // int size = huadongchuangkou(s);
        int size = youhuahuadongchuangkou(s);
        System.out.println("max zichuan:" + size);
    }

    // 求解不重复的子串
    // 暴力求解 2 个for 逐个检查子串（如果新元素和原来的串中有重复就停止这次搜索，检查与当前maxResult谁更大）
    //  用到的新的地方；
    // linkedhashmap  keep the sequence of the map as the key is inserted （ 方便输出看每个子串的结果）
    //  tmpResult.keySet().forEach(p->System.out.println(p));
    // 时间复杂度O(n^2) 2遍循环o（n^2)
    public static int baolifa(String s) {
        LinkedHashMap tmpResult = new LinkedHashMap();
        int maxResult = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {

                if (!tmpResult.containsKey(s.charAt(j))) {
                    tmpResult.put(s.charAt(j), s.charAt(j));

                } else {

                    if (tmpResult.size() >= maxResult) {
//                       System.out.println("current tmpResult:");
//                       tmpResult.keySet().forEach(p->System.out.println(p));
                        maxResult = tmpResult.size();
                    }

                    tmpResult.clear();
                    break;

                }
            }
        }

        // 针对 输入为“”的测试用例，输出为1
        if (tmpResult.size() >= maxResult) {
            maxResult = tmpResult.size();
        }
        System.out.println("max zi chuan length:" + maxResult);
        return maxResult;
    }

    // 滑动窗口 ， [i,j] 里面有没有重复子串，如果没有继续[i,j+1）， 如果有则把[i+1, j)
    //时间复杂度：O(2n) = O(n)O(2n)=O(n)，在最糟糕的情况下，每个字符将被 i 和 j 访问两次。  这个地方我是没有想明白
    // 什么情况 每个字符会被i,j 访问2次。
    public static int huadongchuangkou(String s) {
        int i = 0;
        int j = 0;
        int n = s.length();
        Set<Character> maxSet = new HashSet<Character>();
        int max = 0;
        while (i < n && j < n) {
            // 如果滑动窗口 s[j] 的字符已经包含在当前的子串中，就把滑动窗口i向右移动(i++)，注意这个时候[i,j-1] 就是和子串
            // (j-i)就是子串长度.  i+_
            if (maxSet.contains(s.charAt(j))) {
                maxSet.remove(s.charAt(i));
                max = Math.max(max, j - i);
                i++;
            } else {
                //如果滑动窗口s{j] 的字符不包含在当前的子串中，就把j 向右移动（j++）, 让窗口滑动继续。
                maxSet.add(s.charAt(j));
//                System.out.println("maxSet:");
//                maxSet.stream().forEach(p->System.out.println(p));

            }
        }

        // 针对 输入为“”的测试用例，输出为1
        max = Math.max(max, maxSet.size());
        return max;
    }


    //优化的滑动窗口
    // 注意 i+1 后， 没有说s[i+1, j] 就是不重复子串，可能和s.charAt(j) 重复不是charAt(i)
    //  优化的滑动窗口就是把这个和当前j 重复的字符 another_j 找到，然后 i = another_j+1;
    public static int youhuahuadongchuangkou(String s) {
        int i = 0;
        int j = 0;
        int n = s.length();
        Set<Character> maxSet = new HashSet<Character>();
        HashMap<Character, Integer> position = new HashMap<>();
        int max = 0;
        for (j = 0; j < n; j++) {
            // 如果滑动窗口 s[j] 的字符已经包含在当前的子串中，就把滑动窗口i向右移动(i++)，注意这个时候[i,j-1] 就是和子串
            // (j-i)就是子串长度.  新i 的位置  another_j+1
            System.out.println("i=" + i + " j=" + j);
            if (maxSet.contains(s.charAt(j))) {
                int another_j = position.get(s.charAt(j));
                max = Math.max(max, j - i);

                for (int m = i; m < another_j; m++) {
                    maxSet.remove(s.charAt(m));
                }
                i = another_j + 1;

            } else {
                //如果滑动窗口s{j] 的字符不包含在当前的子串中，就把j 向右移动（j++）, 让窗口滑动继续。
                position.put(s.charAt(j), j);
                maxSet.add(s.charAt(j));
                System.out.println("maxSet:");
                maxSet.stream().forEach(p -> System.out.println(p));

            }
        }


        // 针对 输入为“”的测试用例，输出为1
        max = Math.max(maxSet.size(), max);
        return max;
    }
}
