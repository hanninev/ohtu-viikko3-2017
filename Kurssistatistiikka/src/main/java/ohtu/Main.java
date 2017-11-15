package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

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
        System.out.println("yhteensä: " + exercises + ", aikaa kului " + hours + " tuntia");

    }
}
