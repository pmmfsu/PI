import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class transformTwitter {
    public static void main(String[] args) {
        String s = "@WaitingForThis3 " +
                "; Passar o dia no Castelo de Beja" +
                ";1581471123000" +
                ";1";
        try {
            FileWriter writer = new FileWriter( "outputTwitter.csv");
            BufferedReader reader = new BufferedReader(new FileReader("./twitter_1581498492749.csv"));

            writer.write("Username ; Comentario ; TimeStamp ; id do patrimonio" + System.lineSeparator());

            String line;
            while ((line = reader.readLine()) != null) {

                writer.write(line+System.lineSeparator());
            }


            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
