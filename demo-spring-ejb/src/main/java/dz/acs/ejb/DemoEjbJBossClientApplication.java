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

import dz.acs.rem.spring.CalcRemote;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@PropertySource(value = {"classpath:jboss.properties"})
@Slf4j
public class DemoEjbJBossClientApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(DemoEjbJBossClientApplication.class, args);
		// Jboss Sample
		CalcImpl bean = ctx.getBean("calcTest", CalcImpl.class);		

		System.out.println("jboss -->" +bean.addRemote(10, 40));
		System.out.println("jboss -->" +bean.addRemote(100, 400));
		System.out.println("jboss -->" +bean.addRemote(1000, 4000));
	}

	@Bean("contextEjbJBoss")
	public Context contextEjbJBoss(
			@Value("${jb.java.naming.factory.initial}") String initial,
			@Value("${jb.java.naming.provider.url}") String url,
			@Value("${jb.jboss.naming.client.ejb.context}") boolean isContext) {

		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, initial);		
		jndiProps.put(Context.PROVIDER_URL, url);
		jndiProps.put("jboss.naming.client.ejb.context", isContext);
		InitialContext context=null;
		try {
			context = new InitialContext(jndiProps);
		} catch (NamingException e) {
			log.error(e.getMessage());
		}
		return context;
	}


	@Bean("calcRemoteJboss")
	public CalcRemote calcRemoteJboss(@Qualifier("contextEjbJBoss") Context context,@Value("${jb.calc.jndi-name}") String jndiName) {
		CalcRemote obj = null;	
		try {
			obj =  (CalcRemote)context.lookup(jndiName);
		} catch (NamingException e) {
			log.error(e.getMessage());
		}
		return obj;

	}
}
