package com.garamgaebee.teamapplicationservice.handler;

import com.garamgaebee.teamapplicationservice.dto.command.CreateNotificationCommand;
import com.garamgaebee.teamapplicationservice.dto.response.CreateNotificationResponse;
import com.garamgaebee.teamapplicationservice.mapper.TeamDataMapper;
import com.garamgaebee.teamapplicationservice.ports.output.TeamFeign;
import com.garamgaebee.teamapplicationservice.ports.output.TeamRepository;
import com.garamgaebee.teamdomainservice.TeamDomainService;
import com.garamgaebee.teamdomainservice.entity.Notification;
import com.garamgaebee.teamdomainservice.entity.Team;
import com.garamgaebee.teamdomainservice.valueobject.Image;
import com.garamgaebee.teamdomainservice.valueobject.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateNotificationHandler {
    private final TeamRepository teamRepository;
    private final TeamDomainService teamDomainService;
    private final TeamDataMapper teamDataMapper;
    private final TeamFeign teamFeign;

    public CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand) {
        Notification notification = teamDataMapper.createNotificationCommandToNotification(createNotificationCommand);
        teamDomainService.notificationInitAndValidate(notification);
        Team team = teamRepository.findByTeamId(new TeamId(createNotificationCommand.getTeamId()));
        List<Image> imageUrlList = new ArrayList<>();
        if (imageFileCheck(createNotificationCommand.getImageList())) {
            imageUrlList = teamFeign.imageSaveByMultipartList(createNotificationCommand.getImageList());
        }
        teamDomainService.notificationSetTeamAndInitNotificationImage(notification,team,imageUrlList);
        teamRepository.saveNotification(team);
        return teamDataMapper.teamToCreateNotificationResponse(team);
    }

    /**
     * 빈 이미지라면 false 리턴
     * @param multipartFileList
     * @return boolean
     */
    public boolean imageFileCheck(List<MultipartFile> multipartFileList) {
        for (MultipartFile multipartFile : multipartFileList) {
            if (!multipartFile.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
