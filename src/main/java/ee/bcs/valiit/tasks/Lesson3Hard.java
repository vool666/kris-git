package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Random;


public class Lesson3Hard {
    public static void main(String[] args) {
        morseCode("a");
    }


        public static int evenFibonacci ( int x){
            // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
            return 0;
        }

        public static void randomGame () {
            // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
            // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
            // ja kasutaja peab saama uuesti arvata
            // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
            Random random = new Random();
            int i = random.nextInt(100);
            System.out.println(i);
        }

        public static String morseCode (String text){
            // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
            // Kasuta sümboleid . ja -
            // kasuta mapi
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
          int sõnaPikkus = text.length();

            return "";
        }
    }
