public class ClientModel extends AbstractObservable {
    private static ClientModel INSTANCE;

    private ClientModel() { }

    public synchronized static ClientModel getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ClientModel();
        }

        return INSTANCE;
    }
}
