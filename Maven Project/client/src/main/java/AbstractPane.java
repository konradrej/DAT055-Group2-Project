import javax.swing.*;
import java.awt.*;

public abstract class AbstractPane {
    protected final JFrame frame;
    protected final Container originalContentPane;
    protected final Container contentPane;

    public AbstractPane(JFrame frame) {
        this.frame = frame;
        this.originalContentPane = this.frame.getContentPane();
        contentPane = new JPanel();
        init();
    }

    public abstract void init();

    public void start() {
        this.frame.setContentPane(this.contentPane);
        this.frame.pack();
    }

    protected void stop() {
        this.frame.setContentPane(this.originalContentPane);
        this.frame.pack();
    }
}