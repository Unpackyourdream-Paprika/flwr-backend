package com.flwr.api.user.repository;

import com.flwr.api.user.domain.QUser;
import com.flwr.api.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

  private final JPAQueryFactory queryFactory;
  private final QUser user = QUser.user;

  @Override
  public List<User> searchUsersByFirstName(String firstName) {
    return queryFactory.selectFrom(user).where(firstNameEq(firstName)).fetch();
//    return queryFactory.selectFrom(user).where(firstNameEq(firstName), lastNameEq(lastName)).fetch();
  }

  @Override
  public Page<User> findAllUsersOrderByIdAsc(Pageable pageable) {
    List<User> content = queryFactory.selectFrom(user).orderBy(user.id.asc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    long total = Optional.ofNullable(queryFactory.select(user.count()).from(user).fetchOne()).orElse(0L);

    return new PageImpl<>(content, pageable, total);
  }

  private BooleanExpression lastNameEq(String lastName) {
    return lastName != null ? QUser.user.lastName.eq(lastName) : null;
  }

  private BooleanExpression firstNameEq(String firstName) {
    return firstName != null ? QUser.user.firstName.eq(firstName) : null;
  }
}
