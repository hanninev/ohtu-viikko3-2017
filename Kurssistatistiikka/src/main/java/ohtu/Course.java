package ohtu;

import java.util.ArrayList;

public class Course {

    private ArrayList<Integer> exercises;
    private String name;
    private String term;

    public Course(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }
    
    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<Integer> getExercises() {
        return exercises;
    }

    public int getMax(int week) {
        return exercises.get(week - 1);
    }

}
