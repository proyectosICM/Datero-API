package com.icm.DateroAPI.Repositories;

import com.icm.DateroAPI.Models.TicketCountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCountRepository extends JpaRepository<TicketCountModel, Long> {
}
