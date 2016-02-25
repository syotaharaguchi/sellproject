import java.io.BufferedReader;
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
		HashMap<String,Long> amount = new HashMap<String,Long>();
		HashMap<String,String> commodity = new HashMap<String,String>();
		HashMap<String,Long> sum = new HashMap<String,Long>();

		File file = new File(args[0],"branch.lst");
		File files = new File(args[0],"commodity.lst");

		try{
			if(!file.exists()){
				System.out.println("支店定義ファイルが存在しません");
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

				if(!cd.matches("^[0-9]{3}+$" )){
					System.out.println("支店定義ファイルのフォーマットは不正です");
					return;
				}

				branch.put(cd, name);
				amount.put(cd, (long) 0);
			}
			br.close();

		}catch(IOException e){
			System.out.println(e);
		}

		try{

			if(!files.exists()){
				System.out.println("商品定義ファイルが存在しません");
				return;
			}

			BufferedReader brs = new BufferedReader(new FileReader(files));
			String a;
			while((a = brs.readLine()) != null){
				String array[] = a.split(",");
				if(array.length != 2){
					System.out.println("商品定義ファイルのフォーマットが不正です");
					return;
				}
					String shcord = array[0];
					String shname = array[1];

					if(!shcord.matches("^[0-9a-zA-Z]{8}+$")){
						System.out.println("商品定義ファイルのフォーマットが不正です");
						return;
					}
					commodity.put(shcord,shname);
					sum.put(shcord, (long) 0);
			}

			brs.close();

		}catch(IOException e){
			System.out.println(e);
		}

		try{
			ArrayList<File> lists = new ArrayList<File>();
			File dir = new File(args[0]);
			File[] f1 = dir.listFiles();
			for(int i = 0; i< f1.length; i++){
				if(f1[i].getName().endsWith(".rcd")){
					lists.add(f1[i]);
				}
			}

			for(int i = 0; i < lists.size(); i++){
				//箱の初期化の宣言
				ArrayList<String> list = new ArrayList<String>();

				String str = lists.get(i).getName().split(".rcd")[0];
				if(Integer.parseInt(str) != i+1){
					System.out.println("売上ファイルが連番になっていません");
					return;
				}

						BufferedReader  bb = new BufferedReader(new FileReader(lists.get(i)));
						String b;
					while((b = bb.readLine()) != null){;
						list.add(b);
					}

				int	counter = list.size();
					if(counter == 4){
						System.out.println("フォーマットが不正です。");
					}

						// 加算処理をする
						String cord = list.get(0);
						String sh = list.get(1);
						String sell = list.get(2);

					if(branch.containsKey(cord)){
						amount.replace(cord, Long.parseLong(sell) + amount.get(cord));
					}else{
						System.out.println("支店コードが不正です");
						return;
					}

					if(commodity.containsKey(sh)){
						sum.replace(sh, Long.parseLong(sell) + sum.get(sh));
					}else{
						System.out.println("商品コードが不正です");
						return;
					}

				bb.close();

				if(!cord.matches("^[0-9]{3}+$")){
						System.out.println("エラー、３桁数字固定");
						return;
				}
				if(!sh.matches("^[0-9a-zA-Z]{8}+$")){
						System.out.println("アルファベットと数字。８桁固定");
						return;
				}
				long sellcord = amount.get(cord);
				long value = String.valueOf(sellcord).length();
				if(value >= 10){
					System.out.println("合計金額が10桁を超えました");
					return;
				}
			}

		}catch(IOException e){
			System.out.println(e);
		}

		try{
			File newfile = new File(args[0],"branch.out");
			FileWriter filewriter = null;
			filewriter = new FileWriter(newfile);

			List<HashMap.Entry<String,Long>> entries = new ArrayList<HashMap.Entry<String,Long>>
			(amount.entrySet());

		        Collections.sort(entries, new Comparator<HashMap.Entry<String,Long>>() {

		        	public int compare(
			             Entry<String,Long> entry1, Entry<String,Long> entry2) {
			             return ((Long)entry2.getValue()).compareTo((Long)entry1.getValue());
		        	}
		        }
		        );

		        for (Entry<String,Long> s : entries){
			        System.out.println(s);
		        	if(!s.getKey().matches("^[0-9a-zA-Z]{3}+$")){
			        	System.out.println("アルファベットと数字。８桁固定");
			        	return;
			        }
		        	filewriter.write(s.getKey() + "," + branch.get(s.getKey()) + "," + s.getValue() + "\r\n");

		        }
		        filewriter.close();

		}catch(IOException e){
			System.out.println(e);
		}

		try{
			File newfile = new File(args[0],"commodity.out");
			FileWriter filewriter = null;
			filewriter = new FileWriter(newfile);

				List<HashMap.Entry<String,Long>> entries = new ArrayList<HashMap.Entry<String,Long>>
				(sum.entrySet());

			        Collections.sort(entries, new Comparator<HashMap.Entry<String,Long>>() {
			        	public int compare(
				             Entry<String,Long> entry1, Entry<String,Long> entry2) {
				             return ((Long)entry2.getValue()).compareTo((Long)entry1.getValue());
			        	}
			        }
			        );
			        for (Entry<String,Long> s : entries){
			        	if(!s.getKey().matches("^[0-9a-zA-Z]{8}+$")){
				        	System.out.println("アルファベットと数字。８桁固定");
				        	return;
				        }
			        	filewriter.write(s.getKey() + "," + commodity.get(s.getKey()) + "," + s.getValue() + "\r\n");

			        }
			        filewriter.close();


		}catch(IOException e){
			System.out.println(e);
		}
	}
}

