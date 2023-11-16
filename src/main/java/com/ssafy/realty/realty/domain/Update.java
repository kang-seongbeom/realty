package com.ssafy.realty.realty.domain;

import com.ssafy.realty.realty.domain.wrap.Markers;
import lombok.Value;

@Value
public class Update {
    UpdateUserId updateUserId;
    UpdateCustomId updateCustomId;
    UpdateData updateData;

    public static Update init(Long userId, Long customId, String title, Markers markers){
        return new Update(
                new UpdateUserId(userId),
                new UpdateCustomId(customId),
                new UpdateData(title, markers)
        );
    }

    @Value
    public static class UpdateUserId {
        Long userId;
    }

    @Value
    public static class UpdateCustomId{
        Long customId;
    }

    @Value
    public static class UpdateData {
        String title;
        Markers markers;
    }
}
