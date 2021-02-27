package collections;

import java.io.*;

public abstract class AbstractCollection implements AllCollections {

    /**
     * Serializing and updates this object
     */

    @Override
    public void serializeCollection(String s) {

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(s))){
            stream.writeObject(this);
        }
        catch (IOException e) {
            System.err.println("Error initializing stream " + e.getMessage());
            e.printStackTrace();
        }
    }
}
