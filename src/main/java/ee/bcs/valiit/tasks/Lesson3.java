package ee.bcs.valiit.tasks;

public class Lesson3 {
    public static void main(String[] args) {
        //sum(new int[] {1,2,3});
        //isPrime(9);
        //reverseString("Tere");

    }

    public static int sum(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int sum = 0;
        for (int i = 0; i <= x.length; i++) {
            sum = sum + i;
            System.out.print(sum); //midagi valesti?
        }

        return sum;
    }

    // TODO tagasta x faktoriaal.
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120

    public static int factorial(int x) {
        int fac = 1;
        for (int i = 1; i <= x; i++) {
            fac = fac * i;
        }
        System.out.println(fac);
        return fac;
    }


    public static int[] sort(int[] a) {
        // TODO sorteeri massiiv suuruse järgi.
        // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni


        return new int[0];

    }

    public static String reverseString(String a) { //Stringi ei saa niisama ümber pöörata, küll on see võimekus StringBuilderi classil. Seega teisendad selleks.
        // TODO tagasta string tagurpidi
        StringBuilder tagurpidi = new StringBuilder();
        tagurpidi.append(a);  // append kasutatakse Strinbuilderile nimega tagurpidi väärtuse andmiseks, ehk anname väärtuse mis main methodis (a) sisse kirjutame.
        tagurpidi = tagurpidi.reverse();
        System.out.println(tagurpidi);
        return "";
    }

    public static boolean isPrime(int x) {
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
        boolean kasOn = false;              //teed esialgse booleani, mille paned falseks.
        for (int i = 2; i <= x / 2; i++) {
            if (x % i == 0) {               //teed for loopi.
                kasOn = true;               //kui siin on true, siis ta ei ole primaararv. kui ta loopi lõpuks ei jaga millegiga, peab olema primaararv.
                break;                      // kohe kui on true, breakid loopi.
            }
        }
        if (!kasOn)
            System.out.println(x + " on primaararv.");
        else
            System.out.println(x + " ei ole primaararv.");
    return false;
    }
}
