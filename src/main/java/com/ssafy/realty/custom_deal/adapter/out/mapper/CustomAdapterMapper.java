package com.ssafy.realty.custom_deal.adapter.out.mapper;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.domain.Summary;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAdapterMapper {
    public Summaries mapToSummaries(Page<CustomDealJpaEntity> total) {
        return new Summaries(total.map(this::mapToSummary));
    }

    private Summary mapToSummary(CustomDealJpaEntity deal){
        return Summary.init(
                deal.getId(), deal.getUser().getNickname(),
                deal.getTitle(), deal.getLook(),
                deal.getStar(), deal.getCreateDate()
        );
    }
}
