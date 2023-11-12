package gestioneCarrello;

import java.util.ArrayList;
import java.util.List;

//rappresenta un carrello con tutti i suoi oggetti e le operazioni possibili su di essi
public class Carts<T>
{
    List<T> items;
    
    public Carts() {
        this.items = new ArrayList<T>();
    }
    
    public void addItem(final T item) {
        this.items.add(item);
    }
    
    public void deleteItem(final T item) {
        this.items.remove(item);
    }
    
    public List<T> getItems() {
        return this.items;
    }
    
    public void deleteItems() {
        this.items.clear();
    }
}