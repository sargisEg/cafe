package com.cafe.facade.manager;

import com.cafe.facade.dto.AssignTableRequestDto;
import com.cafe.facade.dto.CreateProductRequestDto;
import com.cafe.facade.dto.CreateTableRequestDto;
import com.cafe.facade.dto.CreateUserRequestDto;
import com.cafe.facade.dto.entity.ProductDto;
import com.cafe.facade.dto.entity.TableDto;
import com.cafe.facade.dto.entity.UserDto;

public interface ManagerFacade {

    UserDto createUser(CreateUserRequestDto dto);

    TableDto createTable(CreateTableRequestDto dto);

    ProductDto createProduct(CreateProductRequestDto dto);

    TableDto assignTable(AssignTableRequestDto dto);

}
