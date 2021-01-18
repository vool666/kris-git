package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson3;
import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lesson3HardController {
    @GetMapping("evenFibonacci")
    public int evenFibonacci(@RequestParam("x") int x) {
        return Lesson3Hard.evenFibonacci(x);
    }

    @GetMapping("morseCode")
    public String morseCode(@RequestParam("text") String text) {
        return Lesson3Hard.morseCode(text);
    }
}
