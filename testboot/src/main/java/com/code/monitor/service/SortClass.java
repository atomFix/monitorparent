package com.code.monitor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SortClass {

    /*请完成下面这个函数，实现题目要求的功能
 当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^

 给定一个帐户列表，每个元素accounts [i]是一个字符串列表，
 其中第一个元素accounts [i] [0]是账户名称，其余元素是这个帐户的电子邮件。
 现在，我们想合并这些帐户。
 如果两个帐户有相同的电子邮件地址，
 则这两个帐户肯定属于同一个人。
 请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，
 因为两个不同的人可能会使用相同的名称。
 一个人可以拥有任意数量的账户，但他的所有帐户肯定具有相同的名称。
 合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按字典序排序后的电子邮件。
 帐户本身可以按任何顺序返回。

 输入：
 [

     ["John", "johnsmith@mail.com", "john00@mail.com"],

     ["John", "johnnybravo@mail.com"],

     ["John", "johnsmith@mail.com", "john_newyork@mail.com"],

     ["Mary", "mary@mail.com"]

 ]

 *****************************

 输出：
 [

     ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],

     ["John", "johnnybravo@mail.com"],

     ["Mary", "mary@mail.com"]

 ]

 *****************************
 解释:

 第一个第三个John是同一个人的账户，因为这两个账户有相同的邮箱："johnsmith@mail.com".

 剩下的两个账户分别是不同的人。因为他们没有和别的账户有相同的邮箱。



 样例输入：
 4
 John,johnsmith@mail.com,john00@mail.com
 John,johnnybravo@mail.com
 John,johnsmith@mail.com,john_newyork@mail.com
 Mary,mary@mail.com


 John,john00@mail.com,john_newyork@mail.com,johnsmith@mail.com
 John,johnnybravo@mail.com
 Mary,mary@mail.com



 ******************************开始写代码******************************/
    static List<List<String>> mergeAccount(List<List<String>> accounts) {
        // 在此处编写实现代码逻辑
        HashMap<String, List<List<String>>> map = new HashMap<>();

        ArrayList<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            String name = accounts.get(i).get(0);
            if (!map.containsKey(name)) {
                ArrayList<List<String>> lists1 = new ArrayList<>();
                lists1.add(accounts.get(i));
                map.put(name, lists1);
            } else {
                List<List<String>> nameList = map.get(name);
                for (int j = 1; j < accounts.get(i).size(); j++) {
                    for (List<String> strings : nameList) {
                        for (int l = 0; l < strings.size(); l++) {
                            if (strings.contains(accounts.get(i).get(j))) {
                                map.get(name).set(i, merge(map.get(name).get(i), strings));
                                break;
                            }
                        }
                    }
                }
            }
        }
        map.forEach((key, value) -> {
            lists.addAll(value);
        });
        return lists;
    }

    static List<String> merge(List<String> old1, List<String> old2) {
        ArrayList<String> strings = new ArrayList<>(old1);
        for (int i = 1; i < old2.size(); i++) {
            if (!strings.contains(old1.get(i))) {
                strings.add(old1.get(i));
            }
        }
        return strings;
    }
/******************************结束写代码******************************/

}