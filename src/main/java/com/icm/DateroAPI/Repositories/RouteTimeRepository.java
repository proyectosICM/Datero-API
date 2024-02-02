package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.RouteTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteTimeRepository extends JpaRepository<RouteTimeModel, Long> {
}
