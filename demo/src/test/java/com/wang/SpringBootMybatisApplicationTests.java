package com.wang;


import com.wang.util.EhCacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringBootMybatisApplicationTests {
//
//  @Test
//  public void contextLoads() {
//  }
//
//}
import org.springframework.beans.factory.annotation.Autowired;
import com.wang.entity.User;
import com.wang.dao.UserMapper;


@RunWith(SpringRunner.class)
@SpringBootTest()
//相当于  --spring.profiles.active=dev
//@ActiveProfiles(value="dev")
public class SpringBootMybatisApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("张飞");
        user.setPhone("12312312312");
        user.setPassword("123456");
        mapper.insert(user);
        System.out.println("插入用户信息" + user.getUserName());
    }

    @Test
    public void testSelect() {
        User user = mapper.selectByPrimaryKey(7);
        System.out.println("查找用户成功：" + user);
    }

    @Test
    public void TestCahce()
    {
        //string测试
        EhCacheUtil.getInstance().put("ehcache","userEch","test001");
        String val = (String) EhCacheUtil.getInstance().get("ehcache", "userEch");
        System.out.println(val);
    }
}