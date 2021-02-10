import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowSelectionPane extends AbstractPane {
    public ShowSelectionPane(JFrame frame){
        super(frame);
    }

    public void init(){
        contentPane.setLayout(new BorderLayout());

        contentPane.add(movieSelection(), BorderLayout.NORTH);
        contentPane.add(showSelection(), BorderLayout.CENTER);
        contentPane.add(userControls(), BorderLayout.SOUTH);
    }

    public Container movieSelection(){
        Container movieSelection = new JPanel();

        movieSelection.setLayout(new GridLayout(1, 1));

        JButton continueButton1 = new JButton("Continue");
        continueButton1.setEnabled(false);

        continueButton1.addActionListener((ActionEvent e) -> {

        });
        movieSelection.add(continueButton1);
        movieSelection.setPreferredSize(new Dimension(frame.getWidth(), 100));

        return movieSelection;
    }

    public Container showSelection(){
        Container showSelection = new JPanel();

        showSelection.setLayout(new GridLayout(1, 1));

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

        return userControls;
    }
}
