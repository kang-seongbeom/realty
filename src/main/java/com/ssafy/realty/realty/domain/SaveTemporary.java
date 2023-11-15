package com.ssafy.realty.realty.domain;

import com.ssafy.realty.realty.domain.wrap.Markers;
import lombok.Value;

@Value
public class SaveTemporary {
    SaveUserId saveUserId;
    SaveData saveData;

    public static SaveTemporary init(Long userId, Markers markers){
        return new SaveTemporary(
                new SaveUserId(userId),
                new SaveData(markers)
        );
    }

    @Value
    public static class SaveUserId {
        Long userId;
    }

    @Value
    public static class SaveData {
        Markers markers;
    }
}
