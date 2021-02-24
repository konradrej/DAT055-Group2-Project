import server.GetMoviesCommand;
import server.GetShowsByMovieCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

public class ShowSelectionPane extends AbstractPane implements IObserver<ClientModel> {
    private MovieCollection movieCollection;
    private ShowCollection showCollection;

    public ShowSelectionPane(JFrame frame){
        super(frame);
    }

    @Override
    public void init(){
        ClientModel.getInstance().addObserver(this);

        contentPane.setLayout(new BorderLayout());

        contentPane.add(movieSelection(), BorderLayout.NORTH);
        contentPane.add(showSelection(), BorderLayout.CENTER);
        contentPane.add(userControls(), BorderLayout.SOUTH);
    }

    @Override
    public void start(){
        super.start();

        SocketClientCommunication.getInstance().sendCommand(new GetMoviesCommand());
    }

    public Container movieSelection() {
        Container movieSelection = new JPanel();

        movieSelection.setLayout(new GridLayout(1, 1));

        JLabel label = new JLabel("Loading ...", SwingConstants.CENTER);
        movieSelection.add(label);

        movieSelection.setPreferredSize(new Dimension(frame.getWidth(), 50));

        return movieSelection;
    }

    public void updateMovieSelection(){
        if(this.movieCollection != null){
            Container con = (Container) contentPane.getComponent(0);
            con.removeAll();

            JComboBox<String> movieList = new JComboBox<>();

            for(Movie movie : this.movieCollection.getAllMovies()){
                movieList.addItem(movie.getTitle());
            }

            movieList.addActionListener(e -> {
                Movie movie = movieCollection.getMovieByTitle(String.valueOf(movieList.getSelectedItem()));
                SocketClientCommunication.getInstance().sendCommand(new GetShowsByMovieCommand(movie));
            });

            con.add(movieList);

            contentPane.validate();
        }
    }

    public void updateShowSelection(){
        Container con = (Container) contentPane.getComponent(1);
        con.removeAll();

        JComboBox<String> showList = new JComboBox<>();

        for(Show show : showCollection.getAllShows()){
            showList.addItem(show.getShowDateAndTime().toString());
            System.out.println(show.getShowDateAndTime().toString());
        }

        con.add(showList);

        contentPane.validate();
    }

    public Container showSelection(){
        Container showSelection = new JPanel();

        showSelection.setLayout(new GridLayout());

        JButton continueButton2 = new JButton("Continue");
        continueButton2.setEnabled(false);

        continueButton2.addActionListener((ActionEvent e) -> {

        });
        showSelection.add(continueButton2);

        return showSelection;
    }

    public Container userControls(){
        Container userControls = new JPanel();
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

        userControls.setPreferredSize(new Dimension(frame.getWidth(), 50));

        return userControls;
    }

    /*
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource() instanceof ClientModel){
            if(evt.getPropertyName().equals("movieCollection")){
                this.movieCollection = (MovieCollection) evt.getNewValue();

                updateMovieSelection();
            }else if(evt.getPropertyName().equals("showCollection")){
                this.showCollection = (ShowCollection) evt.getNewValue();

                updateShowSelection();
            }
        }
    }
    */

    @Override
    public void notify(ClientModel observable) {

    }
}
