import java.util.Stack;

public class Navigator {
    private final AbstractPane startPane;
    private final Stack<AbstractPane> paneTrace = new Stack<>();

    public Navigator(AbstractPane startPane){
        this.startPane = startPane;
        paneTrace.add(this.startPane);
        paneTrace.peek().start();
    }

    public void startNewPane(AbstractPane nextPane){
        paneTrace.push(nextPane);

        next();
    }

    private void next(){
        paneTrace.peek().start();
    }

    public void back(){
        paneTrace.pop().stop();
    }

    public void backToMainMenu(){
        while(paneTrace.peek() != startPane){
            paneTrace.pop().stop();
        }
    }
}
