package paket;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Queue {
	ArrayList<Process> sira1;
	ArrayList<Process> sira2;
	ArrayList<Process> sira3;
	//Kuyruklara ait Arraylist tanimlari
	public Queue() {
		sira1 = new ArrayList<Process>();
		sira2 = new ArrayList<Process>();
		sira3 = new ArrayList<Process>();
		//Kuyruklara ait ArrayList olusturur.
	}
    public static int satirsayisi(File f) throws FileNotFoundException { 
		//Dosyadan satir sayisini bulmak icin yazilmis bir method.
		

		Scanner dosya = new Scanner(f);
		int satirSayisi = 0;

		while (dosya.hasNextLine()) 
		{
			dosya.nextLine();
			satirSayisi++;
		}
		dosya.close();
	return satirSayisi;
	}

	public int donguSayi() throws FileNotFoundException {
		//Bu method dosyadan okunan verileri kullanarak dongu sayisini bulur. 
        //Dongu sayisi dosyadaki islemlerin toplam suresini temsil eder.
		File dosya = new File("giris.txt");

		int counter = satirsayisi(dosya);
		int[][] list = new int[counter][3];
		int donusSayac = 0;
			Scanner okunan = new Scanner(dosya);
		int satir = 0;

		while (okunan.hasNextLine()) {
			String ifade = okunan.nextLine();
			
			String[] arrSplit = ifade.split(", ");
			int listSatir = 0;
			
			for (String arrSplit1 : arrSplit) {
				
				list[satir][listSatir] = Integer.parseInt(arrSplit1);

				listSatir++;

			}
			donusSayac = donusSayac + list[satir][2];
		}
		okunan.close();
		return donusSayac;


	}
	
	public ArrayList<Process> gelisZamaniBul(ArrayList<Process> kuyruk, int sure) {
		// Bu metod, verilen saniye degerine gore kuyruktaki islemleri bir ArrayList'e ekler.
		ArrayList<Process> araci = new ArrayList<Process>();

		for (Process yeni : kuyruk) {
			if (yeni.gelisZaman == sure) {
				araci.add(yeni);
			}
		}

		return araci;
	}

	public ArrayList<Process> object() throws FileNotFoundException {
		// Bu metod, dosyadan okunan verileri kullanarak Process nesneleri olusturur.   
        // Olusturulan nesneler bir ArrayList'e eklenir ve bu ArrayList dondurulur..
		File dosya = new File("giris.txt");
		int donusSayac = 0;
		Scanner dosyaIslem = new Scanner(dosya);
		int satir = satirsayisi(dosya);

		int counter = 0;
		int[][] list = new int[satir][6];

		Process[] is = new Process[satir];

		while (dosyaIslem.hasNextLine()) {
			String satirstring = dosyaIslem.nextLine();

			String[] parca = satirstring.split(", ");
			int dizisatir = 1;
			for (String parca1 : parca) {
				list[counter][dizisatir] = Integer.parseInt(parca1);
				dizisatir++;
			}
			list[counter][0] = counter;

			list[counter][4] = 0;
			list[counter][5] = -1;
			Process islem = new Process(list[counter]);
			is[counter] = islem;
			donusSayac += list[counter][3];
			counter++;
		}
		ArrayList<Process> nesne = new ArrayList<Process>(Arrays.asList(is));
		return nesne;


	}

	

	public ArrayList<ArrayList> kuyrukKontrol(ArrayList<Process> kuyrukSaniye, ArrayList<Process> gercekZaman, ArrayList<Process> kullanici) {
    // Bu metod, kuyrukSaniye ArrayList'indeki islemleri onceliklerine gore kuyruklara ekler.
    // Gercek zaman ve kullanici kuyruklarýnýn son hali, araci ArrayList'ine eklenir ve döndürülür.
        
		for (Process eleman : kuyrukSaniye) 
		{

			switch (eleman.priority ) {
			case 0:
				gercekZaman.add(eleman);
				break;
			default:
				kullanici.add(eleman);
				break;
			}
		}
		ArrayList<ArrayList> lis = new ArrayList<ArrayList>(); 
																		
		lis.add(kullanici);
		lis.add(kullanici);
		lis.set(0, gercekZaman);
		lis.set(1, kullanici);
		return lis;


	}


	public void Yaz(int saniye, int Statament, Process isletilen) 
	{

		switch (Statament) {
		case 0://basladi
			System.out.println(saniye + " sn proses baþladý(id:" + isletilen.Id + "\töncelik:"+ isletilen.priority + "\tkalan süre:" + isletilen.calismaZaman + ")");
			break;
		case 1://yurutuluyor
			System.out.println(saniye + " sn proses yürütülüyor\t(id:" + isletilen.Id + "\töncelik:"+ isletilen.priority  + "\tkalan süre:" + isletilen.calismaZaman + ")");
			break;
		case 2://askida
			System.out.println(saniye + " sn proses askida\t\t(id:" + isletilen.Id + "\töncelik:"+ isletilen.priority  + "\tkalan süre:" + isletilen.calismaZaman+ ")");
			break;
		case 3://sonlandi
			System.out.println(saniye + " sn proses sonlandi\t\t(id:" + isletilen.Id + "\toncelik:"+ isletilen.priority + "\tkalan süre:" + isletilen.calismaZaman + ")");
			break;
		case 4://zamanasimi
			System.out.println(saniye + " sn proses zaman aþýmýna uðradý\t\t(id:" + isletilen.Id + "\töncelik:"+ isletilen.priority  + "\tkalan süre:" + isletilen.calismaZaman + ")");
			break;
		}
	}

	public ArrayList<Process> asimKontrol(ArrayList<Process> list, int saniye)
	{
		 //bu method, zaman asimina ugrayan bir fonksiyon varsa belirler ve siler.
		int i = list.size();
		if (i == 0)
			return list;
		for (int a = 0; a < i; a++) {
			if (saniye - list.get(a).asim == 20 && list.get(a).asim != -1) {
				Yaz(saniye, 4, list.get(a));
				list.remove(a);
				i--;
				if (list.size() == 0) {
					break;
				}
			}
		}
		return list;


	}
	public Process gercekzaman(ArrayList<Process> gercekZaman, Process isletilen, int saniye)
	{
		//Bu metod, gercekZaman kuyrugundaki islemleri isleme alir ve isleme alinan islemlerin onceliklerine gore islemlerin yurutulmesine veya askiya alinmasina karar verir.
		if (isletilen.Id == -1) { //Veri ilk process ise process baslatilir.
			isletilen = gercekZaman.get(0);
			Yaz(saniye, 0, isletilen);
			isletilen.calismaZaman -= 1;
		} else if ((isletilen.priority  == 0) && (isletilen.calismaZaman != 0)) { //Verinin calisma suresi varsa yurutulur.
																							
			Yaz(saniye, 1, isletilen);
			isletilen.calismaZaman -= 1;
		} else if ((isletilen.priority == 0) && (isletilen.calismaZaman == 0)) {//Verinin calisma suresi kalmadiysa sonlandirilir.
																						
																							
			Yaz(saniye, 3, isletilen);
			gercekZaman.remove(0);
			if (!gercekZaman.isEmpty())
			{
				isletilen = gercekZaman.get(0);
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman -= 1;
			}
		} 
		else    //Process onceligi 0 olmayan bir processten gelirse gelen process askiya alinir.
				//gercek zamanli process baslatilir.
		{ 
			isletilen.asim = saniye;
			Yaz(saniye, 2, isletilen);
			isletilen = gercekZaman.get(0);
			Yaz(saniye, 0, isletilen);
			isletilen.calismaZaman--;
		}

		sira1 = asimKontrol(sira1 , saniye); //verilerin zaman asimi kontrolu yapilir.
																
		sira2 = asimKontrol(sira2 , saniye);

		sira3 = asimKontrol(sira3, saniye);

		return isletilen;


	}

	
	
	public Process kullaniciKuyruk(ArrayList<Process> kullanici, Process isletilen, int saniye)//kullaniciKuyruk kuyrugu 1,2 ve 3 oncelikli processleri isler.
	{

		for (Process proses : kullanici) { //oncelik sirasina gore priority kisimlarina dagitilir.
													
			if (proses.priority  == 1)
			{
				sira1.add(proses);
			} else if (proses.priority  == 2) 
			{
				sira1.add(proses);
			} else if (proses.priority  == 3)
			{
				sira1.add(proses);
			}
		}

		if (!sira1.isEmpty()) { //oncelik 1 ise ilk once calistirilir.
			if (isletilen.priority  != 0 && isletilen.priority  != -1 && isletilen.calismaZaman != 0) 
			{
				Yaz(saniye, 2, isletilen);
			}
			isletilen.asim = saniye; //Zaman asimi kontrolu icin guncelleme yapilir.
			isletilen = sira1.get(0);
			sira1.remove(0);
			if ((isletilen.calismaZaman- 1) > 0) { //processin calisma zamanina gore 3 satirda islenir eger sonlanma suresi bitmediyse onceligi arttirilarak priority 2 ye atilir.
				Yaz(saniye, 0, isletilen); 
				isletilen.calismaZaman --; 
				isletilen.priority ++;
				sira2.add(isletilen);
			} 
			
			else if (isletilen.priority  == 1) {//calisma zamani 1 saniye ise sonlandirilir.
															
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman--;
				isletilen.priority ++;
				Yaz(saniye, 3, isletilen);
			} 

			else
			{
				isletilen.calismaZaman--;
				isletilen.priority ++;
				Yaz(saniye, 3, isletilen);
			}

		} 
		else if (!sira2.isEmpty())  //1 oncelikli process var ise bitmesini bekler ve bittiginde calistirilir.
		{ 
												
			if (isletilen.priority  != 0 && isletilen.priority  != -1 && isletilen.calismaZaman != 0) 
			{
				Yaz(saniye, 2, isletilen);
			}
			isletilen.asim = saniye;
			isletilen = sira2.get(0);
			sira2.remove(0);
			if ((isletilen.calismaZaman - 1) > 0)
			{
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman --;
				isletilen.priority ++;
				sira3.add(isletilen);
			} 

			else if (isletilen.calismaZaman == 1)
			{
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman --;
				isletilen.priority ++;
				Yaz(saniye, 3, isletilen);
			} 


			else {
				isletilen.calismaZaman--;
				isletilen.priority ++;
				Yaz(saniye, 3, isletilen);
			}

		} 
		else if (!sira3.isEmpty()) { //1 ve 2 oncelikli process var ise bitmesini bekler ve bittiginde calistirilir.
												
			if (isletilen.priority  != 0 && isletilen.priority  != -1 && isletilen.priority  != 0) 
			{
				Yaz(saniye, 2, isletilen);
			}
			isletilen.asim = saniye;
			isletilen = sira3.get(0);
			sira3.remove(0);
			if ((isletilen.calismaZaman - 1) > 0) 
			{
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman--;
				sira3.add(isletilen);
			} 
			
			else if (isletilen.calismaZaman == 1) 
			{
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman--;
				Yaz(saniye + 1, 3, isletilen);
			}
			
			
			else {
				isletilen.calismaZaman --;
			    Yaz(saniye, 3, isletilen);
			}
		}

		sira1 = asimKontrol(sira1, saniye);//zaman asimi kontrolu yapilir.
		sira2 = asimKontrol(sira2, saniye);
		sira3 = asimKontrol(sira3, saniye);

		return isletilen;


	}

	
	

}





















