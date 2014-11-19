package topcoder.srm219.TurntableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    public Map<Integer, List<Integer>> didNotEatHash;
    public Map<Integer, List<Integer>> distFood;
    public int numPlates;
    public int shift;
    public int time;
    public String path = "";
    public int[] whenAte;
    private int round=0;

    public Table(Map<Integer, List<Integer>> didNotEatHash,Map<Integer, List<Integer>> distFood, int n, int shift, int time) {
        this.whenAte = new int[n];
        for(int i=0; i<n; i++)
        {
            this.whenAte[i] = -9;
        }
        this.didNotEatHash = didNotEatHash;
        this.distFood = distFood;
        this.numPlates = n;
        this.shift = shift;
        this.time = time;
        if(shift==0){
            this.path="0";
        }
    }

    public int indexOf(int plateNum) {
        // Example: [0 1 2 3], shift: 2
        // [2 3 0 1] => f(0): 2 f(1): 3, f(2): 0, f(3): 1
        // f(0): (0+2)%4 = 2
        // f(1): (1+2)%4 = 3
        // f(2): (2+2)%4 = 0
        // f(3): (3+2)%4 = 1
        int spot = mod(plateNum + shift, this.numPlates);
        return spot;

        // Example: [0 1 2 3], shift: -3
        // [3 0 1 2] => f(0): 1 f(1): 2, f(2): 3, f(3): 0
        // f(0): (0-3)%4 = 1
        // f(1): (1-3)%4 = 2
        // f(2): (2-3)%4 = 3
        // f(3): (3-3)%4 = 0
    }

    public int getItem(int spot) {
        // Example: [0 1 2 3], shift: 2
        // [2 3 0 1] => f(0): 2 f(1): 3, f(2): 0, f(3): 1
        // f(0): (0-2)%4 = 2
        // f(1): (1-2)%4 = 3
        // f(2): (2-2)%4 = 0
        // f(3): (3-2)%4 = 1
        int plateNum = mod(spot - shift, this.numPlates);
        return plateNum;

        // Example: [0 1 2 3], shift: -3
        // [3 0 1 2] => f(0): 3 f(1): 0, f(2): 1, f(3): 2
        // f(0): (0+3)%4 = 3
        // f(1): (1+3)%4 = 0 //Broken
        // f(2): (2+3)%4 = 1
        // f(3): (3+3)%4 = 2 //Broken
    }

    public Table clone() {
        Map<Integer,List<Integer>> newDidNotEat = new HashMap<Integer,List<Integer>>();
        Map<Integer,List<Integer>> newDistFood = new HashMap<Integer,List<Integer>>();
        int[] newWhenAte = new int[this.whenAte.length];
        copyVals(this.didNotEatHash,newDidNotEat);
        copyVals(this.distFood,newDistFood);
        copyArray(this.whenAte,newWhenAte);
        
        Table t = new Table(
                newDidNotEat,
                newDistFood,
                this.numPlates,
                this.shift,
                this.time);
        t.whenAte = newWhenAte;
        t.path = this.path;
        t.round = this.round;
        return t;
    }    

    private void copyArray(int[] source, int[] dest) {
        for(int i=0; i<source.length; i++)
        {
            dest[i] = source[i];
        }
    }

    public void shiftItems(int delta) throws Exception {
        if(!this.path.equals("")){
            path+=",";
        }
        this.path+=delta;
        this.shift += delta;
        if(delta!=0){
            this.time += Math.abs(delta) + 1;
        }
        this.round++;
        recalculate();
    }

    private void recalculate() throws Exception {
        int[] ate = new int[this.numPlates];
        boolean anybodyEat = false;
        List<Integer> removeFromDidNotEat = new ArrayList<Integer>();
        for (int person : this.didNotEatHash.keySet()) {
            List<Integer> hisFaves = this.didNotEatHash.get(person);
            int tableItem = getItem(person); //Get current food in front of person aka at spot
            for (int j = 0; j < hisFaves.size(); j++) {
                int currentFaveFood = hisFaves.get(j);                
                if (currentFaveFood == tableItem) { //Have a match, somebody eats (15s)
                    anybodyEat = true;
                    ate[person]++;
                    whenAte[person] = round;
                    removeFromDidNotEat.add(person); //Person no longer needs to eat
                    break;
                } else { //No match = currentFaveFood is not in front of person => person stays in didNotEatHash
                         //           however distance to currentFaveFood item has changed
                    if (ate[person] == 0) {
                        /*
                        int start = person;
                        int dist1 = 0;
                        while (start != food) {
                            --dist1;
                            if (start == 0) {
                                start = n;
                            }
                        }
                        int dist2 = 0;
                        start = person;
                        while (start != food) {
                            ++dist1;
                            if (start == (n - 1)) {
                                start = 0;
                            }
                        }*/
                        
                        // Example: [0 1 2 3], shift: 2
                        //          [2 3 0 1] 
                        //
                        // currentFaveFood = 2, person=3
                        // foodSpot = 0
                        // dist1 = 3-0 = 3
                        // dist2 = 3-4 = -1
                        int foodSpot = indexOf(currentFaveFood);
                        int dist1 = person-foodSpot;
                        int dist2 = Integer.MAX_VALUE;
                        if(dist1==0){ throw new Exception("Error in code!");}
                        if(dist1>0)
                        {
                            dist2 = dist1-this.numPlates;
                        }
                        else{
                            dist2 = this.numPlates+dist1;
                        }
                        
                        // Example: [0 1 2 3], shift: 2
                        //          [2 3 0 1] 
                        //
                        // currentFaveFood = 1, person=0
                        // foodSpot = 3
                        // dist1 = 0-3 = -3
                        // dist2 = 4-3 = 1
                                                                         
                        //int shortestDist = Math.min(dist1, dist2);
                        
                        int shortestDist=dist2;
                        if(Math.abs(dist1)<Math.abs(dist2))
                        {
                            shortestDist = dist1;
                        }

                        /*
                        if (didNotEatHash.containsKey(person)) {
                            //didNotEatHash.get(person).add(food);
                            //distFood.get(person).add(shortestDist);                            
                        } else {
                            List<Integer> foodList = new ArrayList<Integer>();
                            didNotEatHash.put(person, foodList);
                            foodList.add(food);

                            List<Integer> distList = new ArrayList<Integer>();
                            distList.add(shortestDist);
                            distFood.put(person, distList);
                        }
                        */
                        
                        distFood.get(person).set(j, shortestDist);
                    }
                }
            }
        }

        if (anybodyEat) {
            this.time += 15;
        }
        
        for(int p:removeFromDidNotEat)
        {
            this.didNotEatHash.remove(p);
            this.distFood.remove(p);
        }
    }
    
    private static void copyVals(Map<Integer, List<Integer>> source,
            Map<Integer, List<Integer>> dest) {
        for(int k:source.keySet())
        {
            List<Integer> newList = new ArrayList<Integer>();
            copyList(source.get(k),newList);
            dest.put(k, newList);
        }
        
    }

    private static void copyList(List<Integer> source, List<Integer> dest) {
        for(int v:source)
        {
            dest.add(v);
        }
    }

    private static int mod(int calc, int n) {
        int result;
        if (calc < 0) {
            calc = Math.abs(calc);
            calc = calc % n;
            if (calc == 0) {
                result = 0;
            } else {
                result = n - calc;
            }
        } else {
            result = calc % n;
        }
        return result;
    }

}
