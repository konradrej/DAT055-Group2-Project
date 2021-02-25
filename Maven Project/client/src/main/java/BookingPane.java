import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import collections.*;
import cinemaObjects.*;
import ObserverPattern.*;
import server.GetCustomerBySSNCommand;

public class BookingPane extends AbstractPane implements IObserver<ClientModel> {
    private ClientModel cm;
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private Customer customer;

    /**
     * Card panels
     */
    private int currentPage = 0;
    private JPanel cardPanel;
    private JPanel movieShowSelectionPanel;
    private JPanel seatSelectionPanel;
    private JPanel customerInformationPanel;
    private JPanel statusPanel;

    /**
     * User control buttons
     */
    private JButton continueButton;
    private JButton backButton;
    private JButton cancelButton;

    /**
     *  Variables for new booking
     */
    private Show bookShow;
    private List<Row> bookRows;
    private Customer bookCustomer;

    public BookingPane(JFrame frame){
        super(frame);
    }

    private JPanel createCardPanel(){
        CardLayout bookingCardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(bookingCardLayout);

        movieShowSelectionPanel = createMovieShowSelectionPanel();
        seatSelectionPanel = createSeatSelectionPanel();
        customerInformationPanel = createCustomerInformationPanel();
        statusPanel = createStatusPanel();

        cardPanel.add(movieShowSelectionPanel, String.valueOf(0));
        cardPanel.add(seatSelectionPanel, String.valueOf(1));
        cardPanel.add(customerInformationPanel, String.valueOf(2));
        cardPanel.add(statusPanel, String.valueOf(3));

        return cardPanel;
    }

    private JPanel createStatusPanel(){
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout());

        JLabel statusText = new JLabel("");



        // TODO

