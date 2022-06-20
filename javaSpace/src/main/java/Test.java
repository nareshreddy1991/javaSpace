import java.io.CharConversionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = List.of(10, 20, 30, 55, 22, 8, 4);
        List<Integer> results = list.stream()
//                .filter(e -> isDivisible(e))
                .filter(e -> e % 4 == 0)
                .collect(Collectors.toList());
        Stream<Integer> stream = list.stream();
        list.stream().mapToInt(e -> e);
        int sum = list.stream().mapToInt(e -> e).sum();
        System.out.println("Sum:" + sum);

        results.forEach(System.out::println);
        boolean validate = validate("(()))");
        System.out.println("validation output:" + validate);

    }


    private static boolean validate(String str) {// (())
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        boolean isValid = true;
        int i = 0;
        for (; i < chars.length; i++) {
            if (chars[i] == '(')
                stack.push(Character.valueOf(chars[i]));
            else if (chars[i] == ')' && !stack.empty()) {
                Character peek = stack.pop();
                if (!peek.equals('(')) {
                    isValid = false;
                    break;
                }
            }
        }

        return isValid && stack.empty();
    }

    private static boolean isDivisible(int n) {
        while (n > 0) {
            n = n - 4;
        }
        if (n == 0)
            return true;
        return false;
    }
}

class Pair<K, V> {
    K key;
    V val;

    Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }
}

class Calculator<T extends Number> {

    public T add(T a, T b) {
        Double a1, a2;
        a1 = (Double) a;
        a2 = (Double) b;
        double sum = Double.sum(a1, a2);
//        return (T) Double.valueOf(sum);
        return (T) Double.valueOf(a.doubleValue()+b.doubleValue());
    }

    public T product(T a, T b) {
        double a1 = (Double) a;
        double a2 = (Double) b;
        return (T) Double.valueOf(a1 * a2);
    }
}