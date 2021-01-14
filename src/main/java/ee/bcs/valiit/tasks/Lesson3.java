package ee.bcs.valiit.tasks;

public class Lesson3 {
    public static void main(String[] args) {
        nthPower(new int[]{1, 2, 3, 4, 5}, 5);
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
        for (int j = 0; j < a.length; j++) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] > a[i + 1]) {
                    int tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                }
            }
        }
        return a;
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


    public static int nthPower(int[] array, int n) {
        if (n >= array.length) {
            return -1;
        } else {
            double d = (Math.pow(array[n], n));
            int i = (int) d;
            System.out.println(i);
            return i;
        }
    }

    public static int howOld(final String herOld) {
        String vanusString = herOld.substring(0,1);
        int vanus = Integer.parseInt(vanusString);
        return vanus;
    }
}