        return statusPanel;
    }

    private void updateSeatSelectionPanel(){
        Component[] components = cardPanel.getComponents();
        cardPanel.removeAll();

        seatSelectionPanel = createSeatSelectionPanel();
        components[1] = seatSelectionPanel;

        for(Component comp : components){
            cardPanel.add(comp);
        }

        contentPane.validate();
    }

    private JPanel createMovieShowSelectionPanel(){
        JPanel movieShowSelectionPanel = new JPanel();
        JPanel movieSelectionPanel = createMovieSelectionPanel();
        JPanel showSelectionPanel = createShowSelectionPanel();

        movieShowSelectionPanel.setLayout(new BorderLayout());
        movieShowSelectionPanel.add(movieSelectionPanel, BorderLayout.NORTH);
        movieShowSelectionPanel.add(showSelectionPanel, BorderLayout.CENTER);

        return movieShowSelectionPanel;
    }

    private JPanel createSeatSelectionPanel(){
        bookRows = new ArrayList<>();

        JPanel seatSelectionPanel = new JPanel();
        JPanel screenWrapper = new JPanel();
        JPanel screenPanel = new JPanel();
        screenPanel.setBackground(Color.BLACK);
        screenPanel.setPreferredSize(new Dimension(contentPane.getWidth() - 200, 30));

        JLabel label = new JLabel("Screen");
        label.setForeground(Color.WHITE);
        screenPanel.add(label);

        screenWrapper.add(screenPanel);
        screenWrapper.setPreferredSize(new Dimension(contentPane.getWidth(), 110));

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());

        if(bookShow != null){
            Collection<Row> rows = bookShow.getTheater().getCollectionOfRows();
            for(Row row : rows){
                JPanel rowPanel = new JPanel();

                rowPanel.setPreferredSize(new Dimension(contentPane.getWidth(), 35));

                for(Seat seat : row.getAllSeats()){
                    JButton seatButton = new JButton();
                    seatButton.setPreferredSize(new Dimension(25, 25));
                    // Needed? with mouseClick listener
                    seatButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                    if(seat.getAvailable()){
                        seatButton.setBackground(Color.GREEN);

                        seatButton.addActionListener((ActionEvent e) -> {
                            handleSeatClick(row, seat, seatButton);
                        });
                    }else{
                        seatButton.setBackground(Color.RED);
                    }

                    rowPanel.add(seatButton);
                }

                seatSelectionPanel.add(rowPanel);
            }
        }

        wrapperPanel.add(screenWrapper, BorderLayout.NORTH);
        wrapperPanel.add(seatSelectionPanel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private void handleSeatClick(Row row, Seat seat, JButton seatButton){
        int rowIndex = -1;

        for(int i = 0; i < bookRows.size(); i++){
            if(bookRows.get(i).getRowNumber() == row.getRowNumber()){
                rowIndex = i;
                break;
            }
        }

        if(rowIndex == -1){
            bookRows.add(new Row(row.getRowNumber(), new ArrayList<>(Collections.singletonList(seat))));
            seatButton.setBackground(Color.ORANGE);
        }else{
            Row operatingRow = bookRows.get(rowIndex);
            int seatIndex = -1;

            for(int i = 0; i < operatingRow.getAllSeats().size(); i++){
                if(operatingRow.getAllSeats().get(i).getSeatNumber() == seat.getSeatNumber()){
                    seatIndex = i;
                    break;
                }
            }

            if(seatIndex == -1){
                operatingRow.addSeat(seat);
                seatButton.setBackground(Color.ORANGE);
            }else{
                operatingRow.removeSeat(seat);
                seatButton.setBackground(Color.GREEN);
            }
        }
    }




















    private JPanel createCustomerInformationPanel(){
        if(customer == null){
            customer = new Customer();
        }

        JPanel customerInformationPanel = new JPanel();
        customerInformationPanel.setLayout(new BorderLayout());

        JPanel findBySSNPanel = new JPanel();
        JPanel enterInfoPanel = new JPanel();

        JLabel getInfoLabel = new JLabel("Get info by SSN");
        JTextField getInfoTextField = new JTextField();
        JButton searchButton = new JButton("Find");

        searchButton.addActionListener((ActionEvent e) -> {
            SocketClientCommunication.getInstance().sendCommand(new GetCustomerBySSNCommand(getInfoTextField.getText()));
        });

        findBySSNPanel.setLayout(new BoxLayout(findBySSNPanel, BoxLayout.Y_AXIS));
        findBySSNPanel.add(getInfoLabel);
        findBySSNPanel.add(getInfoTextField);
        findBySSNPanel.add(searchButton);

        JLabel enterNameLabel = new JLabel("Name");
        JTextField enterNameTextField = new JTextField();
        enterNameTextField.addActionListener((ActionEvent e) -> {
            customer.setName(enterNameTextField.getText());
        });

        JLabel enterSSNLabel = new JLabel("SSN");
        JTextField enterSSNTextField = new JTextField();
        enterSSNTextField.addActionListener((ActionEvent e) -> {
            customer.setSSN(enterSSNTextField.getText());
        });

        JLabel enterPhoneNumberLabel = new JLabel("Phone number");
        JTextField enterTelephoneTextField = new JTextField();
        enterTelephoneTextField.addActionListener((ActionEvent e) -> {
            customer.setPhoneNumber(enterTelephoneTextField.getText());
        });

        enterInfoPanel.setLayout(new BoxLayout(enterInfoPanel, BoxLayout.Y_AXIS));
        enterInfoPanel.add(enterNameLabel);
        enterInfoPanel.add(enterNameTextField);
        enterInfoPanel.add(enterSSNLabel);
        enterInfoPanel.add(enterSSNTextField);
        enterInfoPanel.add(enterPhoneNumberLabel);
        enterInfoPanel.add(enterTelephoneTextField);

        JPanel wrapperEnterInfoPanel = new JPanel();
        wrapperEnterInfoPanel.setLayout(new BorderLayout());
        wrapperEnterInfoPanel.add(enterInfoPanel, BorderLayout.NORTH);

        customerInformationPanel.add(findBySSNPanel, BorderLayout.NORTH);
        customerInformationPanel.add(wrapperEnterInfoPanel, BorderLayout.CENTER);

        return customerInformationPanel;
    }

    private void updateCustomerInformationPanel(){
        JPanel con = (JPanel) customerInformationPanel.getComponent(1);
        JPanel con2 = (JPanel) con.getComponent(0);
        JTextField[] textFields = new JTextField[3];

        textFields[0] = (JTextField) con2.getComponent(1);
        textFields[1] = (JTextField) con2.getComponent(3);
        textFields[2] = (JTextField) con2.getComponent(5);

        textFields[0].setText(customer.getName());
        textFields[1].setText(customer.getSSN());
        textFields[2].setText(customer.getPhoneNumber());
    }












    @Override
    public void init(){
        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new BorderLayout());

        JPanel cardPanel = createCardPanel();
        JPanel userControlsPanel = createUserControlsPanel();

        contentPane.add(cardPanel, BorderLayout.CENTER);
        contentPane.add(userControlsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void start(){
        super.start();

        cm.updateMovies();
    }

    private void nextPage(){
        if((currentPage + 1) < cardPanel.getComponentCount()){
            currentPage++;

            ((CardLayout) cardPanel.getLayout()).next(cardPanel);
            backButton.setEnabled(true);
        }

        if(currentPage == cardPanel.getComponentCount()){
            continueButton.setEnabled(false);
        }
    }

    private void previousPage(){
        if((currentPage - 1) >= 0){
            currentPage--;

            ((CardLayout) cardPanel.getLayout()).previous(cardPanel);
            continueButton.setEnabled(true);
        }

        if(currentPage == 0){
            backButton.setEnabled(false);
        }
    }

    private JPanel createMovieSelectionPanel() {
        JPanel movieSelection = new JPanel();

        movieSelection.setLayout(new GridLayout(1, 1));

        JLabel label = new JLabel("Loading ...", JLabel.CENTER);
        movieSelection.add(label);

        return movieSelection;
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

                        updateSeatSelectionPanel();

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

        continueButton.addActionListener((ActionEvent e) ->
                nextPage());

        backButton.addActionListener((ActionEvent e) ->
                previousPage());

        cancelButton.addActionListener((ActionEvent e) ->
                stop());

        userControls.add(continueButton);
        userControls.add(backButton);
        userControls.add(cancelButton);

        return userControls;
    }

    @Override
    public void notify(ClientModel observable) {
        MovieCollection movieCollection = observable.getMovieCollection();
        ShowCollection showCollection = observable.getShowCollection();
        Customer customer = observable.getCustomer();

        if(movieCollection != null && movieCollection != this.movieCollection){
            this.movieCollection = movieCollection;

            updateMovieSelection();
        }
        if(showCollection != null && showCollection != this.showCollection){
            this.showCollection = showCollection;

            updateShowSelection();
        }
        if(customer != null && customer != this.customer){
            this.customer = customer;

            updateCustomerInformationPanel();
        }
    }
}