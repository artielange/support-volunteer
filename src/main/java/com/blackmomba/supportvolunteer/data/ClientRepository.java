package com.blackmomba.supportvolunteer.data;

import com.blackmomba.supportvolunteer.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}
