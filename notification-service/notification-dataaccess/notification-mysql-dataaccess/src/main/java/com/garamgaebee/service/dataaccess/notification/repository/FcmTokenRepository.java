package com.garamgaebee.service.dataaccess.notification.repository;

import com.garamgaebee.service.dataaccess.notification.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {

    @Modifying
    @Query("delete from FcmTokenEntity f where f.fcmToken in :fcmTokenList")
    public void deleteAllByFcmTokenValue(@Param("fcmTokenList") List<String> fcmTokenList);

    public void deleteByFcmToken(String fcmToken);

    public void deleteAllByTimeBefore(LocalDateTime time);
}

