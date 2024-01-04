    package gestioneCarrello;

import java.util.ArrayList;
import java.util.List;

public class Carts<T> {
   List<T> items = new ArrayList();

   public void addItem(T item) {
      this.items.add(item);
   }

   public void deleteItem(T item) {
      this.items.remove(item);
   }

   public List<T> getItems() {
      return this.items;
   }

   public void deleteItems() {
      this.items.clear();
   }
}