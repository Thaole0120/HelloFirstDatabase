package movies;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

import static input.InputUtils.*;
import static input.InputUtils.yesNoInput;

public class MovieList {

    private static final String DB_PATH = "jdbc:sqlite:movie_watchlist.sqlite";
    private static Database database;

    public static void main(String[] args) {
        database = new Database(DB_PATH);
        //addNewMovie();
        // checkIfWatchedAndRate();
         deleteWatchedMovie();
        displayMovieList();

    }

    public static void addNewMovie(){
        do{
            String movieName = stringInput("Enter the movie name");
            boolean movieWatched = yesNoInput("Have you seen this movie yet?");
            int movieStars = 0;
            if(movieWatched){
                movieStars = positiveIntInput(" What is your rating, ins stars out of 5?");

            }
            Movie movie = new Movie(movieName, movieStars, movieWatched);
            database.addNewMovie(movie);

        }while (yesNoInput("Add a movie to the watchlist?"));
    }

    public static void displayMovieList(){
        List<Movie> movies = database.getAllMovies();
        if(movies.isEmpty()){
            System.out.println("No movies");
        }else {
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    public static void checkIfWatchedAndRate(){
        List<Movie> unwatchedMovies = database.getAllMoviesByWatched(false);

        for (Movie movie : unwatchedMovies) {
            boolean hasWatched = yesNoInput("Have you watched" + movie.getName() + "yet? ");
            if(hasWatched){
                int stars = positiveIntInput(" what is your rating for " + movie.getName() + " out of 5? ");
                movie.setWatched(true);
                movie.setStars(stars);
                database.updateMovie(movie);
            }
        }
    }

    public static void deleteWatchedMovie(){

        System.out.println( " Here are all the movies you have seen ");

        List<Movie> watchedMovies = database.getAllMoviesByWatched(true);

        for (Movie movie : watchedMovies) {
            boolean delete = yesNoInput("Delete " + movie.getName() + "?");
            if(delete){
                database.deleteMovie(movie);
            }
        }
    }
}
