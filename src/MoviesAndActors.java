import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;


public class MoviesAndActors {
    public static ArrayList<Movie2> getMovieDB(String fileName) {
        ArrayList<Movie2> movies = new ArrayList<Movie2>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    Movie2 s = new Movie2(data[0], data[1]);
                    movies.add(s);
                }

            }
        } catch (FileNotFoundException noFile) {
            System.out.println("Not Found");
            return null;
        }
        return movies;
    }

    public static ArrayList<String> getActors(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        ArrayList<String> actors = new ArrayList<String>();
        while (s.hasNext()){
            String actor = s.nextLine();
            actors.add(actor);
        }
        s.close();
        return actors;
    }

}
