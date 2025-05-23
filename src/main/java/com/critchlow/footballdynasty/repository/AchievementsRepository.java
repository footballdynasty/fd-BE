package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Achievements;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AchievementsRepository extends JpaRepository<Achievements, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO achievements (id, description, reward, date_completed) VALUES" +
            "(:#{#me.id}, :#{#me.description}, :#{#me.reward}, :#{#me.date_completed}) ON CONFLICT DO NOTHING",
            nativeQuery = true)
    void insert(@Param("me") Achievements me);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM achievements WHERE id = :id", nativeQuery = true)
    void delete(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Achievements a SET a.description = :description, a.reward = :reward, a.date_completed = :dateCompleted WHERE a.id = :id")
    void update(@Param("id") UUID id, @Param("description") String description, @Param("reward") String reward, @Param("dateCompleted") Long dateCompleted);
}

