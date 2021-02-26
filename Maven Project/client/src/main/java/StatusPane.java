import ObserverPattern.IObserver;
import misc.ResponseStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Pane for displaying status and options after making a booking.
 */
public class StatusPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private ResponseStatus response;
    private final JPanel statusPanel;

    private JPanel createStatusPanel(){
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout());

        JLabel loadingLabel = new JLabel("Loading ...", JButton.CENTER);
        statusPanel.add(loadingLabel);

        return statusPanel;
    }

    private JPanel createUserControlsPanel(){
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        JButton mainMenuButton = new JButton("Main menu");
        mainMenuButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());

        userControls.add(mainMenuButton);

        if(!this.response.getStatus()){
            JButton backButton = new JButton("Back");
            backButton.addActionListener((ActionEvent e) ->
                    cm.getNavigator().back());

            userControls.add(backButton);
        }

        return userControls;
    }

    private void updateStatusPanel(){
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

    @Override
    public void notify(ClientModel observable) {
        ResponseStatus response = observable.getResponse();

        if(response != null && response != this.response){
            this.response = response;

            updateStatusPanel();
            JPanel userControlsPanel = createUserControlsPanel();
            contentPane.add(userControlsPanel, BorderLayout.SOUTH);
            contentPane.validate();
        }
    }
}
