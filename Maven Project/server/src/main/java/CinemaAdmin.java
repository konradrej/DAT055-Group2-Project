import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

import cinemaObjects.*;

/**
 * This class handles GUI for the admin and
 * execute operations such as adding theaters and shows.
 *
 * @author Phong Nguyen
 * @version 2021-03-02
 */

public class CinemaAdmin {
    private int page = 1;
    private final Container pane;
    private Movie selectedMovie;
    private Theater selectedTheater;
    private CinemaDate date;
    private DefaultListModel<String> dayList;

    /**
     * Constructor for initializing the CinemaAdmin with GUI
     */

    public CinemaAdmin() {
        JFrame frame = new JFrame();
        frame.setTitle("Admin");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        pane = frame.getContentPane();
        date = new CinemaDate();

        makeMenu(frame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Constructs the menu bar and inserts sections
     *
     * @param frame of the interface window
     */

    private void makeMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Menu");
        menuBar.add(fileMenu);

        JMenuItem addShow = new JMenuItem("Add Show");
        addShow.addActionListener((ActionEvent e) -> displayAddShowMenu(frame));

        JMenuItem addTheater = new JMenuItem("Add Theater");
        addTheater.addActionListener((ActionEvent e) -> displayAddTheaterMenu(frame));

        fileMenu.add(addShow);
        fileMenu.add(addTheater);
    }

    /**
     * Display section where you can add shows
     *
     * @param frame of the interface window
     */

    private void displayAddShowMenu(JFrame frame) {
        frame.setVisible(false);
        pane.removeAll();
        createListsWithListeners();
        frame.validate();
        frame.setVisible(true);
    }

    /**
     * Display section where you can add theaters
     *
     * @param frame of the interface window
     */

    private void displayAddTheaterMenu(JFrame frame) {
        frame.setVisible(false);
        pane.removeAll();
        frame.setLayout(new GridLayout(2, 1));
        createTheaterOption();
        frame.validate();
        frame.setVisible(true);
    }

    /**
     * This method handles the graphics and controls for creating a theater,
     * with Theater, Row and Seat.
     */

    private void createTheaterOption() {
        DefaultListModel<Object> listModel = new DefaultListModel<>();
        ArrayList<Row> rows = new ArrayList<>();
        JPanel insertPanel = new JPanel();
        JPanel listPanel = new JPanel();

        JButton addTheaterButton = new JButton("Add Theater");
        addTheaterButton.addActionListener((ActionEvent e) -> {
            int theaterNr = CinemaBookingSystem.getInstance().getCinema().getTheaterCollection().size() + 1;
            if (!(rows.isEmpty())) {
                Theater t = new Theater(theaterNr, rows);
                CinemaBookingSystem.getInstance().getCinema().addTheater(t);
                int sum = 0;
                for (Row r : rows) {
                    sum += r.getAllSeats().size();
                }
                JOptionPane.showMessageDialog(null, "Theater " + theaterNr + " has been added with " + sum +
                        " seats!", "Theater added to " +
                        CinemaBookingSystem.getInstance().getCinema().getCinemaName(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Insert rows!", "Empty row list", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton removeRowButton = new JButton("Remove row");
        removeRowButton.addActionListener((ActionEvent e) -> {
            if (!(rows.isEmpty())) {
                listModel.removeElement(listModel.lastElement());
                rows.remove(rows.size() - 1);
            } else {
                JOptionPane.showMessageDialog(null, "Insert rows!", "Invalid list", JOptionPane.ERROR_MESSAGE);
            }
        });

        JList<Object> jlRows = new JList<>(listModel);
        JScrollPane listOfRows = new JScrollPane(jlRows);
        listOfRows.setColumnHeaderView(new JLabel(" Rows:"));

        JPanel test = new JPanel();
        test.setLayout(new GridLayout(2, 1, 5, 5));
        listPanel.add(listOfRows, BorderLayout.WEST);
        test.add(addTheaterButton);
        test.add(removeRowButton);
        listPanel.add(test, BorderLayout.EAST);

        JLabel insertLabel = new JLabel("Insert amount of seat (1-15)");
        JTextField insertText = new JTextField(5);
        JButton insertSeatsButton = new JButton("Add row");
        insertSeatsButton.addActionListener((ActionEvent e) -> {
            int seats = Integer.parseInt(insertText.getText());
            if (seats > 0 && seats <= 15) {
                int rowNr = listModel.size() + 1;
                listModel.addElement("Row " + rowNr + " " + "Seats: " + seats);
                ArrayList<Seat> seatList = new ArrayList<>();
                for (int i = 0; i < seats; i++) {
                    seatList.add(new Seat(i + 1));
                }
                rows.add(new Row(rowNr, seatList));
            } else {
                JOptionPane.showMessageDialog(null, "Insert a seat amount between 1-15", "Invalid amount",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        insertPanel.add(insertLabel);
        insertPanel.add(insertText);
        insertPanel.add(insertSeatsButton);
        pane.add(insertPanel);
        pane.add(listPanel);
    }


    /**
     * This method handles the graphics and controls for creating a show,
     * with Theater, Movie , Day and Time.
     */

    private void createListsWithListeners() {
        dayList = new DefaultListModel<>();
        CardLayout cl = new CardLayout();
        JPanel cardPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        cardPanel.setLayout(cl);

        ArrayList<String> movieArr = new ArrayList<>();
        ArrayList<String> theaterArr = new ArrayList<>();

        for (Movie m : CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()) {
            movieArr.add(m.getTitle());
        }

        for (Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()) {
            theaterArr.add(Integer.toString(t.getTheaterNumber()));
        }

        JList<Object> jlMovie = new JList<>(movieArr.toArray());
        JScrollPane panelMovies = new JScrollPane(jlMovie);
        panelMovies.setColumnHeaderView(new JLabel("Movie:"));

        JList<Object> jlTheater = new JList<>((theaterArr.toArray()));
        JScrollPane panelTheaters = new JScrollPane(jlTheater);
        panelTheaters.setColumnHeaderView(new JLabel("Theater:"));

        JList<String> jlMonth = new JList<>(new DateFormatSymbols().getMonths());
        JScrollPane panelMonth = new JScrollPane(jlMonth);
        panelMonth.setColumnHeaderView(new JLabel("Month:"));

        JList<String> jlDay = new JList<>(dayList);
        JScrollPane panelDay = new JScrollPane(jlDay);
        panelDay.setColumnHeaderView(new JLabel("Day:"));

        JList<String> jlTime = new JList<>(new String[]{"17:00", "19:00", "21:00"});
        JScrollPane panelTime = new JScrollPane(jlTime);
        panelTime.setColumnHeaderView(new JLabel("Time:"));

        MouseListener movieListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    for (Movie m : CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()) {
                        if (m.getTitle().equals(jlMovie.getSelectedValue())) {
                            selectedMovie = m;
                            break;
                        }
                    }
                }
            }
        };

        MouseListener theaterListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    for (Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()) {
                        if (jlTheater.getSelectedValue().equals(Integer.toString(t.getTheaterNumber()))) {
                            selectedTheater = t;
                            break;
                        }
                    }
                }
            }
        };


        MouseListener dayListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String s = jlDay.getSelectedValue();
                    date = date.setDay(s);
                }
            }
        };

        MouseListener monthListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    dayList.removeAllElements();
                    String s = jlMonth.getSelectedValue();
                    date = date.setMonth(s);
                    if (date.thirtyoneDays()) {
                        for (int i = 1; i <= 31; i++) {
                            dayList.addElement(Integer.toString(i));
                        }
                    } else if (date.twentyeightDays()) {
                        for (int i = 1; i <= 28; i++) {
                            dayList.addElement(Integer.toString(i));
                        }
                    } else {
                        for (int i = 1; i <= 30; i++) {
                            dayList.addElement(Integer.toString(i));
                        }
                    }
                }
            }
        };

        MouseListener timeListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String s = jlTime.getSelectedValue();
                    date = date.setTime(s);
                }
            }
        };
        jlMovie.addMouseListener(movieListener);
        jlTheater.addMouseListener(theaterListener);
        jlMonth.addMouseListener(monthListener);
        jlDay.addMouseListener(dayListener);
        jlTime.addMouseListener(timeListener);

        cardPanel.add(panelMovies, "1");
        cardPanel.add(panelTheaters, "2");
        cardPanel.add(panelMonth, "3");
        cardPanel.add(panelDay, "4");
        cardPanel.add(panelTime, "5");
        pane.add(cardPanel, BorderLayout.NORTH);

        JButton prev = new JButton("Previous");
        JButton next = new JButton("Next");
        JButton addShow = new JButton("Add Show");
        prev.setEnabled(false);

        next.addActionListener((ActionEvent e) -> {
            if (page < 5) {
                cl.show(cardPanel, "" + (++page));
                prev.setEnabled(true);
            } else {
                next.setEnabled(false);
            }

        });

        prev.addActionListener((ActionEvent e2) -> {
            if (page > 1) {
                cl.show(cardPanel, "" + (--page));
                next.setEnabled(true);
            } else {
                prev.setEnabled(false);
            }
        });
        addShow.addActionListener((ActionEvent e) -> addShowToCollection());

        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(addShow);
        pane.add(buttonPanel, BorderLayout.SOUTH);
    }


    /**
     * Method that adds a show if Movie, Theater and date are valid
     */

    private void addShowToCollection() {

        if (selectedMovie != null && selectedTheater != null &&
                date.getMonth() != null && date.getTime() != null && date.getDay() != null) {

            boolean addShow = true;
            for (Show s2 : CinemaBookingSystem.getInstance().getShowCollection().getAllShows()) {
                if (s2.getTheater().getTheaterNumber() == selectedTheater.getTheaterNumber() &&
                        s2.getShowDateAndTime().equals(date)) {
                    addShow = false;
                    JOptionPane.showMessageDialog(null, "Theater " + selectedTheater.getTheaterNumber() +
                                    " is occupied at " + date.toString(),
                            "Not added", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
            if (addShow) {
                Theater t = selectedTheater.cloneTheater();
                Show s = new Show(selectedMovie, date, CinemaBookingSystem.getInstance().getCinema(), t);
                JOptionPane.showMessageDialog(null, s.toString(), "Show added", JOptionPane.INFORMATION_MESSAGE);
                CinemaBookingSystem.getInstance().getShowCollection().addShow(s);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Choose movie, theater, date", "Not added", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Thread(SocketServerCommunication.getInstance()).start();
        CinemaBookingSystem.getInstance().readAllCollections();
        CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();
        new CinemaAdmin();

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                CinemaBookingSystem.getInstance().serializeAllCollection()));

    }
}