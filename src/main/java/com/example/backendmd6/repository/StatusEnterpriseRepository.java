package com.example.backendmd6.repository;

import com.example.backendmd6.model.StatusEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEnterpriseRepository extends JpaRepository<StatusEnterprise, Long> {
}
