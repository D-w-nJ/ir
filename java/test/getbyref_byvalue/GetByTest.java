package getbyref_byvalue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GetByTest {

    @Test
    @DisplayName("get by reference")
    void testGetByRef() {
        // given
        int chickenWeight = 1100;
        Chicken chicken = new Chicken(chickenWeight, false);

        // when
        GetBy getBy = new GetBy();
        getBy.makeBonelessByReference(chicken);

        // then
        Assertions.assertNotEquals(chickenWeight, chicken.getWeight());
        Assertions.assertEquals(chickenWeight - GetBy.CHICKEN_BONE_WIGHT, chicken.getWeight());
    }

    @Test
    @DisplayName("get by value")
    void testGetByValue() {
        // given
        int chickenWeight = 1100;
        Chicken chicken = new Chicken(chickenWeight, false);

        // when
        GetBy getBy = new GetBy();
        getBy.makeBonelessByValue(chicken.getWeight(), chicken.isBoneless());

        // then
        Assertions.assertEquals(chickenWeight, chicken.getWeight());
    }

    @Test
    @DisplayName("get by Wrapper")
    void testGetByWrapper() {
        // given
        int chickenWeight = 1100;
        Chicken chicken = new Chicken(chickenWeight, false);

        // when
        GetBy getBy = new GetBy();
        getBy.makeBonelessByWrapper(chicken.getWeight(), chicken.isBoneless());

        // then
        System.out.println("chicken.getWeight() = " + chicken.getWeight());
        System.out.println("chicken.isBoneless() = " + chicken.isBoneless());
        Assertions.assertEquals(chickenWeight, chicken.getWeight());
        Assertions.assertNotEquals(chickenWeight - GetBy.CHICKEN_BONE_WIGHT, chicken.getWeight());
    }

}