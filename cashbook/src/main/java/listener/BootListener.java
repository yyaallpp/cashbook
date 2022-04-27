package listener;

import java.sql.DriverManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {
	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
		// 현재 접속자 카운트 속성
		sce.getServletContext().setAttribute("currentCount", 0);
	
    }
}