package strc.data.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import strc.data.calculator.service.CalcService;

@RestController
@RequiredArgsConstructor
public class CustomController {
    private final CalcService calcService;

    @RequestMapping("/")
    public double calc(){
        return calcService.calculate("(120 / 2) * 3 / 1.2 + ( 10 / 3)");
    }
}
