package com.flwr.api.product.repository;

import com.flwr.api.brand.domain.QBrand;
import com.flwr.api.category.domain.QCategory;
import com.flwr.api.product.domain.Product;
import com.flwr.api.product.domain.QProduct;
import com.flwr.api.tag.domain.QTag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QProduct product = QProduct.product;
  private final QCategory category = QCategory.category;
  private final QBrand brand = QBrand.brand;
  private final QTag tag = QTag.tag;

  @Override
  public Page<Product> findAllProductsOrderByIdAsc(Pageable pageable) {
    List<Product> content = queryFactory.selectFrom(product).join(product.category, category).fetchJoin().join(product.brand, brand).fetchJoin().join(product.tag, tag).fetchJoin().offset(pageable.getOffset()).limit(pageable.getPageSize()).orderBy(product.id.asc()).fetch();
    Long total = queryFactory.select(product.count()).from(product).fetchOne();

    System.out.println(content);

    return new PageImpl<>(content, pageable, total != null ? total : 0L);
  }
}
