    package gestioneAcquisti;

public class OrdineBean {
   String numeroOrdine;
   String email;
   String nome;
   String cognome;
   String indirizzo;
   String cap;
   String comune;
   String provincia;
   String prezzo;
   String prodotti;
   String controllato;

   public String toString() {
      return super.toString() + "GestoreOrdini";
   }

   public String getControllato() {
      return this.controllato;
   }

   public void setControllato(String controllato) {
      this.controllato = controllato;
   }

   public String getNumeroOrdine() {
      return this.numeroOrdine;
   }

   public void setNumeroOrdine(String numeroOrdine) {
      this.numeroOrdine = numeroOrdine;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getCognome() {
      return this.cognome;
   }

   public void setCognome(String cognome) {
      this.cognome = cognome;
   }

   public String getIndirizzo() {
      return this.indirizzo;
   }

   public void setIndirizzo(String indirizzo) {
      this.indirizzo = indirizzo;
   }

   public String getCap() {
      return this.cap;
   }

   public void setCap(String cap) {
      this.cap = cap;
   }

   public String getComune() {
      return this.comune;
   }

   public void setComune(String comune) {
      this.comune = comune;
   }

   public int hashCode() {
      //int prime = true;
      int result = 1;
      /*int*/ result = 31 * result + (this.cap == null ? 0 : this.cap.hashCode());
      result = 31 * result + (this.cognome == null ? 0 : this.cognome.hashCode());
      result = 31 * result + (this.comune == null ? 0 : this.comune.hashCode());
      result = 31 * result + (this.controllato == null ? 0 : this.controllato.hashCode());
      result = 31 * result + (this.email == null ? 0 : this.email.hashCode());
      result = 31 * result + (this.indirizzo == null ? 0 : this.indirizzo.hashCode());
      result = 31 * result + (this.nome == null ? 0 : this.nome.hashCode());
      result = 31 * result + (this.numeroOrdine == null ? 0 : this.numeroOrdine.hashCode());
      result = 31 * result + (this.prezzo == null ? 0 : this.prezzo.hashCode());
      result = 31 * result + (this.prodotti == null ? 0 : this.prodotti.hashCode());
      result = 31 * result + (this.provincia == null ? 0 : this.provincia.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof OrdineBean)) {
         return false;
      } else {
         OrdineBean other = (OrdineBean)obj;
         if (this.cap == null) {
            if (other.cap != null) {
               return false;
            }
         } else if (!this.cap.equals(other.cap)) {
            return false;
         }

         if (this.cognome == null) {
            if (other.cognome != null) {
               return false;
            }
         } else if (!this.cognome.equals(other.cognome)) {
            return false;
         }

         if (this.comune == null) {
            if (other.comune != null) {
               return false;
            }
         } else if (!this.comune.equals(other.comune)) {
            return false;
         }

         if (this.controllato == null) {
            if (other.controllato != null) {
               return false;
            }
         } else if (!this.controllato.equals(other.controllato)) {
            return false;
         }

         if (this.email == null) {
            if (other.email != null) {
               return false;
            }
         } else if (!this.email.equals(other.email)) {
            return false;
         }

         if (this.indirizzo == null) {
            if (other.indirizzo != null) {
               return false;
            }
         } else if (!this.indirizzo.equals(other.indirizzo)) {
            return false;
         }

         if (this.nome == null) {
            if (other.nome != null) {
               return false;
            }
         } else if (!this.nome.equals(other.nome)) {
            return false;
         }

         if (this.numeroOrdine == null) {
            if (other.numeroOrdine != null) {
               return false;
            }
         } else if (!this.numeroOrdine.equals(other.numeroOrdine)) {
            return false;
         }

         if (this.prezzo == null) {
            if (other.prezzo != null) {
               return false;
            }
         } else if (!this.prezzo.equals(other.prezzo)) {
            return false;
         }

         if (this.prodotti == null) {
            if (other.prodotti != null) {
               return false;
            }
         } else if (!this.prodotti.equals(other.prodotti)) {
            return false;
         }

         if (this.provincia == null) {
            if (other.provincia != null) {
               return false;
            }
         } else if (!this.provincia.equals(other.provincia)) {
            return false;
         }

         return true;
      }
   }

   public String getProvincia() {
      return this.provincia;
   }

   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }

   public String getPrezzo() {
      return this.prezzo;
   }

   public void setPrezzo(String prezzzo) {
      this.prezzo = prezzzo;
   }

   public String getProdotti() {
      return this.prodotti;
   }

   public void setProdotti(String prodotti) {
      this.prodotti = prodotti;
   }
}
