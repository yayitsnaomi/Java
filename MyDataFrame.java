package project2;



import java.util.*;

public class MyDataFrame {
    // data is a 2-dimensional dataset using list of lists
    private List<List<Comparable>> data = new ArrayList<List<Comparable>>();
    // the header of a dataframe
    private List<String > header = new ArrayList<>();
    // a HashMap maps each header to column index
    private HashMap<String, Integer> map = new HashMap<>();
    // the shape of the dataframe(number of rows and number of columns)
    private Integer[] shape = new Integer[2];
    

    public MyDataFrame(List<List<Comparable>> input_data, List<String> input_header) {
    	// constructor
        this.data = input_data;
        this.header = input_header;
        this.shape[0] = input_data.get(0).size();
        this.shape[1] = input_data.size();
        this.map = new HashMap<>();
        // build HashMap by pairing up the column name and column index
        for (int i = 0; i < input_header.size(); i++) {
            map.put(input_header.get(i), i);
        }
    }

	//print the dataframe
    public void print() {
    	System.out.println(this.header.toString().substring(1,this.header.toString().length()-1));
        System.out.println("----------------------------------");
        for (int i = 0; i < this.shape[0]; i++) {
            String row = "";
            for (int j = 0; j < this.shape[1]; j++) {
                row += this.data.get(j).get(i) + ",";
            }
            System.out.println(row.substring(0, row.length() - 1));
        }
        System.out.println("----------------------------------");
    }

    //get the item of a cell in row i and column j
    public Comparable getCell(int i, int j) {
        return this.data.get(j).get(i);
    }

    //get the item of a cell in row i and specified column name
    public Comparable getCell(int i, String col) {
        return this.data.get(this.map.get(col)).get(i);
    }
 
    //get the shape of dataframe
    public Integer[] getShape() {
        return this.shape;
    }

    //get the 2-dimensional list that stores the data in dataframe
    public List<List<Comparable>> getData() {
        return this.data;
    }

    //get the header of dataframe
    public List<String> getHeader() {
        return header;
    }

    //Returns the first n rows of the data.
    public MyDataFrame head(int n) {
    	//initialize an empty list of lists to store the output
        List<List<Comparable>> newData = new ArrayList<>();
        //if n is smaller than the total number of rows of the dataframe, store the first n rows to newData
        //if n is larger than total number of rows, then store the whole dataframe to newData
        for (int j = 0; j < this.shape[1]; j++) {
            newData.add(this.data.get(j).subList(0, Math.min(n, this.shape[0])));
        }
        return new MyDataFrame(newData, this.header);
    }

    //Returns the last n rows of the data.
    public MyDataFrame tail(int n) {
    	//initialize an empty list of lists to store the output
        List<List<Comparable>> newData = new ArrayList<>();
        //if n is smaller than the total number of rows of the dataframe, store the last n rows to newData
        //if n is larger than total number of rows, then store the whole dataframe to newData
        for (int j = 0; j < this.shape[1]; j++) {
            newData.add(this.data.get(j).subList(Math.max(0, this.shape[0] - n), this.shape[0]));
        }
        return new MyDataFrame(newData, this.header);
    }

    // TODO: 2018/12/1  ask what if the data is empty
    //Returns the type of the column specified by index. If the type is not uniform, return ‘String’.
    public String dType(int index) {
    	// if the dataframe is empty, return None
        if (this.shape[0] == 0) {
            return "None | Dataframe is empty";
        }
        // identify the data type of the first item in that column
        String type = this.getData().get(index).get(0).getClass().getSimpleName();
        // if any item in this column has a data type different from the first item's data type
        // return "String"
        for (int i = 1; i < this.shape[0]; i++) {
            if (!this.getCell(i, index).getClass().getSimpleName().equals(type)) {
                return "String";
            }
        }
        // otherwise return type
        return type;
    }

    //Returns the type of the column specified by name. If the type is not uniform, return ‘String’.
    public String dType(String name) {
    	// convert the column name to column index using HashMap and call the dType method above
        return this.dType(this.map.get(name));
    }
    
    //Returns the column specified by index.
    public MyDataFrame slice(int index) {
    	// initialize an empty list of lists to store the output
        List<List<Comparable>> newData = new ArrayList<>();
        // add the selected column into newData
        newData.add(this.data.get(index));
        // create a arraylist for the selected header
        List<String> newHeader = new ArrayList<>();
        newHeader.add(this.header.get(index));
        // return the new dataframe with selected column and header
        return new MyDataFrame(newData, newHeader);
    }

    public MyDataFrame slice(String name) {
    	// convert the column name to column index using HashMap and call the slice method above
        return this.slice(this.map.get(name));
    }

    public MyDataFrame slice(int[] indexArr) {
        List<List<Comparable>> newData = new ArrayList<>();
        //add the selected columns to newData
        for (int index : indexArr) {
            newData.add(this.data.get(index));
        }
        //add the headers of the selected columns to newHeader
        List<String> newHeader = new ArrayList<>();
        for (int index : indexArr) {
            newHeader.add(this.header.get(index));
        }
        return new MyDataFrame(newData, newHeader);
    }

