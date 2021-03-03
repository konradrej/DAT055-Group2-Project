import utils.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Pane for displaying the main menu.
 *
 * @author Konrad Rej
 * @version 2021-03-03
 */
public class MainMenuPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private JButton bookingButton;
    private JButton findBookingButton;

    /**
     * Generates the main menu panel.
     *
     * @return the JPanel with main menu items added
     */
    public JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(3, 1, 10, 10));

        bookingButton = new JButton("Book");
        findBookingButton = new JButton("Find your bookings");
        JButton exitButton = new JButton("Exit");

        bookingButton.setEnabled(false);
        bookingButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().startNewPane(new ShowSelectionPane(frame)));

        findBookingButton.setEnabled(false);
        findBookingButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().startNewPane(new FindBookingPane(frame)));

        exitButton.addActionListener((ActionEvent e) -> System.exit(0));

        mainMenuPanel.add(bookingButton);
        mainMenuPanel.add(findBookingButton);
        mainMenuPanel.add(exitButton);

        return mainMenuPanel;
    }

    /**
     * Instantiates a new Main menu pane.
     *
     * @param frame the window frame
     */
    public MainMenuPane(JFrame frame) {
        super(frame);

        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new GridLayout());

        JPanel mainMenuPanel = createMainMenuPanel();
        contentPane.add(mainMenuPanel);
    }

    /**
     * Method to be called my observed object to notify about changes.
     * Retrieves new relevant values and updates the GUI accordingly.
     *
     * @param observable the observed object
     */
    @Override
    public void notify(ClientModel observable) {
        boolean connectionAlive = observable.getConnectionAlive();

        if (connectionAlive) {
            bookingButton.setEnabled(true);
            findBookingButton.setEnabled(true);
        } else {
            bookingButton.setEnabled(false);
            findBookingButton.setEnabled(false);
        }
    }
}