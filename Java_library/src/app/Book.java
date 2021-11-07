
package app;

/**
 *
 * @author Endiur
 */
class Book {
    private int id_ksiazka, dostepna;
    private long isbn;
    private String tytul, autor, wydawnictwo, id_kategoria, rok_wydania;
    
    public Book(int id_ksiazka, String id_kategoria, long isbn, int dostepna, String tytul, String autor, 
            String wydawnictwo, String rok_wydania){
        this.id_ksiazka= id_ksiazka;
        this.id_kategoria= id_kategoria;
        this.isbn= isbn;
        this.dostepna= dostepna;
        this.tytul= tytul;
        this.autor= autor;
        this.wydawnictwo= wydawnictwo;
        this.rok_wydania= rok_wydania;
    }
    public int getid_ksiazka(){
        return id_ksiazka;
    }
    public String getid_kategoria(){
        return id_kategoria;
    }
    public long getisbn(){
        return isbn;
    }
    public int getdostepna(){
        return dostepna;
    }
    
    public String gettytul(){
        return tytul;
    }
    public String getautor(){
        return autor;
    }
    public String getwydawnictwo(){
        return wydawnictwo;
    }
    public String getrok_wydania(){
        return rok_wydania;
    }
}
