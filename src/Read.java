import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Read {
	public static void main(String[] args){
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String,String> maps = new HashMap<String,String>();
		HashMap<String,String> list = new HashMap<String,String>();
		HashMap<String,String> lists = new HashMap<String,String>();


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
				//System.out.println(array[0]);
				if(array.length != 2) throw new NumberFormatException();
				String cd   = (array[0]);
				String name = (array[1]);
				map.put(cd, name);
				//System.out.println(map.get("001"));
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
			for(int i = 0; i < f1.length; i++){
				if(f1[i].getName().endsWith(".rcd")){
					File f2 = f1[i];

			BufferedReader  bb = new BufferedReader(new FileReader(f2));
			String b;
			while((b = bb.readLine())!= null){
				String array[] = b.split(",");
				if(array.length != 3) throw new NumberFormatException();
				String cd = array[0];
				String shcord = array[1];
				String sell = array[2];
				list.put(cd, sell);
				lists.put(shcord, sell);
				System.out.println("001");
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
	}
}












