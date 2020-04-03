package com.blackmomba.supportvolunteer.data;

import com.blackmomba.supportvolunteer.domain.SupportEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportEventRepository extends JpaRepository<SupportEvent, Long> {
}
