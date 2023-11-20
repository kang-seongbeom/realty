package com.ssafy.realty.custom_deal.application.port.in.dto;

import com.ssafy.realty.common.SearchType;
import com.ssafy.realty.custom_deal.application.port.in.dto.util.AbstractResizePageable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

@Data
public class SearchCustomDto extends AbstractResizePageable {
    private SearchType searchType;
    private String value;

    public SearchCustomDto(SearchType searchType, String value, Pageable pageable){
        super(pageable);
        this.searchType = searchType;
        this.value = value;
    }
}
