package com.critchlow.footballdynasty.repository;


import com.critchlow.footballdynasty.model.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeekRepository extends JpaRepository<Week, UUID> {
    Week findWeekByWeekNumber(int weekNumber);
}
