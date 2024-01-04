    package gestioneAccount;

import java.util.ArrayList;
import java.util.Iterator;

public class UtenteBean {
   private String nome;
   private String cognome;
   private String email;
   private String indirizzo;
   private String password;

   public int hashCode() {
      //int prime = true;
      int result = 1;
      /*int*/ result = 31 * result + (this.cognome == null ? 0 : this.cognome.hashCode());
      result = 31 * result + (this.email == null ? 0 : this.email.hashCode());
      result = 31 * result + (this.indirizzo == null ? 0 : this.indirizzo.hashCode());
      result = 31 * result + (this.nome == null ? 0 : this.nome.hashCode());
      result = 31 * result + (this.password == null ? 0 : this.password.hashCode());
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
         UtenteBean other = (UtenteBean)obj;
         if (this.cognome == null) {
            if (other.cognome != null) {
               return false;
            }
         } else if (!this.cognome.equals(other.cognome)) {
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

         if (this.password == null) {
            if (other.password != null) {
               return false;
            }
         } else if (!this.password.equals(other.password)) {
            return false;
         }

         return true;
      }
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

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getIndirizzo() {
      return this.indirizzo;
   }

   public void setIndirizzo(String indirizzo) {
      this.indirizzo = indirizzo;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Object fetchadd(ArrayList<UtenteBean> userList, String username) {
      Iterator var4 = userList.iterator();

      while(var4.hasNext()) {
         UtenteBean u = (UtenteBean)var4.next();
         if (u.getEmail().equals(username)) {
            return u.getIndirizzo();
         }
      }

      return "";
   }

   public Object fetchemail(ArrayList<UtenteBean> userList, String username) {
      Iterator var4 = userList.iterator();

      while(var4.hasNext()) {
         UtenteBean u = (UtenteBean)var4.next();
         if (u.getEmail().equals(username)) {
            return u.getEmail();
         }
      }

      return "";
   }

   public Object fetchname(ArrayList<UtenteBean> userList, String username) {
      Iterator var4 = userList.iterator();

      while(var4.hasNext()) {
         UtenteBean u = (UtenteBean)var4.next();
         if (u.getEmail().equals(username)) {
            return u.getNome();
         }
      }

      return "";
   }
}
