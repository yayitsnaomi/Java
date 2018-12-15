package project2;

import java.io.*;
import java.util.*;

public class MyPandas {

    public MyPandas() {
    }

    public MyDataFrame readCSV(String path) throws FileNotFoundException {

        /* Initialization */
        // since the dataset has five columns, initialize five arraylists to store each column
        List<Comparable> state = new ArrayList<>();
        List<Comparable> gender = new ArrayList<>();
        List<Comparable> year = new ArrayList<>();
        List<Comparable> name = new ArrayList<>();
        List<Comparable> count = new ArrayList<>();

        // create a header based on the dataset given
        List<String> header = Arrays.asList("state", "gender", "year", "name", "count");

        File file = new File(path);

        // initialize the two dimensional dataset
        List<List<Comparable>> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // read in each line from the file
                String line = scanner.nextLine();
                state.add(line.split(",")[0]);
                gender.add(line.split(",")[1]);
                year.add(Integer.valueOf(line.split(",")[2]));
                name.add(line.split(",")[3]);
                count.add(Integer.valueOf(line.split(",")[4]));
            }
            data.add(state);
            data.add(gender);
            data.add(year);
            data.add(name);
            data.add(count);

            // construct a new instance of MyDataFrame with data and header created above
            MyDataFrame df = new MyDataFrame(data, header);
            return df;
        }
    }

    public void writeCSV(MyDataFrame df,String path) throws IOException {
		System.out.println(path);
		FileWriter writer = new FileWriter(path);
		String header="";
		// write header
		for(String h:df.getHeader()) {
			header=header+h+",";
		}

		writer.write(header);
		writer.write("\r\n");       
		
        // for each row in the dataframe, write to the file and separate each cell by comma
		for (int i = 0; i < df.getShape()[0]; i++) {
			String row = "";
			for (int j = 0; j < df.getShape()[1]; j++) {
				row += df.getData().get(j).get(i) + ",";
			}
			writer.write(row);
			writer.write("\r\n");
			System.out.println(row.substring(0, row.length() - 1));
		}
		writer.close();
	}


    public MyDataFrame concat(MyDataFrame df1, MyDataFrame df2) throws headerNotMatchException {
        //check whether the headers of two dataframes match, if not, raise headerNotMatch Exception
        if (df1.getHeader().equals(df2.getHeader())) {
            // initialize a new dataframe to store the concatenated data
            List<List<Comparable>> newData = new ArrayList<>();

            // concate the jth column in dataframe1 and dataframe2 where j = column index
            for (int j = 0; j < df1.getShape()[1]; j++) {
                List<Comparable> col = new ArrayList<>();
                col.addAll(df1.getData().get(j));
                col.addAll(df2.getData().get(j));

                //add the concatenated column to newData
                newData.add(col);
            }
            // return the concatenated dataframe with header = header of dataframe1
            return new MyDataFrame(newData, df1.getHeader());
        } else {
            throw new headerNotMatchException();
        }

    }

    @SuppressWarnings("serial")
	static class headerNotMatchException extends Exception {
        headerNotMatchException() {
        }
    }


}

