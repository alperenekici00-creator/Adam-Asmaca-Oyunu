package adamasmaca;

import java.awt.EventQueue;

/**
 * Adam Asmaca Oyunu - Giriş Noktası
 * Programlama 2 Ödevi
 */
public class Main {

    public static void main(String[] args) {
        // Swing EDT üzerinde GUI güvenli şekilde başlatılıyor (Ders: EDP / EDT)
        EventQueue.invokeLater(() -> {
           new SifreEkran().setVisible(true);
        });    }

}
