package exception;

/**
 * Created by aaron on 5/27/2018.
 */
public class ParameterApiNameNotPassed extends ParameterException {

    public ParameterApiNameNotPassed(String requestURL) {
        super("Parameter Api Name NotPassed : ".concat(requestURL));
    }
}
