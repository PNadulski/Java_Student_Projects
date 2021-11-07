/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Endiur
 */
public class Biblioteka extends javax.swing.JFrame {

    /**
     * Creates new form Biblioteka
     */
    public int stan;
    public int userID;
    
    public Biblioteka() {
        initComponents();
        
    }

    public int getStan() {
        return stan;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setStan(int stan) {
        this.stan = stan;
        if (this.stan != 2) {
            jtpMainHud.remove(jpUsers);
        } else if (this.stan == 0 ) {
            jbNowaKsiazka.setVisible(false);
            jbZmienStatus.setVisible(false);
        }
    }
    

    public ArrayList<Book> bookList() {
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            String query = "SELECT * FROM ksiazka";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            Book book;

            while (rs.next()) {
                book = new Book(rs.getInt("id_ksiazka"), rs.getString("id_kategoria"), rs.getLong("isbn"), rs.getInt("dostepna"),
                        rs.getString("tytul"), rs.getString("autor"), rs.getString("wydawnictwo"), rs.getString("rok_wydania"));
                bookList.add(book);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return bookList;
    }

    public void show_book() {
        ArrayList<Book> list = bookList();
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[8];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getid_ksiazka();
            row[1] = list.get(i).getid_kategoria();
            row[2] = list.get(i).getisbn();
            row[3] = list.get(i).gettytul();
            row[4] = list.get(i).getautor();
            row[5] = list.get(i).getwydawnictwo();
            row[6] = list.get(i).getrok_wydania();
            row[7] = list.get(i).getdostepna();
            model.addRow(row);
        }
    }

    public ArrayList<User> userList() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            String query = "SELECT * FROM uzytkownik";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user;

            while (rs.next()) {
                user = new User(rs.getInt("id_uzytkownik"), rs.getString("login"), rs.getString("haslo"), rs.getString("imie"), rs.getString("nazwisko"),
                        rs.getString("ulica"), rs.getString("miasto"), rs.getString("kod_pocztowy"), rs.getString("telefon"), rs.getString("email"),
                        rs.getString("rola"));
                userList.add(user);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return userList;
    }
    
    public ArrayList<Zamowienie> zamowienieList() {
        ArrayList<Zamowienie> zamList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            String query = "SELECT * FROM zamowienie";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            Zamowienie zam;
            
            while (rs.next()) {
                zam = new Zamowienie(rs.getInt("id_zam"),
                        rs.getInt("id_uzytkownik"),
                        rs.getInt("id_ksiazka"),
                        rs.getString("data_wypoz"),
                        rs.getString("status_zam"));
                zamList.add(zam);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        System.out.println(zamList);
        return zamList;
    }
    
    public void show_zamowienia() {
        ArrayList<Zamowienie> zamList = zamowienieList();
        
        DefaultTableModel model = (DefaultTableModel) jtZamowienia.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        
        for (int i = 0; i < zamList.size(); i++) {
            if (getStan() == 0) {
                if(zamList.get(i).getIdUzytkownika() == getUserID()) {
                    row[0] = zamList.get(i).getIdZamowienia();
                    row[1] = zamList.get(i).getIdUzytkownika();
                    row[2] = zamList.get(i).getIdKsiazki();
                    row[3] = zamList.get(i).getDataWypozyczenia();
                    row[4] = zamList.get(i).getStatusZamowienia();
                    model.addRow(row);
                }
            } else {
                row[0] = zamList.get(i).getIdZamowienia();
                row[1] = zamList.get(i).getIdUzytkownika();
                row[2] = zamList.get(i).getIdKsiazki();
                row[3] = zamList.get(i).getDataWypozyczenia();
                row[4] = zamList.get(i).getStatusZamowienia();
                model.addRow(row);
            }
        }


    }

    public void show_user() {
        ArrayList<User> list2 = userList();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] row = new Object[10];

        for (int i = 0; i < list2.size(); i++) {
            row[0] = list2.get(i).getid_uzytkownik();
            row[1] = list2.get(i).getlogin();
            row[2] = list2.get(i).getimie();
            row[3] = list2.get(i).getnazwisko();
            row[4] = list2.get(i).getulica();
            row[5] = list2.get(i).getmiasto();
            row[6] = list2.get(i).getkod_pocztowy();
            row[7] = list2.get(i).gettelefon();
            row[8] = list2.get(i).getemail();
            row[9] = list2.get(i).getRola();
            model.addRow(row);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdModyfikowanie = new javax.swing.JDialog();
        jpMainPan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfMiasto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfKodPoczt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtfLogin = new javax.swing.JTextField();
        jbModyfikuj = new javax.swing.JButton();
        jtfImie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfNazwisko = new javax.swing.JTextField();
        jtfUlica = new javax.swing.JTextField();
        jbAnulujMod = new javax.swing.JButton();
        jtfTelefon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jpHeader = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jdZamowienie = new javax.swing.JDialog();
        jpMainPan2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jbZamow = new javax.swing.JButton();
        jbAnuluj = new javax.swing.JButton();
        jpMainApp = new javax.swing.JPanel();
        jtpMainHud = new javax.swing.JTabbedPane();
        jpKsiazki = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jBKsiazki = new javax.swing.JButton();
        jbNowaKsiazka = new javax.swing.JButton();
        jbZamowienie = new javax.swing.JButton();
        jpZamowienia = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtZamowienia = new javax.swing.JTable();
        jbShowZam = new javax.swing.JButton();
        jbAnulujZam = new javax.swing.JButton();
        jbZmienStatus = new javax.swing.JButton();
        jcbStatusZamowienia = new javax.swing.JComboBox<>();
        jpUsers = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jbUzytkownicy = new javax.swing.JButton();
        jbModyfikacja = new javax.swing.JButton();

        jpMainPan.setBackground(new java.awt.Color(44, 62, 80));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Imie:");

        jtfMiasto.setBackground(new java.awt.Color(108, 122, 137));
        jtfMiasto.setForeground(new java.awt.Color(228, 241, 254));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nazwisko:");

        jtfKodPoczt.setBackground(new java.awt.Color(108, 122, 137));
        jtfKodPoczt.setForeground(new java.awt.Color(228, 241, 254));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ulica:");

        jtfEmail.setBackground(new java.awt.Color(108, 122, 137));
        jtfEmail.setForeground(new java.awt.Color(228, 241, 254));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Miasto");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kod Pocztowy:");

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Telefon:");

        jtfLogin.setBackground(new java.awt.Color(108, 122, 137));
        jtfLogin.setForeground(new java.awt.Color(228, 241, 254));

        jbModyfikuj.setBackground(new java.awt.Color(34, 167, 240));
        jbModyfikuj.setText("Modyfikuj");
        jbModyfikuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModyfikujActionPerformed(evt);
            }
        });

        jtfImie.setBackground(new java.awt.Color(108, 122, 137));
        jtfImie.setForeground(new java.awt.Color(228, 241, 254));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login:");

        jtfNazwisko.setBackground(new java.awt.Color(108, 122, 137));
        jtfNazwisko.setForeground(new java.awt.Color(228, 241, 254));

        jtfUlica.setBackground(new java.awt.Color(108, 122, 137));
        jtfUlica.setForeground(new java.awt.Color(228, 241, 254));

        jbAnulujMod.setBackground(new java.awt.Color(192, 57, 43));
        jbAnulujMod.setText("Anuluj");
        jbAnulujMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnulujModActionPerformed(evt);
            }
        });

        jtfTelefon.setBackground(new java.awt.Color(108, 122, 137));
        jtfTelefon.setForeground(new java.awt.Color(228, 241, 254));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email:");

        javax.swing.GroupLayout jpMainPanLayout = new javax.swing.GroupLayout(jpMainPan);
        jpMainPan.setLayout(jpMainPanLayout);
        jpMainPanLayout.setHorizontalGroup(
            jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainPanLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpMainPanLayout.createSequentialGroup()
                        .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addGap(29, 29, 29)
                        .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtfLogin)
                                .addComponent(jtfImie)
                                .addComponent(jtfNazwisko)
                                .addComponent(jtfMiasto)
                                .addComponent(jtfKodPoczt)
                                .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtfUlica, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpMainPanLayout.createSequentialGroup()
                        .addComponent(jbModyfikuj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbAnulujMod)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpMainPanLayout.setVerticalGroup(
            jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainPanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfImie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNazwisko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUlica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfMiasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfKodPoczt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(35, 35, 35)
                .addGroup(jpMainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAnulujMod)
                    .addComponent(jbModyfikuj))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jpHeader.setBackground(new java.awt.Color(248, 148, 6));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Modyfikuj");

        javax.swing.GroupLayout jpHeaderLayout = new javax.swing.GroupLayout(jpHeader);
        jpHeader.setLayout(jpHeaderLayout);
        jpHeaderLayout.setHorizontalGroup(
            jpHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHeaderLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel9)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jpHeaderLayout.setVerticalGroup(
            jpHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHeaderLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdModyfikowanieLayout = new javax.swing.GroupLayout(jdModyfikowanie.getContentPane());
        jdModyfikowanie.getContentPane().setLayout(jdModyfikowanieLayout);
        jdModyfikowanieLayout.setHorizontalGroup(
            jdModyfikowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpMainPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdModyfikowanieLayout.setVerticalGroup(
            jdModyfikowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdModyfikowanieLayout.createSequentialGroup()
                .addComponent(jpHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpMainPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpMainPan2.setBackground(new java.awt.Color(44, 62, 80));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Kategoria", "ISBN", "Tytul", "Autor", "Wydawnictwo", "Rok wydania", "Dostępność"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jbZamow.setBackground(new java.awt.Color(108, 122, 137));
        jbZamow.setText("Zamów");
        jbZamow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbZamowActionPerformed(evt);
            }
        });

        jbAnuluj.setBackground(new java.awt.Color(108, 122, 137));
        jbAnuluj.setText("Anuluj");
        jbAnuluj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnulujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMainPan2Layout = new javax.swing.GroupLayout(jpMainPan2);
        jpMainPan2.setLayout(jpMainPan2Layout);
        jpMainPan2Layout.setHorizontalGroup(
            jpMainPan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainPan2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpMainPan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbZamow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbAnuluj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jpMainPan2Layout.setVerticalGroup(
            jpMainPan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainPan2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbZamow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbAnuluj)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jdZamowienieLayout = new javax.swing.GroupLayout(jdZamowienie.getContentPane());
        jdZamowienie.getContentPane().setLayout(jdZamowienieLayout);
        jdZamowienieLayout.setHorizontalGroup(
            jdZamowienieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMainPan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdZamowienieLayout.setVerticalGroup(
            jdZamowienieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMainPan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(44, 62, 80));

        jtpMainHud.setBackground(new java.awt.Color(108, 122, 137));

        jpKsiazki.setBackground(new java.awt.Color(44, 62, 80));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Kategoria", "ISBN", "Tytul", "Autor", "Wydawnictwo", "Rok wydania", "Dostępność"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        jBKsiazki.setBackground(new java.awt.Color(108, 122, 137));
        jBKsiazki.setText("Pokaż Ksiązki");
        jBKsiazki.setPreferredSize(new java.awt.Dimension(100, 28));
        jBKsiazki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBKsiazkiActionPerformed(evt);
            }
        });

        jbNowaKsiazka.setBackground(new java.awt.Color(108, 122, 137));
        jbNowaKsiazka.setText("Nowa Książka");
        jbNowaKsiazka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNowaKsiazkaActionPerformed(evt);
            }
        });

        jbZamowienie.setBackground(new java.awt.Color(108, 122, 137));
        jbZamowienie.setText("Zamów");
        jbZamowienie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbZamowienieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpKsiazkiLayout = new javax.swing.GroupLayout(jpKsiazki);
        jpKsiazki.setLayout(jpKsiazkiLayout);
        jpKsiazkiLayout.setHorizontalGroup(
            jpKsiazkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpKsiazkiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpKsiazkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbZamowienie, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbNowaKsiazka, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jBKsiazki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpKsiazkiLayout.setVerticalGroup(
            jpKsiazkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpKsiazkiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBKsiazki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbZamowienie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbNowaKsiazka)
                .addContainerGap(568, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );

        jtpMainHud.addTab("Książki", jpKsiazki);

        jpZamowienia.setBackground(new java.awt.Color(44, 62, 80));

        jtZamowienia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Zamowienia", "ID Uzytkownika", "ID Ksiazki", "Data wypożyczenia", "Status zamówienia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jtZamowienia);

        jbShowZam.setBackground(new java.awt.Color(108, 122, 137));
        jbShowZam.setText("Pokaż zamowienia");
        jbShowZam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbShowZamActionPerformed(evt);
            }
        });

        jbAnulujZam.setBackground(new java.awt.Color(108, 122, 137));
        jbAnulujZam.setText("Anuluj zamówienie");
        jbAnulujZam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnulujZamActionPerformed(evt);
            }
        });

        jbZmienStatus.setBackground(new java.awt.Color(108, 122, 137));
        jbZmienStatus.setText("Zmień status zamówienia");
        jbZmienStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbZmienStatusActionPerformed(evt);
            }
        });

        jcbStatusZamowienia.setBackground(new java.awt.Color(108, 122, 137));
        jcbStatusZamowienia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Oczekujący", "Realizowany", "Zrealizowany" }));
        jcbStatusZamowienia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbStatusZamowieniaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpZamowieniaLayout = new javax.swing.GroupLayout(jpZamowienia);
        jpZamowienia.setLayout(jpZamowieniaLayout);
        jpZamowieniaLayout.setHorizontalGroup(
            jpZamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpZamowieniaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpZamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpZamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jbZmienStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addComponent(jbAnulujZam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbShowZam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jcbStatusZamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpZamowieniaLayout.setVerticalGroup(
            jpZamowieniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addGroup(jpZamowieniaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbShowZam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbAnulujZam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbZmienStatus)
                .addGap(18, 18, 18)
                .addComponent(jcbStatusZamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtpMainHud.addTab("Zamowienia", jpZamowienia);

        jpUsers.setBackground(new java.awt.Color(44, 62, 80));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Login", "Imie", "Nazwisko", "Ulica", "Miasto", "Kod pocztowy", "Telefon", "Email", "Rola"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jbUzytkownicy.setBackground(new java.awt.Color(108, 122, 137));
        jbUzytkownicy.setText("Użytkownicy");
        jbUzytkownicy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUzytkownicyActionPerformed(evt);
            }
        });

        jbModyfikacja.setBackground(new java.awt.Color(108, 122, 137));
        jbModyfikacja.setText("Modyfikuj");
        jbModyfikacja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbModyfikacjaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpUsersLayout = new javax.swing.GroupLayout(jpUsers);
        jpUsers.setLayout(jpUsersLayout);
        jpUsersLayout.setHorizontalGroup(
            jpUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbModyfikacja, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(jbUzytkownicy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpUsersLayout.setVerticalGroup(
            jpUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbUzytkownicy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbModyfikacja)
                .addContainerGap(608, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jtpMainHud.addTab("Użytkownicy", jpUsers);

        javax.swing.GroupLayout jpMainAppLayout = new javax.swing.GroupLayout(jpMainApp);
        jpMainApp.setLayout(jpMainAppLayout);
        jpMainAppLayout.setHorizontalGroup(
            jpMainAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainAppLayout.createSequentialGroup()
                .addComponent(jtpMainHud)
                .addContainerGap())
        );
        jpMainAppLayout.setVerticalGroup(
            jpMainAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainAppLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jtpMainHud, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1196, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 15, Short.MAX_VALUE)
                    .addComponent(jpMainApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 15, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(jpMainApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBKsiazkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBKsiazkiActionPerformed
        show_book();
    }//GEN-LAST:event_jBKsiazkiActionPerformed

    private void jbUzytkownicyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUzytkownicyActionPerformed
        show_user();
    }//GEN-LAST:event_jbUzytkownicyActionPerformed

    private void jbNowaKsiazkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNowaKsiazkaActionPerformed
        DodajKsiazke dodaj = new DodajKsiazke();
        dodaj.setVisible(true);
        dodaj.setLocationRelativeTo(null);
    }//GEN-LAST:event_jbNowaKsiazkaActionPerformed

    private void jbZamowienieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbZamowienieActionPerformed
        
        jdZamowienie.setVisible(true);
        jdZamowienie.setSize(990, 503);
        jdZamowienie.setLocationRelativeTo(null);

        jpMainPan2.setVisible(true);
        
        DefaultTableModel model1 = (DefaultTableModel) jTable.getModel();
        Vector wafelek = (Vector) model1.getDataVector().elementAt(jTable.getSelectedRow());
        
        System.out.println(wafelek);
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        model3.addRow(wafelek);
        
    }//GEN-LAST:event_jbZamowienieActionPerformed

        
    private void jbModyfikacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModyfikacjaActionPerformed
        jdModyfikowanie.setVisible(true);
        jpMainPan.setVisible(true);
        jpHeader.setVisible(true);
        jdModyfikowanie.setSize(331, 435);
        
        jdModyfikowanie.setLocationRelativeTo(null);
        
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        Vector polonez = (Vector) model2.getDataVector().elementAt(jTable2.getSelectedRow());
        
        System.out.println(polonez);
        
        jtfLogin.setText("" + polonez.elementAt(1));
        jtfImie.setText("" + polonez.elementAt(2));
        jtfNazwisko.setText("" + polonez.elementAt(3));
        jtfUlica.setText("" + polonez.elementAt(4));
        jtfMiasto.setText("" + polonez.elementAt(5));
        jtfKodPoczt.setText("" + polonez.elementAt(6));
        jtfTelefon.setText("" + polonez.elementAt(7));
        jtfEmail.setText("" + polonez.elementAt(8));

        
    }//GEN-LAST:event_jbModyfikacjaActionPerformed

    private void jbModyfikujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbModyfikujActionPerformed
        int updateQuery = 0;
        
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        Vector polonez = (Vector) model2.getDataVector().elementAt(jTable2.getSelectedRow());
        
        int id = (int) polonez.elementAt(0);
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            PreparedStatement pstatement = con.prepareStatement("UPDATE uzytkownik SET login = ? , imie = ? , nazwisko = ? , ulica = ? , miasto = ? , kod_pocztowy = ? , telefon = ? , email = ? WHERE id_uzytkownik = " + id + ";");
            pstatement.setString(1, jtfLogin.getText());
            pstatement.setString(2, jtfImie.getText());
            pstatement.setString(3, jtfNazwisko.getText());
            pstatement.setString(4, jtfUlica.getText());
            pstatement.setString(5, jtfMiasto.getText());
            pstatement.setString(6, jtfKodPoczt.getText());
            pstatement.setString(7, jtfTelefon.getText());
            pstatement.setString(8, jtfEmail.getText());

            updateQuery = pstatement.executeUpdate();

            if (updateQuery > 0) {
                JOptionPane.showMessageDialog(null, "Zmodyfikowano użytkownika", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
            }else
            {
                JOptionPane.showMessageDialog(null, "Błąd!!!", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        jdModyfikowanie.setVisible(false);
    }//GEN-LAST:event_jbModyfikujActionPerformed

    private void jbAnulujModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnulujModActionPerformed
        JOptionPane.showMessageDialog(null, "Anulowanie", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
        jdModyfikowanie.setVisible(false);
    }//GEN-LAST:event_jbAnulujModActionPerformed

    private void jbAnulujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnulujActionPerformed
        jdZamowienie.setVisible(false);
        
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        
        model3.setRowCount(0);
    }//GEN-LAST:event_jbAnulujActionPerformed

    private void jbZamowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbZamowActionPerformed
        int updateQuery = 0;
        System.out.println(getUserID());
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            PreparedStatement pstatement = con.prepareStatement("insert into zamowienie(id_uzytkownik,id_ksiazka,data_wypoz) values ( ? , ? , ? )"); 
            pstatement.setString(1, "" + getUserID());
            pstatement.setString(2, "" + model3.getValueAt(0, 0));
            pstatement.setString(3, "" + new Date());
            
            updateQuery = pstatement.executeUpdate();
            
             if (updateQuery > 0) {
                //JOptionPane.showMessageDialog(null, "Dodano nową książkę", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
                jdZamowienie.setVisible(false);
            }else{
                 JOptionPane.showMessageDialog(null, "Błąd!!!", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
             }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jbZamowActionPerformed

    private void jbShowZamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbShowZamActionPerformed
        show_zamowienia();
    }//GEN-LAST:event_jbShowZamActionPerformed

    private void jbAnulujZamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnulujZamActionPerformed
        String idZam;
        int updateQuery = 0;
        DefaultTableModel model = (DefaultTableModel) jtZamowienia.getModel();
        Vector wybor = (Vector) model.getDataVector().elementAt(jtZamowienia.getSelectedRow());
        idZam = "" + wybor.elementAt(0);
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            PreparedStatement pst = con.prepareStatement("delete from zamowienie where id_zam = ? ");
            pst.setString(1, idZam);
            
            updateQuery = pst.executeUpdate();
            if (updateQuery > 0) {
                System.out.println("Zamowienie " + idZam + " anuleowane.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbAnulujZamActionPerformed

    private void jcbStatusZamowieniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbStatusZamowieniaActionPerformed

    }//GEN-LAST:event_jcbStatusZamowieniaActionPerformed

    private void jbZmienStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbZmienStatusActionPerformed
       String idZam, statusZam;
        int updateQuery = 0;
        DefaultTableModel model = (DefaultTableModel) jtZamowienia.getModel();
        Vector wybor = (Vector) model.getDataVector().elementAt(jtZamowienia.getSelectedRow());
        idZam = "" + wybor.elementAt(0);
        statusZam = "" + jcbStatusZamowienia.getSelectedItem();
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka", "maciej", "maciej123");
            PreparedStatement pst = con.prepareStatement("update zamowienie set status_zam = ? where id_zam = " + idZam + ";");
            pst.setString(1, statusZam);
            
            updateQuery = pst.executeUpdate();
            if (updateQuery > 0) {
                System.out.println("Zamowienie " + idZam + "ma teraz status: " + statusZam);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbZmienStatusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Biblioteka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Biblioteka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Biblioteka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Biblioteka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteka().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBKsiazki;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jbAnuluj;
    private javax.swing.JButton jbAnulujMod;
    private javax.swing.JButton jbAnulujZam;
    private javax.swing.JButton jbModyfikacja;
    private javax.swing.JButton jbModyfikuj;
    private javax.swing.JButton jbNowaKsiazka;
    private javax.swing.JButton jbShowZam;
    private javax.swing.JButton jbUzytkownicy;
    private javax.swing.JButton jbZamow;
    private javax.swing.JButton jbZamowienie;
    private javax.swing.JButton jbZmienStatus;
    private javax.swing.JComboBox<String> jcbStatusZamowienia;
    private javax.swing.JDialog jdModyfikowanie;
    private javax.swing.JDialog jdZamowienie;
    private javax.swing.JPanel jpHeader;
    private javax.swing.JPanel jpKsiazki;
    private javax.swing.JPanel jpMainApp;
    private javax.swing.JPanel jpMainPan;
    private javax.swing.JPanel jpMainPan2;
    private javax.swing.JPanel jpUsers;
    private javax.swing.JPanel jpZamowienia;
    private javax.swing.JTable jtZamowienia;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfImie;
    private javax.swing.JTextField jtfKodPoczt;
    private javax.swing.JTextField jtfLogin;
    private javax.swing.JTextField jtfMiasto;
    private javax.swing.JTextField jtfNazwisko;
    private javax.swing.JTextField jtfTelefon;
    private javax.swing.JTextField jtfUlica;
    private javax.swing.JTabbedPane jtpMainHud;
    // End of variables declaration//GEN-END:variables
}
