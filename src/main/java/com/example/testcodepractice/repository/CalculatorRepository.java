package com.example.testcodepractice.repository;
import com.example.testcodepractice.domain.Calculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculatorRepository extends JpaRepository<Calculator, Long>{
}
