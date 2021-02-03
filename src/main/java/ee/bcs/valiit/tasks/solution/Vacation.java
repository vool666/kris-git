package ee.bcs.valiit.tasks.solution;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private SolutionEmployee2 solutionEmployee2;

    private LocalDate beginDate;

    public Integer getId() {
        return id;
    }

    public Vacation setId(Integer id) {
        this.id = id;
        return this;
    }

    public SolutionEmployee2 getEmployee() {
        return solutionEmployee2;
    }

    public Vacation setEmployee(SolutionEmployee2 solutionEmployee2) {
        this.solutionEmployee2 = solutionEmployee2;
        return this;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public Vacation setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
        return this;
    }
}
