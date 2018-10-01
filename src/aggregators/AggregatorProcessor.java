package aggregators;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import fileprocessors.StockFileReader;

public class AggregatorProcessor<E extends Aggregator> {
	E agg=null;
	String filePath;
	
	public AggregatorProcessor(E aggregator,String filename) {
		this.agg=aggregator;
		this.filePath=filename;
	}
	
	public double runAggregator(int colNum) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			//skip the first line:
			br.readLine();
			String line = null;
			// populate data from next line onwards
			while((line = br.readLine()) != null){
				String day[]=line.split(",");
				agg.add(Double.parseDouble(day[colNum]));
			}
		}
		return agg.calculate();
	}
	public double runAggregator2(int colNum) throws IOException{
		StockFileReader fileReader = new StockFileReader(filePath);
		List<String> lines = fileReader.readFileData();
		colNum--;
		for(String line:lines) {
			String[] numbers = line.split(",");
			double value = Double.parseDouble(numbers[colNum]);
			agg.add(value);
		}
		return agg.calculate();
	}
}
