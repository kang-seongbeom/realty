package com.ssafy.realty.custom_deal.adapter.out.respository;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.domain.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomDealCriteriaRepository {

    private final EntityManager entityManager;

    public Page<CustomDealJpaEntity> findByDynamicSearch(Search search){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomDealJpaEntity> criteriaQuery = criteriaBuilder.createQuery(CustomDealJpaEntity.class);
        Root<CustomDealJpaEntity> root = criteriaQuery.from(CustomDealJpaEntity.class);

        String type = search.getSearchData().getSearchType().getValue();
        String content = search.getSearchData().getContent();
        Pageable pageInfo = search.getSearchData().getPageable();

        Predicate predicate = criteriaBuilder.like(root.get(type), "%" + content + "%");

        criteriaQuery.where(predicate);

        List<CustomDealJpaEntity> results = entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageInfo.getOffset())
                .setMaxResults(pageInfo.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(CustomDealJpaEntity.class))).where(predicate);
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(results, pageInfo, totalCount);
    }
}
