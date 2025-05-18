package com.carlosamaral.dass21.repository;

import com.carlosamaral.dass21.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantModel, Long> {
}
