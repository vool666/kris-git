package ee.bcs.valiit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("test")
    public String getHelloWorld() {
        return "Hello world.";
    }
}
