package com.ssafy.realty.custom_deal.application.port.in.dto;

import com.ssafy.realty.custom_deal.application.port.in.dto.util.AbstractResizePageable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

@Data
public class CustomCatalogDto extends AbstractResizePageable {

    public CustomCatalogDto(Pageable pageable) {
        super(pageable);
    }
}
