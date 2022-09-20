package sever;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;

public class ServerRPC {
    public static void main(String[] args) throws IOException, XmlRpcException {
        System.out.println("Initialitize server...");
        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
        mapping.addHandler("operaciones", operaciones.class);

        WebServer server = new WebServer(1800);
        server. getXmlRpcServer().setHandlerMapping(mapping);
        server.start();
        System.out.println("Server running in http://localhost:1800");
        System.out.println("Waiting request...");
    }
}
