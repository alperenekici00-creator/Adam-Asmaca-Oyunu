package adamasmaca;



import java.io.*;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.*;



/**


*/

public class DosyaYonetici {



public static final String ANA_KLASOR = "C:\\P2Oyun";

public static final String RESIMLER_KLASOR = "C:\\P2Oyun\\Resimler";

public static final String TXT_KLASOR = "C:\\P2Oyun\\TXTDosyalar";

public static final String KELIMELER_DOSYA = TXT_KLASOR + "\\kelimeler.txt";

public static final String SIFRE_DOSYA = TXT_KLASOR + "\\sifre.txt";

public static final String LOG_DOSYA = TXT_KLASOR + "\\log.txt";

public static final String OYUNLAR_DOSYA = TXT_KLASOR + "\\oyunlar.txt";

public static final String KELIMELER_GECMIS = TXT_KLASOR + "\\gecmis.txt";



public static final DateTimeFormatter TARIH_FORMAT =

DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");



public static void klasorleriOlustur() {

new File(RESIMLER_KLASOR).mkdirs();

new File(TXT_KLASOR).mkdirs();

}



public static List<String> dosyaOku(String yol) {

List<String> satirlar = new ArrayList<>();

File f = new File(yol);

if (!f.exists()) return satirlar;

try (BufferedReader br = new BufferedReader(

new InputStreamReader(new FileInputStream(f), "UTF-8"))) {

String satir;

while ((satir = br.readLine()) != null) {

if (!satir.trim().isEmpty()) satirlar.add(satir.trim());

}

} catch (IOException e) {

e.printStackTrace();

}

return satirlar;

}



public static void dosyayaEkle(String yol, String icerik) {

try (BufferedWriter bw = new BufferedWriter(

new OutputStreamWriter(new FileOutputStream(yol, true), "UTF-8"))) {

bw.write(icerik);

bw.newLine();

} catch (IOException e) {

e.printStackTrace();

}

}



public static void dosyayaYaz(String yol, String icerik) {

try (BufferedWriter bw = new BufferedWriter(

new OutputStreamWriter(new FileOutputStream(yol, false), "UTF-8"))) {

bw.write(icerik);

} catch (IOException e) {

e.printStackTrace();

}

}



public static void dosyayiTemizle(String yol) {

dosyayaYaz(yol, "");

}



public static String sifreOku() {

List<String> satirlar = dosyaOku(SIFRE_DOSYA);

return satirlar.isEmpty() ? null : satirlar.get(0);

}



public static void sifreKaydet(String sifre) {

dosyayaYaz(SIFRE_DOSYA, sifre);

}



public static boolean sifreVarMi() {

String s = sifreOku();

return s != null && !s.isEmpty();

}



public static void logKaydet(String etiket) {

String zaman = LocalDateTime.now().format(TARIH_FORMAT);

dosyayaEkle(LOG_DOSYA, etiket + " | " + zaman);

}



public static void oyunKaydet(String sonuc, long sureSaniye) {

String zaman = LocalDateTime.now().format(TARIH_FORMAT);

dosyayaEkle(OYUNLAR_DOSYA, zaman + " | " + sonuc + " | " + sureSaniye + " saniye");

}



public static String rastgeleKelime() {

List<String> kelimeler = dosyaOku(KELIMELER_DOSYA);

if (kelimeler.isEmpty()) return "ALGORITMA";

Random rand = new Random();

String kelime = kelimeler.get(rand.nextInt(kelimeler.size()));

return kelime.toUpperCase(new Locale("tr", "TR"));

}



public static void kelimelerOlustur() {

File f = new File(KELIMELER_DOSYA);

if (!f.exists()) {

String[] varsayilan = {

"DIRENCLER","KONDANSATOR","TRANSISTOR","GERILIM","KAPASITANS","OSILOSOP",

"MANTIKKAPISI","NANDKAPISI","NORKAPISI","XORKAPISI","KARNAUGH","KODLAYICI",

"PROGRAMLAMA","KAPSULLEME","KALITIM","ARAYUZLER","SINIFLAR","NESNELER",

"VERITABANI","NORMALFORM","TABLOSEMA","BIRINCILANAHTAR","YABANCILANAHTAR","INDEKSLEME",

"DETERMINISTIK","OTOMAT","ALFABELER","GRAMERLER","SONDURUM","BASLANGIC"

};

StringBuilder sb = new StringBuilder();

for (String k : varsayilan) sb.append(k).append("\n");

dosyayaYaz(KELIMELER_DOSYA, sb.toString().trim());

}

}

public static void gecmisKaydet(String kelime, String sonuc) {

dosyayaEkle(KELIMELER_GECMIS, kelime + " | " + sonuc);

}

} 

