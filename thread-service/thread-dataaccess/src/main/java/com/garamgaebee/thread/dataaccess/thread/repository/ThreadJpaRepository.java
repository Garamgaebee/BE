package com.garamgaebee.thread.dataaccess.thread.repository;

import com.garamgaebee.thread.dataaccess.thread.entity.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThreadJpaRepository extends JpaRepository<ThreadEntity, UUID> {

    Optional<List<ThreadEntity>> findThreadEntitiesByParentIdx(String parentIdx);

    @Query("select t from ThreadEntity t where t.status = 'ACTIVE' order by t.createdAt desc")
    List<ThreadEntity> findAllOrderByCreatedAt();

    @Query("select t from ThreadEntity t where t.status = 'ACTIVE' order by count(t.likes) desc")
    List<ThreadEntity> findAllOrderByLikeNumber();

    @Query("select t from ThreadEntity t where t.status = 'ACTIVE' and t.teamIdx = :teamIdx order by t.createdAt desc")
    Optional<List<ThreadEntity>> findAllByTeamIdx(Long teamIdx);
}