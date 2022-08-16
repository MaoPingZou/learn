import com.andy.entity.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author MaoPing Zou
 * @date 2022/8/14 16:21
 */
public class TestStream {

    /**
     * 过滤出所有年龄大于10岁的用户并打印出来
     */
    @Test
    public void testFilter() {
        List<User> userList = getUserList();
        // 设置过滤条件，转换成一个流
        Stream<User> userStream = userList.stream()
                .filter(user -> user.getAge() > 10);
        // 使用forEach遍历流做打印操作
        userStream.forEach(s -> System.out.println(s));
    }

    /**
     * 打印所有用户的名字
     */
    @Test
    public void testMap1() {
        List<User> userList = getUserList();
        userList.stream()
                // map映射出所有的用户名称
                .map(user -> user.getName())
                // 遍历所有用户名称输出
                .forEach(s -> System.out.println(s));
    }

    /**
     * 给所有用户的年龄加2，并打印
     */
    @Test
    public void testMap2() {
        List<User> userList = getUserList();
        userList.stream()
                // 获取流元素
                .map(user -> user.getAge())
                // 对流中的元素进行计算
                .map(age -> age + 2)
                .forEach(age -> System.out.println(age));
    }

    /**
     * 打印去重后所有用户的名字
     */
    @Test
    public void testDistinct() {
        List<User> userList = getUserList();
        userList.stream()
                // 去重
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }

    /**
     * 根据年龄进行升序排序
     */
    @Test
    public void testSorted() {
        List<User> userList = getUserList();
        userList.stream()
                // 排序，使用Comparable
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .forEach(s -> System.out.println(s));
    }


    public List<User> getUserList() {
        // 初始化用户
        User user1 = new User(1L, "shelly", 23, null);
        User user2 = new User(2L, "lisa", 32, null);
        User user3 = new User(3L, "smith", 24, null);
        User user4 = new User(4L, "andrew", 18, null);
        User user5 = new User(5L, "duke", 19, null);
        User user6 = new User(6L, "will", 18, null);
        User user7 = new User(7L, "andy", 7, null);
        // 用于测试distinct方法
        User user8 = new User(7L, "andy", 7, null);
        return Lists.newArrayList(user1, user2, user3, user4, user5, user6, user7, user8);
    }
}
