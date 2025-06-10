package com.flwr.api.product.controller;

import com.flwr.api.common.dto.ApiResponse;
import com.flwr.api.product.dto.ProductResponse;
import com.flwr.api.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/all")
  public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.ok(new ApiResponse<>(productService.getAllProducts(pageable).map(ProductResponse::of)));
  }
}
