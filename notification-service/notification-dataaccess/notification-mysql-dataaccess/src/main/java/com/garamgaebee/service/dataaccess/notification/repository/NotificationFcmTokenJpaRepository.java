package com.garamgaebee.service.dataaccess.notification.repository;

import com.garamgaebee.service.dataaccess.notification.entity.NotificationFcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationFcmTokenJpaRepository extends JpaRepository<NotificationFcmTokenEntity, Long> {

    @Modifying
    @Query("delete from NotificationFcmTokenEntity n where n.fcmToken in :fcmTokenList")
    public void deleteAllByFcmToken(@Param("fcmTokenList") List<String> fcmTokenList);
}
