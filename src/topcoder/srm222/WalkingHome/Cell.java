package topcoder.srm222.WalkingHome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
    public int row;
    public int column;
    public char value;    
    public int crossing=0;
    private List<String> path = new ArrayList<String>();
    private String id;
    private Map<String,Boolean> visited = new HashMap<String,Boolean>();
    public Cell(int row, int column, char value)
    {
        this.row = row;
        this.column = column;
        this.value = value;
        this.id = "R"+row+"_C"+column;
    }
    public String getId(){return this.id;}
    
    public boolean hasVisited(Cell newCell) {
        return this.visited.containsKey(newCell.id);
    }
    public void addToPath(Cell newCell) {
        putVisited(newCell.visited);
        newCell.visited.put(this.id, true);
        putPath(newCell.path);
        newCell.path.add(this.id);
    }
    private void putPath(List<String> other) {
        for(String p:this.path)
        {
            other.add(p);
        }
    }
    private void putVisited(Map<String,Boolean> other)
    {
        for(String k:this.visited.keySet())
        {
            other.put(k, this.visited.get(k));
        }
    }

    public void clearVisited() {
        this.visited.clear();        
    }
    public String printPath() {
        String result = "";
        boolean first = true;
        for(String p:this.path)
        {
            if(first)
            {
                first = false;
            }
            else{
                result += ",";
            }
            result+=p;
        }
        return result;
    }
}
