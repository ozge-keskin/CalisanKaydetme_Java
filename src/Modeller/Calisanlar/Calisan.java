package Modeller.Calisanlar;

import Modeller.Departmanlar.BilisimTeklonojileriDepartmani;
import Modeller.Departmanlar.Departman;
import Veritabani.Calisanlar;

public class Calisan {
    private String calisanId;
    private String adSoyad;
    private int maas;
    private Departman departman;
    private String isimKodu = "";

    public Calisan(String adSoyad, int maas, String departmanKodu) {
        this.adSoyad = adSoyad;
        this.maas = maas;
        setDepartman(departmanKodu);
        setCalisanId(); // constructor çalıştığında, aşağıda tanımlayacağınız bu metod vasıtasıyla tekil bi ID alacak...
        // Örn: Şirkette 257 calisan var, Bilişim teklonojileri departmaninda Mehmet Ali Bulut kaydedilecek olsun,
        // Mehmet Ali icin ID 'BTD258MAB' olmalıdır.
        Calisanlar.addACalisan(this);
    }

    // Terminalden girilen calisanin departman koduna göre, gerekli departman set edilmelidir.
    // Çalışan sınıfının Constructor 'ı main'de tanımlı. Program çalıştığında, terminalden gireceğimiz
    // departman kodu, buradaki metod (setDepartman) vasıtasıyla departman listesinin tümünü dolaşıp (foreach)
    // getDepartmanKodu() ile bulunan sonuçlardan biriyle aynı mı? Diye bakıyoruz...
    // Aynıysa, "terminalden girilen çalışanı bu departmana set et" demektir...
    private void setDepartman(String departmanKodu) {
        for (Departman departman : Veritabani.Departmanlar.getDepartmanList()) {
            if (departman.getDepartmanKodu().equals(departmanKodu)) {
                this.departman = departman;
                break;
                 /*
            İpucu: Departman listesinin (Veritabani.Departmanlar.DepartmanList) içerisindeki departmanların kodları var,
        bu kodlari donguye tutmak ise yarayabilir.
       */
            }
        }
    }

    private void setCalisanId() {
        String departmanKodu = departman.getDepartmanKodu();
        int calisanSayisi = Calisanlar.getCalisanList().size() + 1;
        String isimKodu = getCalisanIsimKodu();
        this.calisanId = departmanKodu + calisanSayisi + isimKodu;
         /*
            İpucu: Detayli anlatim CalisanKaydetmeProjesiTanıtım.txt içerisinde.
         */
    }

    // Calisanin ID sinin sonuna isim kodu eklenmesi için, ismi parçalayan bir method.
    private String getCalisanIsimKodu() {
        String[] isimParcalari = adSoyad.split(" ");
        String isimKodu="";
        for (String isimParcasi : isimParcalari) {
            isimKodu += isimParcasi.charAt(0);
        }

        /* Basit string metodlari ise isinize cok yarayacaktir fakat dinamik olmasina dikkat edelim... Mesela 2 isim bir
            soyisim girildiğinde hata vermesin.  */
        return isimKodu;
    }

    // Calisanin id sini almak icin basit getter method
    public String getCalisanId() {
        return calisanId;
    }
    // Calisanin departmanini almak icin basit getter method
    public Departman getDepartman() {
        return departman;
    }
    // Departman adini verebilmek için bir method
    public String getDepartmanAdi() {

         /*
                İpucu: Departman Kodu YD ise departman adi Yonetim Departmani olarak kaydedilmelidir.
         */

            return departman.getDepartmanAdi();

    }

    public static void zamYap(String calisanId) {
        for (Calisan calisan : Calisanlar.getCalisanList()) {
            if (calisan.getCalisanId().equals(calisanId)) {
                double zamOrani = calisan.getDepartman().getZamOrani();
                int eskiMaas = calisan.getMaas();
                int yeniMaas = (int) (eskiMaas + (eskiMaas * zamOrani / 100));
                calisan.setMaas(yeniMaas);
                break;
            }
        }
    }

    private void setMaas(int yeniMaas) {
        this.maas = yeniMaas;
    }

    private int getMaas() {
        return maas;
    }

    @Override
    public String toString() {
        return "Calisan ID : '" + calisanId
                + "', Isim Soyisim : '" + adSoyad +
                "', Maas : '" + maas +
                "', Departman : '" + departman.getDepartmanKodu() + "'";
    }
}