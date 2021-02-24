import java.io.*;

public abstract class AbstractCollection implements AllCollections {

    /**
     * Serializing and updates this object
     *
     * 	@Override
     */

    public void updateCollection(String s) {

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(s)))){
            stream.writeObject(this);
        }
        catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }
}
