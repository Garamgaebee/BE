package com.garamgaebee.teamapplicationservice.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SaveImageFeign {
    List<MultipartFile> multipartFileList;
}
