package com.garamgaebee.imageapplicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SaveImageCommand {
    List<MultipartFile> imageList;
}
