import javax.swing.*;
import java.awt.*;

public abstract class AbstractPane {
    protected final JFrame frame;
    protected final Container originalContentPane;
    protected final Container contentPane;
    protected boolean recursiveStop = false;

    public AbstractPane(JFrame frame) {
        this.frame = frame;
        this.originalContentPane = this.frame.getContentPane();
        contentPane = new JPanel();
    }

    public void start() {
        this.frame.setContentPane(this.contentPane);
        this.frame.pack();
    }

    protected void stop() {
        stop(false);
    }

    protected void stop(boolean stopAll) {
        this.frame.setContentPane(this.originalContentPane);
        this.frame.pack();

        recursiveStop = stopAll;
    }
}