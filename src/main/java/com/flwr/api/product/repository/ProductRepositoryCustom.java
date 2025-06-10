package com.flwr.api.product.repository;

import com.flwr.api.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
 Page<Product> findAllProductsOrderByIdAsc(Pageable pageable);
}
