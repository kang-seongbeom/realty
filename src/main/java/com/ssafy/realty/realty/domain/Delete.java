package com.ssafy.realty.realty.domain;

import lombok.Value;

@Value
public class Delete {
    DeleteUserId userId;
    DeleteCustomId customId;

    public static Delete init(Long userId, Long customId) {
        return new Delete(new DeleteUserId(userId), new DeleteCustomId(customId));
    }

    @Value
    public static class DeleteUserId {
        Long value;
    }

    @Value
    public static class DeleteCustomId {
        Long value;
    }
}
