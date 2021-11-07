package app;

class User {
    private int id_uzytkownik;
    private String login, haslo, imie, nazwisko, ulica, miasto, kod_pocztowy, telefon, email, rola;
    
    public User(int id_uzytkownik, String login, String haslo, String imie, String nazwisko, 
            String ulica, String miasto, String kod_pocztowy, String telefon, String email, String rola){
        this.id_uzytkownik=id_uzytkownik;
        this.login= login;
        this.haslo= haslo;
        this.imie= imie;
        this.nazwisko= nazwisko;
        this.ulica= ulica;
        this.miasto= miasto;
        this.kod_pocztowy= kod_pocztowy;
        this.telefon= telefon;
        this.email= email;
        this.rola= rola;
    }
    
    public int getid_uzytkownik(){
        return id_uzytkownik;
    }
    public String getlogin(){
        return login;
    }
    public String getimie(){
        return imie;
    }
    public String getnazwisko(){
        return nazwisko;
    }
    public String getulica(){
        return ulica;
    }
    public String getmiasto(){
        return miasto;
    }
    public String getkod_pocztowy(){
        return kod_pocztowy;
    }
    public String gettelefon(){
        return telefon;
    }
    public String getemail(){
        return email;
    }

    public String getRola() {
        return rola;
    }        
}
