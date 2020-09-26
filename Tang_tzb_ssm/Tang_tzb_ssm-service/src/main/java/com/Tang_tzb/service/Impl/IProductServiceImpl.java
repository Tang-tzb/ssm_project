package com.Tang_tzb.service.Impl;

import com.Tang_tzb.dao.IProductDao;
import com.Tang_tzb.domain.Product;
import com.Tang_tzb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {

    @Autowired
    IProductDao iProductDao;
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }


    public void save(Product product) throws Exception{
        iProductDao.save(product);
    }
}
