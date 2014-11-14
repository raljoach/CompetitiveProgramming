package topcoder.srm233.RevolvingDoors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
    private String id;
    public int row;
    public int column;
    public char value;
    public int openDoorsCount=0;
    public MyMap<String,Boolean> visited = new MyMap<String, Boolean>();
    public List<Door> doors = new ArrayList<Door>();
    public MyPath<String> path = new MyPath<String>();
    public Cell(int row, int column, char value){
        this.row = row;
        this.column = column;
        this.value = value;
        this.id = createId(row, column);
    }
    
    public String getId() { return this.id; }
    private String createId(int row, int column) {
        return "R"+row + "_C"+column;
    }
}
