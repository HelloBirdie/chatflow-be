package com.hellobirdie.chatflow.repository;

import com.hellobirdie.chatflow.entity.User;
import com.hellobirdie.chatflow.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {
    Optional<UserSetting> findByUser(User user);
}
