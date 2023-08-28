package com.garamgaebee.teamdomainservice.entity;

import com.garamgaebee.teamdomainservice.common.entity.AggregateRoot;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.NotificationId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Notification extends AggregateRoot<NotificationId> {
    String content;
    Image image;
}
