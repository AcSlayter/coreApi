import com.exception.ApiNotFoundException;
import com.exception.ParameterApiNameNotPassed;
import factorio.FactorioApi;
import org.junit.jupiter.api.*;


/**
 * Created by aaron on 5/27/2018.
 */
class ApiAssignerTest {
    @Test
    void getByteResponseHappyPath() {
        boolean hasException = false;
        ApiAssigner apiAssigner = new ApiAssigner();
        try {
            apiAssigner.getByteResponse("/api/Factorio/getNames.json");
        } catch (Exception e){
            hasException = true;
        }
        Assertions.assertFalse(hasException);

    }

    @Test
    void getByteResponse_ParameterApiNameNotPassed() {
        boolean hasException = false;
        ApiAssigner apiAssigner = new ApiAssigner();
        try {
            apiAssigner.getByteResponse("/api");
        } catch (ParameterApiNameNotPassed e){
            hasException = true;
        } catch (Exception e){
            hasException = false;
        }
        Assertions.assertTrue(hasException);
    }

    @Test
    void getByteResponse_ParameterApiNameNotPassedwithBack() {
        boolean hasException = false;
        ApiAssigner apiAssigner = new ApiAssigner();
        try {
            apiAssigner.getByteResponse("/api/");
        } catch (ParameterApiNameNotPassed e){
            hasException = true;
        } catch (Exception e){
            hasException = false;
        }
        Assertions.assertTrue(hasException);
    }

    @Test
    void getApiReturnTest() {
        ApiAssigner apiAssigner = new ApiAssigner();
        try {
            Object name = apiAssigner.getApi("Factorio");
            Assertions.assertEquals( name.getClass(), FactorioApi.class);
        } catch (ApiNotFoundException e) {
            Assertions.fail("exception");
        }
    }

    @Test
    void getApiApiNotFoundExceptionTest() {
        boolean hasException = false;

        ApiAssigner apiAssigner = new ApiAssigner();
        try {
            Object name = apiAssigner.getApi("fail");
            Assertions.assertEquals( name.getClass(), FactorioApi.class);
        } catch (ApiNotFoundException e) {
            hasException = true;
        }
        Assertions.assertTrue(hasException);
    }

}