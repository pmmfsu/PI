import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class transformTripAdvisor {
    public static void main(String[] args) {
        String s ="Mario S escreveu uma avaliação a nov de 2019;" +
                "Muito bem conservado;" +
                "Monumento que surpreendeu pela positiva. Muito bem conservado e limpo e com vista impressionante da sua torre sobre Beja e arredores. Sem duvida o ex libris desta cidade." +
                ";1";
        try {
            FileWriter writer = new FileWriter( "outputTripadivsor.csv");
            BufferedReader reader = new BufferedReader(new FileReader("./tripAdvisor_1581494997823.csv"));

            writer.write("Username ; Data ; Titulo ; Comentario ; id do patrimonio" + System.lineSeparator());

            String line;
            while ((line = reader.readLine()) != null) {

                String[] subLine = line.split(";");

                String[] sub0 = subLine[0].split(" escreveu uma avaliação a ");

                writer.write(sub0[0]+";"+sub0[1]+";"+subLine[1]+";"+ subLine[2]+";"+subLine[3]+System.lineSeparator());
            }


            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
