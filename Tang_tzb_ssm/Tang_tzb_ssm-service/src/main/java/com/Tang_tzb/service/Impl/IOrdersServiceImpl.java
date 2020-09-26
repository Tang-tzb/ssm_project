package com.Tang_tzb.service.Impl;

import com.Tang_tzb.dao.IOrdersDao;
import com.Tang_tzb.domain.Orders;
import com.Tang_tzb.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {

        return iOrdersDao.findById(ordersId);
    }
}
