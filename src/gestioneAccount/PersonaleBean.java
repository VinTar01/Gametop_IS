    package gestioneAccount;

import java.util.ArrayList;
import java.util.Iterator;

public class PersonaleBean {
   private String nome;
   private String cognome;
   private String email;
   private String password;
   private String ruolo;

   public int hashCode() {
      //int prime = true;
      int result = 1;
      /*int*/ result = 31 * result + (this.cognome == null ? 0 : this.cognome.hashCode());
      result = 31 * result + (this.email == null ? 0 : this.email.hashCode());
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
         PersonaleBean other = (PersonaleBean)obj;
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

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRuolo() {
      return this.ruolo;
   }

   public void setRuolo(String ruolo) {
      this.ruolo = ruolo;
   }

   public Object fetchemail(ArrayList<PersonaleBean> userList, String username) {
      Iterator var4 = userList.iterator();

      while(var4.hasNext()) {
         PersonaleBean u = (PersonaleBean)var4.next();
         if (u.getEmail().equals(username)) {
            return u.getEmail();
         }
      }

      return "";
   }

   public Object fetchname(ArrayList<PersonaleBean> userList, String username) {
      Iterator var4 = userList.iterator();

      while(var4.hasNext()) {
         PersonaleBean u = (PersonaleBean)var4.next();
         if (u.getEmail().equals(username)) {
            return u.getNome();
         }
      }

      return "";
   }
}