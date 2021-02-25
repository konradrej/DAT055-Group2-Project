import ObserverPattern.IObserver;
import cinemaObjects.CinemaDate;
import cinemaObjects.Movie;
import cinemaObjects.Show;
import collections.MovieCollection;
import collections.ShowCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class ShowSelectionPane extends AbstractPaneV2 implements IObserver<ClientModel> {
    private ClientModel cm;
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private JPanel movieShowSelectionPanel;
    private JPanel userControlsPanel;

    /**
     * User control buttons
     */
    private JButton continueButton;
    private JButton backButton;
    private JButton cancelButton;

    /**
     *  Variable for new booking
     */
    private Show bookShow;

    private JPanel createMovieShowSelectionPanel(){
        JPanel movieShowSelectionPanel = new JPanel();
        JPanel movieSelectionPanel = createMovieSelectionPanel();
        JPanel showSelectionPanel = createShowSelectionPanel();

        movieShowSelectionPanel.setLayout(new BorderLayout());
        movieShowSelectionPanel.add(movieSelectionPanel, BorderLayout.NORTH);
        movieShowSelectionPanel.add(showSelectionPanel, BorderLayout.CENTER);

        return movieShowSelectionPanel;
    }

    private JPanel createMovieSelectionPanel() {
        JPanel movieSelection = new JPanel();

        movieSelection.setLayout(new GridLayout(1, 1));

        JLabel label = new JLabel("Loading ...", JLabel.CENTER);
        movieSelection.add(label);

        return movieSelection;
    }

    private JPanel createShowSelectionPanel(){
        JPanel showSelection = new JPanel();
        showSelection.setLayout(new GridLayout());

        JLabel label = new JLabel("Please select a movie.", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        showSelection.add(label);

        return showSelection;
    }

    private JPanel createUserControlsPanel(){
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        continueButton = new JButton("Continue");
        backButton = new JButton("Back");
        cancelButton = new JButton("Cancel");

        continueButton.setEnabled(false);
        backButton.setEnabled(false);

        continueButton.addActionListener((ActionEvent e) -> {
            SeatSelectionPane seatPane = new SeatSelectionPane(frame, bookShow);
            seatPane.start();
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            stop();
        });

        userControls.add(continueButton);
        userControls.add(backButton);
        userControls.add(cancelButton);

        return userControls;
    }

    public void updateMovieSelection(){
        if(this.movieCollection != null){
            Container con = (Container) movieShowSelectionPanel.getComponent(0);
            con.removeAll();

            JComboBox<String> movieList = new JComboBox<>();
            movieList.addItem("--- Pick a movie ---");

            for(Movie movie : this.movieCollection.getAllMovies()){
                movieList.addItem(movie.getTitle());
            }

            movieList.addActionListener(e -> {
                if(movieList.getSelectedIndex() > 0){
                    Container con2 = (Container) movieShowSelectionPanel.getComponent(1);
                    con2.removeAll();

                    con2.add(new JLabel("Loading shows..."));

                    Movie movie = movieCollection.getMovieByTitle(String.valueOf(movieList.getSelectedItem()));
                    continueButton.setEnabled(false);

                    cm.updateShows(movie);
                }
            });

            con.add(movieList);

            contentPane.validate();
        }
    }

    public void updateShowSelection(){
        Container con = (Container) movieShowSelectionPanel.getComponent(1);
        con.removeAll();

        Set<String> dates = new HashSet<>();
        Set<String> times = new HashSet<>();

        for(Show show : showCollection.getAllShows()){
            CinemaDate datetime = show.getShowDateAndTime();

            dates.add(datetime.getDay() + " " + datetime.getMonth());
            times.add(datetime.getTime());
        }

        List<String> datesList = new ArrayList<>(dates);
        List<String> timesList = new ArrayList<>(times);

        datesList.sort(new DateComparator());
        timesList.sort(Comparator.naturalOrder());

        JScrollPane showGrid = createShowGrid(datesList, timesList);

        con.add(showGrid);
        con.setLayout(new GridLayout());
        contentPane.validate();
    }

    private JScrollPane createShowGrid(List<String> datesList, List<String> timesList){
        JPanel showGrid = new JPanel();
        showGrid.setLayout(new GridLayout((timesList.size() + 1), (datesList.size() + 1)));

        JScrollPane gridWrapper = new JScrollPane(showGrid);
        gridWrapper.setLayout(new ScrollPaneLayout());

        if(timesList.size() != 0 && datesList.size() != 0){
            JPanel[][] panelArray = new JPanel[(timesList.size() + 1)][(datesList.size() + 1)];
            for(int i = 0; i < panelArray.length; i++){
                for(int j = 0; j < panelArray[i].length; j++){
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    panel.setBorder(BorderFactory.createEtchedBorder());
                    panel.setPreferredSize(new Dimension(170, 50));

                    panelArray[i][j] = panel;
                    showGrid.add(panel);
                }
            }

            for(int i = 1; i < (datesList.size() + 1); i++){
                panelArray[0][i].add(new JLabel(datesList.get(i - 1)));
            }

            for(int i = 1; i < (timesList.size() + 1); i++){
                panelArray[i][0].add(new JLabel(timesList.get(i-1)));
            }

            for(Show show : showCollection.getAllShows()){
                int i, j;

                i = timesList.indexOf(show.getShowDateAndTime().getTime()) + 1;
                j = datesList.indexOf(show.getShowDateAndTime().getDay() + " " + show.getShowDateAndTime().getMonth()) + 1;

                JPanel wrapperPanel = new JPanel();
                wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Cinema: " + show.getCinema().getCinemaName(), JLabel.CENTER);
                label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                wrapperPanel.add(label);

                label = new JLabel("Theater: " + show.getTheater().getTheaterNumber(), JLabel.CENTER);
                label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                wrapperPanel.add(label);

                label = new JLabel("Free seats: " + show.getAllAvailableSeats().size(), JLabel.CENTER);
                label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                wrapperPanel.add(label);

                if(show.getShow().getAllAvailableSeats().size() > 0){
                    JButton button = new JButton("Select");
                    button.setActionCommand(show.getUniqueID());
                    button.setAlignmentX(JButton.CENTER_ALIGNMENT);

                    button.addActionListener((ActionEvent e) -> {
                        bookShow = cm.getShowCollection().getShowByUID(e.getActionCommand());

                        // TODO
                        //updateSeatSelectionPanel();

                        continueButton.setEnabled(true);
                        backButton.setEnabled(true);
                    });

                    wrapperPanel.add(button);
                }

                panelArray[i][j].add(wrapperPanel);
            }
        }else{
            JLabel label = new JLabel("No available shows.", JLabel.CENTER);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            showGrid.add(label);
        }

        return gridWrapper;
    }















    public ShowSelectionPane(JFrame frame) {
        super(frame);

        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new BorderLayout());

        this.movieShowSelectionPanel = createMovieShowSelectionPanel();
        this.userControlsPanel = createUserControlsPanel();

        contentPane.add(movieShowSelectionPanel, BorderLayout.CENTER);
        contentPane.add(userControlsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void start(){
        super.start();

        cm.updateMovies();
    }

    @Override
    public void notify(ClientModel observable) {
        MovieCollection movieCollection = observable.getMovieCollection();
        ShowCollection showCollection = observable.getShowCollection();

        if(movieCollection != null && movieCollection != this.movieCollection){
            this.movieCollection = movieCollection;

            updateMovieSelection();
        }
        if(showCollection != null && showCollection != this.showCollection){
            this.showCollection = showCollection;

            updateShowSelection();
        }
    }
}
