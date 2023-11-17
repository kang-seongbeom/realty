package com.ssafy.realty.custom_deal.adapter.out.mapper;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.entity.CustomUserJpaEntity;
import com.ssafy.realty.custom_deal.adapter.out.entity.UserStarCustomJpaEntity;
import com.ssafy.realty.custom_deal.domain.Summary;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CustomAdapterMapper {
    public Summaries mapToSummaries(Page<CustomDealJpaEntity> total) {
        return new Summaries(total.map(this::mapToSummary));
    }

    public UserStarCustomJpaEntity mapToUserStarCustomJpaEntity(CustomUserJpaEntity user, CustomDealJpaEntity custom) {
        return UserStarCustomJpaEntity
                .builder()
                .user(user)
                .custom(custom)
                .build();
    }

    private Summary mapToSummary(CustomDealJpaEntity deal){
        return Summary.init(
                deal.getId(), deal.getUser().getNickname(),
                deal.getTitle(), deal.getView(),
                deal.getStar(), deal.getCreateDate()
        );
    }
}
