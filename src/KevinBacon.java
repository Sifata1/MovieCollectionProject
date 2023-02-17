import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KevinBacon {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Movie2> movies = MoviesAndActors.getMovieDB("src/movie_data");
        ArrayList<String> kbActors = MoviesAndActors.getActors("src/ActorNames");
        ArrayList<Movie2> kbActorsMovies = MoviesAndActors.getMovieDB("src/kbActorMovies");
        ArrayList<Movie2> kbMovies = MoviesAndActors.getMovieDB("src/kbMovies");
        ArrayList<Movie2> actorMovies = new ArrayList<>();
        ArrayList<String> actorMoviesActors = new ArrayList<>();
        ArrayList<Movie2> actorMoviesActorsMovies = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String actor = "";
        System.out.print("Enter an actor's name: ");
        actor = s.nextLine();

        for (Movie2 value : movies) {
            if (value.getActors().contains(actor)) {
                actorMovies.add(value);
            }
        }
        for (Movie2 simpleMovie : actorMovies) {
            for (int j = 0; j < simpleMovie.getActors().size(); j++) {
                String actorIn = simpleMovie.getActors().get(j);
                if (!(actorMoviesActors.contains(actorIn))) {
                    actorMoviesActors.add(actorIn);
                }
            }
        }

        for (Movie2 movie : movies) {
            for (String actorMoviesActor : actorMoviesActors) {
                if (movie.getActors().contains(actorMoviesActor)) {
                    actorMoviesActorsMovies.add(movie);
                    break;
                }
            }
        }
        boolean checker = false;
        if (kbActors.contains(actor)) {
            System.out.println("Bacon Number: 1");
            for (Movie2 kbMovie : kbMovies) {
                if (kbMovie.getActors().contains(actor)) {
                    System.out.println(actor + " --> " + kbMovie.getTitle() + "  --> Kevin Bacon\n");
                    break;
                }
            }
            checker = true;
        }
        if (!checker) {
            for (Movie2 kbActorsMovie : kbActorsMovies) {
                if (kbActorsMovie.getActors().contains(actor)) {
                    System.out.println("Bacon Number: 2");
                    String mainMovie = kbActorsMovie.getTitle();
                    checker = true;
                    for (int k = 0; k < kbActorsMovie.getActors().size(); k++) {
                        String nextActor = kbActorsMovie.getActors().get(k);
                        if (kbActors.contains(nextActor)) {
                            for (Movie2 kbMovie : kbMovies) {
                                if (kbMovie.getActors().contains(nextActor)) {
                                    String nextMovie = kbMovie.getTitle();
                                    System.out.println(nextMovie);
                                    System.out.println(actor + " --> " + mainMovie + " --> " + nextActor + " --> " + nextMovie + "  --> Kevin Bacon\n");
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        if (!checker) {
            for (String actorMoviesActor : actorMoviesActors) {
                for (Movie2 kbActorsMovie : kbActorsMovies) {
                    if (kbActorsMovie.getActors().contains(actorMoviesActor)) {
                        String mainMovie = "";
                        System.out.println("Bacon Number: 3");
                        checker = true;
                        for (Movie2 actorMovie : actorMovies) {
                            if (actorMovie.getActors().contains(actorMoviesActor)) {
                                mainMovie = actorMovie.getTitle();
                            }
                        }
                        for (int k = 0; k < kbActorsMovie.getActors().size(); k++) {
                            if (kbActors.contains(kbActorsMovie.getActors().get(k))) {
                                String actorTwo = kbActorsMovie.getActors().get(k);
                                for (Movie2 kbMovie : kbMovies) {
                                    if (kbMovie.getActors().contains(actorTwo)) {
                                        String movieTwo = kbMovie.getTitle();
                                        System.out.println(actor + " --> " + mainMovie + " --> " + actorMoviesActor + " --> " + kbActorsMovie.getTitle() + " --> " + actorTwo + " --> " + movieTwo + "  --> Kevin Bacon\n");
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;

                    }
                }
                if (checker) {
                    break;
                }
            }
        }
        if (!checker) {
            String firstActor = "";
            for (Movie2 actorMoviesActorsMovie : actorMoviesActorsMovies) {
                for (int j = 0; j < kbActorsMovies.size(); j++) {
                    for (int k = 0; k < actorMoviesActorsMovie.getActors().size(); k++) {
                        if (kbActorsMovies.get(j).getActors().contains((actorMoviesActorsMovie.getActors().get(k)))) {
                            firstActor = actorMoviesActorsMovie.getActors().get(k);
                            Movie2 mainMovie = null;
                            String ogActor = "";
                            for (String actorMoviesActor : actorMoviesActors) {
                                if (actorMoviesActorsMovie.getActors().contains(actorMoviesActor)) {
                                    ogActor = actorMoviesActor;
                                    for (Movie2 actorMovie : actorMovies) {
                                        if (actorMovie.getActors().contains(ogActor)) {
                                            mainMovie = actorMovie;
                                        }
                                    }
                                }
                            }
                            for (Movie2 kbActorsMovie : kbActorsMovies) {
                                if (kbActorsMovie.getActors().contains(firstActor)) {
                                    for (String kbActor : kbActors) {
                                        if (kbActorsMovie.getActors().contains(kbActor)) {
                                            for (Movie2 kbMovie : kbMovies) {
                                                if (kbMovie.getActors().contains(kbActor)) {
                                                    System.out.println("Bacon Number: 4");
                                                    System.out.println(actor + " --> " + mainMovie.getTitle() + " --> " + ogActor + " --> " + actorMoviesActorsMovie.getTitle() + " --> " + firstActor + " --> " + kbActorsMovie.getTitle() + " --> " + kbActor + " --> " + kbMovie.getTitle() + "  --> Kevin Bacon\n");
                                                    checker = true;
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (firstActor != "") {
                        break;
                    }
                }
                if (firstActor != "") {
                    break;
                }
            }

        }
        if (!checker) {
            System.out.println("Bacon Number: 5");
        }
    }
}