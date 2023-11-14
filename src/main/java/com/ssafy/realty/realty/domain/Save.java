package com.ssafy.realty.realty.domain;

import com.ssafy.realty.realty.domain.wrap.Markers;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
public class Save {
    SaveUserId saveUserId;
    SaveData saveData;

    public static Save init(Long userId, String title, Markers markers){
        return new Save(
                new SaveUserId(userId),
                new SaveData(title, markers)
        );
    }

    @Value
    public static class SaveUserId {
        Long userId;
    }

    @Value
    public static class SaveData {
        String title;
        Markers markers;
    }
}
