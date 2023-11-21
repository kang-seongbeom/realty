package com.ssafy.realty.custom_deal.adapter.out;

import com.ssafy.realty.common.SearchType;
import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.mapper.CustomAdapterMapper;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealCriteriaRepository;
import com.ssafy.realty.custom_deal.adapter.out.respository.CustomDealJpaRepository;
import com.ssafy.realty.custom_deal.adapter.out.respository.UserStarCustomJpaRepository;
import com.ssafy.realty.custom_deal.application.port.out.QueryCustomPort;
import com.ssafy.realty.custom_deal.domain.*;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import com.ssafy.realty.realty.adapter.out.entity.CustomJpaEntity;
import com.ssafy.realty.realty.adapter.out.repository.CustomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class QueryCustomPersistenceAdapter implements QueryCustomPort {

    private final CustomJpaRepository customJpaRepository;
    private final CustomDealJpaRepository customDealJpaRepository;
    private final CustomDealCriteriaRepository customDealCriteriaRepository;
    private final UserStarCustomJpaRepository userStarCustomJpaRepository;

    private final CustomAdapterMapper customAdapterMapper;

    @Override
    public Summaries myCustomInfos(OwnCustomCatalog ownCustomCatalog) {
        Page<CustomDealJpaEntity> infos =
                customDealJpaRepository.findByUserId(
                        ownCustomCatalog.getOwnCustomCatalogUserId().getValue(),
                        ownCustomCatalog.getOwnCustomCatalogData().getPageable()
                );
        return customAdapterMapper.mapToSummaries(infos);
    }

    @Override
    public boolean isOwner(IsOwner isOwner) {
        CustomJpaEntity custom = customJpaRepository.findById(isOwner.getIsOwnerCustomId().getValue())
                .orElseThrow(() -> new NoSuchElementException("해당 매물 정보 글을 찾을 수 없습니다."));

        return (long) custom.getUser().getId() == isOwner.getIsOwnerUserId().getValue();
    }

    @Override
    public Summaries search(Search search) {
        Page<CustomDealJpaEntity> catalogs;

        if (search.getSearchData().getSearchType() == SearchType.TITLE) {
            catalogs = customDealJpaRepository.findByTitleContainingOrderByIdDesc(search.getSearchData().getContent(), search.getSearchData().getPageable());
        } else if(search.getSearchData().getSearchType() == SearchType.AUTHOR){
            catalogs = customDealJpaRepository.findByNicknameContaining(search.getSearchData().getContent(), search.getSearchData().getPageable());
        }else {
            catalogs = customDealJpaRepository.findAllByOrderByIdDesc(search.getSearchData().getPageable());
        }
        return customAdapterMapper.mapToSummaries(catalogs);
    }

    @Override
    public Summaries ownStarCustom(OwnStarCustom ownStarCustom) {
        Page<CustomDealJpaEntity> catalogs = userStarCustomJpaRepository.findCustomByUserId(
                ownStarCustom.getOwnStarCustomUserId().getValue(),
                ownStarCustom.getOwnStarCustomData().getPageable()
        );
        return customAdapterMapper.mapToSummaries(catalogs);
    }
}
