import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        try {
//             Properties defaultProps = new Properties();
//             defaultProps.setProperty("Default-Port", "8080");
//             SaveData(defaultProps);

            Properties test = getProperties();
            System.out.println(test.getProperty("Default-Port"));
        } catch (Exception e){

        }



    }

    private static void SaveData(Properties applicationProps) throws IOException {
        FileOutputStream out = new FileOutputStream("appProperties");
        applicationProps.store(out, "---No Comment---");
        out.close();
    }

    private static Properties getProperties() throws IOException {
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("appProperties");
        defaultProps.load(in);
        in.close();
        return defaultProps;
    }
}
