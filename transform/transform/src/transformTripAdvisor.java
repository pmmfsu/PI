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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class transformTripAdvisor {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        FileInputStream serviceAccount =
                   new FileInputStream("./ServiceAccountKey.json");


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://projetointegrado-71528.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();


        try {

            File_Timestamp file_timestamp = getFileTrip(db);
            BufferedReader reader = new BufferedReader(new FileReader(file_timestamp.getFile()));



            String line;
            while ((line = reader.readLine()) != null) {

                String[] subLine = line.split(";");

                String[] sub0 = subLine[0].split(" escreveu uma avaliação a ");

                String[] sub1 = sub0[1].split(" de ");


                int year = Integer.parseInt(sub1[1]);
                int month = getMonth(sub1[0]);

                Date date = new GregorianCalendar(year, month - 1, 10).getTime();


                Date d = new Date(file_timestamp.getTimestamp().getTime());
                LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int y  = localDate.getYear();
                int m = localDate.getMonthValue();

                localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int Y  = localDate.getYear();
                int M = localDate.getMonthValue();

                if (Y == y && m == M) {

                    DocumentReference docRef = db.collection("Trip").document();
                    Trip com = new Trip(sub0[0], new Timestamp(date.getTime()), subLine[1], subLine[2], Integer.parseInt(subLine[3]));
                    ApiFuture<WriteResult> result = docRef.set(com);
                    result.get();
                }
            }



            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int getMonth(String s) {
        if ("dez".equals(s)){
            return 12;

        }else if ("nov".equals(s)){
            return 11;

        }else if ("out".equals(s)){
            return 10;

        }else if ("set".equals(s)){
            return 9;

        }else if ("ago".equals(s)){
            return 8;

        }else if ("jul".equals(s)){
            return 7;

        }else if ("jun".equals(s)){
            return 6;

        }else if ("mai".equals(s)){
            return 5;

        }else if ("abr".equals(s)){
            return 4;

        }else if ("mar".equals(s)){
            return 3;

        }else if ("fev".equals(s)){
            return 2;

        }else{
            return 1;
        }
    }

    static class Trip{

        private String Username;
        private Timestamp Date;
        private String Title;
        private String Comment;
        private int Patrimony_Id;

        public Trip(String Username, Timestamp Date, String Title, String Comment, int Patrimony_Id){
            this.Username = Username;
            this.Date = Date;
            this.Title = Title;
            this.Comment = Comment;
            this.Patrimony_Id = Patrimony_Id;
        }

        public Timestamp getDate() {
            return Date;
        }

        public String getUsername() {
            return Username;
        }

        public int getPatrimony_Id() {
            return Patrimony_Id;
        }

        public String getComment() {
            return Comment;
        }

        public String getTitle() {
            return Title;
        }

    }
    private static File_Timestamp getFileTrip(Firestore db) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("LastRan").document("Trip");

        ApiFuture<DocumentSnapshot> future = docRef.get();
        File file = null;
        Timestamp t = null;
        DocumentSnapshot document = future.get();
        Timestamp timestamp = null;
        if (document.exists()) {
            timestamp = Objects.requireNonNull(document.getTimestamp("Last")).toSqlTimestamp();
        }


        File folder = new File("./Files/tripAdivisor");
        File[] listOfFiles = folder.listFiles();

        if( listOfFiles != null) {
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

        if (t == null){
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
}
