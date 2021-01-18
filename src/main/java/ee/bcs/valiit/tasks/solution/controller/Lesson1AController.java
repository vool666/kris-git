package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import ee.bcs.valiit.tasks.solution.SolutionLesson1MathUtil;
import org.springframework.web.bind.annotation.*;

@RestController

public class Lesson1AController {

    // http://localhost:8080/solution/min?a=5&b=9
    @GetMapping("min")
    public int min(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable){
        return Lesson1MathUtil.min(aVariable, bVariable);
    }

    // http://localhost:8080/solution/max/5/9
    @GetMapping("max/{a}/{b}")
    public int max(@PathVariable("a") int aVariable, @PathVariable("b") int bVariable){
        return Lesson1MathUtil.max(aVariable, bVariable);
    }

    @GetMapping("abs")
    public int abs(@RequestParam("a") int aVariable){
        return Lesson1MathUtil.abs(aVariable);
    }

    @GetMapping("isEven")
    public boolean isEven(@RequestParam("a") int aVariable){
        return Lesson1MathUtil.isEven(aVariable);
    }

    @GetMapping("min3")
    public int min3(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable, @RequestParam("c") int cVariable){
        return Lesson1MathUtil.min3(aVariable, bVariable, cVariable);
    }

    @GetMapping("max3")
    public int max3(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable, @RequestParam("c") int cVariable){
        return Lesson1MathUtil.max3(aVariable, bVariable, cVariable);
    }
}
