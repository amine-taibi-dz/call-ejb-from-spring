package dz.acs.ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import dz.elit.jee.demo.CalcRemote;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@PropertySource(value = {"classpath:glassfish.properties"})
@Slf4j
public class DemoEjbGlassfishClientApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(DemoEjbGlassfishClientApplication.class, args);
		TestCalc bean = ctx.getBean("test_calc", TestCalc.class);		
		System.out.println("GF -->" +bean.addRemote(10, 20));
		System.out.println("GF -->" +bean.addRemote(100, 200));
		System.out.println("GF -->" +bean.addRemote(1000, 2000));
	}

	@Bean("contextEjbGF")
	public Context contextEjbGF(
			@Value("${gf.java.naming.factory.initial}") String initial,
			@Value("${gf.java.naming.factory.url.pkgs}") String pkgs,
			@Value("${gf.java.naming.factory.state}") String state,
			@Value("${gf.org.omg.CORBA.ORBInitialHost}") String host,
			@Value("${gf.org.omg.CORBA.ORBInitialPort}") String port) {

		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,initial);
		jndiProps.put(Context.URL_PKG_PREFIXES,pkgs);
		jndiProps.put(Context.STATE_FACTORIES,state);
		//Special GlassFish
		jndiProps.put("org.omg.CORBA.ORBInitialHost",host);
		jndiProps.put("org.omg.CORBA.ORBInitialPort",port);

		InitialContext context=null;
		try {
			context = new InitialContext(jndiProps);
		} catch (NamingException e) {
			log.error(e.getMessage());
		}
		return context;
	}

	@Bean("calcRemoteGlassFish")
	public CalcRemote calcRemoteGlassFish(@Qualifier("contextEjbGF") Context context,@Value("${gf.calc.jndi-name}") String jndiName) {
		CalcRemote obj = null;	
		try {
			obj =  (CalcRemote)context.lookup(jndiName);
		} catch (NamingException e) {
			log.error(e.getMessage());
		}
		return obj;
	}
}
