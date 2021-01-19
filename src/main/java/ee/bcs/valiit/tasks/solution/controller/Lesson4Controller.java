package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

// teha eraldi teenused if statementidega (getbalance jne) igale teenusele url. ntks public void createaccount (). ehk bank/cerateaccount\accountnr=ee123. siis public bigdecimal accountbalance - accountbalance?accountnr=ee123. public void depositmoney jne.
// bank/transferMoney?fromACCOUNT=EE123&toAccount jne
@RestController
public class Lesson4Controller {
    HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();

    @GetMapping("createAccount")
    public String createAccount(@RequestParam("accountNr") String accountNr) {
        accountBalanceMap.put(accountNr, BigDecimal.ZERO);
        return ("Konto nr " + accountNr + " loomine õnnestus.");
    }

    @GetMapping("getBalance")
    public String getBalance(@RequestParam("accountNr") String accountNr) {
        BigDecimal x = accountBalanceMap.get(accountNr);
        if (x == null) {
            return ("Sellist kontot ei ole.");
        } else {
            return ("Teie konto balanss on " + x + " eurot.");
        }
    }

    @GetMapping("depositMoney")
    public String depositMoney(@RequestParam("accountNr") String accountNr, @RequestParam("sum") String sum) {
        BigDecimal x = new BigDecimal(sum);
        BigDecimal y = accountBalanceMap.get(accountNr);
        MathContext mc = new MathContext(4);
        int comp = (x.compareTo(BigDecimal.ZERO));
        if (comp == -1) {
            return ("Kontole ei saa negatiivset summat lisada.");
        } else if (y == null) {
            return ("Sellist kontot ei ole.");
        } else {
            accountBalanceMap.put(accountNr, (x.add(y, mc)));
            return ("Teie kontole lisati " + x + " eurot.");
        }
    }

    @GetMapping("withdrawMoney")
    public String withdrawMoney(@RequestParam("accountNr") String accountNr, @RequestParam("sum") String sum) {
        BigDecimal x = new BigDecimal(sum); // raha väärtus
        BigDecimal y = accountBalanceMap.get(accountNr); // konto nr millelt võetakse
        MathContext mc = new MathContext(4);
        int comp = (x.compareTo(BigDecimal.ZERO));
        int comp2 = (x.compareTo(y));
        if (comp == -1) {
            return ("Kontolt ei saa negatiivset summat välja võtta.");
        } else if (y == null) {
            return ("Sellist kontot ei ole.");
        } else if (comp2 == 1) {
            return ("Kontolt ei saa nii palju raha välja võtta- rahalised vahendid puuduvad.");
        } else {
            accountBalanceMap.put(accountNr, (y.subtract(x, mc)));
            return ("Teie kontolt võeti välja " + x + " eurot.");
        }
    }

    @GetMapping("transfer")
    public String transfer(@RequestParam("fromAccount") String fromAccount, @RequestParam("toAccount") String toAccount, @RequestParam("sum") String sum) {
        BigDecimal x = new BigDecimal(sum); //number, mida hakatakse liitma ühele kontole ja lahutama teiselt
        BigDecimal y = accountBalanceMap.get(fromAccount); // konto, millelt raha maha võetakse
        BigDecimal z = accountBalanceMap.get(toAccount); // konto, millele raha liidetakse
        MathContext mc = new MathContext(4);
        int comp = (x.compareTo(BigDecimal.ZERO));
        int comp2 = (x.compareTo(y));
        if (comp == -1) {
            return ("Negatiivset summat ei ole võimalik kanda.");
        } else if (y == null) {
            return ("Kontot, millelt soovite raha kanda, ei ole olemas.");
        } else if (z == null) {
            return ("Kontot, millele soovite raha kanda, ei ole olemas.");
        } else if (comp2 == 1) {
            return ("Kontolt ei saa nii palju raha edastada- rahalised vahendid puuduvad.");
        } else {
            accountBalanceMap.put(fromAccount, (y.subtract(x, mc)));
            accountBalanceMap.put(toAccount, (z.add(x, mc)));
            return ("Makse õnnestus. Te kandsite " + x + " eurot kontolt " + fromAccount + " kontole " + toAccount + ".");
        }
    }
}

