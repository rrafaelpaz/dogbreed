package au.com.dogs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {

	public static String getBreenName(String file) throws NullPointerException{

		//creates pattern in order to retrieve breedName from url
		Pattern p = Pattern.compile("breeds/(.*?)/");
		Matcher m = p.matcher(file);
		
		//executes match
		m.find();
		
		//retrives breedName
		String dogBreed = m.group(1);
		
	    if(dogBreed.equals("") || dogBreed == null){
	    	throw new NullPointerException(); 
	    }else{
	    	return dogBreed;
	    }
	}
}
