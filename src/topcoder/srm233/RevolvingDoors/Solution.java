package topcoder.srm233.RevolvingDoors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) throws Exception {
        // 0)
        int test = 0;/*
        test = test(test, new String[] { "    ### ", "    #E# ", "   ## # ",
                "####  ##", "# S -O-#", "# ###  #", "#      #", "########" }, 2);
        // This is the example from the problem statement.

        // 1)

        test = test(test, new String[] { "#### ", "#S|##", "# O #", "##|E#",
                " ####" }, -1);

        // There is no way to reach the end square.

        // 2)

        test = test(test, new String[] { " |  |  |     |  |  |  |  |  | ",
                " O  O EO -O- O  O  O  O  OS O ",
                " |  |  |     |  |  |  |  |  | " }, 7);
        // The optimal path involves turning the 3rd door twice, and the 5th,
        // 6th, 7th, 8th, and 9th doors once each (counting from the left). Note
        // that the 'S' and 'E' do not block doors, and in fact you must turn
        // the 3rd door twice to end up on the 'E'.
         

        // 3)

        test = test(test, new String[] { "###########", "#    #    #",
                "#  S | E  #", "#    O    #", "#    |    #", "#         #",
                "###########" }, 0);

        // 4)

        test = test(test, new String[] { "        E", "    |    ", "    O    ",
                "    |    ", " -O-S-O- ", "    |    ", "    O    ",
                "    |    ", "         " }, -1);
*/                
       
        // You are stuck, unable to move or turn any doors from this position.

        // 5)

        test = test(test, new String[] { "##E#   ", "#  ##  ", " -O-## ",
                " #  ## ", " ##  ##", "  -O-  ", "##  ## ", " # ### ",
                " #  S  " }, 5);
        /*
        // 6)

        test = test(test, new String[] { "#############", "#  #|##|#   #",
                "#   O  O    #", "# E || || S #", "#    O  O   #",
                "#   #|##|#  #", "#############" }, 4);

        // After all the doors have been turned, the map looks like this:
        // #############
        // # # ## # #
        // # -O--O- #
        // # E S #
        // # -O--O- #
        // # # ## # #
        // #############
*/
    }

    private static int test(int test, String[] input, int expected)
            throws Exception {
        System.out.println("Test" + (test++));
        System.out.println("input: " + print(input));
        System.out.println("Expected: " + expected);
        int actual = solve(input);
        System.out.println("Actual:" + actual);
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("-----------------");
        return test;
    }

    private static Cell start;
    private static Cell end;

    private static int solve(String[] input) throws Exception {
        start = null;
        end = null;
        processGrid(input);
        if (checkFound(start, end)) {
            return 0;
        }
        List<Cell> q = new ArrayList<Cell>();
        q.add(start);
        Map<String,Boolean> bounceBack = new HashMap<String,Boolean>();
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Cell next = q.remove(0);

            boolean isStuck = true;
            while (isStuck && getNumberOfDoorTurns(next)<ans) {
                print(next);
                int hasNeighbors = 0;
                List<Cell> neighborList = findSuccessors(input, next);
                for (Cell neighbor : neighborList) {
                    System.out.print("Neighbor: " + neighbor.getId());
                    hasNeighbors++;
                    if (!next.visited.containsKey(neighbor.getId())) {
                        System.out.println("(Unvisited)");
                        isStuck = false;
                        if (checkFound(neighbor, end)) {
                            int thisAns = getNumberOfDoorTurns(neighbor);
                            if(thisAns<ans)
                            {
                                ans = thisAns;
                            }
                        }
                        else
                        {
                            q.add(neighbor);
                        }
                    } else {
                        System.out.println(" (Visited)");
                    }

                }
                System.out.println("----------------------------------");
                if (isStuck) {
                    if (hasNeighbors == 1) {
                        if(bounceBack.containsKey(next.getId()))
                        {
                            break;
                        }
                        else
                        {
                            bounceBack.put(next.getId(),true);
                        }
                        System.out.println("next: "
                                        + next.getId()
                                        + " is stuck. Unstuck this guy by clearing his visited cache!");
                        next.visited.clear();
                    } else {
                        break;
                    }
                }
            }
        }
        if(ans<Integer.MAX_VALUE){
            return ans;
        }
        return -1;
    }

    private static void print(Cell next) {
        System.out.print("Next: ");
        System.out.println(next.getId());
        System.out.println("Path:");
        boolean first = true;
        if (next.path.size() == 0) {
            System.out.println("None");
        } else {
            for (String p : next.path) {
                if (first) {
                    first = false;
                } else {
                    System.out.print(",");
                }
                System.out.print(p);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int getNumberOfDoorTurns(Cell next) {
        int total = 0;
        for (Door door : next.doors) {
            total += door.getTurns();
        }
        System.out.println("Answer: " + total);
        print(next);
        return total;
    }

    private static List<Cell> findSuccessors(String[] grid, Cell current)
            throws Exception {
        int prevRow = current.row - 1;
        int prevColumn = current.column - 1;
        int nextColumn = current.column + 1;
        int nextRow = current.row + 1;
        List<Cell> list = new ArrayList<Cell>();
        if (withinRowBounds(grid, prevRow)) {
            char val = 'L';
            Cell newCell = new Cell(prevRow, current.column, val);
            if (isFree(val = getValue(grid, current, newCell), Location.Above)) {
                newCell.value = val;
                list.add(newCell);
            }
        }
        if (withinRowBounds(grid, nextRow)) {
            char val = 'P';
            Cell newCell = new Cell(nextRow, current.column, val);
            if (isFree(val = getValue(grid, current, newCell), Location.Below)) {
                newCell.value = val;
                list.add(newCell);
            }
        }
        if (withinColumnBounds(grid, prevColumn)) {
            char val = 'C';
            Cell newCell = new Cell(current.row, prevColumn, val);
            if (isFree(val = getValue(grid, current, newCell), Location.Left)) {
                newCell.value = val;
                list.add(newCell);
            }
        }

        if (withinColumnBounds(grid, nextColumn)) {
            char val = 'W';
            Cell newCell = new Cell(current.row, nextColumn, val);
            if (isFree(val = getValue(grid, current, newCell), Location.Right)) {
                list.add(newCell);
            }
        }
        return list;
    }

    private static boolean withinColumnBounds(String[] grid, int newColumn) {
        return newColumn >= 0 && newColumn < grid[0].length();
    }

    private static boolean withinRowBounds(String[] grid, int newRow) {
        return newRow >= 0 && newRow < grid.length;
    }

    private static boolean isFree(char c, Location loc) {
        return c == ' '
                || c == 'S'
                || c == 'E'
                || (c == '-' && (loc == Location.Above || loc == Location.Below))
                || (c == '|' && (loc == Location.Left || loc == Location.Right));
    }

    private static char getValue(String[] grid, Cell current, Cell newCell)
            throws Exception {
        boolean foundDoor = false;
        char result = '*';
        int row = newCell.row;
        int column = newCell.column;
        current.visited.copy(newCell.visited);
        current.path.copy(newCell.path);
        newCell.path.add(current.getId());
        for (Door door : current.doors) {
            if (door.canOpen(current, newCell)) {
                foundDoor = true;
                // result = door.getValue(newCell);
                Door newDoor = door.clone();
                newCell.path.add(newCell.getId() + "_"+newDoor.getId() + "_opened");
                newDoor.turnDoor();
                newCell.doors.add(newDoor);
                
                char couldBe;
                if(newDoor.nearDoor(newCell))
                {
                    couldBe = newDoor.getValue(newCell);
                }
                else{
                    couldBe = grid[row].charAt(column);
                }
                //newCell.value = result;
                
                //result = ' ';
                result = couldBe;
                
            } else {
                newCell.doors.add(door.clone());
                if(door.nearDoor(newCell)){
                    result = door.getValue(newCell);
                    newCell.value = result;
                    foundDoor = true;
                }
            }
        }
        
        
        newCell.visited.put(current.getId(), true);
        if (foundDoor) {
            newCell.value = result;
            return result;
        } else {
            newCell.value = grid[row].charAt(column);
            return newCell.value;
        }
    }

    private static boolean checkFound(Cell current, Cell target) {
        return current.value == target.value;
    }

    private static void processGrid(String[] grid) throws Exception {
        start = null;
        end = null;
        List<Door> doorList = new ArrayList<Door>();
        for (int i = 0; i < grid.length; i++) {
            String row = grid[i];
            for (int j = 0; j < row.length(); j++) {
                char val = row.charAt(j);
                if (val == 'S' || val == 's') {
                    start = new Cell(i, j, val);
                } else if (val == 'E' || val == 'e') {
                    end = new Cell(i, j, val);
                } else if (val == 'O' || val == 'o') {
                    Door door = Door.createDoor(grid, i, j);
                    doorList.add(door);
                }
            }
        }

        if (start == null) {
            throw new Exception("Start cell could not be located!");
        }

        if (end == null) {
            throw new Exception("End cell could not be located!");
        }

        start.doors = doorList;
    }

    private static String print(String[] input) {
        String result = "\n  ";
        int col = 0;
        for (; col < input[0].length(); col++) {
            result += col%10;
        }
        result += "\n";
        int count = 0;
        for (String s : input) {
            result += (count%10) + ":" + s + "\n";
            count++;
        }
        return result;
    }

}
