package com.Tang_tzb.dao;

import com.Tang_tzb.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import java.util.List;


public interface IProductDao {
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Select("select * from product where id = #{id}")
    Product findById(String id) throws Exception;

    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
