import helper.RequestInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by aaron on 5/24/2018.
 */
public class ConnectionHandler implements Runnable {

    private final Socket socket;
    private String rootDir;
    private ApiAssigner apiAssigner;
    private LOGGER logger = new LOGGER("logs/Error.csv", false);

    public ConnectionHandler(Socket local_socket, String rootDirectory, ApiAssigner apiassigner){
        this.socket = local_socket;
        this.rootDir = rootDirectory;
        this.apiAssigner = apiassigner;
    }
    public ConnectionHandler(Socket local_socket){
        this(local_socket,"Static",new ApiAssigner());
    }
    public void run() {
        RequestInfo info = new RequestInfo();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = socket.getOutputStream();

            String line;
            while( (line = in.readLine()) != null){
                if(line.isEmpty()){
                    break;
                }else if(line.contains(":")) {
                    info.addItem(line);
                }else if(line.contains("GET")) {
                    info.addGet(line);
                }
            }

            byte[] returnType = new byte[0];

            String type = "text/html";
            boolean notFound = false;
            try {
                if(info.getRequestURL().contains("api")){
                    returnType = apiAssigner.getByteResponse(info.getRequestURL()) ;
                    type = "application/json";
                } else {
                    returnType = FileSystemFile.getFileSystemFile(this.rootDir,info.getRequestURL());
                }
                socket.getRemoteSocketAddress();
            } catch (Exception e) {
                notFound = true;
                logger.log(e,info.getRequestURL().concat(", remoteSocket=").concat(this.socket.getRemoteSocketAddress().toString()));
            }

            if(info.getRequestURL().contains(".png")){
                type =  "image/png";
            } else if (info.getRequestURL().contains(".css")){
                type =  "text/css";
            }

            String httpResponse = "";
            byte[] bytes = null;
            if (notFound) {
                httpResponse = "HTTP/1.1 404 Not Found ; Content-Type: text/html \r\n\r\n";
                bytes = httpResponse.getBytes("UTF-8");
                out.write(bytes);
                out.write("NOT FOUND".getBytes());
            } else {
                httpResponse = "HTTP/1.1 200 charset=UTF-8 OK; Content-Type: " + type +"; \r\n\r\n";
                bytes = httpResponse.getBytes("UTF-8");
                out.write(bytes);
                out.write(returnType);
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
