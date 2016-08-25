package main;

import java.util.Arrays; 
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class StreamOrnek {

	public class ornekPojo{
		private Long id;
		private String ad;
		private String soyad;
		private String tc;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getAd() {
			return ad;
		}
		public void setAd(String ad) {
			this.ad = ad;
		}
		public String getSoyad() {
			return soyad;
		}
		public void setSoyad(String soyad) {
			this.soyad = soyad;
		}
		public String getTc() {
			return tc;
		}
		public void setTc(String tc) {
			this.tc = tc;
		}
       }
	
	public static void yazdir(Integer veri){
		
		System.out.println("Bilgi"+veri);
	}
	public static void yazdir(String veri){
		
		System.out.println("Bilgi String"+veri);
	}
	
	public static void yazdir(StreamOrnek.ornekPojo veri){
		
		System.out.println("Bilgi Pojo kullanýmý:"+veri.getAd());
	}
	
	public static void main(String[] args) {

		//String ile kullaným örneði
		List<String> dizi =Arrays.asList("Abcde","Bcde","Cdefg","Hýijk","Ijklm","Jklmn","Klmno","Lmnop");
		Stream<String> islemdizisi = dizi.stream();
		islemdizisi.forEach(basliklar ->{
			System.out.println("Baþlýk:"+ basliklar);
			
		});
	  
		StreamOrnek.ornekPojo obj1  =  new StreamOrnek().new ornekPojo();
		obj1.setId(1L);
		obj1.setAd("Selçuk");
		obj1.setSoyad("Uzunsoy");
		obj1.setTc("12345678910");
	
		StreamOrnek.ornekPojo obj2  =  new StreamOrnek().new ornekPojo();
		obj2.setId(2L);
		obj2.setAd("R2D2");
		obj2.setSoyad("Starwars");
		obj2.setTc("1010101010101010");
	
		
		//Pojo benzeri sýnýflar ile kullaným örneði
		List<StreamOrnek.ornekPojo> dizi_pojo =Arrays.asList(obj1, obj2);
		Stream<StreamOrnek.ornekPojo> islemdizisi_pojo = dizi_pojo.stream();
		islemdizisi_pojo.forEach(obj ->{
			System.out.println("ID:"+ obj.getId());
			System.out.println("Ýsim:"+ obj.getAd());
			System.out.println("Soyisim:"+ obj.getSoyad());
			System.out.println("TC:"+ obj.getTc());
		});
		
		islemdizisi_pojo = dizi_pojo.stream();
		islemdizisi_pojo.forEach(StreamOrnek::yazdir); //Yeni operatör tipi olan : yapýsý ile kullanýmý.
	
		//Obje filtreleme kullanýmý:
		System.out.println("----------");
		islemdizisi_pojo = dizi_pojo.stream();
		Predicate<StreamOrnek.ornekPojo> pd_pojo = icindeki_deger -> icindeki_deger.getAd().contains("R2D2");
		Stream<StreamOrnek.ornekPojo> filtrelenmis_islemdizisi_pojo = islemdizisi_pojo.filter(pd_pojo);
		filtrelenmis_islemdizisi_pojo.forEach(StreamOrnek::yazdir);
		System.out.println("----------");
		
		//Integer kullanýmý: Double, char, int, Long vs tüm tipler için kullanabilirsiniz.
		List<Integer> dizi_values =Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Stream<Integer> islemdizisi_values = dizi_values.stream();
		islemdizisi_values.forEach(deger ->{
			System.out.println("Deðer:=>"+ deger);
		});
		
		
		//Þimdi filtreleme yapalým ve birtakým deðerleri eleyelim !
		//Predicate Interface ini referans alan ayrý bir obje ile çalýþalým.
		islemdizisi_values = dizi_values.stream();// Stream ile oluþan nesne tek kullanýmlýk bir yapýya sahiptir. Yeniden açýp kullanmamýz gereklidir.
		Predicate<Integer> pd = icindeki_deger -> icindeki_deger < 6;
		Stream<Integer> filtrelenmis_islemdizisi_values = islemdizisi_values.filter(pd);
		filtrelenmis_islemdizisi_values.forEach(StreamOrnek::yazdir);
		 
		
		//Distinct kullanýmý
		List<Integer> tekrarliDizi = Arrays.asList(1,2,3,4,4,4,5,5,6,6,6,6,7,7,8,8,9,10);
		tekrarliDizi.stream().distinct().forEach(deger ->{ System.out.println("Veriler:"+deger);});
		
		System.out.println("---------");
		//Sýralanmýþ veriler
		List<Integer> sirasizDizi = Arrays.asList(10,5,21,12,14,11,11,34,54,65,1,5);
		sirasizDizi.stream().sorted().forEach(deger ->{ System.out.println("Veriler:"+deger);});
 

		System.out.println("---------");
		//Sýralanmýþ veriler
		List<Integer> veriKisitlamasi = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		veriKisitlamasi.stream().limit(5).forEach(deger ->{ System.out.println("Veriler:"+deger);});
		
		//EK BÝLGÝ: Eðer bir stream aralýk ihtiyacýnýz var ise nunu LongStream ile karþýlayabilir siniz !
		LongStream aralikListesi = LongStream.range(1, 500);
		aralikListesi.limit(30).forEachOrdered(deger ->{ System.out.println("Bilgi:"+deger);});
		 
		System.out.println("---------");
		//Sýralanmýþ veriler
		List<Integer> intaralikListesi = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("Kaç Adet Var:"+ intaralikListesi.stream().count());
		
		 
	}

}
