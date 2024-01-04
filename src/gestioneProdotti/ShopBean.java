    package gestioneProdotti;

import java.io.Serializable;

public class ShopBean implements Serializable {
   private static final long serialVersionUID = 1L;
   String codiceProdotto = "";
   String idCategoria = "";
   int quantitaProdotto = 0;
   String titolo = "";
   String descrizione = "";
   int prezzo = 0;
   String copertina = "";

   public String getCodiceProdotto() {
      return this.codiceProdotto;
   }

   public void setCodiceProdotto(String codiceProdotto) {
      this.codiceProdotto = codiceProdotto;
   }

   public int getQuantitaProdotto() {
      return this.quantitaProdotto;
   }

   public void setQuantitaProdotto(int quantitaProdotto) {
      this.quantitaProdotto = quantitaProdotto;
   }

   public String getIdCategoria() {
      return this.idCategoria;
   }

   public void setIdCategoria(String idCategoria) {
      this.idCategoria = idCategoria;
   }

   public String getTitolo() {
      return this.titolo;
   }

   public void setTitolo(String titolo) {
      this.titolo = titolo;
   }

   public String getDescrizione() {
      return this.descrizione;
   }

   public void setDescrizione(String descrizione) {
      this.descrizione = descrizione;
   }

   public int getPrezzo() {
      return this.prezzo;
   }

   public int hashCode() {
      //int prime = true;
      int result = 1;
      /*int*/ result = 31 * result + (this.codiceProdotto == null ? 0 : this.codiceProdotto.hashCode());
      result = 31 * result + (this.copertina == null ? 0 : this.copertina.hashCode());
      result = 31 * result + (this.descrizione == null ? 0 : this.descrizione.hashCode());
      result = 31 * result + (this.idCategoria == null ? 0 : this.idCategoria.hashCode());
      result = 31 * result + this.prezzo;
      result = 31 * result + this.quantitaProdotto;
      result = 31 * result + (this.titolo == null ? 0 : this.titolo.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ShopBean other = (ShopBean)obj;
         if (this.codiceProdotto == null) {
            if (other.codiceProdotto != null) {
               return false;
            }
         } else if (!this.codiceProdotto.equals(other.codiceProdotto)) {
            return false;
         }

         if (this.copertina == null) {
            if (other.copertina != null) {
               return false;
            }
         } else if (!this.copertina.equals(other.copertina)) {
            return false;
         }

         if (this.descrizione == null) {
            if (other.descrizione != null) {
               return false;
            }
         } else if (!this.descrizione.equals(other.descrizione)) {
            return false;
         }

         if (this.idCategoria == null) {
            if (other.idCategoria != null) {
               return false;
            }
         } else if (!this.idCategoria.equals(other.idCategoria)) {
            return false;
         }

         if (this.prezzo != other.prezzo) {
            return false;
         } else if (this.quantitaProdotto != other.quantitaProdotto) {
            return false;
         } else {
            if (this.titolo == null) {
               if (other.titolo != null) {
                  return false;
               }
            } else if (!this.titolo.equals(other.titolo)) {
               return false;
            }

            return true;
         }
      }
   }

   public void setPrezzo(int prezzo) {
      this.prezzo = prezzo;
   }

   public boolean isEmpty() {
      return this.codiceProdotto == "";
   }

   public String getCopertina() {
      return this.copertina;
   }

   public void setCopertina(String copertina) {
      this.copertina = copertina;
   }
}