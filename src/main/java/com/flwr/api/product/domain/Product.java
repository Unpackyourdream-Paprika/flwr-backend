package com.flwr.api.product.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flwr.api.brand.domain.Brand;
import com.flwr.api.category.domain.Category;
import com.flwr.api.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", nullable = false)
  private Brand brand;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_id", nullable = false)
  private Tag tag;

  @Column(name = "img_url", nullable = false)
  private String imgUrl;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(nullable = false)
  private int cost;

  @Column(nullable = false)
  @Builder.Default
  @Enumerated(EnumType.STRING)
  private ProductStatus status = ProductStatus.onsale;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "deadline_at")
  private LocalDateTime deadlineAt;

  @LastModifiedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public enum ProductStatus {
    onsale,
    soldout,
    soon
  }
}

