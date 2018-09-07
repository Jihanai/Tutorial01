//import beberapa modul
import java.util.*;
import java.io.*;
/**
 * 
 * @author Jihan Amalia Irfani, 1606837846
 *
 */
public class SDA1718TUGAS1 {
	//membuat instance variable
	private static Set<Kucing> kucing = new TreeSet<Kucing>();				//insvar untuk histori
	private static ArrayList<Stack> kucingTab = new ArrayList<>();			//insvar yang menggambarkan tab tab pada website
	private static int currentTab = 0;										//untuk tab yang sedang aktif
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader masukan = new BufferedReader(new InputStreamReader( System.in))) {
			
			String baca = masukan.readLine();								//membaca baris selanjutnya
			int perintah = Integer.parseInt(baca); 							//mengconvert ke integer jumlah perintah
			
			String next = masukan.readLine(); 								//membaca baris selanjutnya
			Stack<Kucing> tabAwal = new Stack<>(); 							//membuat stack baru
			
			kucingTab.add(tabAwal);											//insert untuk tab awal
			
			//melakukan loop sesuai banyak perintah yang akan dimasukkan
			for(int i = 0 ; i < perintah; i++) {
				
				//mengkategorikan sesuai panjang karakter perintah lalu sesuai dengan perintah awal
				if(4 <= next.length() && next.length()  <= 7) {
					
					if(next.equalsIgnoreCase("BACK")) {
						
						if(!kucingTab.get(currentTab).isEmpty()) {			//jika isi tab tidak kosong atau ada histori
							
							Kucing kucingCurrent = (Kucing) kucingTab.get(currentTab).pop();									//mengambil elemen terakhir dari tab

							//cek lagi untuk history sebelumnya apakah kosong atau tidak
							if((kucingTab.get(currentTab).isEmpty())) {		
								System.out.println("EMPTY TAB");			//menampilkan pesan
							}else {	//jika tidak kosong, ditampilkan
								Kucing kucingBack = (Kucing) kucingTab.get(currentTab).peek();		 //mengintip histori sebelumnya
								System.out.println("VIEWING([" + kucingBack.getNama() + ":" + kucingBack.getJenis() + "])");	//menampilkan yang sedang aktif
							}	
						}else { //jika tab tersebut kosong atau tidak ada histori
							System.out.println("EMPTY TAB");				//menampilkan pesan
						}
						
					}else if (next.equalsIgnoreCase("NEWTAB")) {
						currentTab +=1;										//menambahkan tab baru
						kucingTab.add(new Stack<>());						//menambahkan elemen tab baru ke browser
						
					}else if(next.equalsIgnoreCase("HISTORY")) {
						if(kucing.isEmpty()) {								//jika historinya kosong
							System.out.println("NO RECORD");				//menampilkan pesan
						}else {												//jika masih ada histori
							for(Kucing k : kucing) {						//loop isi histori
								System.out.println(k.getNama() + ":" + k.getJenis());				//menampilkan masing masing histori
							}
						}
						
					}
				}else{
					
					String[] info = next.split(";");						//untuk perintah yang dipisahkan dengan ; di split
					
					if(info[0].equalsIgnoreCase("VIEW")) {
						Kucing kucingBaru = new Kucing(info[1], info[2]);	//membuat objek kucing baru sesuai info
						
						kucing.add(kucingBaru);								//menambahkan histori
						
						kucingTab.get(currentTab).push(kucingBaru);			//menambahkan objek yang dilihat pada tab yang aktif
						
						System.out.println("VIEWING([" + kucingBaru.getNama() + ":" + kucingBaru.getJenis() + "])");	//menampilkan informasi laman kucing
						
					}else if(info[0].equalsIgnoreCase("CHANGETAB")) {
						//mengganti pointer tab sesuai dengan perintah
						currentTab = Integer.parseInt(info[1]);
						
						if(!( 0 <= currentTab && currentTab < kucingTab.size())) {	//jika tab yang dibutuhkan diluar dari range
							System.out.println("NO TAB");					//menampilkan pesan
						}else {												//jika masih ada dalam range
							System.out.println("TAB: " + (currentTab));		//menampilkan pesan tab sekarang
						}
						
					}
				}
				
				next = masukan.readLine();	//mengupdate baris perintah yang diproses
			}
			
		} 	
	}
}
/**
 * 
 * class yang menggambarkan kucing yang implements interface Comparable
 *
 */
class Kucing implements Comparable<Kucing>{
	//instance variable
	private String nama;
	private String jenis;
	/**
	 * method constructor
	 * @param nama
	 * @param jenis
	 */
	public Kucing(String nama, String jenis) {
		super();
		this.nama = nama;
		this.jenis = jenis;
	}
	/**
	 * method override dari interface Comparable
	 */
	@Override 
	public int compareTo(Kucing another) {
		return this.getNama().compareTo(another.getNama());		//pengurutan nama berdasarkan leksikografi
	}
	/**
	 * fungsi getter untuk mendapatkan nama dari objek
	 * @return mengembalikan nama dari objek
	 */
	public String getNama() {
		return nama;
	}
	/**
	 *fungsi mutator untuk mengganti nama objek 
	 * @param nama
	 */
	public void setNama(String nama) {
		this.nama = nama;
	}
	/**
	 * fungsi getter untuk mendapatkan jenis dari objek
	 * @return mengembalikan jenis objek
	 */
	public String getJenis() {
		return jenis;
	}
	/**
	 * fungsi mutator untuk mengganti jenis objek
	 * @param jenis
	 */
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	
}