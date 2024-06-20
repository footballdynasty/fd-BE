package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Achievements;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AchievementsRepository extends JpaRepository<Achievements, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO achievements (id, description, reward, date_completed) VALUES" +
            "(:#{#me.id}, :#{#me.description}, :#{#me.reward}, :#{#me.date_completed})",
            nativeQuery = true)
    void insert(@Param("me") Achievements me);
}

