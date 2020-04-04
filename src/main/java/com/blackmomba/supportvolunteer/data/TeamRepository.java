package com.blackmomba.supportvolunteer.data;

import com.blackmomba.supportvolunteer.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
