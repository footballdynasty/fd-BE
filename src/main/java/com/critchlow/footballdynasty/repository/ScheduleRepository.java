package com.critchlow.footballdynasty.repository;


import com.critchlow.footballdynasty.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
