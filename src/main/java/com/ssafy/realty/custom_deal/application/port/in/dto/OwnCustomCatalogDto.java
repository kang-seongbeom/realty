package com.ssafy.realty.custom_deal.application.port.in.dto;

import com.ssafy.realty.custom_deal.application.port.in.dto.util.AbstractResizePageable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

@Data
public class OwnCustomCatalogDto extends AbstractResizePageable {
    private Long userId;

    public OwnCustomCatalogDto(Long userId, Pageable pageable){
        super(pageable);
        this.userId = userId;
    }
}
