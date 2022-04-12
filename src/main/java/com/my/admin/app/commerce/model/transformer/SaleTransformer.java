package com.my.admin.app.commerce.model.transformer;

import com.black.food.manager.model.api.response.SaleResponse;
import com.black.food.manager.model.entity.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleTransformer {

    public static final CommonTransformer<Sale, SaleResponse> FROM_SALE_TO_SALE_RESPONSE;

    static {
        FROM_SALE_TO_SALE_RESPONSE = CommonTransformer.mapping(Sale.class, SaleResponse.class)
                .constructor(SaleResponse::new)
                .fields(Sale::getId, SaleResponse::setId)
                .fields(Sale::getPayment, SaleResponse::setPayment)
                .fields(Sale::getReturned, SaleResponse::setReturned)
                .fields(Sale::getDate, SaleResponse::setDate)
                .fields(sale -> sale.getOrder().getId().toString(), SaleResponse::setOrderId)
                .fields(sale -> sale.getCash().getUser().getUserName(), SaleResponse::setUserName)
                .fields(sale -> CashTransformer.FROM_CASH_TO_BASIC_CASH_RESPONSE.apply(sale.getCash()), SaleResponse::setCash);
    }

    public SaleResponse transformSaleToSaleResponse(final Sale sale) {
        var saleResponse = new SaleResponse();

        saleResponse.setId(sale.getId());

        return saleResponse;
    }
}
