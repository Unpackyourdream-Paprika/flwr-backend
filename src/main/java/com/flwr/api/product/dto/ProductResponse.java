package com.flwr.api.product.dto;

import com.flwr.api.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductResponse {
  private Long id;
  private String category;
  private String brand;
  private String tag;
  private String imgUrl;
  private String name;
  private int cost;
  private Product.ProductStatus status;
  private LocalDateTime deadlineAt;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;

  public static ProductResponse of(Product product) {
    return ProductResponse.builder().id(product.getId()).category(product.getCategory().getName()).brand(product.getBrand().getName()).tag(product.getTag().getName()).imgUrl(product.getImgUrl()).name(product.getName()).cost(product.getCost()).status(product.getStatus()).deadlineAt(product.getDeadlineAt()).updatedAt(product.getUpdatedAt()).createdAt(product.getCreatedAt()).build();
  }
}
