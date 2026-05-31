package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.SessionEntity;

@Repository
public interface SessionRepo
        extends JpaRepository<SessionEntity,Integer> {
	
    @Query("""
            SELECT MAX(s.endTimeUtc)
            FROM SessionEntity s
            WHERE s.offeringId = :offeringId
            """)
     LocalDateTime findLatestEndTime(
             @Param("offeringId") int offeringId);
    
    
    
    List<SessionEntity>
    findByOfferingId(int offeringId);
}