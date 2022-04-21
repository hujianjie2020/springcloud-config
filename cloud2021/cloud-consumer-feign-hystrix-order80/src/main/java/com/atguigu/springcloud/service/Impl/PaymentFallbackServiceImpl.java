package com.atguigu.springcloud.service.Impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackServiceImpl类的 fallback paymentInfo_OK()方法调用成功，我相当满意!";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackServiceImpl类的 fallback paymentInfo_TimeOut()方法调用超时，我相当的不满意!";
    }
}
