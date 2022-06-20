package bokoff.il.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {
  private String email;
  private String password;

  public Credentials(String email, String password) {
    this.email=email;
    this.password=password;
  }
}
