package com.practice.spring_splunk.controller;

import com.practice.spring_splunk.dto.JobStatus;
import com.practice.spring_splunk.dto.Order;
import com.practice.spring_splunk.service.JobService;
import com.practice.spring_splunk.service.OrderService;
import com.practice.spring_splunk.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService service;

    @Autowired
    private JobService jobService;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        logger.info("OrderController:placeOrder persist order request {}", Mapper.mapToJsonString(order));
        Order addOrder = service.addOrder(order);
        logger.info("OrderController:placeOrder response from service {}", Mapper.mapToJsonString(addOrder));
        return addOrder;
    }

    @GetMapping
    public List<Order> getOrders() {
        List<Order> orders = service.getOrders();
        logger.info("OrderController:getOrders response from service {}", Mapper.mapToJsonString(orders));
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id) {
        logger.info("OrderController:getOrder fetch order by id {}", id);
        Order order = service.getOrder(id);
        logger.info("OrderController:getOrder fetch order response {}", Mapper.mapToJsonString(order));
        return order;
    }
    @PostMapping("/job")
    public JobStatus processOrder(@RequestBody Order order) {
        logger.info("OrderController:processOrder  order request {}", Mapper.mapToJsonString(order));
        JobStatus jobStatus = jobService.process(order);
        logger.info("current job status {}", jobStatus);
        return jobStatus;
    }

}
