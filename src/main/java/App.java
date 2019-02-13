import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception {
        UserStorage security = new UserStorageHashMap();

        ServletExamples servlet1 = new ServletExamples();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new ServletCookies()), "/c/*");
        handler.addServlet(new ServletHolder(servlet1), "/admin/*");
        handler.addServlet(new ServletHolder(new ServletLogin(security)), "/login/*");
        handler.addServlet(new ServletHolder(new ServletCalculator(security)), "/calc/*");

        handler.addFilter(new FilterHolder(new CalculatorFilter()), "/calc", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(CalculatorFilter.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(CalculatorFilterDivByZero.class, "/calc/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

        Server server = new Server(80);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
