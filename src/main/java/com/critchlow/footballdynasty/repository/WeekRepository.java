package com.critchlow.footballdynasty.repository;


import com.critchlow.footballdynasty.model.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WeekRepository extends JpaRepository<Week, UUID> {
    Week findWeekByWeekNumber(int weekNumber);

    @Query("SELECT max(week.weekNumber) from Week week where week.year = :year")
    int findLargestWeekNumberByYear(int year);
}
