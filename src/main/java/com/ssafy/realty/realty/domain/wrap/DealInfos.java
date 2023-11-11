package com.ssafy.realty.realty.domain.wrap;

import com.ssafy.realty.realty.domain.DealInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class DealInfos {
    private final List<DealInfo> dealInfos;

    private Long sumDealAmount;
    private Long maxDealAmount;
    private Long minDealAmount;
    private Long avgDealAmount;

    public DealInfos(List<DealInfo> dealInfos) {
        this.dealInfos = dealInfos;

        if(dealInfos.isEmpty()) return;

        dealInfos.sort(DealInfo::compareTo);
        calculationAmount();
    }

    private void calculationAmount() {
        calculationSumDealAmount();

        calculationMaxDealMount();
        calculationMinDealMount();
        calculationAvgDealMount();
    }

    private void calculationSumDealAmount() {
        for(DealInfo dealInfo : dealInfos){
            this.sumDealAmount += dealInfo.getDealInfoData().getDealAmount();
        }
    }

    private void calculationMaxDealMount() {
        maxDealAmount = this.dealInfos.get(this.getDealInfos().size()-1).getDealInfoData().getDealAmount();
    }

    private void calculationMinDealMount() {
        minDealAmount = this.dealInfos.get(0).getDealInfoData().getDealAmount();
    }

    private void calculationAvgDealMount() {
        avgDealAmount = (this.sumDealAmount / this.dealInfos.size());
    }
}
