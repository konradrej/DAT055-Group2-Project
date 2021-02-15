import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowSelectionPane extends AbstractPane {
    public ShowSelectionPane(JFrame frame){
        super(frame);
    }

    @Override
    public void init(){
        contentPane.setLayout(new BorderLayout());

        contentPane.add(movieSelection(), BorderLayout.NORTH);
        contentPane.add(showSelection(), BorderLayout.CENTER);
        contentPane.add(userControls(), BorderLayout.SOUTH);
    }

    @Override
    public void start(){
        super.start();

        SocketClientCommunication.getInstance().closeConnection();
        //ServerCommunication.getInstance().sendMessage("loadMovieNames");
    }

    public Container movieSelection() {
        Container movieSelection = new JPanel();

        movieSelection.setLayout(new GridLayout(1, 1));

        JLabel label = new JLabel("Loading ...", SwingConstants.CENTER);
        movieSelection.add(label);

        movieSelection.setPreferredSize(new Dimension(frame.getWidth(), 50));

        return movieSelection;
    }

    public void updateMovieSelection(String[] movies){
        Container con = (JPanel) contentPane.getComponent(0);
        con.removeAll();

        JComboBox movieList = new JComboBox(movies);
        con.add(movieList);
    }

    public Container showSelection(){
        Container showSelection = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        showSelection.setLayout(new ScrollPaneLayout());

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
}
