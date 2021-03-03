import utils.IObserver;
import cinemaObjects.Customer;
import cinemaObjects.Row;
import cinemaObjects.Show;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Pane for inputting customer information during booking.
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public class CustomerInformationPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private final JPanel customerInformationPanel;
    private final List<JTextField> customerInfoFields = new ArrayList<>();
    private JButton continueButton;

    /**
     * Variables for new booking
     */
    private final Show bookShow;
    private final List<Row> bookRows;
    private Customer bookCustomer;

    /**
     * Generates the customer information entering panel.
     *
     * @return the JPanel with input fields added
     */
    public JPanel createCustomerInformationPanel() {
        if (bookCustomer == null) {
            bookCustomer = new Customer();
        }

        JPanel customerInformationPanel = new JPanel();
        customerInformationPanel.setLayout(new BorderLayout());

        JPanel findBySSNPanel = new JPanel();
        JPanel enterInfoPanel = new JPanel();

        JLabel getInfoLabel = new JLabel("Get info by SSN");
        JTextField getInfoTextField = new JTextField();
        JButton searchButton = new JButton("Find");

        searchButton.addActionListener((ActionEvent e) -> cm.updateCustomer(getInfoTextField.getText()));

        findBySSNPanel.setLayout(new BoxLayout(findBySSNPanel, BoxLayout.Y_AXIS));
        findBySSNPanel.add(getInfoLabel);
        findBySSNPanel.add(getInfoTextField);
        findBySSNPanel.add(searchButton);

        JLabel enterNameLabel = new JLabel("Name");
        JTextField enterNameTextField = new JTextField();
        enterNameTextField.getDocument().addDocumentListener((new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                bookCustomer.setName(enterNameTextField.getText());
                updateContinueButton();
            }
        }));
        customerInfoFields.add(enterNameTextField);

        JLabel enterSSNLabel = new JLabel("SSN");
        JTextField enterSSNTextField = new JTextField();
        enterSSNTextField.getDocument().addDocumentListener((new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                bookCustomer.setSSN(enterSSNTextField.getText());
                updateContinueButton();
            }
        }));
        customerInfoFields.add(enterSSNTextField);

        JLabel enterPhoneNumberLabel = new JLabel("Phone number");
        JTextField enterTelephoneTextField = new JTextField();
        enterTelephoneTextField.getDocument().addDocumentListener((new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                bookCustomer.setPhoneNumber(enterTelephoneTextField.getText());
                updateContinueButton();
            }
        }));
        customerInfoFields.add(enterTelephoneTextField);


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

    /**
     * Generates the user controls panel.
     *
     * @return the JPanel with buttons added
     */
    public JPanel createUserControlsPanel() {
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        continueButton = new JButton("Continue");
        JButton backButton = new JButton("Back");
        JButton cancelButton = new JButton("Cancel");

        continueButton.setEnabled(false);

        continueButton.addActionListener((ActionEvent e) -> {
            cm.createBooking(bookShow, bookCustomer, new ArrayList<>(bookRows));
            cm.getNavigator().startNewPane(new StatusPane(frame));
        });

        backButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().back());

        cancelButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());

        userControls.add(continueButton);
        userControls.add(backButton);
        userControls.add(cancelButton);

        return userControls;
    }

    /**
     * Updates the customer information entering panel by entering
     * customer information from server into the input fields.
     */
    public void updateCustomerInformationPanel() {
        JPanel con = (JPanel) customerInformationPanel.getComponent(1);
        JPanel con2 = (JPanel) con.getComponent(0);
        JTextField[] textFields = new JTextField[3];

        textFields[0] = (JTextField) con2.getComponent(1);
        textFields[1] = (JTextField) con2.getComponent(3);
        textFields[2] = (JTextField) con2.getComponent(5);

        textFields[0].setText(bookCustomer.getName());
        textFields[1].setText(bookCustomer.getSSN());
        textFields[2].setText(bookCustomer.getPhoneNumber());

        updateContinueButton();
        contentPane.validate();
    }

    /**
     * Checks text fields and updates continue button if all fields contain text.
     */
    public void updateContinueButton() {
        boolean enabled = true;

        for (JTextField textField : customerInfoFields) {
            if (textField.getText() == null || textField.getText().equals("")) {
                enabled = false;
                break;
            }
        }

        continueButton.setEnabled(enabled);
    }

    /**
     * Instantiates a new Customer information pane.
     *
     * @param frame the window frame
     * @param show  the show to book
     * @param rows  the rows containing the seats to be booked
     */
    public CustomerInformationPane(JFrame frame, Show show, List<Row> rows) {
        super(frame);

        this.bookShow = show;
        this.bookRows = rows;

        cm = ClientModel.getInstance();
        cm.addObserver(this);

        contentPane.setLayout(new BorderLayout());

        this.customerInformationPanel = createCustomerInformationPanel();
        JPanel userControlsPanel = createUserControlsPanel();

        contentPane.add(customerInformationPanel, BorderLayout.CENTER);
        contentPane.add(userControlsPanel, BorderLayout.SOUTH);
    }

    /**
     * Method to be called my observed object to notify about changes.
     * Retrieves new relevant values and updates the GUI accordingly.
     *
     * @param observable the observed object
     */
    @Override
    public void notify(ClientModel observable) {
        Customer bookCustomer = observable.getCustomer();

        if (bookCustomer != null && bookCustomer != this.bookCustomer) {
            this.bookCustomer = bookCustomer;

            updateCustomerInformationPanel();
        }
    }
}