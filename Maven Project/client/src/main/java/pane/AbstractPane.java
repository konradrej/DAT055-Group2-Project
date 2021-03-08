package pane;

import javax.swing.*;
import java.awt.*;

/**
 * @author Konrad Rej
 * @version 2021-03-08
 */
public abstract class AbstractPane {
    protected final JFrame frame;
    protected final Container originalContentPane;
    protected final Container contentPane;

    /**
     * Constructs an abstractPane
     *
     * @param frame the JFrame window to be used
     */
    public AbstractPane(JFrame frame) {
        this.frame = frame;
        this.originalContentPane = this.frame.getContentPane();
        this.contentPane = new JPanel();
    }

    /**
     * Used to start using current pane.
     */
    public void start() {
        this.frame.setContentPane(this.contentPane);
        this.frame.pack();
    }

    /**
     * Used to stop using current pane.
     */
    public void stop() {
        this.frame.setContentPane(this.originalContentPane);
        this.frame.pack();
    }
}