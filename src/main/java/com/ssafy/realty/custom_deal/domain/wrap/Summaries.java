package com.ssafy.realty.custom_deal.domain.wrap;

import com.ssafy.realty.custom_deal.domain.Summary;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class Summaries {
    List<Summary> data;
}
