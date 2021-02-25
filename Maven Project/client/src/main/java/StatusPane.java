import ObserverPattern.IObserver;

import javax.swing.*;
import java.awt.*;

public class StatusPane extends AbstractPane implements IObserver<ClientModel> {




    private JPanel createStatusPanel(){
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout());

        JLabel statusText = new JLabel("");



        // TODO

        return statusPanel;
    }







    public StatusPane(JFrame frame) {
        super(frame);


    }

    @Override
    public void notify(ClientModel observable) {

    }
}
