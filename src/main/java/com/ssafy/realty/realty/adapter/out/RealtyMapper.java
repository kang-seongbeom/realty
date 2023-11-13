package com.ssafy.realty.realty.adapter.out;

import com.ssafy.realty.common.TransportationType;
import com.ssafy.realty.realty.adapter.out.mybatis.MarkerVicinityHomeInfo;
import com.ssafy.realty.realty.domain.Marker;
import com.ssafy.realty.realty.domain.VicinityHomeInfo;
import com.ssafy.realty.realty.domain.wrap.TotalVicinityHomeInfos;
import com.ssafy.realty.realty.domain.wrap.VicinityHomeInfos;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RealtyMapper {

    public TotalVicinityHomeInfos mapToTotalVicinityHomeInfos(List<VicinityHomeInfos> infos){
        return new TotalVicinityHomeInfos(infos);
    }

    public VicinityHomeInfos mapToVicinityHomeInfos(List<MarkerVicinityHomeInfo> infos,
                                                    TransportationType type, Integer time){
        List<VicinityHomeInfo> elements = infos.stream()
                .map(this::mapToVicinityHomeInfo)
                .collect(Collectors.toList());
        return new VicinityHomeInfos(elements, type, time);
    }

    private VicinityHomeInfo mapToVicinityHomeInfo(MarkerVicinityHomeInfo info) {
        return VicinityHomeInfo.init(
                info.getAptCode(), info.getApartmentName(),
                info.getLat(), info.getLng(),
                info.getAddress(), info.getTotalDealAmount(),
                info.getMaxDealAmount(), info.getMinDealAmount(),
                info.getAvgDealAmount(), info.getAvgArea()
        );
    }



}
