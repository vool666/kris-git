package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lesson3Controller {
    @GetMapping("sum")
    public int sum(@RequestParam("array") int[] x) {
        return Lesson3.sum(x);
    }

    @GetMapping("factorial")
    public int factorial(@RequestParam("x") int x) {
        return Lesson3.factorial(x);
    }

    @GetMapping("sort")
    public int[] sort(@RequestParam("x") int[] x) {
        return Lesson3.sort(x);
    }

    @GetMapping("reverseString")
    public String reverseString(@RequestParam("a") String a) {
        return Lesson3.reverseString(a);
    }

    @GetMapping("isPrime")
    public boolean isPrime(@RequestParam("x") int x) {
        return Lesson3.isPrime(x);
    }

    @GetMapping("nthPower")
    public int nthPower(@RequestParam("array") int [] array, @RequestParam("n") int n) {
        return Lesson3.nthPower(array, n);
    }

}
