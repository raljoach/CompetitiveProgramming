package topcoder.srm209.MedalTable;

public class Item implements Comparable<Item> {
    public String country;
    public int gold = 0;
    public int silver = 0;
    public int bronze = 0;
    
    public Item(String country)
    {
        this.country = country;        
    }
    
    @Override
    public int compareTo(Item other) {
        if (this.gold < other.gold) {
            return -1;
        } else if (this.gold > other.gold) {
            return +1;
        } else if (this.silver < other.silver) {
            return -1;
        } else if (this.silver > other.silver) {
            return +1;
        } else if (this.bronze < other.bronze) {
            return -1;
        } else if (this.bronze > other.bronze) {
            return +1;
        } else {
            return other.country.compareTo(this.country);
        }
    }

    public void increment(int medalIndex) throws Exception {
        switch (medalIndex) {
        case 0:
            this.gold++;
            break;
        case 1:
            this.silver++;
            break;
        case 2:
            this.bronze++;
            break;
        default:
            throw new Exception("Error: MedalIndex value '" + medalIndex
                    + "' not handled");
        }

    }
}
