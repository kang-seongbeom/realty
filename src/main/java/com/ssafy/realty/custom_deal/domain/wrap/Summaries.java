package com.ssafy.realty.custom_deal.domain.wrap;

import com.ssafy.realty.custom_deal.domain.Summary;
import lombok.Value;
import org.springframework.data.domain.Page;


@Value
public class Summaries {
    Page<Summary> data;
}
