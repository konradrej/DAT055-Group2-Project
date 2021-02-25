import cinemaObjects.Customer;
import cinemaObjects.Row;
import cinemaObjects.Show;
import server.GetCustomerBySSNCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomerInformationPane extends AbstractPane {
    private ClientModel cm;
    private Customer customer;
    private JPanel customerInformationPanel;
    private JPanel userControlsPanel;

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

    private JPanel createUserControlsPanel(){
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        continueButton = new JButton("Continue");
        backButton = new JButton("Back");
        cancelButton = new JButton("Cancel");

        continueButton.setEnabled(false);
        backButton.setEnabled(true);

        continueButton.addActionListener((ActionEvent e) -> {
            CustomerInformationPane customerPane = new CustomerInformationPane(frame, bookShow, bookRows);
            customerPane.start();
        });

        backButton.addActionListener((ActionEvent e) -> {
            stop();
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            // TODO
            stop();
        });

        userControls.add(continueButton);
        userControls.add(backButton);
        userControls.add(cancelButton);

        return userControls;
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





    public CustomerInformationPane(JFrame frame, Show show, List<Row> rows) {
        super(frame);

        this.bookShow = show;
        this.bookRows = rows;

        cm = ClientModel.getInstance();

        contentPane.setLayout(new BorderLayout());

        this.customerInformationPanel = createCustomerInformationPanel();
        this.userControlsPanel = createUserControlsPanel();

        contentPane.add(customerInformationPanel, BorderLayout.CENTER);
        contentPane.add(userControlsPanel, BorderLayout.SOUTH);
    }
}