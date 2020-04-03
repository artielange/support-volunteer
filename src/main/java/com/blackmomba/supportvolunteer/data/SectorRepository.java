package com.blackmomba.supportvolunteer.data;

import com.blackmomba.supportvolunteer.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
