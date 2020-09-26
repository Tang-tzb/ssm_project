package com.Tang_tzb.dao;

import com.Tang_tzb.domain.Member;
import com.Tang_tzb.domain.Orders;
import org.apache.ibatis.annotations.*;
import com.Tang_tzb.domain.Product;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IOrdersDao {


    @Select("select * from Orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,one = @One(select = "com.Tang_tzb.dao.IProductDao.findById"))
    })

    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,one = @One(select = "com.Tang_tzb.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class,one = @One(select = "com.Tang_tzb.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class,many = @Many(select = "com.Tang_tzb.dao.ITravellerDao.findByOredersId")),

    })
    Orders findById(@RequestParam(name = "id",required = true) String id) throws Exception;
}
