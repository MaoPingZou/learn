package strategy.pattern;

/**
 * 3折活动策略实现类
 *
 * @author MaoPing Zou
 * @date 2022/8/21 10:56
 */
public class Discount3Strategy implements DiscountStrategy {
    @Override
    public void executeDiscount(String festival) {
        // 具体的业务逻辑
        System.out.println("今天是" + festival + "，商场内商场所有商品都打3折，手慢无！");
    }
}
