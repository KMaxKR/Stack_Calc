package strc.data.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strc.data.calculator.entity.Expression;

@Repository
public interface ExpressionRepository extends JpaRepository<Expression, Long> {
}
