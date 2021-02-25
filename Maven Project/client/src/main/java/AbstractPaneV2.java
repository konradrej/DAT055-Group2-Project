import javax.swing.*;
import java.awt.*;

public abstract class AbstractPaneV2 {
    protected final JFrame frame;
    protected final Container originalContentPane;
    protected final Container contentPane;

    public AbstractPaneV2(JFrame frame) {
        this.frame = frame;
        this.originalContentPane = this.frame.getContentPane();
        contentPane = new JPanel();
    }

    public void start() {
        this.frame.setContentPane(this.contentPane);
        this.frame.pack();
    }

    protected boolean stop() {
        return stop(false);
    }

    protected boolean stop(boolean stopAll) {
        this.frame.setContentPane(this.originalContentPane);
        this.frame.pack();

        return stopAll;
    }
}