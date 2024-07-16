package getbyref_byvalue;

public class GetBy {
    public static final int CHICKEN_BONE_WIGHT = 736;

    public void makeBonelessByReference(Chicken chicken) throws RuntimeException {
        if (chicken.cookingCompleted()) {
            throw new RuntimeException("이미 조리 완료된 치킨입니다.");
        }

        chicken.setBoneless(true);
        chicken.setWeight(chicken.getWeight() - CHICKEN_BONE_WIGHT);
    }

    public void makeBonelessByValue(int chickenWeight, boolean boneless) {
        chickenWeight -= CHICKEN_BONE_WIGHT;
        boneless = true;
    }

    public void makeBonelessByWrapper(Integer chickenWeight, Boolean boneless) {
        chickenWeight = Integer.valueOf(chickenWeight - CHICKEN_BONE_WIGHT);
        boneless = Boolean.valueOf(true);
    }
}
