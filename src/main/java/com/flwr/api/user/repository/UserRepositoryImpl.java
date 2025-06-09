package com.flwr.api.user.repository;

// import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// import static com.flwr.api.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

  // private final JPAQueryFactory queryFactory;

  @Override
  public long updateUserAgeByNameFromQueryDsl(String name, int age) {
    return 1;

    // return queryFactory
    // .update(user)
    // .set(user.age, age)
    // .where(user.name.eq(name))
    // .execute();
  }
}
