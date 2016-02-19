import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Read {
	public static void main(String[] args){
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String,String> maps = new HashMap<String,String>();
		HashMap<String,String> lists = new HashMap<String,String>();
		HashMap<String,String> shlist = new HashMap<String,String>();
		HashMap<String,String> sum = new HashMap<String,String>();

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
				if(array.length != 2) throw new NumberFormatException();
				String cd   = (array[0]);
				String name = (array[1]);
				map.put(cd, name);

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
				maps.put(shcord,shname);
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
			int sums = 0;
			String tmp = null;
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
					String shcord = list.get(1);
					String sell = list.get(2);
					lists.put(cord, sell);
					shlist.put(shcord,sell);
					//cordとcdのコードを比較、同じならば売上を加算
					if(map.containsKey(cord) ){
						tmp = lists.get(cord);
						if(tmp ==   ){
							//System.out.println(tmp);
							sums += Integer.parseInt(tmp);
						}
					}else{
						System.out.println("test");
					}
				bb.close();
				}

				}

						System.out.println(sums);
			//			System.out.println(lists.entrySet());
			//			System.out.println(shlist.entrySet());

		}catch(IOException x){
			System.out.println(x);
		}catch(NumberFormatException x){
			System.out.println("該当ファイル名のフォーマットは不正です");
			return;
		}
	}
}

