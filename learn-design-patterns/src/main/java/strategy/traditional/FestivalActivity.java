package strategy.traditional;

/**
 * 多种条件判断下调用不同的业务逻辑时使用if-else的传统写法
 *
 * @author MaoPing Zou
 * @date 2022/8/21 11:23
 */
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
