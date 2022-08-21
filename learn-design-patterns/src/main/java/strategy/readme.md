# 了解策略模式
## 定义
> 定义一系列算法，封装每个算法，并使其可互换。策略允许算法独立于使用它的客户端而变化。
## 解释
白话解释：
> 策略模式可以让你在程序运行时选择最适合的算法。

实际举例：
> 商场打折或满减活动在现实生活中非常常见，比如打8折、7折、5折、满300减100等等活动。
> 商场可以在不同的节日采用不同的活动策略。

## 编码示例 
### 传统写法
使用一连串的if-else对不同节日做判断，然后执行相应的打折活动执行逻辑；
```java
public class FestivalActivity {
    /**
     * 传统的if-else写法
     */
    public void execute(String festival) {
        if ("愚人节".equals(festival)) {
            // 具体的业务逻辑
            System.out.println("今天是" + festival + "，全场1折，跳楼价，亏本甩卖啦！");
        } else if ("中秋节".equals(festival)) {
            // 具体的业务逻辑
            System.out.println("今天是" + festival + "，商场内所有商品都打7折，速来抢购！");
        } else if ("春节".equals(festival)) {
            // 具体的业务逻辑
            System.out.println("今天是" + festival + "，商场内商场所有商品都打3折，手慢无！");
        }
        // 省去各种各样其他的节日
        else {
            System.out.println(festival + "，商场内无打折活动");
        }
    }
}
```
这样的写法在判断条件不多，且每个判断条件内部的业务逻辑代码不多的情况下可以使用。
但是，当判断条件变多时，需要添加新的if-else，会使方法内部代码越来越臃肿。

### 策略模式写法

```java
/**
 * 打折策略接口
 */
@FunctionalInterface
public interface DiscountStrategy {
    void executeDiscount(String festival);
}
```
具体的策略实现类，实现各自不同的执行策略。
```java
/**
 * 打1折活动策略
 */
public class Discount1Strategy implements DiscountStrategy {
    @Override
    public void executeDiscount(String festival) {
        // 具体的业务逻辑
        System.out.println("今天是" + festival + "，全场1折，跳楼价，亏本甩卖啦！");
    }
}

/**
 * 打3折活动策略
 */
public class Discount3Strategy implements DiscountStrategy {
    @Override
    public void executeDiscount(String festival) {
        // 具体的业务逻辑
        System.out.println("今天是" + festival + "，商场内商场所有商品都打3折，手慢无！");
    }
}

/**
 * 打7折活动策略
 */
public class Discount7Strategy implements DiscountStrategy {
    @Override
    public void executeDiscount(String festival) {
        // 具体的业务逻辑
        System.out.println("今天是" + festival + "，商场内所有商品都打7折，速来抢购！");
    }
}
```
最后，需要一个策略上下文类，用于获取不同的策略并执行。
```java
public class StrategyContext {

    /**
     * 用于存储各个节日及其对应策略的
     * key-节日名称，value-策略接口
     */
    private static final Map<String, DiscountStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put("愚人节", new Discount1Strategy());
        STRATEGY_MAP.put("春节", new Discount3Strategy());
        STRATEGY_MAP.put("中秋节", new Discount7Strategy());
        // 省略其他各种各样的业务逻辑
    }

    /**
     * 根据不同节日获取不同活动策略实现
     */
    public void execute(String festival) {
        if (STRATEGY_MAP.get(festival) == null) {
            throw new RuntimeException(festival + "，商场内无打折活动");
        }
        STRATEGY_MAP.get(festival).executeDiscount(festival);
    }
}
```

## 策略模式的优劣势
### 优势
1. 策略可以自由实现切换；
2. 扩展性很好，添加具体的策略实现类即可；

### 劣势
1. 复杂场景下，策略实现类会很多；
2. 调用策略上下文类的一方，需要知道具体有哪些策略可以使用；

