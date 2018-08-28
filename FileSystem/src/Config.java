import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
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

    public static void SaveData(Properties applicationProps) {
        try {
            FileOutputStream out = new FileOutputStream("app.Properties.data");
            applicationProps.store(out, "---No Comment---");
            out.close();
        }catch (Exception e){

        }
    }

    public static Properties getProperties() throws IOException {
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("app.Properties.data");
        defaultProps.load(in);
        in.close();
        return defaultProps;
    }
}
