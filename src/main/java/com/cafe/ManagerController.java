package com.cafe;

import com.cafe.facade.dto.AssignTableRequestDto;
import com.cafe.facade.dto.CreateProductRequestDto;
import com.cafe.facade.dto.CreateTableRequestDto;
import com.cafe.facade.dto.CreateUserRequestDto;
import com.cafe.facade.dto.entity.ProductDto;
import com.cafe.facade.dto.entity.TableDto;
import com.cafe.facade.dto.entity.UserDto;
import com.cafe.facade.manager.ManagerFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
public class ManagerController {

    private final ManagerFacade managerFacade;

    public ManagerController(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    @GetMapping
    public String ok() {
        return "ok";
    }

    @PostMapping("/create/user")
    public UserDto createUser(
            @RequestBody CreateUserRequestDto dto) {
        return managerFacade.createUser(dto);
    }

    @PostMapping("/create/table")
    public TableDto createTable(
            @RequestBody CreateTableRequestDto dto) {
        return managerFacade.createTable(dto);
    }

    @PostMapping("/create/product")
    public ProductDto createProduct(
            @RequestBody CreateProductRequestDto dto) {
        return managerFacade.createProduct(dto);
    }

    @PostMapping("/assign")
    public TableDto assignTable(
            @RequestBody AssignTableRequestDto dto) {
        return managerFacade.assignTable(dto);
    }
}
