package project2;

import java.io.IOException;

import project2.MyPandas.headerNotMatchException;

public class test {
    public static void main(String[] args) throws IOException, headerNotMatchException {
        // Test MyPandas readCSV and store the data as MyDataFrame
    	MyPandas pd = new MyPandas();
        MyDataFrame df = pd.readCSV("/Users/qiankejin/Desktop/Project2/test.txt");
        df.print();
        MyDataFrame df2 = pd.readCSV("/Users/qiankejin/Desktop/Project2/test2.txt");
        df2.print();
        MyDataFrame edf = pd.readCSV("/Users/qiankejin/Desktop/Project2/empty.txt");
        edf.print();
        
        
        // Test MyDataFrame.head(int n)
        df.head(5).print();
        System.out.println(df2.getShape()[0]);
        df2.head(20).print();

        // Test MyDataFrame.tail(int n)
        df.tail(5).print();

        // test on empty data
        edf.head(5).print();
        edf.tail(5).print();

        // test MyDataFrame.dtype(int index)
        System.out.println(edf.dType(0));
        System.out.println(df.dType(0));
        System.out.println(df.dType(2));

        // test MyDataFrame.dtype(String name)
        System.out.println(df.dType("state"));
        System.out.println(df.dType("year"));

        //test MyDataFrame.slice(int index)
        df.slice(3).head(10).print();

        //test MyDataFrame.slice(String name)
        df.slice("name").head(10).print();

        //test MyDataFrame.slice(int[] indexArr)
        df.slice(new int[]{3, 4}).head(5).print();
        df.slice(new int[]{1,2,3}).tail(5).print();

        //test MyDataFrame.slice(String[] nameArr)
        df.slice(new String[]{"name", "year"}).head(5).print();
        df.slice(new String[]{"gender", "name", "count"}).tail(5).print();

        //test MyDataFrame.filter(String col, String op, Object o)
        df.filter("name", "=", "Mary").print();
        df.filter("count", ">=", 10).print();
        df.filter("year", "<", 1944).print();

        // test MyDataFrame.loc(int index)
        df.loc(10).print();

        // test MyDataFrame.loc(int from, int to)
        df.loc(2, 4).print();

        // test MyDataFrame.sort(int index)
        df.sort(4).print();
        df.sort(2).print();

        // test MyDataFrame.sort(String name)
        df.sort("name").print();

        // test MyDataFrame.getMin(int index)
        System.out.println(df.getMin(2));

        // test MyDataFrame.getMin(String name)
        System.out.println(df.getMin("name"));
        System.out.println(df.getMin("count"));

        // test MyDataFrame.getMax(int index)
        System.out.println(df.getMax(2));

        // test MyDataFrame.getMax(String name)
        System.out.println(df.getMax("name"));
        System.out.println(df.getMax("count"));

        // test MyPandas.writeCSV(MyDataFrame, path)
        pd.writeCSV(df.filter("count", ">=", 10), "ge10.txt");

        // test MyPandas.concat(MyDataFrame df1, MyDataFrame df2))
        pd.concat(df, df2).print();
        pd.writeCSV(pd.concat(df, df2), "con1.txt");


    }
}