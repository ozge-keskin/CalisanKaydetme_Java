package Veritabani;

import Modeller.Calisanlar.Calisan;
import Modeller.Departmanlar.BilisimTeklonojileriDepartmani;
import Modeller.Departmanlar.YonetimDepartmani;

import java.util.ArrayList;

public class Calisanlar {


    // Buradaki calisanList static cünkü proje calismaya basladiği anda oluşması lazım. Bunu bir veritabani
    // gibi görebiliriz. Calisanlarimizin hepsi bu liste icerisinde yer alacak.
    private static ArrayList<Calisan> calisanList = new ArrayList<>();

    // Calisanlari almak icin basit bir getter method
    public static ArrayList<Calisan> getCalisanList() {
        return calisanList;
    }

    // Bir çalışan eklemek için gerekli method.
    public static void addACalisan(Calisan calisan) {
        calisanList.add(calisan);


    }


    // Bir çalışan silmek için gerekli method.
    public static void deleteACalisanWithId(String calisanId) {
        for (Calisan calisan : calisanList){
            if (calisan.getCalisanId().equals(calisanId)){
                calisanList.remove(calisan);
                break;
            }
        }

    }

    // Departman kodu verilerek, konsola sadece o departmanda calisanlari yazdirmak için
    public static void printDepartmandakiCalisanlar(String departmanKodu) {
        for (Calisan calisan : calisanList){
            if (calisan.getDepartman().getDepartmanKodu().equals(departmanKodu)){
                System.out.println(calisan);
            }
        }

    }


    // Calisanlari konsola yazdirmak için
    public static void printCalisanlar() {
        for(Calisan calisan : calisanList){
            System.out.println(calisan);
        }

    }


}