package com.z.re;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
JUnit
    类: public class
        public class XxxTest {}

    方法: non-static
        @Test
        public void testXxx() {}

regexBuddy4
 */
public class Re00Test {

    /*
        底层也是 Pattern
            boolean String#matches(regex)
            replaceAll(regex, replacement)
            replaceFirst(regex, replacement)
            String[] split(regex, limit=0)
     */
    @Test
    public void testString() {
    }

    @Test
    public void testPredicate() {
        Pattern p = Pattern.compile("\\w+");
        Predicate<String> predicate = p.asPredicate();
//        boolean b = predicate.test("你好世界"); // false
        boolean b = predicate.test("你好abc世界");// true
        System.out.println(b);
    }

    /*
        默认模式: 全局模式
     */
    @Test
    public void testMode5() {
        String regex = "1[3-9]\\d{9}"; // 没有 ^ 限定符
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("13939399439\n" +
                "13838389438\n" +
                "13636369436");
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    /*
        Pattern.MULTILINE
            正则中使用到 ^, $, 并且需要进行多行匹配

        13939399439
        13838389438
        13636369436
     */
    @Test
    public void testMode4() {
        String regex = "^1[3-9]\\d{9}";
//        Pattern p = Pattern.compile(regex);
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher("13939399439\n" +
                "13838389438\n" +
                "13636369436");
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    @Test
    public void testMode3() {
//        String regex = "<p>(.*?)</p>";
        String regex = "<P>(.*?)</p>"; // 前面的 p 是大写的
//        Pattern p = Pattern.compile(regex);
//        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Pattern p = Pattern.compile(regex, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("<p>\n" +
                "\thaha\n" +
                "</p>");
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    @Test
    public void testMode2() {
        String regex = "<p>(.*?)</p>";
//        Pattern p = Pattern.compile(regex);
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher m = p.matcher("<p>\n" +
                "\thaha\n" +
                "</p>");
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    /*
        模式
            i
            s
            m
     */
    @Test
    public void testMode() {
        String regex = "apple";
//        Pattern p = Pattern.compile(regex);
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("apple - Apple");
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    @Test
    public void testSplit3() {
        String text = "789,1,2;456,3,4;5,123;";
        String regex = "[,;]";
        Pattern p = Pattern.compile(regex);
        Stream<String> ss = p.splitAsStream(text);
        List<Integer> list = ss.sorted()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testSplit2() {
        String text = "1,2,3,4,5;123;456;789";
        String regex = "[,;]";
        Pattern p = Pattern.compile(regex);
        String[] strings = p.split(text, 5);
        System.out.println(Arrays.toString(strings));
    }

    /*
        split
     */
    @Test
    public void testSplit() {
        String text = "1,2,3,4,5;123;456;789";
        String regex = "[,;]";
        Pattern p = Pattern.compile(regex);
        String[] strings = p.split(text);
        System.out.println(Arrays.toString(strings));
    }

    /*
        替换
     */
    @Test
    public void testReplace3() {
        String sql = "insert into tb_user(name, sex) values(#{ name }, #{sex});";
        System.out.println(sql.indexOf("#") + " -- " + sql.indexOf("}"));
        System.out.println(sql.lastIndexOf("#") + " -- " + sql.lastIndexOf("}"));
        System.out.println("--------------- before");

        String regex = "#\\{\\s*(\\w+)\\s*\\}";
        Pattern p = Pattern.compile(regex);

        String[][] arr = new String[2][];
        arr[0] = new String[]{"张三", "m"};
        arr[1] = new String[]{"李四", "f"};

        for (String[] strings : arr) {
            Matcher m = p.matcher(sql);
            int index = 0;
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                //追加替换后的内容到sb中
                //jdk9 才支持使用 StringBuilder
                m.appendReplacement(sb, strings[index]);
                int start = m.start();
                int end = m.end();
                System.out.println(start + " -- " + end + " -- " + sql.substring(start, end));
                index++;
            }
            //将字符串内容追加到sb中
            m.appendTail(sb);
            System.out.println(sb);
        }
    }

    /*
        命名分组捕获 - 外部反射引用
     */
    @Test
    public void testReplace2() {
        String text = "哈哈哈 嘻嘻嘻 呵呵呵";
        String regex = "(?<repeat>.)\\k<repeat>+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(
                    m.group() + ", group(0): " + m.group(0)
                            + ", group(1): " + m.group(1) // m.group(1) 就是 regex 的 \\1 引用的分组捕获内容
                            + ", group(\"repeat\"): " + m.group("repeat")
            );
        }
        String result = m.replaceAll("${repeat}");
        System.out.println(result);
    }

    /*
        匿名分组捕获 - 外部反射引用
     */
    @Test
    public void testReplace() {
        String text = "哈哈哈 嘻嘻嘻 呵呵呵";
        String regex = "(.)\\1+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(
                    m.group() + ", group(0): " + m.group(0)
                            + ", group(1): " + m.group(1) // m.group(1) 就是 regex 的 \\1 引用的分组捕获内容
            );
        }
        String result = m.replaceAll("$1");
        System.out.println(result);
    }

