import server.GetBookingsBySSNCommand;
import server.GetMoviesCommand;
import server.GetShowsByMovieCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class ShowSelectionPane extends AbstractPane implements IObserver<ClientModel> {
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private ClientModel cm;

    private final JPanel showSelectionPanel = new JPanel();
    private final JPanel seatSelectionPanel = new JPanel();
    private final JPanel customerInformationPanel = new JPanel();

    public ShowSelectionPane(JFrame frame){
        super(frame);
    }

    @Override
    public void init(){
        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new BorderLayout());

        contentPane.add(movieSelection(), BorderLayout.NORTH);
        contentPane.add(showSelection(), BorderLayout.CENTER);
        contentPane.add(userControls(), BorderLayout.SOUTH);
    }

    @Override
    public void start(){
        super.start();

        cm.updateMovies();
    }

    public Container movieSelection() {
        Container movieSelection = new JPanel();

        movieSelection.setLayout(new GridLayout(1, 1));

        JLabel label = new JLabel("Loading ...", JLabel.CENTER);
        movieSelection.add(label);

        return movieSelection;
    }

    public void updateMovieSelection(){
        if(this.movieCollection != null){
            Container con = (Container) contentPane.getComponent(0);
            con.removeAll();

            JComboBox<String> movieList = new JComboBox<>();
            movieList.addItem("--- Pick a movie ---");

            for(Movie movie : this.movieCollection.getAllMovies()){
                movieList.addItem(movie.getTitle());
            }

            movieList.addActionListener(e -> {
                if(movieList.getSelectedIndex() > 0){
                    Container con2 = (Container) contentPane.getComponent(1);
                    con2.removeAll();

                    con2.add(new JLabel("Loading shows..."));

                    Movie movie = movieCollection.getMovieByTitle(String.valueOf(movieList.getSelectedItem()));

                    cm.updateShows(movie);
                }
            });

            con.add(movieList);

            contentPane.validate();
        }
    }

    public void updateShowSelection(){
        Container con = (Container) contentPane.getComponent(1);
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

        datesList.sort((o1, o2) -> {
            String[] o1Parts = o1.split(" ");
            String[] o2Parts = o2.split(" ");

            List<String> months = new ArrayList<>(Arrays.asList(
                    "january",
                    "february",
                    "march",
                    "april",
                    "may",
                    "june",
                    "july",
                    "august",
                    "september",
                    "october",
                    "november",
                    "december"
            ));

            if (!o1Parts[1].equals(o2Parts[1])) {
                return Integer.compare(months.indexOf(o1Parts[1].toLowerCase()), months.indexOf(o2Parts[1].toLowerCase()));
            } else {
                return Integer.compare(Integer.parseInt(o1Parts[0]), Integer.parseInt(o2Parts[0]));
            }
        });
        Collections.sort(timesList);

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

                label = new JLabel(show.toString());
                label.setVisible(false);
                wrapperPanel.add(label);

                JButton button = new JButton("Select");
                button.setActionCommand(show.getUniqueID());
                button.setAlignmentX(JButton.CENTER_ALIGNMENT);

                button.addActionListener((ActionEvent e) -> {
                    System.out.println(e.getActionCommand());
                });

                wrapperPanel.add(button);

                panelArray[i][j].add(wrapperPanel);
            }

        }else{
            JLabel label = new JLabel("No available shows.", JLabel.CENTER);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            showGrid.add(label);
        }


        con.add(gridWrapper);
        con.setLayout(new GridLayout());
        contentPane.validate();
    }

    public Container showSelection(){
        Container showSelection = new JPanel();
        showSelection.setLayout(new GridLayout());

        JLabel label = new JLabel("Please select a movie.", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        showSelection.add(label);

        return showSelection;
    }

    public Container userControls(){
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        JButton continueButton = new JButton("Continue");
        JButton cancelButton = new JButton("Cancel");
        continueButton.setEnabled(false);

        continueButton.addActionListener((ActionEvent e) -> {

        });

        cancelButton.addActionListener((ActionEvent e) -> {
            stop();
        });

        userControls.add(continueButton);
        userControls.add(cancelButton);


        return userControls;
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
