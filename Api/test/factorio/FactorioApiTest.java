package factorio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by aaron on 5/27/2018.
 */
class FactorioApiTest {
    @Test
    void getSubArray() {
        FactorioApi factorioApi = new FactorioApi();
        String name = factorioApi.getSubArray("/api/Factorio/nothing");
        assertTrue(name.equals("/nothing"));
    }

    @Test
    void getByteResponse_InvalidApiRequest() {
        boolean throwsException = false;
        FactorioApi factorioApi = new FactorioApi();
        try {
            factorioApi.getByteResponse("/api/Factorio/nothing");
        } catch (Exception e) {
            throwsException = true;
        }
        assertTrue(throwsException);
    }

    @Test
    void getByteResponse_getNames(){
        try {
            FactorioApi factorioApi = new FactorioApi();
            factorioApi.getByteResponse("/api/Factorio/getNames.json");
        } catch (Exception e) {
            fail("caught exception");
        }
    }
    @Test
    void getByteResponse_getItemRecipe() {
        try {
            FactorioApi factorioApi = new FactorioApi();
            factorioApi.getSubArray("/api/Factorio/getItemRecipe/Copper_plate");
        } catch (Exception e) {
            fail("caught exception");
        }
    }
    @Test
    void getByteResponse_getBreakDown() {
        try {
            FactorioApi factorioApi = new FactorioApi();
            factorioApi.getSubArray("/api/Factorio/getBreakDown.json");
        } catch (Exception e) {
            fail("caught exception");
        }
    }

}