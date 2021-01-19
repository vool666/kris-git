package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.Scanner;
//@RequestParam("") pärast küsimärki       @PathVariable("") enne küsimärki

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

    int count = 0;
    Random random = new Random();
    int suvalineNumber = random.nextInt(100);

    @GetMapping("randomGame") //vihje - sul kaob ära tsükkel. inimene arvab numbrit- sina vastad kudias ta arvas.
    public String randomGame(@RequestParam("x") int x) {
    count ++;
        if (x < suvalineNumber) {
            return("Number on väiksem kui suvaline number.");
        } else if (x > suvalineNumber) {
            return("Number on suurem kui suvaline number.");
        } else {
            return("Suvaline number oli " + suvalineNumber + ", ehk sisestatud number oli õige. Sul läks arvamiseks " + count + " korda.");
        }

    }
}

