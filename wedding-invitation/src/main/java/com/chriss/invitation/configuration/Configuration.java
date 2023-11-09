package com.chriss.invitation.configuration;

import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.io.Reader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configuration {
	public static String ReadFile(String path) throws IOException, ParseException {
		
		if (path.equals("")) {
			path = "./log/";
		}
		String logfile = path + getToday() + ".log"; 

		Reader reader = new FileReader("./test.json");
		
		JSONParser parser = new JSONParser();
		
		JSONObject jo = (JSONObject) parser.parse(reader);
		
		return (String)jo.get("test");
//		Reader reader = new FileReader(path + "");
	}
	
	public static void WriteFile(String path) {
		try {
			File file = new File("./t.json");
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("test success");
			
			bw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String getToday() {
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		return dt.format(cal.getTime());
	}
}

