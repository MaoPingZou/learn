package strategy.pattern;

/**
 * 1折活动策略实现类
 *
 * @author MaoPing Zou
 * @date 2022/8/21 10:59
 */
public class Discount1Strategy implements DiscountStrategy {
    @Override
    public void executeDiscount(String festival) {
        // 具体的业务逻辑
        System.out.println("今天是" + festival + "，全场1折，跳楼价，亏本甩卖啦！");
    }
}
