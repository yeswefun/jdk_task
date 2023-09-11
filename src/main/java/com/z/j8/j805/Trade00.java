package com.z.j8.j805;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Trade00 {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

//        test00(transactions);
//        test01(transactions);
//        test02(transactions);
//        test03(transactions);
//        test04(transactions);
//        test05(transactions);
//        test06(transactions);
        test07(transactions);
    }

    /*
        8. Find the transaction with the smallest value.
     */
    private static void test07(List<Transaction> transactions) {
        transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .ifPresent(System.out::println);
    }

    /*
        7. What’s the highest value of all the transactions?
     */
    private static void test06(List<Transaction> transactions) {
//        transactions.stream()
//                .map(Transaction::getValue)
//                .reduce((i, j) -> i > j ? i : j)
//                .ifPresent(System.out::println);
        transactions.stream()
                //.max((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
                .max(Comparator.comparingInt(Transaction::getValue))
                .ifPresent(System.out::println);
    }

    /*
        6. Print all transactions’ values from the traders living in Cambridge.
     */
    private static void test05(List<Transaction> transactions) {
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /*
        5. Are any traders based in Milan?
     */
    private static void test04(List<Transaction> transactions) {
//        boolean result = transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        boolean flag = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                //.allMatch(city -> "Milan".equals(city))
                .allMatch("Milan"::equals);
        System.out.println(flag);
    }

    /*
        4. Return a string of all traders’ names sorted alphabetically.
     */
    private static void test03(List<Transaction> transactions) {
        String result = transactions.stream()
//                .map(Transaction::getTrader)
//                .map(Trader::getName)
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("|", (name1, name2) -> String.format("%s %s |", name1, name2));
        System.out.println(result);
    }

    /*
        3. Find all traders from Cambridge and sort them by name.
     */
    private static void test02(List<Transaction> transactions) {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                //(t1, t2) -> t1.getName().compareTo(t2.getName())
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    /*
        2. What are all the unique cities where the traders work?
     */
    private static void test01(List<Transaction> transactions) {
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /*
        1. Find all transactions in the year 2011 and sort them by value (small to high).
     */
    private static void test00(List<Transaction> transactions) {
        transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
