package com.ssafy.realty.custom_deal.domain.wrap;

import com.ssafy.realty.custom_deal.domain.Summary;
import lombok.Getter;

import java.util.List;

@Getter
public class Summaries {
    List<Summary> data;

    public Summaries(List<Summary> data) {
        this.data = data;
    }
}
