import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class transformGuiaDaCidade {
    public static void main(String[] args) {

        try {
            FileInputStream serviceAccount =
                    new FileInputStream("./ServiceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://projetointegrado-71528.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            Firestore db = FirestoreClient.getFirestore();

            File_Timestamp file_timestamp = getFileGuia(db);
            BufferedReader reader = new BufferedReader(new FileReader(file_timestamp.getFile()));


            String line;
            while ((line = reader.readLine()) != null) {

                String[] subLine = line.split(";");

                String[] sub2 = subLine[2].split(" ");


                DocumentReference docRef = db.collection("Guia").document();
                Guia total = new Guia(Double.parseDouble(subLine[0]), subLine[1], Integer.parseInt(sub2[0]), Integer.parseInt(subLine[3]));
                ApiFuture<WriteResult> result = docRef.set(total);
                result.get();
            }



            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class Guia {

        private double rating;
        private String classification;
        private int votes;
        private int Patrimony_Id;

        public Guia(double rating, String classification, int votes, int Patrimony_Id) {
            this.rating = rating;
            this.classification = classification;
            this.votes = votes;
            this.Patrimony_Id = Patrimony_Id;
        }

        public int getPatrimony_Id() {
            return Patrimony_Id;
        }

        public double getRating() {
            return rating;
        }

        public int getVotes() {
            return votes;
        }

        public String getClassification() {
            return classification;
        }
    }

    private static File_Timestamp getFileGuia(Firestore db) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("LastRan").document("Guia");

        ApiFuture<DocumentSnapshot> future = docRef.get();
        File file = null;
        Timestamp t = null;
        DocumentSnapshot document = future.get();
        Timestamp timestamp = null;
        if (document.exists()) {
            timestamp = Objects.requireNonNull(document.getTimestamp("Last")).toSqlTimestamp();
        }


        File folder = new File("./Files/GuiaDaCidade");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {


                    String[] sub = listOfFiles[i].getName().replaceFirst("[.][^.]+$", "").split("_");
                    Timestamp fileTimestamp = new Timestamp(Long.parseLong(sub[1]));
                    assert timestamp != null;
                    if (fileTimestamp.getTime() > timestamp.getTime()) {
                        file = listOfFiles[i];
                        t = fileTimestamp;
                    }
                }
            }
        }

        if (t == null) {
            System.exit(0);
        }

        Map<String, Object> docData = new HashMap<>();
        docData.put("Last", t);
        ApiFuture<WriteResult> result = docRef.set(docData);
        result.get();


        return new File_Timestamp(file, subtractDays(t, 10));
    }

    public static Timestamp subtractDays(Timestamp timestamp, int days) {
        Date date = new Date(timestamp.getTime());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);

        return new Timestamp(cal.getTime().getTime());
    }

    static class File_Timestamp {

        private File File;
        private Timestamp Timestamp;


        public File_Timestamp(File File, Timestamp Timestamp) {
            this.File = File;
            this.Timestamp = Timestamp;

        }

        public java.io.File getFile() {
            return File;
        }

        public java.sql.Timestamp getTimestamp() {
            return Timestamp;
        }
    }
}
