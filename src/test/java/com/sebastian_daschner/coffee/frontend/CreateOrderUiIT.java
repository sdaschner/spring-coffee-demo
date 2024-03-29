package com.sebastian_daschner.coffee.frontend;

import com.sebastian_daschner.UiTest;
import com.sebastian_daschner.coffee.frontend.views.IndexView;
import com.sebastian_daschner.coffee.frontend.views.OrderView;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateOrderUiIT {

    private final CoffeeShopUI coffeeShop = new CoffeeShopUI();

    @BeforeEach
    void setUp() {
        coffeeShop.init();
    }

    @Test
    @AllureId("60")
    @UiTest
    @Feature("Order coffee")
    @Owner("janedoe")
    void create_coffee_order() {
        IndexView index = coffeeShop.index();
        int numberOrders = index.getListedOrders().size();

        OrderView orderView = index.followCreateOrderLink();
        assertThat(orderView.getPageHeader()).isEqualTo("Order coffee");
        index = orderView.orderCoffee("Espresso", "Colombia");

        List<Order> orders = index.getListedOrders();
        assertThat(orders).hasSize(numberOrders + 1);
        Order order = orders.get(orders.size() - 1);
        assertThat(order.type).isEqualTo("Espresso");
        assertThat(order.origin).isEqualTo("Colombia");
    }

    @Test
    @AllureId("59")
    @UiTest
    @Feature("Order coffee")
    @Owner("janedoe")
    void create_coffee_order_keyboard_select() {
        IndexView index = coffeeShop.index();
        int numberOrders = index.getListedOrders().size();

        OrderView orderView = index.followCreateOrderLink();
        assertThat(orderView.getPageHeader()).isEqualTo("Order coffee");
        index = orderView.orderCoffeeSelectWithKeyboard("Espresso", "Colombia");

        List<Order> orders = index.getListedOrders();
        assertThat(orders).hasSize(numberOrders + 1);
        Order order = orders.get(orders.size() - 1);
        assertThat(order.type).isEqualTo("Espresso");
        assertThat(order.origin).isEqualTo("Colombia");
    }

}
