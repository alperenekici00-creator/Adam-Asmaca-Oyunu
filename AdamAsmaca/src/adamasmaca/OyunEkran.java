/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adamasmaca;

/**
 *
 * @author msı
 */

public class OyunEkran extends javax.swing.JFrame {
private int yanlisSayisi = 0;
private boolean oyunBitti = false;
private String hedefKelime = "";
private boolean[] tahminEdildi;
private java.util.Set<Character> kullanilanHarfler;
private java.util.List<String> yanlisKelimeler;
private long gecenSaniye = 0;
private javax.swing.Timer sayac;
private javax.swing.JLabel[] harfLabellar;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OyunEkran.class.getName());

    /**
    
     */public OyunEkran() {
        initComponents();
       
        java.awt.Color anaArkaPlan = new java.awt.Color(74, 55, 40); 
        java.awt.Color panelArkaPlan = new java.awt.Color(101, 77, 58); 
        java.awt.Color yaziRengi = new java.awt.Color(245, 240, 225); 
        java.awt.Color canliYesil = new java.awt.Color(40, 167, 69); 
        java.awt.Color neonSari = new java.awt.Color(255, 193, 7); 

        getContentPane().setBackground(anaArkaPlan);
        
        AdamAsmaca.setBackground(panelArkaPlan);
        AdamAsmaca.setForeground(yaziRengi);

        pnlOyun.setBackground(anaArkaPlan);
        pnlSkorlar.setBackground(anaArkaPlan);
        pnlLoglar.setBackground(anaArkaPlan);
        pnlHarfler.setBackground(panelArkaPlan);
        
        lblDurum.setForeground(neonSari);
        lblDurum.setBackground(panelArkaPlan);
        lblDurum.setOpaque(true);
        lblYanlis.setForeground(new java.awt.Color(220, 53, 69)); 
        lblSure.setForeground(new java.awt.Color(23, 162, 184)); 
        lblResim.setBackground(panelArkaPlan);
        lblResim.setOpaque(true);
        lblHarfT.setForeground(yaziRengi);
        lblKelimeT.setForeground(yaziRengi);
        
        txtHarf.setBackground(panelArkaPlan);
        txtHarf.setForeground(yaziRengi);
        txtHarf.setCaretColor(yaziRengi);
        txtKelime.setBackground(panelArkaPlan);
        txtKelime.setForeground(yaziRengi);
        txtKelime.setCaretColor(yaziRengi);

        btnHarf.setBackground(canliYesil);
        btnHarf.setForeground(yaziRengi);
        btnKelime.setBackground(new java.awt.Color(253, 126, 20)); 
        btnKelime.setForeground(yaziRengi);
        btnYeniOyun.setBackground(new java.awt.Color(108, 117, 125)); 
        btnYeniOyun.setForeground(yaziRengi);
        btnTemizleSkor.setBackground(new java.awt.Color(220, 53, 69));
        btnTemizleSkor.setForeground(yaziRengi);
        btnTemizleLog.setBackground(new java.awt.Color(220, 53, 69));
        btnTemizleLog.setForeground(yaziRengi);
        
        tblSkorlar.setBackground(panelArkaPlan);
        tblSkorlar.setForeground(yaziRengi);
        tblSkorlar.setGridColor(anaArkaPlan);
        tblSkorlar.getTableHeader().setBackground(anaArkaPlan);
        tblSkorlar.getTableHeader().setForeground(neonSari);
        
        jScrollPane2.getViewport().setBackground(panelArkaPlan);
        jScrollPane1.getViewport().setBackground(panelArkaPlan);

        tblLoglar.getTableHeader().setBackground(anaArkaPlan);
        tblLoglar.getTableHeader().setForeground(neonSari);
        tblLoglar.setBackground(panelArkaPlan);
        tblLoglar.setForeground(yaziRengi);
        tblLoglar.setGridColor(anaArkaPlan);
        
        this.setTitle("Kelime Keşif Dünyası"); 
        this.setSize(920, 670);
        this.setLocationRelativeTo(null);
        
       
        AdamAsmaca.setTitleAt(0, "Kelime Arenası");
        AdamAsmaca.setTitleAt(1, "Başarı Tablosu");
        AdamAsmaca.setTitleAt(2, "Sistem Günlükleri");

        AdamAsmaca.addChangeListener(e -> {
            int idx = AdamAsmaca.getSelectedIndex();
            if (idx == 1) gridVerileriniGuncelle(tblSkorlar, DosyaYonetici.OYUNLAR_DOSYA, "skor");
            else if (idx == 2) gridVerileriniGuncelle(tblLoglar, DosyaYonetici.LOG_DOSYA, "log");
            else if (idx == 3) istatistikGuncelle();
        });
        yeniOyun();
    }
   
   

        private void yeniOyun() {
    if (sayac != null) sayac.stop();
    hedefKelime = DosyaYonetici.rastgeleKelime();
    tahminEdildi = new boolean[hedefKelime.length()];
    kullanilanHarfler = new java.util.LinkedHashSet<>();
    yanlisKelimeler = new java.util.ArrayList<>();
    yanlisSayisi = 0;
    oyunBitti = false;
    gecenSaniye = 0;
    txtHarf.setEnabled(true);
    txtKelime.setEnabled(true);
    txtHarf.setText("");
    txtKelime.setText("");
    lblDurum.setText("Oyun Başladı!");
    lblYanlis.setText("Yanlış: 0 / 11");
    lblSure.setText("Süre: 0 sn");
    harfPaneliOlustur();
    resimGuncelle();
    sayac = new javax.swing.Timer(1000, e -> {
        gecenSaniye++;
        lblSure.setText("Süre: " + gecenSaniye + " sn");
    });
    sayac.start();
}

