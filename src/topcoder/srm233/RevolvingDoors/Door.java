package topcoder.srm233.RevolvingDoors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Door {
    public int turns = 0;
    public Cell center;
    public DoorState state;
    private String[] grid;

    public Door(String[] grid, Cell center, DoorState state) {
        this.grid = grid;
        this.center = center;
        this.state = state;
        this.nearbyCells = getNearbyCells();
    }

    public int getTurns() {
        return this.turns;
    }

    public static Door createDoor(String[] grid, int row, int column)
            throws Exception {
        char val = grid[row].charAt(column);
        DoorState state = getState(grid, row, column);
        Door door = new Door(grid,new Cell(row, column, val), state);
        return door;
    }

    private static DoorState getState(String[] grid, int row, int column)
            throws Exception {
        boolean isHorizontal = true;
        for (int thisCol : getHorizontalColumns(column)) {
            if (grid[row].charAt(thisCol) != '-') {
                isHorizontal = false;
                break;
            }
        }
        if (isHorizontal) {
            return DoorState.Horizontal;
        }
        boolean isVertical = true;
        for (int thisRow : getVerticalRows(row)) {
            if (grid[thisRow].charAt(column) != '|') {
                isVertical = false;
                break;
            }
        }
        if (isVertical) {
            return DoorState.Vertical;
        }
        throw new Exception(
                "Error: Cannot determine door state for door at (Row=" + row
                        + ",Column=" + column + ")");
    }

    public boolean onDoor(Cell spot) {
        int row = spot.row, column = spot.column;
        if (this.state == DoorState.Horizontal) {
            List<Integer> columns = getHorizontalColumns(this.center.column);

            int thisRow = this.center.row;
            if (thisRow == row) {
                /*
                 * if(column==prevColumn || column==nextColumn){ return true; }
                 */
                for (int col : columns) {
                    if (column == col) {
                        return true;
                    }
                }
            }
        } else {
            List<Integer> rows = getVerticalRows(this.center.row);
            int thisColumn = this.center.column;
            if (thisColumn == column) {
                /*
                 * if(row==prevRow || row==nextRow){ return true; }
                 */
                for (int thisRow : rows) {
                    if (row == thisRow) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static List<Integer> getVerticalRows(int row) {
        List<Integer> rows = new ArrayList<Integer>();
        int prevRow = row - 1;
        int nextRow = row + 1;
        rows.add(prevRow);
        rows.add(nextRow);
        return rows;
    }

    private static List<Integer> getHorizontalColumns(int column) {
        List<Integer> columns = new ArrayList<Integer>();
        int prevColumn = column - 1;
        int nextColumn = column + 1;
        columns.add(prevColumn);
        columns.add(nextColumn);
        return columns;
    }

    public char getValue(Cell spot) throws Exception {
        if (this.nearbyCells.containsKey(spot.getId())) {
            return this.nearbyCells.get(spot.getId());
        } else {
            throw new Exception("Error: Row=" + spot.row + ",Column="
                    + spot.column + " is not near door");
        }
    }

    public void turnDoor() {
        ++turns;
        if (this.state == DoorState.Horizontal) {
            this.state = DoorState.Vertical;
        } else {
            this.state = DoorState.Horizontal;
        }
        this.nearbyCells = getNearbyCells();
    }

    public Door clone() {
        Door clone = new Door(this.grid, this.center, this.state);
        clone.turns = this.turns;
        return clone;
    }

    public String getId() {
        return this.state.toString().substring(0, 1) + "Door_R"
                + this.center.row + "_C" + this.center.column;
    }

    public boolean canOpen(Cell current, Cell next) {
        if (this.state == DoorState.Horizontal) {
            if (onDoor(next)) {
                if (Math.abs(current.row - next.row) == 1) {
                    return true;
                }
            }
        } else {
            if (onDoor(next)) {
                if (Math.abs(current.column - next.column) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private Map<String, Character> nearbyCells;
    public boolean nearDoor(Cell spot) {        
        return nearbyCells.containsKey(spot.getId());
    }

    private Map<String, Character> getNearbyCells() {
        Map<String,Character> map = new HashMap<String,Character>();
        int prevRow = this.center.row-1;
        int nextRow = this.center.row+1;
        int prevCol = this.center.column-1;
        int nextCol = this.center.column+1;
        int c = this.center.column;
        int r = this.center.row;
        /*
        //Upper left
        map.put(new Cell(prevRow,prevCol,'9').getId(),' ');
        
        //Lower left
        map.put(new Cell(nextRow,prevCol,'9').getId(),' ');
        
        //Lower right
        map.put(new Cell(nextRow,nextCol,'9').getId(),' ');
        
        //Upper left
        map.put(new Cell(prevRow,nextCol,'9').getId(),' ');
        */
        if(this.state == DoorState.Horizontal)
        {
            //Upper middle
            if(grid[prevRow].charAt(c)=='|')
            {
                map.put(new Cell(prevRow,c,'9').getId(),' ');
            }
            
            //Lower middle
            if(grid[nextRow].charAt(c)=='|')
            {
                map.put(new Cell(nextRow,c,'9').getId(),' ');
            }
            
            //Left middle
            map.put(new Cell(r,prevCol,'9').getId(),'-');
            
            //Right middle
            map.put(new Cell(r,nextCol,'9').getId(),'-');                       
        }
        else
        {
            //Upper middle
            map.put(new Cell(prevRow,c,'9').getId(),'|');
            
            //Lower middle
            map.put(new Cell(nextRow,c,'9').getId(),'|');
            
            //Left middle
            if(grid[r].charAt(prevCol)=='-')
            {
                map.put(new Cell(r,prevCol,'9').getId(),' ');
            }
            //Right middle
            if(grid[r].charAt(nextCol)=='-'){
                map.put(new Cell(r,nextCol,'9').getId(),' ');
            }
        }
        return map;
    }
}
