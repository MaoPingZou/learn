package strategy.pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略上下文类
 *
 * @author MaoPing Zou
 * @date 2022/8/21 11:01
 */
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
