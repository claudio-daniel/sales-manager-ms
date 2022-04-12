package com.my.admin.app.commerce.model.api.dto;

import java.util.Set;

public class DishDto implements Comparable {

    private Long id;

    private ProductDto mainDish;

    private Set<ProductDto> toppings;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public ProductDto getMainDish() {
        return mainDish;
    }

    public void setMainDish(final ProductDto mainDish) {
        this.mainDish = mainDish;
    }

    public Set<ProductDto> getToppings() {
        return toppings;
    }

    public void setToppings(final Set<ProductDto> toppings) {
        this.toppings = toppings;
    }

    @Override
    public int compareTo(final Object o) {
        if (o instanceof DishDto) {
            DishDto compareDish = (DishDto) o;
            return this.id.compareTo(compareDish.getId());
        }else {
            return 0;
        }
    }
}
