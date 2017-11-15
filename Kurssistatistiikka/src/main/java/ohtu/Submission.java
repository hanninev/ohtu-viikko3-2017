package ohtu;

import java.util.ArrayList;

public class Submission {
    private int week;
    private int hours;
    private ArrayList<Integer> exercises;

    public Submission(int week, int hours, ArrayList<Integer> exercises) {
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;
    }
    
    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public ArrayList<Integer> getExercises() {
        return exercises;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }
    
    @Override
    public String toString() {
        String done = "";
        for (int i = 0; i < exercises.size()-1; i++) {
        done = done + exercises.get(i) + ", ";
        }
        done = done + exercises.get(exercises.size()-1);
        
        return "Viikko " + week + ": tehtyjä tehtäviä yhteensä: " + exercises.size() + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + done;
    }
    
}