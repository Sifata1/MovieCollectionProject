import java.util.ArrayList;

public class Movie2 {
    private String title;
    private String actorsInfo;
    private ArrayList<String> actors;

    public Movie2(String t, String a) {
        actors = new ArrayList<String>();
        this.title = t;
        this.actorsInfo = a;
        String[] actors2 = actorsInfo.split(":");
        for (int i = 0; i < actors2.length; i++) {
            actors.add(actors2[i]);
        }

    }

    public String getActorsStr() {
        String str = "";
        for (int i = 0; i < actors.size(); i++){
            str += actors.get(i) + ":";
        }
        return str;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public String getTitle(){
        return title;
    }

    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}