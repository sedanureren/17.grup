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
	
	public Queue() {
		sira1 = new ArrayList<Process>();
		sira2 = new ArrayList<Process>();
		sira3 = new ArrayList<Process>();
	}
    public static int satirsayisi(File f) throws FileNotFoundException { 
																			

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
		File dosya = new File("C:\\Users\\Dilce\\OneDrive\\Belgeler\\giris.txt");

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

		ArrayList<Process> araci = new ArrayList<Process>();

		for (Process yeni : kuyruk) {
			if (yeni.gelisZaman == sure) {
				araci.add(yeni);
			}
		}

		return araci;
	}

	public ArrayList<Process> object() throws FileNotFoundException {

		File dosya = new File("C:\\Users\\Dilce\\OneDrive\\Belgeler\\giris.txt");
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
		case 0:
			System.out.println(saniye + " sn proses ba�lad�(id:" + isletilen.Id + "\t�ncelik:"+ isletilen.priority + "\tkalan s�re:" + isletilen.calismaZaman + ")");
			break;
		case 1:
			System.out.println(saniye + " sn proses y�r�t�l�yor\t(id:" + isletilen.Id + "\t�ncelik:"+ isletilen.priority  + "\tkalan s�re:" + isletilen.calismaZaman + ")");
			break;
		case 2:
			System.out.println(saniye + " sn proses askida\t\t(id:" + isletilen.Id + "\t�ncelik:"+ isletilen.priority  + "\tkalan s�re:" + isletilen.calismaZaman+ ")");
			break;
		case 3:
			System.out.println(saniye + " sn proses sonlandi\t\t(id:" + isletilen.Id + "\toncelik:"+ isletilen.priority + "\tkalan s�re:" + isletilen.calismaZaman + ")");
			break;
		case 4:
			System.out.println(saniye + " sn proses zaman a��m�na u�rad�\t\t(id:" + isletilen.Id + "\t�ncelik:"+ isletilen.priority  + "\tkalan s�re:" + isletilen.calismaZaman + ")");
			break;
		}
	}

	public ArrayList<Process> asimKontrol(ArrayList<Process> list, int saniye)
	{
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

		if (isletilen.Id == -1) { 
			isletilen = gercekZaman.get(0);
			Yaz(saniye, 0, isletilen);
			isletilen.calismaZaman -= 1;
		} else if ((isletilen.priority  == 0) && (isletilen.calismaZaman != 0)) { 
																							
			Yaz(saniye, 1, isletilen);
			isletilen.calismaZaman -= 1;
		} else if ((isletilen.priority == 0) && (isletilen.calismaZaman == 0)) {
																						
																							
			Yaz(saniye, 3, isletilen);
			gercekZaman.remove(0);
			if (!gercekZaman.isEmpty())
			{
				isletilen = gercekZaman.get(0);
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman -= 1;
			}
		} 
		else
		{ 
			isletilen.asim = saniye;
			Yaz(saniye, 2, isletilen);
			isletilen = gercekZaman.get(0);
			Yaz(saniye, 0, isletilen);
			isletilen.calismaZaman--;
		}

		sira1 = asimKontrol(sira1 , saniye);
																
		sira2 = asimKontrol(sira2 , saniye);
		sira3 = asimKontrol(sira3, saniye);

		return isletilen;
	}

	
	
	public Process kullaniciKuyruk(ArrayList<Process> kullanici, Process isletilen, int saniye)
	{

		for (Process proses : kullanici) {
													
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

		if (!sira1.isEmpty()) { // 1 Öncelikli proses var ise ilk önce çalıştırılır.
			if (isletilen.priority  != 0 && isletilen.priority  != -1 && isletilen.calismaZaman != 0) 
			{
				Yaz(saniye, 2, isletilen);
			}
			isletilen.asim = saniye; // Zaman aşımı kontrolü için güncelleme yapılır.
			isletilen = sira1.get(0);
			sira1.remove(0);
			if ((isletilen.calismaZaman- 1) > 0) { // Prosesin çalışma zamanına göre 3 satırda işenir
				Yaz(saniye, 0, isletilen); // eğer proses sonlanma süresi bitmediyse önceliği arttırılarak
				isletilen.calismaZaman --; // priority 2 ye atılır.
				isletilen.priority ++;
				sira2.add(isletilen);
			} else if (isletilen.priority  == 1) { // Procesin çalışma zamanı 1 saniye ise proses
															// sonlandırılır.
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
		else if (!sira2.isEmpty()) 
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
				// activeProcess.asimZamani=saniye;
				sira3.add(isletilen);
				// ekran(saniye, 2, activeProcess);
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
		else if (!sira3.isEmpty()) { // 1 ve 2 Öncelikli proses var ise bitmesini bekler ve bittiğinde
												// çalıştırılır.
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
				// activeProcess.asimZamani=saniye;
				sira3.add(isletilen);
				// ekran(saniye, 2, activeProcess);
			} else if (isletilen.calismaZaman == 1) 
			{
				Yaz(saniye, 0, isletilen);
				isletilen.calismaZaman--;
				Yaz(saniye + 1, 3, isletilen);
			} else {
				isletilen.calismaZaman --;
			    Yaz(saniye, 3, isletilen);
			}
		}

		sira1 = asimKontrol(sira1, saniye);
		sira2 = asimKontrol(sira2, saniye);
		sira3 = asimKontrol(sira3, saniye);

		return isletilen;
	}

	
	

}
