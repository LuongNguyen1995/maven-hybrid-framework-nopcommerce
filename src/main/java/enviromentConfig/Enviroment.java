package enviromentConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:${envOwner}.properties"})
public interface Enviroment extends Config {

	
	@Key("app.url")
	String applicationUrl();
	
	@Key("app.username")
	String appUsername();
	
	@Key("app.password")
	String appPassword();
	
	
}
