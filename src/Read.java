import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Read {
	public static void main(String[] args){
		ArrayList<String> bank = new ArrayList<String>();
		ArrayList<String> toy  = new ArrayList<String>();
		Map<String,String> map  = new HashMap<String, String>();
		List<String> list = new ArrayList<String>();

		try{
			File file = new File(args[0],"branch.lst");
			File files = new File(args[0],"commodity.lst");

			if(!file.exists()){
				System.out.println("支店定義ファイルがありません");
			//}else if(file.getPath().endsWith(".lst")){
				System.out.println("支店定義ファイルのフォーマットが不正です");
			//  return;

			if(!files.exists()){
				System.out.println("商品定義ファイルがありません");
			//}else if(file.getPath().endsWith(".lst")){
				System.out.println("商品定義ファイルのフォーマットが不正です");
				return;
			}
			}

			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader brs = new BufferedReader(new FileReader(files));
			String a;
			String s;

			while((s = br.readLine())!= null) {
				bank.add(s);
			}
			br.close();

			while((a = brs.readLine())!= null){
				toy.add(a);
			}
			brs.close();

			File dir = new File(args[0]);
			File[] f1 = dir.listFiles();
			for(int i = 0; i < f1.length; i++){
				if(f1[i].getName().endsWith(".rcd")){
					File f2 = f1[i];

			BufferedReader bb = new BufferedReader(new FileReader(f2));
			String b;
			while((b = bb.readLine())!= null){
				System.out.println(b);
				list.add(b);
				String sell = list.get(0);

			}
			bb.close();

				}
			}
//			System.out.println(list.get(2));
//			System.out.println(list.get(5));
//			System.out.println(list.get(8));

		}catch(IOException e){
			System.out.println(e);
		}
	}
}


