import java.util.Collection;

public class AbstractCollection implements AllCollections{
	
	private AllCollections decorator;
	public AbstractCollection (AllCollections d) {
		this.decorator = d;
	}
	
	public void removeObject(AbstractCollectionObject o) {
		for(AbstractCollectionObject o2 : decorator.getCollection()) {
			if(o2.equals(o)) {
				decorator.getCollection().remove(o2);
				System.out.println( o2 + " removed");
				break;
			}
		}
		System.out.println("Not found");
	}
	
	public void addToCollection(AbstractCollectionObject o ) {
		decorator.getCollection().add(o);
	}

	@Override
	public Collection<AbstractCollectionObject> getCollection() {
		return this.decorator.getCollection();
	}
}
