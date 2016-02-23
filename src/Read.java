import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Read {
	public static void main(String[] args){
		HashMap<String,String> branch = new HashMap<String,String>();
		HashMap<String,Integer> amount = new HashMap<String,Integer>();
		HashMap<String,String> bran = new HashMap<String,String>();
		HashMap<String,String> commodity = new HashMap<String,String>();
		HashMap<String,String> dity = new HashMap<String,String>();
		HashMap<String,Integer> sum = new HashMap<String,Integer>();


		File file = new File(args[0],"branch.lst");
		File files = new File(args[0],"commodity.lst");

		try{
			if(!file.exists()){
				System.out.println("支店定義ファイルがありません");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;

			while((s = br.readLine()) != null){
				String array[] = s.split(",");
				if(array.length != 2){
					System.out.println("支店定義ファイルのフォーマットは不正です");
					return;
				}
				String cd   = (array[0]);
				String name = (array[1]);
				branch.put(cd, name);
				bran.put(cd,cd);
				amount.put(cd, 0);

			}
			br.close();

		}catch(IOException e){
			System.out.println(e);
		}catch(NumberFormatException e){
			System.out.println("支店定義ファイルのフォーマットは不正です");
			return;
		}

		try{

			if(!files.exists()){
				System.out.println("商品定義ファイルがありません");
				return;
			}

			BufferedReader brs = new BufferedReader(new FileReader(files));
			String a;
			while((a = brs.readLine()) != null){
				String array[] = a.split(",");
				if(array.length != 2) throw new NumberFormatException();
				String shcord = array[0];
				String shname = array[1];
				commodity.put(shcord,shname);
				dity.put(shcord, shcord);
				sum.put(shcord, 0);
			}
			brs.close();

		}catch(IOException a){
			System.out.println(a);
		}catch(NumberFormatException a){
			System.out.println("支店定義ファイルのフォーマットは不正です");
			return;
		}

		try{
			File dir = new File(args[0]);
			File[] f1 = dir.listFiles();
			for(int i = 0; i < f1.length; i++){
				//箱の初期化の宣言
				ArrayList<String> list = new ArrayList<String>();

				if(f1[i].getName().endsWith(".rcd")){
					File f2 = f1[i];
					BufferedReader  bb = new BufferedReader(new FileReader(f2));
					String b;
					while((b = bb.readLine()) != null){
						list.add(b);

					}
					// 加算処理をする
					String cord = list.get(0);
					String sh = list.get(1);
					String sell = list.get(2);

					if(branch.containsKey(cord)){
						amount.replace(cord, Integer.parseInt(sell) + amount.get(cord));
					}

					if(commodity.containsKey(sh)){
						sum.replace(sh, Integer.parseInt(sell) + sum.get(sh));
					}

				bb.close();

				}

				}

		}catch(IOException x){
			System.out.println(x);
		}catch(NumberFormatException x){
			System.out.println("該当ファイル名のフォーマットは不正です");
			return;
		}


//		try{
//			File newfile = new File(args[0],"branch.out");
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			BufferedWriter bw = new BufferedWriter(new FileWriter(newfile,true));
//			for(String key : branch.keySet()){
//				bw.write(bran.get(key) +","+ branch.get(key) +","+ amount.get(key));
//				bw.newLine();
//			}
//
//			br.close();
//			bw.close();
//
//		}catch(IOException e){
//			System.out.println(e);
//		}

		try{
			File newfile = new File(args[0],"commodity.out");
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(newfile,true));

				List<HashMap.Entry<String,Integer>> entries = new ArrayList<HashMap.Entry<String,Integer>>
				(sum.entrySet());
			        Collections.sort(entries, new Comparator<HashMap.Entry<String,Integer>>() {

			        	public int compare(
			                    Entry<String,Integer> entry1, Entry<String,Integer> entry2) {
			                  return ((Integer)entry2.getValue()).compareTo((Integer)entry1.getValue());
			        	 }
			        });
			        for (Entry<String,Integer> s : entries) {
			            System.out.println(s.getKey());
			            System.out.println(s.getValue());

				bw.write(s.getKey() + "," + s.getValue());
				bw.newLine();
			 }
			br.close();
			bw.close();

		}catch(IOException e){
			System.out.println(e);
		}
	}
}

