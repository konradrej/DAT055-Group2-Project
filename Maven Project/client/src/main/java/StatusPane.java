import ObserverPattern.IObserver;
import misc.ResponseStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StatusPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private ResponseStatus response;
    private final JPanel statusPanel;

    /**
     * User control buttons
     */
    private JButton mainMenuButton;
    private JButton backButton;

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

        mainMenuButton = new JButton("Main menu");
        mainMenuButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToMainMenu());

        userControls.add(mainMenuButton);

        if(!this.response.getStatus()){
            backButton = new JButton("Back");
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
