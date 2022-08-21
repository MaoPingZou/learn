package strategy.pattern;

/**
 * 7折活动策略实现类
 *
 * @author MaoPing Zou
 * @date 2022/8/21 10:55
 */
public class Discount7Strategy implements DiscountStrategy {
    @Override
    public void executeDiscount(String festival) {
        // 具体的业务逻辑
        System.out.println("今天是" + festival + "，商场内所有商品都打7折，速来抢购！");
    }
}
