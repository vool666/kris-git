package ee.bcs.valiit.tasks.solution.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private String jobtitle;
    private int vanus;
    private int staaž;


    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public int getVanus() {
        return vanus;
    }

    public void setVanus(int vanus) {
        this.vanus = vanus;
    }

    public int getStaaž() {
        return staaž;
    }

    public void setStaaž(int staaž) {
        this.staaž = staaž;
    }
}


