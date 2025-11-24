package strc.data.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import strc.data.calculator.dto.RequestDTO;
import strc.data.calculator.dto.ResponseDTO;
import strc.data.calculator.service.CalcService;
import strc.data.calculator.service.OperationsService;

@RestController
@RequiredArgsConstructor
public class CustomController {
    private final CalcService calcService;
    private final OperationsService operationsService;

    @RequestMapping("/")
    public double calc(){
        return calcService.calculate("50 * 20 / ( 53 - 33) + 12 - 5 * 22 - 500");
    }

    @RequestMapping("/save")
    public String saveOperation(){
        RequestDTO request = RequestDTO.builder().request("50 * 20").build();
        String result = String.valueOf(calcService.calculate(request.getRequest()));
        ResponseDTO response = ResponseDTO.builder().response(result).build();
        operationsService.operation(request, response);
        return "Done";
    }
}
