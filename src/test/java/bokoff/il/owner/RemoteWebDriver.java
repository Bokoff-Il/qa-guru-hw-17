package bokoff.il.owner;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface RemoteWebDriver extends Config {
  String login();
  String password();
}
