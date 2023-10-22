package com.garamgaebee.service.dataaccess.notification.repository;

import com.garamgaebee.service.dataaccess.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationJpaRepository extends JpaRepository<Long, NotificationEntity> {
    
}
