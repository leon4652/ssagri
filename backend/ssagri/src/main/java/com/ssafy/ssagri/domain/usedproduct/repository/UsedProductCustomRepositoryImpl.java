package com.ssafy.ssagri.domain.usedproduct.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ssafy.ssagri.entity.usedproduct.ProductCategory;
import com.ssafy.ssagri.entity.usedproduct.QUsedProduct;
import com.ssafy.ssagri.entity.usedproduct.UsedProduct;
import com.ssafy.ssagri.entity.user.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class UsedProductCustomRepositoryImpl implements UsedProductCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    QUsedProduct usedProduct = QUsedProduct.usedProduct;

    public UsedProductCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<UsedProduct> selectAllUsedProduct(ProductCategory productCategory, Region region, Pageable pageable) {
        QueryResults<UsedProduct> usedProductQueryResults = jpaQueryFactory.selectFrom(usedProduct)
                .where(usedProduct.deleteDate.isNull()
                        .and(usedProduct.category.eq(productCategory))
                        .and(usedProduct.user.region.eq(region)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(usedProduct.no.desc())
                .fetchResults();

        List<UsedProduct> results = usedProductQueryResults.getResults();
        long total = usedProductQueryResults.getTotal();

        return new PageImpl<>(results, pageable, total);
    }


}
