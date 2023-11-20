package com.ssafy.realty.custom_deal.application.port.in.dto.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor
@Getter
public abstract class AbstractResizePageable {

    public Pageable pageable;

    public AbstractResizePageable(Pageable pageable) {
        int resize = pageable.getPageNumber() - 1;
        this.pageable = PageRequest.of((resize > 0) ? resize - 1 : 0, pageable.getPageSize());
    }
}
