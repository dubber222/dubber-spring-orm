package com.dubber.vip.dao;

import com.dubber.vip.framework.BaseDaoSupport;
import com.dubber.vip.framework.QueryRule;
import com.dubber.vip.model.ShopUser;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Demo class
 *
 * @author dubber
 * @date 2018/9/8
 */

@Repository
public class ShopUserDao extends BaseDaoSupport<ShopUser,Long> {

    @Override
    protected String getPKColumn() {
        return "id";
    }

    @Override
    @Resource(name="dataSource")
    protected void setDataSource(DataSource dataSource) {
        this.setDataSourceWrite(dataSource);
        this.setDataSourceReadOnly(dataSource);
    }

    /**
     读写分离，在xml文件中配置响应的数据源

    @Override
    @Resource(name="readOnlyDataSource")
    protected void setDataSourceReadOnly(DataSource dataSource) {
        this.setDataSourceReadOnly(dataSource);
    }

    @Resource(name="writeOnlyDataSource")
    protected void setDataSourceWriteOnly(DataSource dataSource) {
        this.setDataSourceWrite(dataSource);
    }*/




    public List<ShopUser> selectAll() throws Exception {
        QueryRule queryRule = QueryRule.getInstance();
        return super.select(queryRule);
    }

}