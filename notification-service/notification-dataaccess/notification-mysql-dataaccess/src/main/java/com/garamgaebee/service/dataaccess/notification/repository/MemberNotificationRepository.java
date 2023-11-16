package com.garamgaebee.service.dataaccess.notification.repository;

import com.garamgaebee.service.dataaccess.notification.entity.MemberNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberNotificationRepository extends JpaRepository<MemberNotificationEntity, Long> {

    public List<MemberNotificationEntity> findAllByMemberId(Long memberId);
}