    public MyDataFrame slice(String[] nameArr) {
    	//convert the selected list of column names to column indexes using HashMap
        int[] indexArr = new int[nameArr.length];
        for (int i = 0; i < nameArr.length; i++) {
            indexArr[i] = this.map.get(nameArr[i]);
        }
        //use the slice method above using the set of indexes found above
        return this.slice(indexArr);
    }

    //Returns data filtered by applying “col op o” on MyDataFrame object
    public MyDataFrame filter(String col, String op, Object o) {
        List<List<Comparable>> newData = new ArrayList<>();
        //Add rows which satisfy the criterion given to newData
        switch (op) {
            case ">":
            	//for each column in dataframe
                for (int j = 0; j < this.shape[1]; j++) {
                	//create a new ArrayList newCol
                    List<Comparable> newCol = new ArrayList<>();
                    //if the item in cell with row index i > comparable o, then add it to newCol
                    for (int i = 0; i < this.shape[0]; i++) {
                        if ((int) this.getCell(i, col) > (int) o) {
                            newCol.add(this.getCell(i, j));
                        }
                    }
                    //after filtering through the whole column, add this newCol to newData
                    newData.add(newCol);
                }
                break;
            case ">=":
                for (int j = 0; j < this.shape[1]; j++) {
                    List<Comparable> newCol = new ArrayList<>();
                    for (int i = 0; i < this.shape[0]; i++) {
                        if ((int) this.getCell(i, col) >= (int) o) {
                            newCol.add(this.getCell(i, j));
                        }
                    }
                    newData.add(newCol);
                }
                break;
            case "<":
                for (int j = 0; j < this.shape[1]; j++) {
                    List<Comparable> newCol = new ArrayList<>();
                    for (int i = 0; i < this.shape[0]; i++) {
                        if ((int) this.getCell(i, col) < (int) o) {
                            newCol.add(this.getCell(i, j));
                        }
                    }
                    newData.add(newCol);
                }
                break;
            case "<=":
                for (int j = 0; j < this.shape[1]; j++) {
                    List<Comparable> newCol = new ArrayList<>();
                    for (int i = 0; i < this.shape[0]; i++) {
                        if ((int) this.getCell(i, col) <= (int) o) {
                            newCol.add(this.getCell(i, j));
                        }
                    }
                    newData.add(newCol);
                }
                break;
            case "=":
                for (int j = 0; j < this.shape[1]; j++) {
                    List<Comparable> newCol = new ArrayList<>();
                    for (int i = 0; i < this.shape[0]; i++) {
                        if (this.getCell(i, col).equals(o)) {
                            newCol.add(this.getCell(i, j));
                        }
                    }
                    newData.add(newCol);
                }
                break;
            case "!=":
                for (int j = 0; j < this.shape[1]; j++) {
                    List<Comparable> newCol = new ArrayList<>();
                    for (int i = 0; i < this.shape[0]; i++) {
                        if (!this.getCell(i, col).equals(o)) {
                            newCol.add(this.getCell(i, j));
                        }
                    }
                    newData.add(newCol);
                }
                break;
        }

        return new MyDataFrame(newData, this.header);
    }

    //Returns the rows starting from index.
    public MyDataFrame loc(int index){
        List<List<Comparable>> newData = new ArrayList<>();
        //for all column in the dataframe
        for (int j=0; j < this.shape[1]; j++) {
            List<Comparable> row = new ArrayList<>();
            //for each row from index to the last row index
            for (int i = index; i < this.shape[0]; i++){
            	//add the content of each cell into row
                row.add(this.data.get(j).get(i));
            }
            //add each row to newData
            newData.add(row);
        }
        return new MyDataFrame(newData, this.header);
    }
    
    //Returns the rows between from and to (including from and to).
    public MyDataFrame loc(int from, int to){
        List<List<Comparable>> newData = new ArrayList<>();
        for (int j=0; j < this.shape[1]; j++) {
            List<Comparable> row = new ArrayList<>();
            for (int i = from; i <= to; i++){
                row.add(this.data.get(j).get(i));
            }
            newData.add(row);
        }
        return new MyDataFrame(newData, this.header);
    }
    
    //Returns the data sorted by the column specified by index in ascending order.
    public MyDataFrame sort(int n) {
        List<List<Comparable>> newData = new ArrayList<>();
        //create a sortKey which equals to the column selected
        List<Comparable> sortKey = this.data.get(n);
        //initialize the ArrayIndexComparator
        ArrayIndexComparator comparator = new ArrayIndexComparator(sortKey);
        //sort the indexes of each column based on the sortKey
        for (int j = 0; j < this.shape[1]; j++) {
            List<Comparable> column = this.data.get(j);
            List<Comparable> sortedColumn = new ArrayList<>();
            Integer[] indexes = comparator.createIndexArray();
            Arrays.sort(indexes, comparator);
            //add the items of each column to sortedColumn based on the sorted keys
            for (int i : indexes) {
                sortedColumn.add(column.get(i));
            }
            newData.add(sortedColumn);
        }
        return new MyDataFrame(newData, this.header);
    }

