import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Read {
	public static void main(String[] args){
		HashMap<String,String> branch = new HashMap<String,String>();
		HashMap<String,Integer> amount = new HashMap<String,Integer>();
		HashMap<String,String> commodity = new HashMap<String,String>();
		HashMap<String,Integer> sum = new HashMap<String,Integer>();
		HashMap<String,String> rcd = new HashMap<String,String>();
		HashMap<String,String> shrcd = new HashMap<String,String>();

		try{
			File file = new File(args[0],"branch.lst");
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
			File files = new File(args[0],"commodity.lst");
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
						//amount.put(cord, Integer.parseInt(sell));
					}

					if(commodity.containsKey(sh)){
						sum.replace(sh, Integer.parseInt(sell) + sum.get(sh));
					}

				bb.close();

				}

				}
						System.out.println(amount);
						System.out.println(sum);
		}catch(IOException x){
			System.out.println(x);
		}catch(NumberFormatException x){
			System.out.println("該当ファイル名のフォーマットは不正です");
			return;
		}


			File newfile = new File(args[0],"branch.out");
			if(!newfile.exists()){
				try{
					newfile.createNewFile();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			File read = new File(args[0],"branch.out");
			BufferedWriter brw = new BufferedWriter(new FileWriter(read));
			String l;


	}
}

