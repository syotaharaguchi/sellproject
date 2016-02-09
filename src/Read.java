import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read {
	public static void main(String[] args){
		ArrayList<String> bank = new ArrayList<String>();
		ArrayList<String> toy  = new ArrayList<String>();
		List<String> list = new ArrayList<String>();

		try{
			File file = new File(args[0],"branch.lst");
			File files = new File(args[0],"commodity.lst");



			if(!file.exists()){
				System.out.println("支店定義ファイルがありません");
			}else if(!files.exists()){
				System.out.println("商品定義ファイルがありません");
				return;
			}

			FileReader fr = new FileReader(file);
			FileReader frs = new FileReader(files);
			BufferedReader br = new BufferedReader(fr);
			BufferedReader brs = new BufferedReader(frs);
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

		}catch(IOException e){
			System.out.println(e);
		}

		File dir = new File(args[0]);
		File[] f1 = dir.listFiles();
		for(int i = 0; i < f1.length; i++){
				File f2 = f1[i];
				System.out.println(f2);
		}

	}
}


