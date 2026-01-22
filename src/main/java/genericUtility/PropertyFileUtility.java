package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
/**
 * This class consists of method to read data from property file
 * @author vivek
 * 
 */
	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
		
		public String toReadDataFromPropertyFile(String key) throws IOException {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
			Properties prop = new Properties();
			prop.load(fis);
			String value = prop.getProperty(key);
			return value;
			
			
			
		}

	}


