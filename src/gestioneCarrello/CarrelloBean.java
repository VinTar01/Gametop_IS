    package gestioneCarrello;

public class CarrelloBean {
   private String idemail;
   private String password;
   private String codiceVestito;

   public String getIdemail() {
      return this.idemail;
   }

   public void setIdemail(String idemail) {
      this.idemail = idemail;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getCodiceVestito() {
      return this.codiceVestito;
   }

   public void setCodiceVestito(String codiceVestito) {
      this.codiceVestito = codiceVestito;
   }

   public int hashCode() {
      //int prime = true;
      int result = 1;
      /*int*/ result = 31 * result + (this.codiceVestito == null ? 0 : this.codiceVestito.hashCode());
      result = 31 * result + (this.idemail == null ? 0 : this.idemail.hashCode());
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
         CarrelloBean other = (CarrelloBean)obj;
         if (this.codiceVestito == null) {
            if (other.codiceVestito != null) {
               return false;
            }
         } else if (!this.codiceVestito.equals(other.codiceVestito)) {
            return false;
         }

         if (this.idemail == null) {
            if (other.idemail != null) {
               return false;
            }
         } else if (!this.idemail.equals(other.idemail)) {
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
}