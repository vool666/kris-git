package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lesson2Controller {

    @GetMapping("exercise4")
    public int excercise4(@RequestParam("n") int n){
        return Lesson2.exercise4(n);
    }
    @GetMapping("seqLength")
    public int seqLength(@RequestParam("n") int n){
        return Lesson2.seqLength(n);
    }

    @GetMapping("exercise1")
    public int[] ex1(int[] array) {
        return Lesson2.exercise1(array);
    }

    @GetMapping("exercise2")
    public int ex2(int x) {
        return Lesson2.exercise2(x);
    }
}
