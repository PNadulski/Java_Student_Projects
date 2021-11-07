/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Kuba
 */
public class Zamowienie {
    private int idZamowienia;
    private int idUzytkownika;
    private int idKsiazki;
    private String dataWypozyczenia;
    private String statusZamowienia;

    public Zamowienie() {
    }

    public Zamowienie(int idZamowienia, int idUzytkownika, int idKsiazki, String dataWypozyczenia, String statusZamowienia) {
        this.idZamowienia = idZamowienia;
        this.idUzytkownika = idUzytkownika;
        this.idKsiazki = idKsiazki;
        this.dataWypozyczenia = dataWypozyczenia;
        this.statusZamowienia = statusZamowienia;
    }

    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public int getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public int getIdKsiazki() {
        return idKsiazki;
    }

    public void setIdKsiazki(int idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public String getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(String dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public String getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(String statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }
    
    
}
