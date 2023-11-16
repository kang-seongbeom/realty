package com.ssafy.realty.custom_deal.domain;

import com.ssafy.realty.common.SearchType;
import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
public class Search {
    SearchData searchData;

    public static Search init(SearchType searchType, String content, Pageable pageable){
        return new Search(new SearchData(searchType, content, pageable));
    }

    @Value
    public static class SearchData{
        SearchType searchType;
        String content;
        Pageable pageable;
    }
}
