package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.OrderDao;
import com.black.food.manager.model.api.request.order.CreateOrderItemsRequest;
import com.black.food.manager.model.api.request.order.CreateOrderRequest;
import com.black.food.manager.model.entity.Order;
import com.black.food.manager.model.entity.OrderItem;
import com.black.food.manager.model.entity.OrderType;
import com.black.food.manager.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private OrderDao orderDao;

    private ProductService productService;

    public Set<OrderItem> createOrderItems(final CreateOrderRequest createOrderRequest) {

        Set<OrderItem> orderItems = new HashSet<>();

        var createOrderItemsRequest = createOrderRequest.getCreateOrderItemsRequest();

        createOrderItemsRequest.forEach(orderItem -> {
            orderItems.add(createOrderItem(orderItem));
        });

        return orderItems;
    }

    private OrderItem createOrderItem(final CreateOrderItemsRequest createOrderItemsRequest) {
        OrderItem orderItem = new OrderItem();

        var product = findProductById(createOrderItemsRequest.getProductId());

        orderItem.setProduct(product);
        orderItem.setCount(createOrderItemsRequest.getCount());

        return orderItem;
    }

    private Product findProductById(final Long productId) {
        return productService.findById(productId);
    }

    public OrderType findOrderTypeById(final Long orderTypeId) {
        return orderDao.findOrderTypeById(orderTypeId);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public Set<OrderType> findAllOrderTypes() {
        return orderDao.findAllOrderTypes();
    }

    @Autowired
    public void setOrderDao(final OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Autowired
    public void setProductService(final ProductService productService) {
        this.productService = productService;
    }
}
