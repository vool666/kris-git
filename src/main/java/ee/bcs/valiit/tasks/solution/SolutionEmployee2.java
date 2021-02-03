package ee.bcs.valiit.tasks.solution;

import javax.persistence.*;
import java.util.List;

@Entity
public class SolutionEmployee2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "solutionEmployee2")
    private List<Vacation> vacations;

    public String getName() {
        return name;
    }

    public SolutionEmployee2 setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SolutionEmployee2 setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public SolutionEmployee2 setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SolutionEmployee2 setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public SolutionEmployee2 setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
        return this;
    }
}
