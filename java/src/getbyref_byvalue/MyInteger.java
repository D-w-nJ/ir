package getbyref_byvalue;

public class MyInteger {
    private int value;

    public MyInteger(int i){
        this.value = i;
    }

    public void setValue(int input) {
        value = input;
    }

    public int getValue() {
        return value;
    }
}
