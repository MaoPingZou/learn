import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * Lambda表达式是JDK8中的一个语法糖(计算机语言中添加的某种语法，这种语法对语言的功能并没有影响，但是更方便程序员使用)。
 * 它让程序员不用关注是什么对象，而是更关注对数据本身进行了什么操作。
 * <p>
 * 基本格式：(参数列表) -> {代码}
 * <p>
 * 省略规则：
 * 1、参数类型可以省略
 * 2、方法体只有一句代码时大括号return和末尾的分号可以省略
 * 3、方法只有一个参数时，小括号可以省略
 *
 * @author MaoPing Zou
 * @date 2022/8/14 16:21
 */
public class TestLambda {

    @Test
    public void test() {
        // 匿名内部类的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类写法！");
            }
        }).start();

        // lambda的简化写法
        new Thread(() -> {
            System.out.println("使用Lambda表达式的写法！");
        }).start();
    }

    @Test
    public void testExample1() {
        // 使用匿名内部类的方式调用自定义的一个方法
        int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });

        // 简化成Lambda表达式
        int i1 = calculateNum((int a, int b) -> {
            return a * b;
        });
        // 进一步简化 (Lambda能自动推断参数类型)
        int i2 = calculateNum((a, b) -> a * b);
    }

    /**
     * IntBinaryOperator 是一个函数式接口，类上有注解标识：@FunctionalInterface
     */
    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    @Test
    public void testExample2() {
        // 匿名内部类写法
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value > 3;
            }
        });

        // lambda表达式简化后写法
        printNum((int value) -> {
            return value > 3;
        });
        // 进一步简化
        printNum((int value) -> value > 3);
        // 再进一步简化 （自动推断类型）
        printNum(value -> value > 3);
    }

    /**
     * IntPredicate 也是一个函数式接口，类上有注解标识：@FunctionalInterface
     */
    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        for (int r : arr) {
            if (predicate.test(r)) {
                System.out.printf("符合判断条件的数字：%s", r);
            }
        }
    }

    @Test
    public void testExample3() {
        // 匿名内部类写法
        typeConverter(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });

        // lambda表达式简化写法
        typeConverter((String s) -> {
            return Integer.valueOf(s);
        });
        // 简化
        typeConverter((String s) -> Integer.valueOf(s));
        // 再简化
        typeConverter(s -> Integer.valueOf(s));
    }

    /**
     * 类型转换器方法
     * Function 也是一个函数式接口
     */
    public static <R> R typeConverter(Function<String, R> function) {
        String str = "1234";
        return function.apply(str);
    }


    @Test
    public void testExample4() {
        // 匿名内部类方式
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println("当前值是：" + value);
            }
        });

        // lambda写法简化
        foreachArr(value -> System.out.println("当前值是：" + value));
    }

    /**
     * IntConsumer 也是一个函数式接口
     */
    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        for (int i : arr) {
            consumer.accept(i);
        }
    }


}
