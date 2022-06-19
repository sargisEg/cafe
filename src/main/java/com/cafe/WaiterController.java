package com.cafe;

import com.cafe.facade.dto.CreateOrderRequestDto;
import com.cafe.facade.dto.CreateProductInOrderRequestDto;
import com.cafe.facade.dto.EditeOrderRequestDto;
import com.cafe.facade.dto.EditeProductInOrderRequestDto;
import com.cafe.facade.dto.entity.OrderDto;
import com.cafe.facade.dto.entity.ProductInOrderDto;
import com.cafe.facade.dto.entity.TableDto;
import com.cafe.facade.waiter.WaiterFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waiter")
public class WaiterController {

    private final WaiterFacade waiterFacade;

    public WaiterController(WaiterFacade waiterFacade) {
        this.waiterFacade = waiterFacade;
    }

    @GetMapping
    public String ok() {
        return "ok";
    }

    @GetMapping("/{username}")
    public List<TableDto> getAssignedTables(@PathVariable String username) {
        return waiterFacade.getAssignedTables(username);
    }

    @PostMapping("/create/order")
    public OrderDto createOrder(@RequestBody CreateOrderRequestDto dto) {
        return waiterFacade.createOrder(dto);
    }

    @PostMapping("/create/product_in_order")
    public ProductInOrderDto createProductInOrder(@RequestBody CreateProductInOrderRequestDto dto) {
        return waiterFacade.createProductInOrder(dto);
    }

    @PutMapping("/edit/product_in_order")
    public ProductInOrderDto editProductInOrder(@RequestBody EditeProductInOrderRequestDto dto) {
        System.out.println(dto.getProductInOrderStatus());
        return waiterFacade.editProductInOrder(dto);
    }

    @PutMapping("/edit/order")
    public OrderDto editeOrder(@RequestBody EditeOrderRequestDto dto) {
        return waiterFacade.editeOrder(dto);
    }
}
