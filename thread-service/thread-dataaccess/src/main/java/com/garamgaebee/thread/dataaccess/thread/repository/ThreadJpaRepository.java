package com.garamgaebee.thread.dataaccess.thread.repository;

import com.garamgaebee.thread.dataaccess.thread.entity.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThreadJpaRepository extends JpaRepository<ThreadEntity, UUID> {
}