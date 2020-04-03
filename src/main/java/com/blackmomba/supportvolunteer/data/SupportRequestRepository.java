package com.blackmomba.supportvolunteer.data;

import com.blackmomba.supportvolunteer.domain.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRequestRepository extends JpaRepository<SupportRequest, Long> {
}