    /*
        11, count: 1, group(0): 11, group(1): 1, group("num"): 1
        22, count: 1, group(0): 22, group(1): 2, group("num"): 2
     */
    @Test
    public void testGroup5() {
        String text = "112233445566";
        String regex = "(?<num>\\d+)\\k<num>";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(
                    m.group() + ", count: " + m.groupCount()
                            + ", group(0): " + m.group(0)
                            + ", group(1): " + m.group(1)
                            + ", group(\"num\"): " + m.group("num")
            );
        }
    }

    /*
        11, count: 1, group(0): 11, group(1): 1
        22, count: 1, group(0): 22, group(1): 2

        m.groupCount(); // 不包括 group(0), 匿名分组捕获编号是从 1 开始算的
        m.group() == m.group(0)
     */
    @Test
    public void testGroup4() {
        String text = "112233445566";
        String regex = "(\\d+)\\1";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(
                    m.group() + ", count: " + m.groupCount()
                            + ", group(0): " + m.group(0)
                            + ", group(1): " + m.group(1)
            );
        }
    }

    /*
        命名分组捕获
            分组名称不能使用 _ 下划线
     */
    @Test
    public void testGroup3() {
        String[] target = {
                "<li><a href=\"http://www.x.com\">haha</a></li>",
                "<li><a href='http://www.y.com'>hehe</a></li>"
        };
        String regex = "<(?<tagLi>.*?)><(?<tagA>.*?) href=(?<tagQuote>['\"])(.*?)\\k<tagQuote>>(.*?)</\\k<tagA>></\\k<tagLi>>";
        Pattern p = Pattern.compile(regex);
        for (String t : target) {
            Matcher m = p.matcher(t);
            System.out.println("--- " + t + " -- " + m.groupCount()); //TODO: 5
            while (m.find()) {
                System.out.println(m.group());
                System.out.println(m.group(1) + " -- " + m.group("tagLi"));
                System.out.println(m.group(2) + " -- " + m.group("tagA"));
                System.out.println(m.group(3) + " -- " + m.group("tagQuote"));
            }
        }
    }

    /*
        匿名分组捕获
     */
    @Test
    public void testGroup2() {
        String[] target = {
                "<li><a href=\"http://www.x.com\">haha</a></li>",
                "<li><a href='http://www.y.com'>hehe</a></li>"
        };
        String regex = "<(.*?)><(.*?) href=(['\"])(.*?)\\3>(.*?)</\\2></\\1>";
        Pattern p = Pattern.compile(regex);
        for (String t : target) {
            Matcher m = p.matcher(t);
            System.out.println("--- " + t);
            while (m.find()) {
                System.out.println(m.group());
                System.out.println(m.group(1));
                System.out.println(m.group(2));
                System.out.println(m.group(3));
            }
        }
    }

    /*
        matches
        find
        group
     */
    @Test
    public void testGroup() {
        String[] target = {"123", "abc", "321abc456"};
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        for (String t : target) {
            Matcher m = p.matcher(t);

            System.out.println("--- " + t);
            while (m.find()) {
                System.out.println(m.group()); // m.group(0), 分组编号为0表示正则匹配的结果
            }
//            boolean b = m.find();
//            System.out.println(b);
//            System.out.println(m.group());
        }
    }

    /*
        boolean Matcher#matches()
            ^regex$
     */
    @Test
    public void testFind() {
        String[] target = {"123", "abc", "321abc123"};
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        for (String t : target) {
            Matcher m = p.matcher(t);
            /*
                true
                false
                true
             */
//            System.out.println(m.find());
            /*
                true
                false
                false
             */
//            System.out.println(m.matches());
            /*
                true - false (find的结果受到了matches的影响)
                false - false
                false - true
             */
            System.out.println(m.matches() + " - " + m.find());
        }
    }

    /*
        Java 中 字符串 的 \ 有特殊含义
            正则中的 \d, java中 \\d
            正则中的 \\d, java中 \\\\d
     */
    @Test
    public void testMatches() {
        String[] target = {"123", "abc", "321"};
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        for (String t : target) {
            Matcher m = p.matcher(t);
            boolean b = m.matches();
            System.out.println(b);
        }
    }

    @Test
    public void testPattern3() {
        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaab");
        boolean b = m.matches();
        System.out.println(b);
    }

    /*
        boolean Pattern.matches(regex, text);
            整体匹配
     */
    @Test
    public void testPattern2() {
        boolean b = Pattern.matches("\\d+", "123abc321");
        System.out.println(b);
    }

    @Test
    public void testPattern() {
        boolean b = Pattern.matches("\\d+", "123");
        System.out.println(b);
    }
}
