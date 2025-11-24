package strc.data.calculator.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strc.data.calculator.dto.RequestDTO;
import strc.data.calculator.dto.ResponseDTO;
import strc.data.calculator.entity.ExResponse;
import strc.data.calculator.entity.Expression;
import strc.data.calculator.repository.ExpressionRepository;
import strc.data.calculator.repository.ResponseRepository;

@Service
@AllArgsConstructor
public class OperationsService {
    private final ExpressionRepository expressionRepository;
    private final ResponseRepository responseRepository;

    public ResponseDTO operation(RequestDTO requestDTO, ResponseDTO responseDTO){
        return saveOperation(requestDTO, responseDTO);
    }

    private ResponseDTO saveOperation(RequestDTO requestDTO, ResponseDTO responseDTO){
        Expression expression = saveExpression(requestDTO);
        return saveResponse(responseDTO, expression.getId());
    }

    private Expression saveExpression(RequestDTO requestDTO){
        return expressionRepository.save(Expression.builder().expression(requestDTO.getRequest()).build());
    }
    private ResponseDTO saveResponse(ResponseDTO responseDTO, int expression_id){
        responseRepository.save(ExResponse.builder().response(responseDTO.getResponse()).expression_id(expression_id).build());
        return responseDTO;
    }
}
