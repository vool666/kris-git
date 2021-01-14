package ee.bcs.valiit.tasks;

import java.util.*;


public class Lesson3Hard {
    public static void main(String[] args) {
        //morseCode("kris");
        //randomGame();
        //evenFibonacci(7);
    }


    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        // kuni seitsmenda jada väärtuseni ntks - 0, 1, 1, 2, 3, 5, 8 - seitse liiget jadas. liita kokku kõik paaris arvud ehk peaks olema 2+8 ja returnima.

        List<Integer> list = new ArrayList<>();

        if (x == 0) {
            return 0;
        } else if (x == 1){
            return 0;
        } else {
            list.add(0);
            list.add(1);
            int a = 0;
            int b = 1;
            for (int i = 1; i < x; i++) {
                int tmp = a;
                a = b;
                b = b + tmp;
                list.add(b);
            }
            System.out.println(list);
        }
        int sum = 0;
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i) %2 == 0) {
                sum = sum + list.get(i);
            }
        }
        System.out.println(sum);
    return sum;
    }


    public static void randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        Random random = new Random();
        int suvalineNumber = random.nextInt(100);
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        for (int i = 0; i < 100; i++) {
            int userInput = scanner.nextInt();
            if (userInput < suvalineNumber) {
                System.out.println("Number on väiksem kui suvaline number.");
                count++;
            } else if (userInput > suvalineNumber) {
                System.out.println("Number on suurem kui suvaline number.");
                count++;
            } else {
                System.out.println("Suvaline number oli " + suvalineNumber + ", ehk sisestatud number oli õige. Sul läks arvamiseks " + count + " korda.");
                break;
            }
        }
    }

    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -
        HashMap<String, String> morse = new HashMap<>();
        morse.put("a", ".-");
        morse.put("b", "-...");
        morse.put("c", "-.-.");
        morse.put("d", "-..");
        morse.put("e", ".");
        morse.put("f", "..-.");
        morse.put("g", "--.");
        morse.put("h", "....");
        morse.put("i", "..");
        morse.put("j", ".---");
        morse.put("k", "-.-");
        morse.put("l", ".-..");
        morse.put("m", "--");
        morse.put("n", "-.");
        morse.put("o", "---");
        morse.put("p", ".--.");
        morse.put("q", "--.-");
        morse.put("r", ".-.");
        morse.put("s", "...");
        morse.put("t", "-");
        morse.put("u", "..-");
        morse.put("v", "...-");
        morse.put("w", ".--");
        morse.put("x", "-..-");
        morse.put("y", "-.--");
        morse.put("z", "--..");
        morse.put(" ", " ");

        int sõnaPikkus = text.length(); //sõnaPikkus on sama mis subStringi viimane number.
        String result = "";
        for (int i = 0; i < sõnaPikkus; i++) {
            String täht = text.substring(i, i + 1);
            result += morse.get(täht) + " ";
        }
        return result;
    }
}
