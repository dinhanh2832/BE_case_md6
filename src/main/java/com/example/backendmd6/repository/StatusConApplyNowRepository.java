package com.example.backendmd6.repository;

import com.example.backendmd6.model.StatusConfirmOfApplyNow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusConApplyNowRepository extends JpaRepository<StatusConfirmOfApplyNow, Long> {
}