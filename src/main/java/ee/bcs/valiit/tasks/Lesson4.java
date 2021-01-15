package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    static HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();

    // Funktsioonid:
    // command: "createAccount ${accountNr}" Teeb konto 0 balansiga.
    // command: "getBalance ${accountNr}"    Näitab konto jääki.
    // command: "depositMoney ${accountNr} ${amount} Saad kanda kontole summa.
    // command: "withdrawMoney ${accountNr} ${amount} Saad kontolt raha välja võtta.
    // command: "transfer ${fromAccount} ${toAccount} ${amount} Saad kanda raha erinevate kontode vahel.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String arr[] = line.split(" ");
            if (arr[0].equalsIgnoreCase("exit")) {
                break;
            } else if (arr[0].equalsIgnoreCase("createAccount")) {
                accountBalanceMap.put(arr[1], BigDecimal.ZERO);
            } else if (arr[0].equalsIgnoreCase("getBalance")) {
                BigDecimal x = accountBalanceMap.get(arr[1]);
                if (x == null) {
                    System.out.println("Sellist kontot ei ole.");
                } else {
                    System.out.println("Teie konto balanss on " + x);
                }
            } else if (arr[0].equalsIgnoreCase("depositMoney")) {
                BigDecimal x = new BigDecimal(arr[2]);
                BigDecimal y = accountBalanceMap.get(arr[1]);
                ;
                MathContext mc = new MathContext(4);
                int comp = (x.compareTo(BigDecimal.ZERO));
                if (comp == -1) {
                    System.out.println("Kontole ei saa negatiivset summat lisada.");
                } else if (y == null) {
                    System.out.println("Sellist kontot ei ole.");
                } else {
                    accountBalanceMap.put(arr[1], (x.add(y, mc)));
                }
            } else if (arr[0].equalsIgnoreCase("withdrawMoney")) {
                BigDecimal x = new BigDecimal(arr[2]); // raha väärtus
                BigDecimal y = accountBalanceMap.get(arr[1]); // konto nr millelt võetakse
                MathContext mc = new MathContext(4);
                int comp = (x.compareTo(BigDecimal.ZERO));
                int comp2 = (x.compareTo(y));
                if (comp == -1) {
                    System.out.println("Kontolt ei saa negatiivset summat välja võtta.");
                } else if (y == null) {
                    System.out.println("Sellist kontot ei ole.");
                } else if (comp2 == 1) {
                    System.out.println("Kontolt ei saa nii palju raha välja võtta- rahalised vahendid puuduvad.");
                } else {
                    accountBalanceMap.put(arr[1], (y.subtract(x, mc)));
                }
            } else if (arr[0].equalsIgnoreCase("transfer")) {
                BigDecimal x = new BigDecimal(arr[3]); //number, mida hakatakse liitma ühele kontole ja lahutama teiselt
                BigDecimal y = accountBalanceMap.get(arr[1]); // konto, millelt raha maha võetakse
                BigDecimal z = accountBalanceMap.get(arr[2]); // konto, millele raha liidetakse
                MathContext mc = new MathContext(4);
                int comp = (x.compareTo(BigDecimal.ZERO));
                int comp2 = (x.compareTo(y));
                if (comp == -1) {
                    System.out.println("Negatiivset summat ei ole võimalik kanda.");
                } else if (y == null) {
                    System.out.println("Kontot, millelt soovite raha kanda, ei ole olemas.");
                } else if (z == null) {
                    System.out.println("Kontot, millele soovite raha kanda, ei ole olemas.");
                } else if (comp2 == 1) {
                    System.out.println("Kontolt ei saa nii palju raha välja võtta- rahalised vahendid puuduvad.");
                } else {
                    accountBalanceMap.put(arr[1], (y.subtract(x, mc)));
                    accountBalanceMap.put(arr[2], (z.add(x, mc)));
                    System.out.println("Makse õnnestus.");
                }

            }
            else {
                System.out.println("Unknown command");
            }
        }
    }
}
