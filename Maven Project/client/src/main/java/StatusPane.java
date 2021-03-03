import utils.IObserver;
import misc.ResponseStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Pane for displaying status and options after making a booking.
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public class StatusPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private ResponseStatus response;
    private final JPanel statusPanel;

    /**
     * Generates the status panel.
     *
     * @return the JPanel with status pane items added
     */
    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout());

        JLabel loadingLabel = new JLabel("Loading ...", JButton.CENTER);
        statusPanel.add(loadingLabel);

        return statusPanel;
    }

    /**
     * Generates the user controls panel.
     *
     * @return the JPanel with buttons added
     */
    private JPanel createUserControlsPanel() {
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        JButton mainMenuButton = new JButton("Main menu");
        mainMenuButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());

        userControls.add(mainMenuButton);

        if (!this.response.getStatus()) {
            JButton backButton = new JButton("Back");
            backButton.addActionListener((ActionEvent e) ->
                    cm.getNavigator().back());

            userControls.add(backButton);
        }

        return userControls;
    }

    /**
     * Updates text value of label in statusPanel to the received response.
     */
    private void updateStatusPanel() {
        JLabel statusLabel = (JLabel) statusPanel.getComponent(0);
        statusLabel.setText(this.response.getMessage());
    }

    /**
     * Instantiates a new Status pane.
     *
     * @param frame the window frame
     */
    public StatusPane(JFrame frame) {
        super(frame);

        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new BorderLayout());

        this.statusPanel = createStatusPanel();

        contentPane.add(statusPanel, BorderLayout.CENTER);
    }

    /**
     * Method to be called my observed object to notify about changes.
     * Retrieves new relevant values and updates the GUI accordingly.
     *
     * @param observable the observed object
     */
    @Override
    public void notify(ClientModel observable) {
        ResponseStatus response = observable.getResponse();

        if (response != null && response != this.response) {
            this.response = response;

            updateStatusPanel();
            JPanel userControlsPanel = createUserControlsPanel();

            contentPane.add(userControlsPanel, BorderLayout.SOUTH);
            contentPane.validate();
        }
    }
}
