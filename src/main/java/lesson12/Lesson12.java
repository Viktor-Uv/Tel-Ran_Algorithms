package lesson12;

import java.util.*;

public class Lesson12 {
    // Stack, Deque, Queue
    // для моделирования оплаты покупок в магазине - Queue
    // для моделирования обслуживания пассажиров разных уровней - PriorityQueue
    // процедуру сдачи книг в библиотеке - Stack
    // автобус - люди входят через переднюю дверь, выходят через заднюю - Queue
    public static void main(String[] args) {
        // палиндром - слово, которое читается одинаково с любой стороны
        //      алла или заказ шалаш поп мадам
        System.out.println(isPalindrome("мадам")); // true
        System.out.println(isPalindrome("шалаш")); // true
        System.out.println(isPalindrome("hello")); // false

        System.out.println(reversePolishCalculator("3 4 + 2 - 6 *")); // 30

        System.out.println(checkBrackets("{()[]{}}")); // true
        System.out.println(checkBrackets("{(]}")); // false
        System.out.println(checkBrackets("[{()}]")); // true
        System.out.println(checkBrackets("{(}")); // false
        System.out.println(checkBrackets("}")); // false

        System.out.println(convert(256, 16)); // 100
        System.out.println(convert(200, 16)); // C8
        System.out.println(convert(255, 2)); // 1111 1111
        System.out.println(convert(256, 8)); // 400
        System.out.println(convert(255, 10)); // 255
        System.out.println(convert(255, 11)); // 212

    } // main

    // number conversion
     public static String convert(int number, int base) {
        String digits = "0123456789ABCDEF";
        Stack<String> stack = new Stack<>();
        while (number > 0) {
            int index = number % base; // индекс цифры
            stack.push(digits.substring(index, index + 1));
            number /= base;
        }
        String r = "";
        while (!stack.empty()) {
            r += stack.pop();
        }
        return r;
     }

    // напишем функцию для проверки правильности расстановки скобок
    // {([
    // {()[()]{}} - true
    // {(]} - false
    // [{()}] - true
    // {(} - false
    // } - false
    public static boolean checkBrackets(String w) {
        Stack<Character> brackets = new Stack<>();
        char [] chars = w.toCharArray();
        // если с самого начала встречается закрывающая скобка - return false
        if (brackets.empty() && (chars[0] == '}' || chars[0] == ']' || chars[0] == ')')) {
            return false;
        }
        for (char c : chars) {
            // если символ это открывающая скобка, заносим ее в stack
            if (c == '{' || c == '[' || c == '(') {
                brackets.push(c);
            }
            // если символ это закрывающая скобка, то убираем из вершины стека
            //      символ только в том случае если это соответсвующая открывающая скобка
            else if (
                    brackets.peek() == '{' && c == '}' ||
                        brackets.peek() == '[' && c == ']' ||
                        brackets.peek() == '(' && c == ')'
            ) {
                // в любом другом случае просто сбрасываем элемент с вершины стека
                brackets.pop();
            }
        }
        return brackets.isEmpty();
    }

    // обратно-польский калькулятор
    // обратно-польская нотация Лукасевича
    // 2 * 2 -> 2 2 *
    // (3 + 4 - 2) * 6
    // 3 4 + 2 - 6 *
    //     7 2 - 6 *
    //         5 6 *
    //             30
    public static int reversePolishCalculator(String s) {
        int r = 0;
        if (s.isEmpty()) {
            return r;
        }
        Queue<String> t = new LinkedList<>(Arrays.asList(s.split(" ")));
        int operand1 = 0;
        if(!t.isEmpty()) {
            operand1 = Integer.parseInt(t.poll());
        }
        while (t.size() > 1) {
            int operand2 = Integer.parseInt(t.poll());
            String operation = t.poll();
            switch (operation) {
                // + - * / - возможные значения operation
                case "+" -> r = operand1 + operand2;
                case "-" -> r = operand1 - operand2;
                case "*" -> r = operand1 * operand2;
                case "/" -> r = operand1 / operand2;
            }
            System.out.printf("%d %s %d = %d\n", operand1, operation, operand2, r);
            // присвоить значение r первому операнду
            operand1 = r;
        }
        return r;
    }

    // check palindrome
    public static boolean isPalindrome(String w) {
        Deque<Character> d = new ArrayDeque<>();
        // нужно добавить все символы в Deque
        // w.toCharArray() char[]
        for (char c : w.toCharArray()) {
            d.add(c);
        }
        // в цикле проверять что размер контейнера > 1
        while (d.size() > 1) {
            //      взять символы с начала и с конца и сравнить через equals
            if (!d.removeFirst().equals(d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
