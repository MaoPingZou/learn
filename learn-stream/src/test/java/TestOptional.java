import com.andy.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 使用Optional是为了避免代码中的 if (obj != null) { } 这样范式的代码，可以采用链式编程的风格。
 *
 * @author MaoPing Zou
 * @date 2022/8/14 16:23
 */
public class TestOptional {

    // =====================Optional 的三种构造方式======================

    /**
     * 要求传入的 obj 不能是 null 值的, 否则直接报 NullPointerException 异常
     */
    @Test
    public void testOf() {
        // of 里面如果为 null 直接抛出 NullPointerException 异常
        try {
            Optional.of(null);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof NullPointerException);
        }
    }


    /**
     * ofNullable 以一种宽松的方式来构造一个 Optional 实例。传 null 进到就得到 Optional.empty(), 非 null 就调用 Optional.of(obj)
     */
    @Test
    public void testOfNullable() {
        // ofNullable 如果参数为 null，返回空的集合
        Assertions.assertFalse(Optional.ofNullable(null).isPresent());
    }

    /**
     * 返回一个空的 Optional 对象
     */
    @Test
    public void testEmpty() {
        // empty() 返回一个空的 Optional 对象
        Assertions.assertTrue(Optional.empty().isPresent() == false);
    }

    // =====================Optional 的三种构造方式======================


    // =====================Optional 中的方法======================

    /**
     * public void ifPresent(Consumer<? super T> consumer)
     * <p>
     * ifPresent 表示 Optional 中的对象存在才会执行 Consumer 接口对象中的方法
     */
    @Test
    public void testIfPresent() {

        List<String> dataList = new ArrayList<>();

        // 1. 不为null没有值的集合
        Optional.ofNullable(dataList)
                .ifPresent(data -> {
                    System.out.println("1"); // 输出 1
                    data.forEach(e -> System.out.println(e));
                });

        // 2. 为 null 的集合, 自动判断为 null, 没有执行 Consumer 接口对象中的方法
        dataList = null;

        Optional.ofNullable(dataList)
                .ifPresent(data -> {
                    System.out.println("2"); // 没有执行
                    data.forEach(e -> System.out.println(e));
                });

        // 3. 有值的集合
        dataList = new ArrayList<>();
        dataList.add("element");

        Optional.ofNullable(dataList)
                .ifPresent(data -> {
                    System.out.println("3"); // 输出 3
                    data.forEach(e -> System.out.println("ifPresent的方式 -> " + e)); // 输出集合中的元素值 element
                });

        // 4. 过去的方式, 多了 if 判断
        if (!CollectionUtils.isEmpty(dataList)) {
            dataList.forEach(a -> System.out.println("if判断的方式 -> " + a));
        }
    }

    /**
     * public Optional<T> filter(Predicate<? super T> predicate)
     * <p>
     * filter 方法可以判断对象是否符合条件，在符合某些条件的情况下才会返回
     * <p>
     * 注意：filter 不会减少集合中对象的数量，只要集合中的任意一个对象满足条件就会返回整个集合，否则返回空集合
     */
    @Test
    public void filter() {

        List<User> userList = new ArrayList<>();
        userList.add(new User(22L, "韩天琪", 21, new ArrayList<>()));
        userList.add(new User(21L, "郝玮", 31, new ArrayList<>()));
        userList.add(new User(19L, "胡亚强", 14, new ArrayList<>()));
        userList.add(new User(14L, "季恺", 25, new ArrayList<>()));
        userList.add(new User(17L, "荆帅", 38, new ArrayList<>()));
        userList.add(new User(16L, "姜有琪", 17, new ArrayList<>()));

        // 1. 只要集合中存在年龄大于 18 岁的就会返回 所有 对象
        Optional.ofNullable(userList)
                .filter(t -> t.stream().anyMatch(u -> u.getAge() > 18))
                .ifPresent(t -> {
                    t.forEach(u -> {
                        System.out.println("年龄大于18的用户：" + u.toString());
                    });
                });

        // 2. 因为集合中没有年龄大于 50 岁的，因此不会返回任何对象
        Optional.ofNullable(userList)
                .filter(t -> t.stream().anyMatch(u -> u.getAge() > 50))
                .ifPresent(t -> {
                    t.forEach(u -> {
                        System.out.println("年龄大于50的用户：" + u.toString());
                    });
                });
    }

    /**
     * public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
     * <p>
     * map 方法可以 1.将 Optional 中的对象转成其他对象; 2.在返回对象前修改对象中的属性
     */
    @Test
    public void map() {

        List<User> userList = new ArrayList<>();

        userList.add(new User(22L, "韩天琪", 21, new ArrayList<>()));
        userList.add(new User(21L, "郝玮", 31, new ArrayList<>()));
        userList.add(new User(19L, "胡亚强", 14, new ArrayList<>()));
        userList.add(new User(14L, "季恺", 25, new ArrayList<>()));
        userList.add(new User(17L, "荆帅", 38, new ArrayList<>()));
        userList.add(new User(16L, "姜有琪", 17, new ArrayList<>()));

        // 1. 返回 optional 中的对象年龄在 18 岁以上的
        Optional.ofNullable(userList)
                .map(t -> {
                    List<User> tempList = new ArrayList<>();
                    t.forEach(u -> {
                        if (u.getAge() > 18) {
                            tempList.add(u);
                        }
                    });
                    return tempList;
                })
                .ifPresent(t -> {
                    t.forEach(u -> {
                        System.out.println("大于18岁的用户:" + u.getName());
                    });
                });

        // 2. 将 optional 中的 User 对象年龄增加 1 岁
        Optional.ofNullable(userList)
                .map(t -> {
                    t.forEach(u -> {
                        u.setAge(u.getAge() + 1);
                    });
                    return t;
                })
                .ifPresent(t -> {
                    t.forEach(u -> {
                        System.out.println("年龄增加1岁:" + u.getName() + " -> " + u.getAge());
                    });
                });
    }

    /**
     * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
     * <p>
     * 将 Optional 中的对象转成 Optional 对象，或者修改对象中的属性
     */
    @Test
    public void flatMap() {

        List<User> userList = new ArrayList<>();

        userList.add(new User(22L, "韩天琪", 21, new ArrayList<>()));
        userList.add(new User(21L, "郝玮", 31, new ArrayList<>()));
        userList.add(new User(19L, "胡亚强", 14, new ArrayList<>()));
        userList.add(new User(14L, "季恺", 25, new ArrayList<>()));
        userList.add(new User(17L, "荆帅", 38, new ArrayList<>()));
        userList.add(new User(16L, "姜有琪", 17, new ArrayList<>()));

        // 1. 返回 Optional 中的对象年龄在 18 岁以上的
        Optional.ofNullable(userList)
                .flatMap(t -> {
                    List<User> tempList = new ArrayList<>();
                    t.forEach(u -> {
                        if (u.getAge() > 18) {
                            tempList.add(u);
                        }
                    });
                    // 返回 optional 对象
                    return Optional.of(tempList);
                })
                .ifPresent(t -> {
                    t.forEach(u -> {
                        System.out.println("大于18岁的用户:" + u.getName());
                    });
                });

        // 2. 将 optional 中的 User 对象的英文名改成大写
        Optional.ofNullable(userList)
                .flatMap(t -> {
                    t.forEach(u -> {
                        u.setAge(u.getAge() + 1);
                    });
                    return Optional.of(t);
                })
                .ifPresent(t -> {
                    t.forEach(u -> {
                        System.out.println("年龄增加1岁:" + u.getName() + " -> " + u.getAge());
                    });
                });
    }

    /**
     * public T orElse(T other)
     * <p>
     * 在构造 Optional 的时候，如果其中的对象为 null, 通过 orElse 方法可以给定一个默认值
     */
    @Test
    public void orElse() {
        List<String> anotherDataList = new ArrayList<>();
        anotherDataList.add("a");
        anotherDataList.add("b");
        anotherDataList.add("c");
        anotherDataList.add("d");

        // 1. 给定的对象为 null, 将会返回 orElse 方法提供的对象
        List<String> dataList = null;

        Optional.ofNullable(dataList)
                .orElse(anotherDataList)
                .forEach(t -> System.out.println("返回orElse方法中提供的数据：" + t));

        // 2. 给定的对象不为 null 且不为空, 不会返回 orElse 方法提供的对象
        dataList = new ArrayList<>();
        dataList.add("aa");
        dataList.add("ab");
        dataList.add("ac");

        Optional.ofNullable(dataList)
                .orElse(anotherDataList)
                .forEach(t -> System.out.println("不返回orElse方法中提供的数据:" + t));

        // 3. 给定的对象不为 null 但是为 empty, 不会返回 orElse 方法提供的对象
        dataList = new ArrayList<>();

        Optional.ofNullable(dataList)
                .orElse(anotherDataList)
                .forEach(t -> System.out.println(t)); // 不会输出任何元素
    }

    /**
     * public T orElseGet(Supplier<? extends T> other)
     * <p>
     * 在构造 Optional 的时候，如果其中的对象为 null, 通过 orElseGet 方法可以动态构造一个对象;
     * 与 orElse 相比，orElseGet 的参数是 Supplier 接口对象
     */
    @Test
    public void orElseGet() {
        List<String> anotherDataList = new ArrayList<>();
        anotherDataList.add("a");
        anotherDataList.add("b");
        anotherDataList.add("c");
        anotherDataList.add("d");

        // 1. 给定的对象为 null, 将会执行 orElseGet 方法提供的 Supplier 接口对象中的方法
        List<String> dataList = null;

        Optional.ofNullable(dataList)
                .orElseGet(() -> anotherDataList)
                .forEach(t -> System.out.println("使用orElseGet方法中得到的元素:" + t));

        // 2. 给定的对象不为 null 且 不为空, 不会执行 orElseGet 方法提供的 Supplier 接口对象中的方法
        dataList = new ArrayList<>();
        dataList.add("aa");
        dataList.add("ab");
        dataList.add("ac");

        Optional.ofNullable(dataList)
                .orElseGet(() -> anotherDataList)
                .forEach(t -> System.out.println("不会使用orElseGet方法中得到的元素:" + t));

        // 3. 给定的对象不为 null 但是 empty, 不会执行 orElseGet 方法提供的 Supplier 接口对象中的方法
        dataList = new ArrayList<>();
        Optional.ofNullable(dataList)
                .orElseGet(() -> anotherDataList)
                .forEach(t -> System.out.println(t)); // 不会输出任何元素

    }

    /**
     * public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X
     * <p>
     * 在使用 Optional 包装的对象前，如果对象为 null 抛出自定义的异常
     */
    @Test
    public void orElseThrow() {
        List<String> anotherDataList = new ArrayList<>();
        anotherDataList.add("a");
        anotherDataList.add("b");
        anotherDataList.add("c");
        anotherDataList.add("d");

        RuntimeException runtimeException = new RuntimeException("dataList 对象为空");

        // 1. 给定的对象为 null, 将会抛出异常
        List<String> dataList = null;
        try {
            Optional.ofNullable(dataList)
                    .orElseThrow(() -> runtimeException)
                    .forEach(t -> System.out.println("给定的对象为null:" + t));
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof RuntimeException);
        }

        // 2. 给定的对象不为 null, 不会抛出异常
        dataList = anotherDataList;
        Optional.ofNullable(dataList)
                .orElseThrow(() -> runtimeException)
                .forEach(t -> System.out.println("给定的对象不为null:" + t)); // 无任何输出
    }

    // =====================Optional 中的方法======================
}
