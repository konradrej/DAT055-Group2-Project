import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Pane for displaying the main menu.
 */
public class MainMenuPane extends AbstractPane {
    private final ClientModel cm;

    /**
     * Generates the main menu pane.
     *
     * @return the JPanel with main menu items added
     */
    public JPanel createMainMenuPanel(){
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton bookingButton = new JButton("Book");
        JButton findBookingButton = new JButton("Find your bookings");
        JButton exitButton = new JButton("Exit");

        bookingButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().startNewPane(new ShowSelectionPane(frame)));

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

        contentPane.setLayout(new GridLayout());

        JPanel mainMenuPanel = createMainMenuPanel();
        contentPane.add(mainMenuPanel);
    }
}