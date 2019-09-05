package springLogSecure.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springLogSecure")
@PropertySource("classpath:persistence.properties")
public class ConfigurationLog {

    // enviroment bude premenna uchovavajuca pesistence.properties pozor na import !!! musi to byt ten hore
    @Autowired
    private Environment environment;

// loger nastaveny na diagnostiku ?????
    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver vymyslenyNazovViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSource securityDataSource(){

        //tvorba pripojenia na pool

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        //nastavenie JDBC driver triedy ... musi byt try lebo moze hadzat chyby...

        try {
            securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException exp) {
            throw new RuntimeException(exp);
        }

        //logovnie cize sledovanie toho co sa deje na pozadi ... vid video na learn2code

        logger.info(">>> jdbc.url=" + environment.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user=" + environment.getProperty("jdbc.user"));

        //nastavenie pripojenia na persistence.properties

        securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        securityDataSource.setUser(environment.getProperty("jdbc.user"));
        securityDataSource.setPassword(environment.getProperty("jdbc.password"));

        //nastavenie connection pool propertie
//                                          pool size sa nachadza v pesistence.properties!!!!
        // tie pool treba dostudovat !!!!!

        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    // cita enviroment a konvvertuje na int

    private int getIntProperty(String propName){
        String propVal = environment.getProperty(propName);

        //convertovanie na int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }
}