    //Returns the data sorted by the column specified by name in ascending order.
    public MyDataFrame sort(String name) {
    	//convert the column name to column index then use the method above
        return this.sort(this.map.get(name));
    }
    
    //Returns the minimum element of the column specified by index.
    public Object getMin(int index) {
    	// get the type of the given column
    	String type = dType(index);

    	ArrayList<Object> columns = new ArrayList<Object>();
   		// loop over each element to get the re-formatted column
  		for (int i = 1; i < this.getData().get(index).size(); i++) {
 			columns.add(this.getData().get(index).get(i).toString());
       }

        // when the column is with type String
    	if (type.equals("String")) {
    		String min = columns.get(0).toString();
    		for (int i = 1; i < columns.size(); i++) {
    			if (columns.get(i).toString().compareTo(min) < 0) {
    				min = columns.get(i).toString();
    				}
    			}
    			System.out.println("Min of " + index + " is "+min);
    			return min;

    			// when the column is with type Integer
    	}else {
    		Integer min = Integer.parseInt(columns.get(0).toString());
    		for (int i = 1; i < columns.size(); i++) {
    			if (Integer.parseInt(columns.get(i).toString()) < min) {
    				min = Integer.parseInt(columns.get(i).toString());
    			}
    		}
    		System.out.println("Min of " + index + " is "+ min);
    		return min;
    		}
    	
    }

    //Returns the minimum element of the column specified by label.
    public Object getMin(String name) {
    	//convert column name to column index and use the method above
    	// Use indexOf to get the index of given column and then repeat steps in getMin(int index)
    	Integer index = this.header.indexOf(name);
    	String type = dType(index);

        ArrayList<Object> columns = new ArrayList<Object>();
    	// loop over each element to get the re-formatted column
    	for (int i = 1; i < this.getData().get(index).size(); i++) {
    		columns.add(this.getData().get(index).get(i).toString());
    	}

    	// when the column is with type String
    	if (type.equals("String")) {
    		String min = columns.get(0).toString();
    		for (int i = 1; i < columns.size(); i++) {
    			if (columns.get(i).toString().compareTo(min) < 0) {
    				min = columns.get(i).toString();
    					}
    		}
    		System.out.println("Min of " + index + " is "+min);
    		return min;
    		// when the column is with type Integer	
    	}else {
    		Integer min = Integer.parseInt(columns.get(0).toString());
    		for (int i = 1; i < columns.size(); i++) {
    			if (Integer.parseInt(columns.get(i).toString()) < min) {
    				min = Integer.parseInt(columns.get(i).toString());
    			}
    		}
    		System.out.println("Min of " + name + " is "+ min);
    		return min;
    	}
    }

 
    //Returns the maximum element of the column specified by index.
    public Object getMax(int index) {
    	// get the type of the given column
    	String type = dType(index);

    	ArrayList<Object> columns = new ArrayList<Object>();
    	// loop over each element to get the re-formatted column
   		for (int i = 0; i < this.getData().get(index).size(); i++) {
    		columns.add(this.getData().get(index).get(i).toString());
    	}
    	//System.out.println(columns);
    	// when the column is with type String
    	if (type.equals("String")) {
    		String max = columns.get(0).toString();
    		for (int i = 0; i < columns.size(); i++) {
    			if (columns.get(i).toString().compareTo(max) > 0) {
    				max = columns.get(i).toString();
    			}
    		}

    		return max;

    		// when the column is with type Integer	
    	}else {
    		Integer max = Integer.parseInt(columns.get(0).toString());
    		for (int i = 1; i < columns.size(); i++) {
    			if (Integer.parseInt(columns.get(i).toString()) > max) {
    				max = Integer.parseInt(columns.get(i).toString());
    			}
    		}
    		System.out.println("Max of " + index + " is "+ max);
    		return max;
    			}

    		}

    //Returns the maximum element of the column specified by label.
    public Object getMax(String name) {
    	// Use indexOf to get the index of given column and then repeat steps in getMax(int index)
    			//Integer index = this.getData().get(0).indexOf(label);



    		Integer index = this.header.indexOf(name);
    		String type = dType(index);

    		ArrayList<Object> columns = new ArrayList<Object>();
    		// loop over each element to get the re-formatted column
    		for (int i = 0; i < this.getData().get(index).size(); i++) {
   				columns.add(this.getData().get(index).get(i).toString());
   			}
    		// when the column is with type String
    		if (type.equals("String")) {
   				String max = columns.get(0).toString();
   				for (int i = 1; i < columns.size(); i++) {
   					if (columns.get(i).toString().compareTo(max) > 0) {
   						max = columns.get(i).toString();    					}
    				}
    			System.out.println("Max of " + name + " is "+ max);
    			return max;
    			// when the column is with type Integer	
    		}else {
    			Integer max = Integer.parseInt(columns.get(0).toString());
    			for (int i = 1; i < columns.size(); i++) {
   					if (Integer.parseInt(columns.get(i).toString()) > max) {
   						max = Integer.parseInt(columns.get(i).toString());
   					}
   				}
    			System.out.println("Max of " + name + " is "+ max);
    			return max;
    			}
    		}
}



