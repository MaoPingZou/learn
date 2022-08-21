package strategy.pattern;

/**
 * 打折策略接口
 *
 * @author MaoPing Zou
 * @date 2022/8/21 10:52
 */
@FunctionalInterface
public interface DiscountStrategy {

    void executeDiscount(String festival);
}
