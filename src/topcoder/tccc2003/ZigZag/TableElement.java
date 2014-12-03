package topcoder.tccc2003.ZigZag;

public class TableElement {
    public int index = -1; 
    public int value = -1;
    public int longestSequence = -1;
    public int extendsSequence = -1;
    public int direction = -1;
    public TableElement(int index, int value, int longestSeq, int extSeq, int direction){
        this.index = index;
        this.value = value;
        this.longestSequence = longestSeq;
        this.extendsSequence = extSeq;
        this.direction = direction;
    }
}
