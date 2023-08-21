package com.garamgaebee.thread.dataaccess.thread.repository;

import com.garamgaebee.thread.dataaccess.thread.entity.ThreadImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThreadImageJpaRepository extends JpaRepository<ThreadImageEntity, UUID> {

    @Modifying
    @Query(value = "delete from ThreadImageEntity i where i.threadIdx = :idx")
    void deleteAllByThreadIdx(UUID idx);
}
