package com.dubber.vip.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author dubber
 * @date 2018/5/10
 */

@Entity
@Table(name = "shop_user")
public class ShopUser implements Serializable {
    private Long userid;
    private String username;
    private String PASSWORD;
    private Integer usertype;
    private Long createtime;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
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

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
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
