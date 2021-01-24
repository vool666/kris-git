package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import ee.bcs.valiit.tasks.solution.SolutionLesson1MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class Lesson1AController {

    public HashMap<BigInteger, Employee> employeelist = new HashMap<>();
    BigInteger x = BigInteger.valueOf(0);

    // http://localhost:8080/solution/min?a=5&b=9
    @GetMapping("min")
    public int min(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable) {
        return Lesson1MathUtil.min(aVariable, bVariable);
    }

    // http://localhost:8080/solution/max/5/9
    @GetMapping("max/{a}/{b}")
    public int max(@PathVariable("a") int aVariable, @PathVariable("b") int bVariable) {
        return Lesson1MathUtil.max(aVariable, bVariable);
    }

    @GetMapping("abs")
    public int abs(@RequestParam("a") int aVariable) {
        return Lesson1MathUtil.abs(aVariable);
    }

    @GetMapping("isEven")
    public boolean isEven(@RequestParam("a") int aVariable) {
        return Lesson1MathUtil.isEven(aVariable);
    }

    @GetMapping("min3")
    public int min3(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable, @RequestParam("c") int cVariable) {
        return Lesson1MathUtil.min3(aVariable, bVariable, cVariable);
    }

    @GetMapping("max3")
    public int max3(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable, @RequestParam("c") int cVariable) {
        return Lesson1MathUtil.max3(aVariable, bVariable, cVariable);
    }

    @RequestMapping("employee") //http://localhost:8080/employee?jobtitle=ehitaja
    @GetMapping("test") //("employee/{jobtitle}/{staaz}/{vanus}/
    public Employee test(@RequestParam("jobtitle") String jobtitle, @RequestParam("staaž") int staaž, @RequestParam("vanus") int vanus) {
        Employee employee = new Employee();
        employee.setJobtitle(jobtitle);
        employee.getJobtitle();
        employee.setStaaž(staaž);
        employee.getStaaž();
        employee.setVanus(vanus);
        employee.getVanus();
        return employee;
    }

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostMapping("test") //http://localhost:8080/test
    public void test2(@RequestBody Employee employee) {
        String sql= "INSERT INTO accounts (kontonr, balance) VALUES (122, 31);"; //VALUES (:nameParameter, :addressParameter) edasi paned paramMap.put("nameParameter", employee.getName())
        Map<String, Object> paramMap = new HashMap<>();
        jdbcTemplate.update(sql, paramMap);
        System.out.println(employee.getJobtitle());
    }

    @PostMapping("addEmployee")
    public void addEmployee(@RequestBody Employee employee) {
        employeelist.put((x = x.add(BigInteger.ONE)), employee);
    }

    @GetMapping("viewEmployees")
    public HashMap<BigInteger, Employee> viewEmployees() {
        return employeelist;
    }

    @GetMapping("view1Employee")
    public Employee view1Employee(@RequestParam("id") BigInteger id) {
        return employeelist.get(id);
    }

    @PutMapping("replaceEmployee")
    public void replaceEmployee(@RequestBody Employee employee, @RequestParam("id") BigInteger id) {
        employeelist.replace(id, employee);
    }

    @DeleteMapping("deleteEmployee")
    public void deleteEmployee(@RequestParam("id") BigInteger id) {
        employeelist.remove(id);
    }

}
