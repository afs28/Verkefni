package hi.verkefni.vidmot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    private Payment price1;
    private Payment price2;

    @Before
    public void setUp() {
        price1 = new Payment(2000);
        price2 = new Payment(40000);
    }

    @After
    public void tearDown() {
        price1 = null;
        price2 = null;
    }

    @Test
    public void testPrice() {
        //fékk villu þegar ég sleppti Integer.valueOf(), lagaðist þegar ég bætti því við.
        assertEquals(Integer.valueOf(2000), price1.getPrice());
        assertEquals(Integer.valueOf(40000), price2.getPrice());
    }

}
