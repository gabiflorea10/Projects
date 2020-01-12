package com.ds.Assignment4.repositories;

import com.ds.Assignment4.models.entities.Recommendation;
import com.ds.Assignment4.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, UUID> {
}
