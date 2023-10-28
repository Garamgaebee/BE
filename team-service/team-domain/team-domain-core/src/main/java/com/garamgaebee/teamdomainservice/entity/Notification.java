package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.NotificationId;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Notification extends AggregateRoot<NotificationId> {
    String content;
    List<Image> imageList;
    int imageCount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    public void validateNotification() {
        if (checkContentLength(content)) {
            throw new BaseException(BaseErrorCode.TEAM_NOTIFICATION_OVER_CHARACTER_LIMIT);
        }
        if (checkImageCount()) {
            throw new BaseException(BaseErrorCode.TEAM_NOTIFICATION_OVER_IMAGE_COUNT_LIMIT);
        }
    }

    public void initializeNotification() {
        setId(new NotificationId(UUID.randomUUID()));
    }

    public void setImageList(List<Image> imageUrlList) {
        this.imageList = imageUrlList;
    }

    private boolean checkContentLength(String content) {
        return content.length() > 500;
    }

    private boolean checkImageCount() {
        return imageCount > 4;
    }
}
