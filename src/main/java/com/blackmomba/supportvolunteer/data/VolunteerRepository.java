package com.blackmomba.supportvolunteer.data;

import com.blackmomba.supportvolunteer.domain.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, String> {
}
