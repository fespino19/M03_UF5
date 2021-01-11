package A2;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        File file = new File(home+"/"+"santako.txt");
        FileReader fr = null;
        String linea = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            linea = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern PareNoel = Pattern.compile("\\*<]:-DOo");
        Pattern Rens = Pattern.compile(">:o\\)");
        Pattern Ajudants = Pattern.compile("[^*]<]:-D");
        int numerolinea=0;
        int pareVegades = 0;
        int rensVegades = 0;
        int ajudantsVegades = 0;

        while (linea != null){
            System.out.println("linea "+numerolinea);
            Matcher pare = PareNoel.matcher(linea);
            Matcher rens = Rens.matcher(linea);
            Matcher ajudants = Ajudants.matcher(linea);

            if (pare.find()){
                pareVegades++;
                System.out.println("Pare Noel"+" ("+pareVegades+")");
            }

            if (rens.find()){
                rensVegades++;
                System.out.println("Rens"+" ("+rensVegades+")");
            }

            if (ajudants.find()){
                ajudantsVegades++;
                System.out.println("Ajudants"+" ("+ajudantsVegades+")");
            }

            try {
                linea=br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
}
