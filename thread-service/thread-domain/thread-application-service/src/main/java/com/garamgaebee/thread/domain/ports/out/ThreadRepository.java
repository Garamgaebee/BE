package com.garamgaebee.thread.domain.ports.out;

import com.garamgaebee.thread.domain.dto.DeleteThreadRes;
import com.garamgaebee.thread.domain.entity.Like;
import com.garamgaebee.thread.domain.entity.Thread;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ThreadRepository {

    Thread createThread(Thread createTarget);

    DeleteThreadRes deleteThread(String threadIdx);

    void createLike(Like like);

    void deleteLike(Like like);

    List<Thread> getCommentList(String threadIdx);

    List<Thread> getThreadListOrderByTime();

    List<Thread> getThreadListOrderByLike();

    List<Thread> getThreadTeamList(Long teamIdx);
}