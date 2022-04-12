package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.PaymentTypeDao;
import com.black.food.manager.model.entity.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PaymentTypeService {

    private PaymentTypeDao paymentTypeDao;

    public PaymentType findById(final Long id){
        return paymentTypeDao.findById(id).orElseThrow();
    }

    public Set<PaymentType> findAll() {
        return paymentTypeDao.findAll();
    }

    @Autowired
    public void setPaymentTypeDao(final PaymentTypeDao paymentTypeDao) {
        this.paymentTypeDao = paymentTypeDao;
    }


}
