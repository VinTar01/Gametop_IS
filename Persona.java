import java.text.DateFormat;
import java.util.*;

public class Persona{
    String nome, cognome, genere;
    int età;
    String annoNascita;

    

    public Persona(String nome, String cognome, String genere, int età, String annoNascita){
        this.nome=nome;
        this.cognome=cognome;
        this.genere=genere;
        this.età=età;
        this.annoNascita=annoNascita;

    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEtà(int età) {
        this.età = età;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setAnnoNascita(String annoNascita) {
        this.annoNascita = annoNascita;
    }

    public String getNome() {
        return nome;
    }
    public String getGenere() {
        return genere;
    }
    public String getCognome() {
        return cognome;
    }

    public int getEtà() {
        return età;
    }
    public String getAnnoNascita() {
        return annoNascita;
    }




}