package com.dubber.vip;

import com.dubber.vip.dao.ShopUserDao;
import com.dubber.vip.model.ShopUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Demo class
 *
 * @author dubber
 * @date 2018/9/8
 */

@ContextConfiguration(locations = {"classpath:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DiyOrmTest {

    @Autowired
    ShopUserDao shopUserDao;

    @Test
    public void test() {
        System.out.println(shopUserDao);
    }


    @Test
    public void testQueryRule() throws Exception {
        List<ShopUser> list = shopUserDao.selectAll();
        System.out.println(Arrays.toString(list.toArray()));
    }
}
