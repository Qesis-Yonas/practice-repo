package com.vaccinationcenter.repository;

import com.vaccinationcenter.entity.VacCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacCenterRepo extends JpaRepository<VacCenter,Integer> {
}
