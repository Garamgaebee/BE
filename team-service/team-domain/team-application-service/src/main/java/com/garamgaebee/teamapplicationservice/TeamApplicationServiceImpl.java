package com.garamgaebee.teamapplicationservice;

import com.garamgaebee.teamapplicationservice.dto.command.*;
import com.garamgaebee.teamapplicationservice.dto.response.*;
import com.garamgaebee.teamapplicationservice.dto.feign.GetFeignTeamResponse;
import com.garamgaebee.teamapplicationservice.handler.*;
import com.garamgaebee.teamapplicationservice.ports.input.TeamApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@Service
@RequiredArgsConstructor
public class TeamApplicationServiceImpl implements TeamApplicationService {
    private final GetMainPageHandler getMainPageHandler;
    private final CreateNotificationHandler createNotificationHandler;
    private final DoneTeamHandler doneTeamHandler;
    private final FeignHandler feignHandler;
    private final ExitTeamHandler exitTeamHandler;
    private final EditTeamHandler editTeamHandler;
    private final GetMemberTeamHandler getMemberTeamHandler;
    @Override
    public GetMainPageResponse getMainPage(GetMainPageCommand getMainPageCommand) {
        return getMainPageHandler.getMainPage(getMainPageCommand);
    }

    @Override
    public CreateNotificationResponse createNotification(CreateNotificationCommand createNotificationCommand) {
        return createNotificationHandler.createNotification(createNotificationCommand);
    }

    @Override
    public GetFeignTeamResponse getFeignTeam(UUID teamId) {
        return feignHandler.getFeignTeam(teamId);
    }

    @Override
    public DoneTeamResponse doneTeam(DoneTeamCommand doneTeamCommand) {
        return doneTeamHandler.doneTeam(doneTeamCommand);
    }

    @Override
    public ExitTeamResponse exitTeam(ExitTeamCommand exitTeamCommand) {
        return exitTeamHandler.exitTeam(exitTeamCommand);
    }

    @Override
    public EditTeamResponse editTeamInfo(EditTeamInfoCommand editTeamInfoCommand) {
        return editTeamHandler.editTeam(editTeamInfoCommand);
    }

    @Override
    public List<GetMemberTeam> findMemberTeamList(GetMemberTeamCommand getMemberTeamCommand) {
        return getMemberTeamHandler.findMemberTeamList(getMemberTeamCommand);
    }
}
