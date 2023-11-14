package com.ssafy.realty.custom_deal.adapter.out.mapper;

import com.ssafy.realty.custom_deal.adapter.out.entity.CustomDealJpaEntity;
import com.ssafy.realty.custom_deal.domain.Summary;
import com.ssafy.realty.custom_deal.domain.wrap.Summaries;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAdapterMapper {
    public Summaries mapToSummaries(List<CustomDealJpaEntity> total) {
        List<Summary> data = total.stream()
                .map(this::mapToSummary)
                .collect(Collectors.toList());

        return new Summaries(data);
    }

    private Summary mapToSummary(CustomDealJpaEntity deal){
        return Summary.init(
                deal.getId(), deal.getUser().getNickname(),
                deal.getTitle(), deal.getLook(),
                deal.getStar(), deal.getCreateDate()
        );
    }
}
