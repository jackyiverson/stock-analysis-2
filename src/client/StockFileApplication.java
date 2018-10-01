package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

public class StockFileApplication {
	
	public static void main(String args[]) throws IOException{
		StockFileReader fr = new StockFileReader("table.csv");
		
		List<HashMap<String, Double>> dataResult = populateStockFileData(fr.getHeaders(), fr.readFileData());
		StockFileData fileData = new StockFileData();
		fileData.addData(dataResult);
		fileData.printData();
		System.out.println(dataResult.size());
	}
	/**
	 * Complete the method body so that it returns the given structure needed to 
	 * populate the data field in the StockFileData class. 
	 * @param headers
	 * @param lines
	 * @return List
	 */
	public static List<HashMap<String, Double>> populateStockFileData(List<String> headers, List<String> lines){
		List<HashMap<String, Double>> dataResult = new ArrayList<>();
		// Insert your code here..
		String[] day=null;	
		for(int i=0;i<lines.size();i++) {		
			HashMap<String,Double> oneRow=new HashMap<String,Double>();
			day = lines.get(i).split(",");
			for(int j=0;j<day.length;j++) {
				oneRow.put(headers.get(j),Double.valueOf(day[j]));
			}
			dataResult.add(oneRow);
		}
		return dataResult;
	}
	
	
}
