import java.io.*;

public class transformGuiaDaCidade {
    public static void main(String[] args) {

        try {
            FileWriter writer = new FileWriter( "outputGuiaDaCidade.csv");
            BufferedReader reader = new BufferedReader(new FileReader("./guiaDaCidade_1581494246383.csv"));

            writer.write("rating ; classificacao ; numero de votos ; id do patrimonio" + System.lineSeparator());

            String line;
            while ((line = reader.readLine()) != null) {

                String[] subLine = line.split(";");

                String[] sub2 = subLine[2].split(" ");

                writer.write(subLine[0]+";"+subLine[1]+";"+sub2[0]+";"+ subLine[3]+System.lineSeparator());
            }


            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
