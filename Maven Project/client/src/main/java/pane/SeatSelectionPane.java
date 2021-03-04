package pane;

import app.ClientModel;
import cinemaObjects.Row;
import cinemaObjects.Seat;
import cinemaObjects.Show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Pane for selecting seat in theater.
 *
 * @author Konrad Rej
 * @version 2021-03-04
 */
public class SeatSelectionPane extends AbstractPane {
    private final ClientModel cm;
    private JPanel seatSelectionPanel;
    private JPanel userControlsPanel;
    private JButton continueButton;

    /**
     * Variables for new booking
     */
    private final Show bookShow;
    private List<Row> bookRows;

    private JPanel createSeatSelectionPanel() {
        bookRows = new ArrayList<>();

        JPanel rowsPanel = new JPanel();
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.Y_AXIS));
        JPanel screenWrapper = new JPanel();
        screenWrapper.setLayout(new GridLayout());
        JPanel screenPanel = new JPanel();
        screenPanel.setBackground(Color.BLACK);
        screenPanel.setPreferredSize(new Dimension(contentPane.getWidth() - 200, 30));

        JLabel label = new JLabel("Screen");
        label.setForeground(Color.WHITE);
        screenPanel.add(label);

        screenWrapper.add(screenPanel);
        screenWrapper.setPreferredSize(new Dimension(contentPane.getWidth(), 110));

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());

        Collection<Row> rows = bookShow.getTheater().getCollectionOfRows();
        for (Row row : rows) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(contentPane.getWidth(), 35));

            for (Seat seat : row.getAllSeats()) {
                JButton seatButton = new JButton();
                seatButton.setPreferredSize(new Dimension(25, 25));

                if (seat.getAvailable()) {
                    seatButton.setBackground(Color.GREEN);
                    seatButton.setToolTipText("Available");

                    seatButton.addActionListener((ActionEvent e) ->
                            handleSeatClick(row, seat, seatButton));
                } else {
                    seatButton.setBackground(Color.RED);
                    seatButton.setToolTipText("Taken");
                }

                rowPanel.add(seatButton);
            }

            rowsPanel.add(rowPanel);
        }

        wrapperPanel.add(screenWrapper, BorderLayout.NORTH);
        wrapperPanel.add(rowsPanel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private JPanel createUserControlsPanel() {
        JPanel userControls = new JPanel();
        userControls.setLayout(new FlowLayout());

        continueButton = new JButton("Continue");
        JButton backButton = new JButton("Back");
        JButton cancelButton = new JButton("Cancel");

        continueButton.setEnabled(false);

        continueButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().startNewPane(new CustomerInformationPane(frame, bookShow, bookRows)));

        backButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().back());

        cancelButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());

        userControls.add(continueButton);
        userControls.add(backButton);
        userControls.add(cancelButton);

        return userControls;
    }

    private void handleSeatClick(Row row, Seat seat, JButton seatButton) {
        int rowIndex = -1;

        for (int i = 0; i < bookRows.size(); i++) {
            if (bookRows.get(i).getRowNumber() == row.getRowNumber()) {
                rowIndex = i;
                break;
            }
        }

        if (rowIndex == -1) {
            bookRows.add(new Row(row.getRowNumber(), new ArrayList<>(Collections.singletonList(seat))));
            seatButton.setBackground(Color.ORANGE);
        } else {
            Row operatingRow = bookRows.get(rowIndex);
            int seatIndex = -1;

            for (int i = 0; i < operatingRow.getAllSeats().size(); i++) {
                if (operatingRow.getAllSeats().get(i).getSeatNumber() == seat.getSeatNumber()) {
                    seatIndex = i;
                    break;
                }
            }

            if (seatIndex == -1) {
                operatingRow.addSeat(seat);
                seatButton.setBackground(Color.ORANGE);
                seatButton.setToolTipText("Selected");
            } else {
                operatingRow.removeSeat(seat);
                seatButton.setBackground(Color.GREEN);
                seatButton.setToolTipText("Available");

                if (operatingRow.getAllSeats().size() == 0) {
                    bookRows.remove(rowIndex);
                }
            }
        }

        continueButton.setEnabled(bookRows.size() != 0);
    }

    /**
     * Instantiates a new Seat selection pane.
     *
     * @param frame the window frame
     * @param show  the show to book and get seats from
     */
    public SeatSelectionPane(JFrame frame, Show show) {
        super(frame);

        this.bookShow = show;

        cm = ClientModel.getInstance();

        contentPane.setLayout(new BorderLayout());

        this.seatSelectionPanel = createSeatSelectionPanel();
        this.userControlsPanel = createUserControlsPanel();

        contentPane.add(seatSelectionPanel, BorderLayout.CENTER);
        contentPane.add(userControlsPanel, BorderLayout.SOUTH);
    }
}
