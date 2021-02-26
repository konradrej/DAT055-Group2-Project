import java.util.Stack;

/**
 * Class for handling navigation between panes.
 */
public class Navigator {
    private final AbstractPane startPane;
    private final Stack<AbstractPane> paneTrace = new Stack<>();

    /**
     * Instantiates a new Navigator.
     *
     * @param startPane the initial pane to show
     */
    public Navigator(AbstractPane startPane){
        this.startPane = startPane;
        paneTrace.add(this.startPane);
        paneTrace.peek().start();
    }

    /**
     * Adds pane to stack and runs nextPane.start().
     *
     * @param nextPane the pane to add to stack
     */
    public void startNewPane(AbstractPane nextPane){
        paneTrace.push(nextPane);
        paneTrace.peek().start();
    }

    /**
     * Removes current pane and goes to the previous pane
     */
    public void back(){
        paneTrace.pop().stop();
    }

    /**
     * Removes all non-Start panes
     */
    public void backToStart(){
        while(paneTrace.peek() != startPane){
            paneTrace.pop().stop();
        }
    }
}
