package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Snippet {
	public static String writerFile(String array, String filename) throws FileNotFoundException,
				IOException {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "UTF-8"));
			bw.write(array.toString());
			bw.close();
			return "1";
		}
}

