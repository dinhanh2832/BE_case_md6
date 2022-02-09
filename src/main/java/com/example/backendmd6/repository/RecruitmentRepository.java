package com.example.backendmd6.repository;

import com.example.backendmd6.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.ws.RequestWrapper;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment,Long>{
}
