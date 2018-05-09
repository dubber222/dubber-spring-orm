package com.dubber.vip.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dubber on 2018/5/10.
 */

@Entity
@Table(name = "shop_user")
public class ShopUser {
    private long userid;
    private String username;
    private String PASSWORD;
    private int usertype;
    private long createtime;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "ShopUser{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", usertype=" + usertype +
                ", createtime=" + createtime +
                '}';
    }
}
