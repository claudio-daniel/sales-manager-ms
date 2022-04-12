package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.SaleDao;
import com.black.food.manager.model.api.request.client.CreateClientRequest;
import com.black.food.manager.model.api.request.order.CreateOrderRequest;
import com.black.food.manager.model.api.request.sale.CreateSaleRequest;
import com.black.food.manager.model.api.response.SaleResponse;
import com.black.food.manager.model.entity.*;
import com.black.food.manager.model.transformer.SaleTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    private SaleDao saleDao;

    private CashService cashService;
    private ClientService clientService;
    private OrderService orderService;
    private PaymentTypeService paymentTypeService;
    private ExceptionService exceptionService;

    private SaleTransformer saleTransformer;

    public Set<SaleResponse> salesFilter(final ZonedDateTime dateFrom, final ZonedDateTime dateTo, final Long userId,
                                         final Integer clientDni, final Long productTypeId, final Long productId, final Long paymentTypeId) {


        var saleFiltered = saleDao.salesFilter(dateFrom, dateTo, userId, clientDni, productId, productTypeId, paymentTypeId);

        saleFiltered.stream()
                .findFirst().orElseThrow(() -> exceptionService.throwNotFoundException("No se encontraron ventas para los filtros seleccionados."));

        return saleFiltered
                .stream()
                .map(SaleTransformer.FROM_SALE_TO_SALE_RESPONSE)
                .collect(Collectors.toSet());
    }

    @Transactional
    public SaleResponse save(final CreateSaleRequest createSaleRequest) {
        Sale sale = new Sale();

        Cash cash = createCash(createSaleRequest.getCashId());
        Client client = createClient(createSaleRequest.getCreateClientRequest());
        Order order = createOrder(createSaleRequest.getCreateOrderRequest());
        PaymentType paymentType = createPaymentType(createSaleRequest.getPaymentTypeId());

        sale.setCash(cash);
        sale.setOrder(order);
        sale.setClient(client);
        sale.setDate(getNowZonedDateTime());
        sale.setPaymentType(paymentType);
        sale.setPayment(createSaleRequest.getPayment());
        sale.setReturned(createSaleRequest.getReturned());
        sale.setTotal(createSaleRequest.getTotal());

        var savedSale = saveSale(sale);

        return saleTransformer.transformSaleToSaleResponse(savedSale);
    }

    @Transactional
    public Sale saveSale(final Sale sale) {
        Sale saleSaved;
        try {
            saleSaved = saleDao.save(sale);
        } catch (Exception e) {
            throw exceptionService.throwConflictException("Ocurrío un error al registrar la venta.");
        }
        return saleSaved;
    }

    public Cash createCash(final Long cashId) {
        return cashService.findById(cashId);
    }

    public Client createClient(final CreateClientRequest createClientRequest) {
        return clientService.findByDniOrElseSave(createClientRequest);
    }

    public Order createOrder(final CreateOrderRequest createOrderRequest) {

        Set<OrderItem> orderItems = createOrderItems(createOrderRequest);
        OrderType orderType = findOrderTypeById(createOrderRequest.getOrderTypeId());

        Order order = new Order();
        order.setOrderItems(new ArrayList<>(orderItems));
        order.setOrderType(orderType);

        return order;
    }

    private Set<OrderItem> createOrderItems(final CreateOrderRequest createOrderRequest) {
        return orderService.createOrderItems(createOrderRequest);
    }

    public PaymentType createPaymentType(final Long paymentTypeId) {
        return paymentTypeService.findById(paymentTypeId);
    }

    private ZonedDateTime getNowZonedDateTime() {
        return ZonedDateTime.now();
    }

    private OrderType findOrderTypeById(final Long orderTypeId) {
        return orderService.findOrderTypeById(orderTypeId);
    }

    @Autowired
    public void setCashService(final CashService cashService) {
        this.cashService = cashService;
    }

    @Autowired
    public void setSaleDao(final SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    @Autowired
    public void setClientService(final ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setOrderService(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setPaymentTypeService(final PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @Autowired
    public void setSaleTransformer(final SaleTransformer saleTransformer) {
        this.saleTransformer = saleTransformer;
    }

    @Autowired
    public void setExceptionService(final ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }
}
