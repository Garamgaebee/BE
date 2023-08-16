package com.garamgaebee.imageapplicationservice;

import com.garamgaebee.imageapplicationservice.dto.DeleteImageCommand;
import com.garamgaebee.imageapplicationservice.dto.DeleteImageResponse;
import com.garamgaebee.imageapplicationservice.dto.SaveImageCommand;
import com.garamgaebee.imageapplicationservice.dto.SaveImageResponse;
import com.garamgaebee.imageapplicationservice.ports.input.ImageApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageApplicationServiceImpl implements ImageApplicationService {
    private final ImageSaveCommandHandler imageSaveCommandHandler;
    private final ImageDeleteCommandHandler imageDeleteCommandHandler;
    @Override
    public SaveImageResponse saveImage(SaveImageCommand saveImageCommand) {
        return imageSaveCommandHandler.saveImage(saveImageCommand);
    }

    @Override
    public DeleteImageResponse deleteImage(DeleteImageCommand deleteImageCommand) {
        return imageDeleteCommandHandler.deleteImage(deleteImageCommand);
    }
}
