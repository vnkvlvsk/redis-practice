package org.example.service;

import org.example.dto.ProductDto;

public interface ProductService {

    ProductDto getById(Long id);

    ProductDto update(Long id, ProductDto product);
}
