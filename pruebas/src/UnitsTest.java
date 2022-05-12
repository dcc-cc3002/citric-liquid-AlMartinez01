import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UnitsTest {
    private Units prueb;
    String name_prueb = "prueb";

    @BeforeEach
    public void setUp(){
        prueb = new Units(name_prueb,4,1,4,3);
    }

    @Test
    public void buildTest(){
        var expectedPrueb = new Units(name_prueb,4,1,4,3);
        Assertions.assertEquals(expectedPrueb,
                prueb);
    }


}