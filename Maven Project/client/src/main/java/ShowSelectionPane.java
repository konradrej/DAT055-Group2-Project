import ObserverPattern.IObserver;
import cinemaObjects.CinemaDate;
import cinemaObjects.Movie;
import cinemaObjects.Show;
import collections.MovieCollection;
import collections.ShowCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.*;

/**
 * Pane for selecting a show
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public class ShowSelectionPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private final JPanel movieShowSelectionPanel;
    private JButton continueButton;

    /**
     * Variable for new booking
     */
    private Show bookShow;

    /**
     * Generates the movie and show selection panel.
     *
     * @return the JPanel with movie and show picking items added
     */
    public JPanel createMovieShowSelectionPanel() {
        JPanel movieShowSelectionPanel = new JPanel();
        JPanel movieSelectionPanel = createMovieSelectionPanel();
        JPanel showSelectionPanel = createShowSelectionPanel();

        movieShowSelectionPanel.setLayout(new BorderLayout());
        movieShowSelectionPanel.add(movieSelectionPanel, BorderLayout.NORTH);
        movieShowSelectionPanel.add(showSelectionPanel, BorderLayout.CENTER);

        return movieShowSelectionPanel;
    }

    /**
     * Generates the movie selection panel.
     *
     * @return the JPanel with movie picking items added
     */
    public JPanel createMovieSelectionPanel() {
        JPanel movieSelection = new JPanel();

        movieSelection.setLayout(new BorderLayout());

        JLabel label = new JLabel("Loading ...", JLabel.CENTER);
        movieSelection.add(label, BorderLayout.CENTER);

        return movieSelection;
    }

    /**
     * Generates the show selection panel.
     *
     * @return the JPanel with show picking items added
     */
    public JPanel createShowSelectionPanel() {
        JPanel showSelection = new JPanel();
        showSelection.setLayout(new GridLayout());

        JLabel label = new JLabel("Please select a movie.", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        showSelection.add(label);

        return showSelection;
    }

    /**
     * Generates the user controls panel.
     *
     * @return the JPanel with buttons added
     */
    public JPanel createUserControlsPanel() {
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        continueButton = new JButton("Continue");
        JButton cancelButton = new JButton("Cancel");

        continueButton.setEnabled(false);

        continueButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().startNewPane(new SeatSelectionPane(frame, bookShow)));

        cancelButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());

        userControls.add(continueButton);
        userControls.add(cancelButton);

        return userControls;
    }

    /**
     * Updates movieSelection panel with new information from movieCollection
     */
    public void updateMovieSelection() {
        if (this.movieCollection != null) {
            Container con = (Container) movieShowSelectionPanel.getComponent(0);
            con.removeAll();

            JComboBox<String> movieList = new JComboBox<>();
            movieList.addItem("--- Pick a movie ---");

            for (Movie movie : this.movieCollection.getAllMovies()) {
                movieList.addItem(movie.getTitle());
            }

            JPanel dateSelector = new JPanel();
            JTextField startDate = new JTextField("Start date (MM-DD)", 10);
            startDate.setForeground(Color.GRAY);
            JTextField endDate = new JTextField("End date (MM-DD)", 10);
            endDate.setForeground(Color.GRAY);

            startDate.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (startDate.getText().equals("Start date (MM-DD)")) {
                        startDate.setText("");
                        startDate.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (startDate.getText().isEmpty()) {
                        startDate.setForeground(Color.GRAY);
                        startDate.setText("Start  (MM-DD)");
                    }
                }
            });

            endDate.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (endDate.getText().equals("End date (MM-DD)")) {
                        endDate.setText("");
                        endDate.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (endDate.getText().isEmpty()) {
                        endDate.setForeground(Color.GRAY);
                        endDate.setText("End date (MM-DD)");
                    }
                }
            });

            movieList.addActionListener(e -> {
                if (movieList.getSelectedIndex() > 0) {
                    Container con2 = (Container) movieShowSelectionPanel.getComponent(1);
                    con2.removeAll();

                    con2.add(new JLabel("Loading shows..."));

                    Movie movie = movieCollection.getMovieByTitle(String.valueOf(movieList.getSelectedItem()));
                    continueButton.setEnabled(false);

                    String startDateText = startDate.getText();
                    String endDateText = endDate.getText();
                    CinemaDate startDateObj = null;
                    CinemaDate endDateObj = null;

                    if (startDateText.matches("\\d{2}-\\d{2}")) {
                        String[] parts = startDateText.split("-");
                        startDateObj = new CinemaDate(parts[0], parts[1], null);
                    }

                    if (endDateText.matches("\\d{2}-\\d{2}")) {
                        String[] parts = endDateText.split("-");
                        endDateObj = new CinemaDate(parts[0], parts[1], null);
                    }

                    cm.updateShows(movie, startDateObj, endDateObj);
                }
            });


            dateSelector.add(startDate);
            dateSelector.add(endDate);

            con.add(movieList, BorderLayout.CENTER);
            con.add(dateSelector, BorderLayout.EAST);

            contentPane.validate();
        }
    }

    /**
     * Updates showSelection panel with new information from showCollection
     */
    public void updateShowSelection() {
        Container con = (Container) movieShowSelectionPanel.getComponent(1);
        con.removeAll();

        Set<String> dates = new HashSet<>();
        Set<String> times = new HashSet<>();

        for (Show show : showCollection.getAllShows()) {
            CinemaDate datetime = show.getShowDateAndTime();

            dates.add(datetime.getDay() + " " + datetime.getMonth());
            times.add(datetime.getTime());
        }

        List<String> datesList = new ArrayList<>(dates);
        List<String> timesList = new ArrayList<>(times);

        datesList.sort((item1, item2) -> {
            List<String> months = new ArrayList<>();

            for (String month : new DateFormatSymbols().getMonths()) {
                months.add(month.toLowerCase());
            }

            String[] items1 = item1.split(" ");
            String[] items2 = item2.split(" ");

            if (!items1[1].equals(items2[1])) {
                return Integer.compare(
                        months.indexOf(items1[1].toLowerCase()),
                        months.indexOf(items2[1].toLowerCase())
                );
            } else {
                return Integer.compare(
                        Integer.parseInt(items1[0]),
                        Integer.parseInt(items2[0])
                );
            }
        });
        timesList.sort(Comparator.naturalOrder());

        JScrollPane showGrid = createShowGrid(datesList, timesList);

        con.add(showGrid);
        con.setLayout(new GridLayout());
        contentPane.validate();
    }

    /**
     * Generates a panel containing a grid based on provided date and time lists
     * as well as adds shows to the matching grid spot.
     *
     * @param datesList list of dates to be added as columns
     * @param timesList list of times to be added as rows
     * @return the panel containing a grid of shows, show dates and show times
     */
    private JScrollPane createShowGrid(List<String> datesList, List<String> timesList) {
        JPanel showGrid = new JPanel();
        showGrid.setLayout(new GridLayout((timesList.size() + 1), (datesList.size() + 1)));

        JScrollPane gridWrapper = new JScrollPane(showGrid);
        gridWrapper.setLayout(new ScrollPaneLayout());

        if (timesList.size() != 0 && datesList.size() != 0) {
            JPanel[][] panelArray = new JPanel[(timesList.size() + 1)][(datesList.size() + 1)];
            for (int i = 0; i < panelArray.length; i++) {
                for (int j = 0; j < panelArray[i].length; j++) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    panel.setBorder(BorderFactory.createEtchedBorder());
                    panel.setPreferredSize(new Dimension(170, 50));

                    panelArray[i][j] = panel;
                    showGrid.add(panel);
                }
            }

            for (int i = 1; i < (datesList.size() + 1); i++) {
                panelArray[0][i].add(new JLabel(datesList.get(i - 1)));
            }

            for (int i = 1; i < (timesList.size() + 1); i++) {
                panelArray[i][0].add(new JLabel(timesList.get(i - 1)));
            }

            for (Show show : showCollection.getAllShows()) {
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

                if (show.getShow().getAllAvailableSeats().size() > 0) {
                    JButton button = new JButton("Select");
                    button.setActionCommand(show.getUniqueID());
                    button.setAlignmentX(JButton.CENTER_ALIGNMENT);

                    button.addActionListener((ActionEvent e) -> {
                        bookShow = cm.getShowCollection().getShowByUID(e.getActionCommand());

                        if (bookShow == null) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Could not retrieve seats.",
                                    "Retrieve seat error",
                                    JOptionPane.ERROR_MESSAGE
                            );

                            cm.getNavigator().back();
                        } else {
                            continueButton.setEnabled(true);
                        }
                    });

                    wrapperPanel.add(button);
                }

                panelArray[i][j].add(wrapperPanel);
            }
        } else {
            JLabel label = new JLabel("No available shows.", JLabel.CENTER);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            showGrid.add(label);
        }

        return gridWrapper;
    }

    /**
     * Instantiates a new Show selection pane.
     *
     * @param frame the window frame
     */
    public ShowSelectionPane(JFrame frame) {
        super(frame);

        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new BorderLayout());

        this.movieShowSelectionPanel = createMovieShowSelectionPanel();
        JPanel userControlsPanel = createUserControlsPanel();

        contentPane.add(movieShowSelectionPanel, BorderLayout.CENTER);
        contentPane.add(userControlsPanel, BorderLayout.SOUTH);
    }

    /**
     * Runs parents start function and also asks CinemaModel for movieCollection update.
     */
    @Override
    public void start() {
        super.start();

        cm.updateMovies();
    }

    /**
     * Method to be called my observed object to notify about changes.
     * Retrieves new relevant values and updates the GUI accordingly.
     *
     * @param observable the observed object
     */
    @Override
    public void notify(ClientModel observable) {
        MovieCollection movieCollection = observable.getMovieCollection();
        ShowCollection showCollection = observable.getShowCollection();

        if (movieCollection != null && movieCollection != this.movieCollection) {
            this.movieCollection = movieCollection;

            updateMovieSelection();
        }
        if (showCollection != null && showCollection != this.showCollection) {
            this.showCollection = showCollection;

            updateShowSelection();
        }
    }
}
