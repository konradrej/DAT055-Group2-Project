public class GetMoviesCommand implements ServerCommand {
    @Override
    public ClientCommand execute(CinemaBookingSystem cbs) {
        return new ResponseGetMoviesCommand(cbs.getMovieCollection());
    }
}
