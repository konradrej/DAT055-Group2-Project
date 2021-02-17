public class ResponseGetMoviesCommand implements ClientCommand {
    private MovieCollection movieCollection;

    public ResponseGetMoviesCommand(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
    }

    @Override
    public void execute(ClientModel c) {
        c.setMovieCollection(movieCollection);
    }
}