private void harfPaneliOlustur() {
   pnlHarfler.removeAll();
    harfLabellar = new javax.swing.JLabel[hedefKelime.length()];
    for (int i = 0; i < hedefKelime.length(); i++) {
        harfLabellar[i] = new javax.swing.JLabel("?"); 
        harfLabellar[i].setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 24)); 
        harfLabellar[i].setForeground(new java.awt.Color(23, 162, 184)); 
        harfLabellar[i].setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(23, 162, 184)));
        harfLabellar[i].setPreferredSize(new java.awt.Dimension(34, 38));
        harfLabellar[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlHarfler.add(harfLabellar[i]);
    }
    pnlHarfler.revalidate();
    pnlHarfler.repaint();
}

private void resimGuncelle() {
    int no = Math.min(yanlisSayisi, 11);
    String yol = DosyaYonetici.RESIMLER_KLASOR + "\\" + no + ".jpg";
    java.io.File f = new java.io.File(yol);
    if (f.exists()) {
        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(yol);
        java.awt.Image scaled = icon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        lblResim.setIcon(new javax.swing.ImageIcon(scaled));
        lblResim.setText("");
    } else {
        lblResim.setIcon(null);
        lblResim.setText("Resim yok: " + no + ".jpg");
    }
}

private void harfTahmin() {
    if (oyunBitti) return;
    String girdi = txtHarf.getText().trim().toUpperCase(new java.util.Locale("tr", "TR"));
    if (girdi.matches(".*[ÇĞİÖŞÜçğışöşü].*")) {
    javax.swing.JOptionPane.showMessageDialog(this, 
        "Lütfen Türkçe karakter kullanmayın!\n(ç→c, ğ→g, ı→i, ö→o, ş→s, ü→u)", 
        "Uyarı", 
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
    txtHarf.setText("");
    if (girdi.isEmpty() || girdi.length() != 1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Tek harf girin!", "Uyarı", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    char harf = girdi.charAt(0);
    if (kullanilanHarfler.contains(harf)) {
        javax.swing.JOptionPane.showMessageDialog(this, "Bu harfi zaten denediniz!", "Uyarı", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    kullanilanHarfler.add(harf);
    boolean bulundu = false;
    for (int i = 0; i < hedefKelime.length(); i++) {
        if (hedefKelime.charAt(i) == harf) {
            tahminEdildi[i] = true;
            harfLabellar[i].setText(String.valueOf(harf));
            harfLabellar[i].setForeground(new java.awt.Color(0, 150, 0));
            bulundu = true;
        }
    }
    if (!bulundu) yanlisSayisi++;
    resimGuncelle();
    durumGuncelle();
    kazandiMiKontrol();
}

private void kelimeTahmin() {
    if (oyunBitti) return;
    String girdi = txtKelime.getText().trim().toUpperCase(new java.util.Locale("tr", "TR"));
    if (girdi.matches(".*[ÇĞİÖŞÜçğışöşü].*")) {
    javax.swing.JOptionPane.showMessageDialog(this, 
        "Lütfen Türkçe karakter kullanmayın!\n(ç→c, ğ→g, ı→i, ö→o, ş→s, ü→u)", 
        "Uyarı", 
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
    txtKelime.setText("");
    if (girdi.isEmpty()) return;
    if (girdi.length() < 6) {
    javax.swing.JOptionPane.showMessageDialog(this, 
        "Kelimeler en az 6 harfli!", 
        "Uyarı", 
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}
    if (girdi.equals(hedefKelime)) {
        for (int i = 0; i < hedefKelime.length(); i++) {
            tahminEdildi[i] = true;
            harfLabellar[i].setText(String.valueOf(hedefKelime.charAt(i)));
            harfLabellar[i].setForeground(new java.awt.Color(0, 150, 0));
        }
        kazandiMiKontrol();
    } else {
        yanlisSayisi++;
        yanlisKelimeler.add(girdi);
        resimGuncelle();
        durumGuncelle();
    }
}

private void durumGuncelle() {
    lblYanlis.setText("Yanlış: " + yanlisSayisi + " / 11");
    if (yanlisSayisi >= 11) oyunBitti();
}


    private void kazandiMiKontrol() {
    for (boolean b : tahminEdildi) if (!b) { durumGuncelle(); return; }
    sayac.stop();
    oyunBitti = true;
    txtHarf.setEnabled(false);
    txtKelime.setEnabled(false);
    lblDurum.setText("Tebrikler! " + hedefKelime);
    
    
    DosyaYonetici.oyunKaydet("KAZANDI", gecenSaniye); 
    
    javax.swing.JOptionPane.showMessageDialog(this, "Tebrikler! Kelime: " + hedefKelime + "\nSüre: " + gecenSaniye + " sn", "Kazandınız! 🎉", javax.swing.JOptionPane.INFORMATION_MESSAGE);
}
    


private void oyunBitti() {
   sayac.stop();
    oyunBitti = true;
    txtHarf.setEnabled(false);
    txtKelime.setEnabled(false);
    for (int i = 0; i < hedefKelime.length(); i++) {
        harfLabellar[i].setText(String.valueOf(hedefKelime.charAt(i)));
        harfLabellar[i].setForeground(new java.awt.Color(220, 53, 69));
    }
    lblDurum.setText("Oyun Sona Erdi! Kelime: " + hedefKelime);
    
    
    DosyaYonetici.oyunKaydet("KAYBETTI", gecenSaniye);
    
    javax.swing.JOptionPane.showMessageDialog(this, 
    "Maalesef tüm tahmin haklarınız tükendi.\nAranan Kelime: " + hedefKelime + "\nToplam Süre: " + gecenSaniye + " saniye", 
    "Oyun Tamamlandı", 
    javax.swing.JOptionPane.ERROR_MESSAGE);

}
private void istatistikGuncelle() {
        java.util.List<String> satirlar = DosyaYonetici.dosyaOku(DosyaYonetici.OYUNLAR_DOSYA);
        int toplamOyun = 0;
        int kazanma = 0;
        int kaybetme = 0;
        long enKisaSure = Long.MAX_VALUE;

        for (String satir : satirlar) {
            String[] parcalar = satir.split("\\|");
            if (parcalar.length >= 3) {
                toplamOyun++;
                String sonuc = parcalar[1].trim();
                long sure = 0;
                
                try {
                    sure = Long.parseLong(parcalar[2].trim().replace("sn", "").trim());
                } catch(Exception e) {}

                if (sonuc.equals("KAZANDI")) {
                    kazanma++;
                    if (sure < enKisaSure) enKisaSure = sure;
                } else if (sonuc.equals("KAYBETTI")) {
                    kaybetme++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("           SİSTEM KULLANIM İSTATİSTİKLERİ\n");
        sb.append("==================================================\n\n");
        sb.append(" [ • ] Toplam Oynanan Oyun : ").append(toplamOyun).append("\n");
        sb.append(" [ • ] Başarılı Sonuç (Kazanma) : ").append(kazanma).append("\n");
        sb.append(" [ • ] Başarısız Sonuç (Kaybetme) : ").append(kaybetme).append("\n");
        
        if (toplamOyun > 0) {
            int oran = (kazanma * 100) / toplamOyun;
            sb.append(" [ • ] Genel Başarı Oranı : %").append(oran).append("\n");
        } else {
            sb.append(" [ • ] Genel Başarı Oranı : Henüz oyun oynanmadı\n");
        }
        
        if (enKisaSure != Long.MAX_VALUE) {
            sb.append(" [ • ] En Hızlı Başarı Süresi : ").append(enKisaSure).append(" saniye\n");
        }

        // Lüks kahverengi temaya uygun arka plan ve yazı ayarları (Kodla yapıyoruz ki uğraşma)
        txtIstatistik.setBackground(new java.awt.Color(101, 77, 58)); 
        txtIstatistik.setForeground(new java.awt.Color(245, 240, 225));
        txtIstatistik.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 14));

        // Metni ekrana basıyor
        txtIstatistik.setText(sb.toString());
        txtIstatistik.setCaretPosition(0); 
    }

private void gridVerileriniGuncelle(javax.swing.JTable tablo, String dosyaYolu, String tur) {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tablo.getModel();
    model.setRowCount(0);
    java.util.List<String> satirlar = DosyaYonetici.dosyaOku(dosyaYolu);
    int no = 1;
    for (String satir : satirlar) {
        String[] p = satir.split("\\|");
        if (tur.equals("skor") && p.length >= 3) {
            model.addRow(new Object[]{no++, p[0].trim(), p[1].trim(), p[2].trim()});
        } else if (tur.equals("log") && p.length >= 2) {
            model.addRow(new Object[]{no++, p[0].trim(), p[1].trim()});
        }
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

        AdamAsmaca = new javax.swing.JTabbedPane();
        pnlOyun = new javax.swing.JPanel();
        lblResim = new javax.swing.JLabel();
        lblYanlis = new javax.swing.JLabel();
        lblSure = new javax.swing.JLabel();
        lblDurum = new javax.swing.JLabel();
        pnlHarfler = new javax.swing.JPanel();
        lblHarfT = new javax.swing.JLabel();
        lblKelimeT = new javax.swing.JLabel();
        txtHarf = new javax.swing.JTextField();
        btnHarf = new javax.swing.JButton();
        txtKelime = new javax.swing.JTextField();
        btnKelime = new javax.swing.JButton();
        btnYeniOyun = new javax.swing.JButton();
        pnlLoglar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoglar = new javax.swing.JTable();
        btnTemizleLog = new javax.swing.JButton();
        pnlSkorlar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSkorlar = new javax.swing.JTable();
        btnTemizleSkor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtIstatistik = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniBasla = new javax.swing.JMenuItem();
        mniYeniden = new javax.swing.JMenuItem();
        mniCikis = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniHakkında = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblYanlis.setText("Yanlış : 0/11");

        lblSure.setText("Süre : 0 sn");

        lblDurum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDurum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDurum.setText("Oyun Başladı!");
        lblDurum.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblHarfT.setText("Harf Tahmini : ");

        lblKelimeT.setText("Kelime Tahmini : ");

        txtHarf.addActionListener(this::txtHarfActionPerformed);

        btnHarf.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHarf.setText("Harf Dene ");
        btnHarf.addActionListener(this::btnHarfActionPerformed);

        txtKelime.addActionListener(this::txtKelimeActionPerformed);

        btnKelime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKelime.setText("Kelime Dene");
        btnKelime.addActionListener(this::btnKelimeActionPerformed);

        btnYeniOyun.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnYeniOyun.setText("Yeni Oyun ");
        btnYeniOyun.addActionListener(this::btnYeniOyunActionPerformed);

        javax.swing.GroupLayout pnlOyunLayout = new javax.swing.GroupLayout(pnlOyun);
        pnlOyun.setLayout(pnlOyunLayout);
        pnlOyunLayout.setHorizontalGroup(
            pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOyunLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addComponent(lblResim, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(lblYanlis, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(lblSure, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKelimeT, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHarfT, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblDurum, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                .addComponent(pnlHarfler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnYeniOyun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOyunLayout.createSequentialGroup()
                                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHarf, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(txtKelime))
                                .addGap(26, 26, 26)
                                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnHarf, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(btnKelime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(387, Short.MAX_VALUE))
        );
        pnlOyunLayout.setVerticalGroup(
            pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOyunLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addComponent(lblDurum, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlHarfler, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYanlis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSure, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblResim, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHarf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHarfT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOyunLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlOyunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKelime, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKelime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnYeniOyun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOyunLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblKelimeT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))))
        );

        AdamAsmaca.addTab("Kelime Arenası", pnlOyun);

        tblLoglar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblLoglar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "#", "Etiket", "Tarih & Zaman "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblLoglar);

        btnTemizleLog.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTemizleLog.setText("Temizle");
        btnTemizleLog.addActionListener(this::btnTemizleLogActionPerformed);

        javax.swing.GroupLayout pnlLoglarLayout = new javax.swing.GroupLayout(pnlLoglar);
        pnlLoglar.setLayout(pnlLoglarLayout);
        pnlLoglarLayout.setHorizontalGroup(
            pnlLoglarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoglarLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(pnlLoglarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(btnTemizleLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(539, Short.MAX_VALUE))
        );
        pnlLoglarLayout.setVerticalGroup(
            pnlLoglarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoglarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnTemizleLog, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AdamAsmaca.addTab("Başarı Tablosu", pnlLoglar);

        tblSkorlar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        tblSkorlar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Tarih & Saat", "Sonuç", "Süre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSkorlar);

        btnTemizleSkor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTemizleSkor.setText("Temizle");
        btnTemizleSkor.addActionListener(this::btnTemizleSkorActionPerformed);

        javax.swing.GroupLayout pnlSkorlarLayout = new javax.swing.GroupLayout(pnlSkorlar);
        pnlSkorlar.setLayout(pnlSkorlarLayout);
        pnlSkorlarLayout.setHorizontalGroup(
            pnlSkorlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkorlarLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(pnlSkorlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(btnTemizleSkor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(536, Short.MAX_VALUE))
        );
        pnlSkorlarLayout.setVerticalGroup(
            pnlSkorlarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSkorlarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTemizleSkor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        AdamAsmaca.addTab("Sistem Günlükleri", pnlSkorlar);

        txtIstatistik.setEditable(false);
        txtIstatistik.setColumns(20);
        txtIstatistik.setRows(5);
        jScrollPane3.setViewportView(txtIstatistik);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1116, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        AdamAsmaca.addTab("Oyuncu İstatistiği", jPanel1);

        jMenu1.setText("Oyun");

        mniBasla.setText("Oyuna Başla ");
        mniBasla.addActionListener(this::mniBaslaActionPerformed);
        jMenu1.add(mniBasla);

        mniYeniden.setText("Yeniden Başla");
        mniYeniden.addActionListener(this::mniYenidenActionPerformed);
        jMenu1.add(mniYeniden);

        mniCikis.setText("Çıkış");
        mniCikis.addActionListener(this::mniCikisActionPerformed);
        jMenu1.add(mniCikis);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Hakkında");

        mniHakkında.setText("Hakkında");
        mniHakkında.addActionListener(this::mniHakkındaActionPerformed);
        jMenu2.add(mniHakkında);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdamAsmaca)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AdamAsmaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarfActionPerformed
        // TODO add your handling code here:
        harfTahmin();
    }//GEN-LAST:event_btnHarfActionPerformed

    private void mniBaslaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBaslaActionPerformed
        // TODO add your handling code here:
        yeniOyun();
        AdamAsmaca.setSelectedIndex(0);

    }//GEN-LAST:event_mniBaslaActionPerformed

    private void btnKelimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelimeActionPerformed
        // TODO add your handling code here:
        kelimeTahmin();
    }//GEN-LAST:event_btnKelimeActionPerformed

    private void btnYeniOyunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYeniOyunActionPerformed
        // TODO add your handling code here:
        yeniOyun();
    }//GEN-LAST:event_btnYeniOyunActionPerformed

    private void mniYenidenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniYenidenActionPerformed
        // TODO add your handling code here:
        yeniOyun();
        AdamAsmaca.setSelectedIndex(0);
    }//GEN-LAST:event_mniYenidenActionPerformed

    private void mniCikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCikisActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mniCikisActionPerformed

    private void mniHakkındaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHakkındaActionPerformed
        // TODO add your handling code here:
        javax.swing.JOptionPane.showMessageDialog(this,
               "Adam Asmaca Kelime Tahmin Oyunu\n\n"
        + "Programlama Dilleri 2 Dönem Projesi\n"
        + "2025-2026 Akademik Yılı\n\n"
        + "Sistem Altyapısı: Java Swing GUI\n"
        + "Kelimelerin İçeriği: Atölye Ekipmanları\n\n"
        + "Uygulamayı Keyifle Oynamanız Dileğiyle!",
        "Uygulama Hakkında",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_mniHakkındaActionPerformed

    private void txtHarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHarfActionPerformed
        // TODO add your handling code here:
        harfTahmin();
    }//GEN-LAST:event_txtHarfActionPerformed

    private void txtKelimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKelimeActionPerformed
        // TODO add your handling code here:
        kelimeTahmin();
    }//GEN-LAST:event_txtKelimeActionPerformed

    private void btnTemizleSkorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemizleSkorActionPerformed
        // TODO add your handling code here:
        String girilen = javax.swing.JOptionPane.showInputDialog(this, "Şifre:");
        if (girilen != null && girilen.equals(DosyaYonetici.sifreOku())) {
            
            DosyaYonetici.dosyayiTemizle(DosyaYonetici.OYUNLAR_DOSYA);
            gridVerileriniGuncelle(tblSkorlar, DosyaYonetici.OYUNLAR_DOSYA, "skor");
        } else if (girilen != null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Hatalı şifre!");
        }
    
    }//GEN-LAST:event_btnTemizleSkorActionPerformed

    private void btnTemizleLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemizleLogActionPerformed
        String girilen = javax.swing.JOptionPane.showInputDialog(null, "Şifre:");
        if (girilen != null && girilen.equals(DosyaYonetici.sifreOku())) {
            DosyaYonetici.dosyayiTemizle(DosyaYonetici.LOG_DOSYA);
            gridVerileriniGuncelle(tblLoglar, DosyaYonetici.LOG_DOSYA, "log");
        } else if (girilen != null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Hatalı şifre!");
        }

    }//GEN-LAST:event_btnTemizleLogActionPerformed

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
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        logger.log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> new OyunEkran().setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane AdamAsmaca;
    private javax.swing.JButton btnHarf;
    private javax.swing.JButton btnKelime;
    private javax.swing.JButton btnTemizleLog;
    private javax.swing.JButton btnTemizleSkor;
    private javax.swing.JButton btnYeniOyun;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDurum;
    private javax.swing.JLabel lblHarfT;
    private javax.swing.JLabel lblKelimeT;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblSure;
    private javax.swing.JLabel lblYanlis;
    private javax.swing.JMenuItem mniBasla;
    private javax.swing.JMenuItem mniCikis;
    private javax.swing.JMenuItem mniHakkında;
    private javax.swing.JMenuItem mniYeniden;
    private javax.swing.JPanel pnlHarfler;
    private javax.swing.JPanel pnlLoglar;
    private javax.swing.JPanel pnlOyun;
    private javax.swing.JPanel pnlSkorlar;
    private javax.swing.JTable tblLoglar;
    private javax.swing.JTable tblSkorlar;
    private javax.swing.JTextField txtHarf;
    private javax.swing.JTextArea txtIstatistik;
    private javax.swing.JTextField txtKelime;
    // End of variables declaration//GEN-END:variables

}