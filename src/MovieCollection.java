import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private ArrayList<String> casts;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }


    public void searchCast() {
        System.out.print("Enter the name of a cast member: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<Movie> results2 = new ArrayList<Movie>();

        if (results.size() == 0) {
            for (int i = 0; i < movies.size(); i++) {
                String[] currentCast = movies.get(i).getCast().split("[|]");
                for (String actor : currentCast) {
                    if (actor.toLowerCase().contains(searchTerm) && results.contains(actor) == false) {
                        results.add(actor);
                    }
                }
            }
            for (int i = 0; i < results.size(); i++) {
                System.out.println(i + 1+ ". " + results.get(i));
            }

            System.out.println("Which actor would you like to know more about?");
            int actor = scanner.nextInt();
            actor = actor-1;

            for (Movie mov : movies) {
                if (mov.getCast().contains(results.get(actor))) {
                    results2.add(mov);
                }
            }

            for (int i = 0; i < results2.size(); i++) {
                System.out.println(i + 1+ ". " + results2.get(i).getTitle());
            }

            System.out.println("Which movie would you like to learn about?");
            int item = scanner.nextInt();


            displayMovieInfo(results2.get(item-1));
            scanner.nextLine();
        } else {
            System.out.println("There is no actor with that name.");
        }
    }

    private void listGenres()
    {
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<Movie> moviesWithGenre = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++) {
            String[] c = movies.get(i).getGenres().split("[|]");
            for (String genre : c) {
                if (!(results.contains(genre))) {
                    results.add(genre);
                }
            }
        }

        for (int i = 0; i < results.size(); i++) {
            System.out.println(i + 1+ ". " + results.get(i));
        }

        System.out.println("Which genre would you like to learn more about?");
        int genreChoice = scanner.nextInt();
        genreChoice--;

        for (Movie mov : movies) {
            if (mov.getGenres().contains(results.get(genreChoice))) {
                moviesWithGenre.add(mov);
            }
        }

        sortResults(moviesWithGenre);

        for (int i = 0; i < moviesWithGenre.size(); i++) {
            System.out.println(i + 1+ ". " + moviesWithGenre.get(i).getTitle());
        }

        System.out.println("Which movie would you like to learn about?");
        int item = scanner.nextInt();


        displayMovieInfo(moviesWithGenre.get(item-1));
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keyword = movies.get(i).getKeywords();
            keyword = keyword.toLowerCase();

            if (keyword.indexOf(searchTerm) != -1)
            {
                //add the Movie objects to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {
        Movie[] top50 = new Movie[50];
        ArrayList<Movie> m = new ArrayList<>();

        for (int i = 0; i < movies.size(); i++) {
            m.add(movies.get(i));
        }

        for (int i = 0; i < 50; i++) {
            double highest = 0;
            int highestRatedIndex = 0;
            Movie highestRatedMovies = new Movie("", "", "", "", "", "", 0, "", 0, 0, 0);
            for (int k = 0; k < m.size(); i++) {
                if (m.get(k).getUserRating() > highest){
                    highest = m.get(k).getUserRating();
                    highestRatedMovies = m.get(k);
                    highestRatedIndex = k;

                }
            }
            m.remove(highestRatedIndex);
            top50[i] = highestRatedMovies;
        }

        for (int i =0 ;i < 50; i++) {
            System.out.println((i+1) + top50[i].getTitle() + ":" + top50[i].getUserRating() );
        }
    }

    private void listHighestRevenue()
    {
        Movie[] top50 = new Movie[50];
        ArrayList<Movie> m = new ArrayList<>();

        for (int i = 0; i < movies.size(); i++) {
            m.add(movies.get(i));
        }

        for (int i = 0; i < 50; i++) {
            double highest = 0;
            int highestRevenueIndex = 0;
            Movie highestRevenueMovies = new Movie("", "", "", "", "", "", 0, "", 0, 0, 0);
            for (int k = 0; k < m.size(); i++) {
                if (m.get(k).getRevenue() > highest){
                    highest = m.get(k).getRevenue();
                    highestRevenueMovies = m.get(k);
                    highestRevenueIndex = k;

                }
            }
            m.remove(highestRevenueIndex);
            top50[i] = highestRevenueMovies;
        }

        for (int i =0 ;i < 50; i++) {
            System.out.println((i+1) + top50[i].getTitle() + ":" + top50[i].getRevenue() );
        }
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }

    private void importCastList(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            casts = new ArrayList<String>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String cast = movieFromCSV[1];

                for (int i = 0; i > movies.size(); i++) {
                    casts.add(cast);
                }
            }
        } catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }


    }
}