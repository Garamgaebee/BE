package com.garamgaebee.teamapplicationservice.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
@Getter
@Builder
@AllArgsConstructor
public class CreateNotificationCommand {
    private UUID teamId;
    private UUID memberId;
    private List<MultipartFile> imageList;
    private String content;
}
