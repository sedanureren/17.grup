package paket;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App {
	
	public void prog() throws FileNotFoundException {

        Queue kuyruk=new Queue();
        
        int don =kuyruk.donguSayi();
        int[] ilk = {-1,-1,-1,-1,0};
        ArrayList<Process> gercekZaman = new ArrayList<Process>();
        ArrayList<Process> kullanici = new ArrayList<Process>();
        ArrayList<ArrayList> gecmisZaman = new ArrayList<ArrayList>();
        Process isletilen = new Process(ilk);
        ArrayList<Process> al = kuyruk.object();
        for(int sn=0; sn<don ; sn++){        
            gecmisZaman = kuyruk.kuyrukKontrol(kuyruk.gelisZamaniBul(al,sn), gercekZaman, kullanici);
            gercekZaman = gecmisZaman.get(0);   
            kullanici = gecmisZaman.get(1);     

            if (!gercekZaman.isEmpty())
            {
                isletilen = kuyruk.gercekzaman(gercekZaman,isletilen,sn);
            }
            if (gercekZaman.isEmpty())
            {
                isletilen = kuyruk.kullaniciKuyruk(kullanici,isletilen,sn);
                
                kullanici.clear();
            }
        }
    }

}
