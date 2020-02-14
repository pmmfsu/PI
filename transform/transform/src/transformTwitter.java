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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class transformTwitter {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        FileInputStream serviceAccount =
                new FileInputStream("./ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://projetointegrado-71528.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();


        try {

            File_Timestamp file_timestamp = getFileTwitter(db);
            BufferedReader reader = new BufferedReader(new FileReader(file_timestamp.getFile()));

            String line;
            while ((line = reader.readLine()) != null) {

                String[] subLine = line.split(";");
                Timestamp timestamp = new Timestamp(Long.parseLong(subLine[2]));
                if(timestamp.getTime() > file_timestamp.getTimestamp().getTime()){
                    DocumentReference docRef = db.collection("Twitter").document();
                    Twitter tweet = new Twitter(subLine[0], subLine[1], timestamp, Integer.parseInt(subLine[3]));
                    ApiFuture<WriteResult> result = docRef.set(tweet);
                    result.get();
                }

            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static File_Timestamp getFileTwitter(Firestore db) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("LastRan").document("Twitter");

        ApiFuture<DocumentSnapshot> future = docRef.get();
        File file = null;
        Timestamp t = null;
        DocumentSnapshot document = future.get();
        Timestamp timestamp = null;
        if (document.exists()) {
            timestamp = Objects.requireNonNull(document.getTimestamp("Last")).toSqlTimestamp();
        }

        File folder = new File("./Files/Twitter");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {


                String[] sub = listOfFiles[i].getName().replaceFirst("[.][^.]+$", "").split("_");
                Timestamp fileTimestamp = new Timestamp(Long.parseLong(sub[1]));
                assert timestamp != null;
                if (fileTimestamp.getTime() > timestamp.getTime()){
                    file = listOfFiles[i];
                    t=fileTimestamp;
                }
            }
        }

        if (t == null){
            System.exit(0);
        }

        Map<String, Object> docData = new HashMap<>();
        docData.put("Last", t);
        ApiFuture<WriteResult> result = docRef.set(docData);
        result.get();


        return new File_Timestamp(file, timestamp);
    }

    static class File_Timestamp{

        private File File;
        private Timestamp Timestamp;


        public File_Timestamp(File File, Timestamp Timestamp){
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

    static class Twitter{

        private String Username;
        private String Comentary;
        private Timestamp Date;
        private int Patrimony_Id;

        public Twitter(String Username, String Comentary, Timestamp Date, int Patrimony_Id){
            this.Username = Username;
            this.Comentary = Comentary;
            this.Date = Date;
            this.Patrimony_Id = Patrimony_Id;
        }

        public int getPatrimony_Id() {
            return Patrimony_Id;
        }

        public String getComentary() {
            return Comentary;
        }

        public String getUsername() {
            return Username;
        }

        public Timestamp getDate() {
            return Date;
        }
    }

}
