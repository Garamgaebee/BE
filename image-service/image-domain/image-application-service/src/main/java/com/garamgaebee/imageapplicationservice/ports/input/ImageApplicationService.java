package com.garamgaebee.imageapplicationservice.ports.input;

import com.garamgaebee.imageapplicationservice.dto.*;

import java.io.File;
import java.util.List;

public interface ImageApplicationService {
    SaveImageResponse saveImage(SaveImageCommand saveImageCommand);

    DeleteImageResponse deleteImage(DeleteImageCommand deleteImageCommand);
}
