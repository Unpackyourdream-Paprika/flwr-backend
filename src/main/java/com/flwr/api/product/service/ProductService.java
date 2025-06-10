package com.flwr.api.product.service;

import com.flwr.api.product.domain.Product;
import com.flwr.api.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<Product> getAllProducts(Pageable pageable) {
    return productRepository.findAllProductsOrderByIdAsc(pageable);
  }
}
