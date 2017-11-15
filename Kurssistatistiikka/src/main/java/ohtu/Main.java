package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        // 011120775
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        String courseurl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String courseinfo = Request.Get(courseurl).execute().returnContent().asString();

        Gson courseMapper = new Gson();
        Course course = courseMapper.fromJson(courseinfo, Course.class);

        String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String statsResponse = Request.Get(statsUrl).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();

        int totalSubmissions = 0;
        int totalExercises = 0;
        for (int i = 1; i < parsittuData.size()+1; i++) {
            String is = i + "";
            totalSubmissions += Integer.parseInt(parsittuData.get(is).getAsJsonObject().get("students").toString());
            totalExercises += Integer.parseInt(parsittuData.get(is).getAsJsonObject().get("exercise_total").toString());
        }

        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm());
        System.out.println("");

        System.out.println("Opiskelijanumero: " + studentNr);
        System.out.println("");
        int exercises = 0;
        int hours = 0;

        for (Submission submission : subs) {
            submission.setCourse(course);
            System.out.println(submission);
            exercises += submission.getExercises().size();
            hours += submission.getHours();
        }

        System.out.println("");
        System.out.println("Yhteensä: " + exercises + ", aikaa kului " + hours + " tuntia\n");
        System.out.println("Kurssilla yhteensä " + totalSubmissions + " palautusta, palautettuja tehtäviä " + totalExercises + " kpl");

    }
}
