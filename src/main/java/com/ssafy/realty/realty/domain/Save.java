package com.ssafy.realty.realty.domain;

import com.ssafy.realty.realty.domain.wrap.Markers;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Save {

    private SaveUserId saveUserId;
    private SaveData saveData;

    public static Save init(Long userId, String title, Markers markers){
        return new Save(
                new SaveUserId(userId),
                new SaveData(title, markers)
        );
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SaveUserId {
        private Long userId;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SaveData {
        private String title;
        private Markers markers;
    }
}
