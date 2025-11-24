package strc.data.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strc.data.calculator.entity.ExResponse;

@Repository
public interface ResponseRepository extends JpaRepository<ExResponse, Long> {
}
