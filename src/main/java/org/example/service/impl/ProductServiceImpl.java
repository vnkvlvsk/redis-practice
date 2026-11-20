package org.example.service.impl;

import org.example.dto.ProductDto;
import org.example.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<Long, ProductDto> products = new HashMap<>(Map.of(
            1L, new ProductDto(1L, "Laptop", new BigDecimal("1500.00")),
            2L, new ProductDto(2L, "Phone", new BigDecimal("800.00"))
    ));

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductDto getById(Long id) {
        System.out.println("Cache miss, loading product " + id);
        sleep();
        return products.get(id);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public ProductDto update(Long id, ProductDto product) {
        ProductDto updated = new ProductDto(id, product.name(), product.price());
        products.put(id, updated);
        return updated;
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
