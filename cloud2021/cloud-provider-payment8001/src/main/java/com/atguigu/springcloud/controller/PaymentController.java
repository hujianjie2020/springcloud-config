package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***插入操作返回爽爽的叫声***" + result);

        if (result > 0) {
            return new CommonResult(200, "服务端口:" + serverPort + "新增数据成功！", result);
        } else {
            return new CommonResult(444, "新增数据成功！", null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaumentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果:{}", payment);

        if (payment != null) {
            return new CommonResult(200, "服务端口:" + serverPort + "查询成功！", payment);
        } else {
            return new CommonResult(444, "没有查询到记录!查询ID:" + id, null);
        }
    }

    /**
     * 对于注册eureka里面的微服务，可以通过服务发现来获得该服务的信息
     *
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("element~~~~~~~~~~~~~~~" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }
        return this.discoveryClient;
    }

    // ???笔记中未关闭的错误是不是因为请求路径有问题了
    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeOut() {
        System.out.println("~~~~~~~~~~~~~~paymentFeignTimeOut from port: " + serverPort);
        try {
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
