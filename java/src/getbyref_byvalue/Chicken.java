package getbyref_byvalue;

public class Chicken {
    private Integer weight;
    private Boolean boneless;

    public Chicken(int weight, boolean boneless) {
        this.weight = weight;
        this.boneless = boneless;
    }

    public boolean cookingCompleted() {
        return boneless;
    }

    public Integer getWeight() {
        return weight;
    }

    public Boolean isBoneless() {
        return boneless;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBoneless(boolean boneless) {
        this.boneless = boneless;
    }
}
