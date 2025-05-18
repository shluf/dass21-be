package com.carlosamaral.dass21.repository;

import com.carlosamaral.dass21.model.ResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseModel, Long> {

    @Query(value = "INSERT INTO responses (participant_id, response_date, total_depression_score, total_anxiety_score, total_stress_score) VALUES (:participant_id, :response_date, :total_depression_score, :total_anxiety_score, :total_stress_score) RETURNING *", nativeQuery = true)
    ResponseModel save(
            @Param("participant_id") Long participantId,
            @Param("response_date") Instant responseDate,
            @Param("total_depression_score") Integer totalDepressionScore,
            @Param("total_anxiety_score") Integer totalAnxietyScore,
            @Param("total_stress_score") Integer totalStressScore
    );

    @Query(value = "UPDATE responses SET response_date = :response_date, total_anxiety_score = :total_anxiety_score, total_stress_score = :total_stress_score, total_depression_score = :total_depression_score, participant_id = :participant_id WHERE id = :response_id RETURNING *", nativeQuery = true)
    ResponseModel update(
            @Param("response_id") Long responseId,
            @Param("participant_id") Long participantId,
            @Param("response_date") Instant responseDate,
            @Param("total_depression_score") Integer totalDepressionScore,
            @Param("total_anxiety_score") Integer totalAnxietyScore,
            @Param("total_stress_score") Integer totalStressScore
    );

    @Query(value = "DELETE FROM responses WHERE participant_id=:id RETURNING 1", nativeQuery = true)
    Integer deleteByParticipantId(@Param("id") Long id);

    @Query(value = "SELECT * FROM responses WHERE participant_id=:id", nativeQuery = true)
    List<ResponseModel> findResponseByParticipantId(@Param("id") Long id);

    @Query(value = "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM responses r WHERE id=:response_id AND participant_id=:participant_id", nativeQuery = true)
    Boolean participantIsResponseOwner(@Param("response_id") Long responseId, @Param("participant_id") Long participantId);
}
