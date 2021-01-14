package A2;

import java.io.*;

public class MainSinExpresiones {
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        File file = new File(home+"/Desktop/santako.txt");
        FileReader fr = null;
        String linea = null;

        try{
            fr = new FileReader(file);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            linea = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String papaNoel ="*<]:-DOo";
        String reno =">:o)";
        String ayudantes =("<]:-D");
        int numeroIndice=0;

        int papaNoelVeces = 0;
        int renoVeces= 0;
        int ayudantesVeces= 0;
        int numeroLinea=1;

        System.out.println("Linea numero: "+numeroLinea);

        while (linea != null){
            if (linea.contains(papaNoel)){
                    numeroIndice=linea.indexOf(papaNoel);
                    numeroIndice=numeroIndice+papaNoel.length();
                    linea = linea.substring(numeroIndice);
                    papaNoelVeces++;
            }if (linea.contains(reno)){
                    numeroIndice=linea.indexOf(reno);
                    numeroIndice=numeroIndice+reno.length();
                    linea = linea.substring(numeroIndice);
                    renoVeces++;
            }if (linea.contains(ayudantes) && !linea.contains(papaNoel) ){
                    numeroIndice=linea.indexOf(ayudantes);
                    numeroIndice=numeroIndice+ayudantes.length();
                    linea = linea.substring(numeroIndice);
                    ayudantesVeces++;
            }if (!linea.contains(ayudantes) && !linea.contains(papaNoel)&&!linea.contains(reno)){
                try {
                    if (papaNoelVeces > 0){
                        System.out.println("Papa Noel "+"("+papaNoelVeces+")");

                    }

                    if (renoVeces > 0){
                        System.out.println("reno "+"("+renoVeces+")");

                    }

                    if (ayudantesVeces > 0){
                        System.out.println("ayudantes "+"("+ayudantesVeces+")");

                    }

                    linea= br.readLine();
                    papaNoelVeces=0;
                    renoVeces=0;
                    ayudantesVeces=0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }
}
