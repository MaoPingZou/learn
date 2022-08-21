package strategy;

import strategy.pattern.StrategyContext;
import strategy.traditional.FestivalActivity;

/**
 * @author MaoPing Zou
 * @date 2022/8/21 11:11
 */
public class Main {

    public static void main(String[] args) {
        // 调用传统实现类
        System.out.println("-----------调用传统实现类-------------");
        FestivalActivity activity = new FestivalActivity();
        System.out.println("愚人节来啦！");
        activity.execute("愚人节");
        System.out.println("中秋节来啦！");
        activity.execute("中秋节");
        System.out.println("春节来啦！");
        activity.execute("春节");

        // 调用策略上下文类
        System.out.println("-----------调用策略上下文类-------------");
        StrategyContext context = new StrategyContext();
        System.out.println("愚人节来啦！");
        context.execute("愚人节");
        System.out.println("中秋节来啦！");
        context.execute("中秋节");
        System.out.println("春节来啦！");
        context.execute("春节");
    }
}
