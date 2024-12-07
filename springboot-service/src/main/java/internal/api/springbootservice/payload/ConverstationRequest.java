package internal.api.springbootservice.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConverstationRequest {

    private String name;

    public String getName(){
        return this.name;
    }
}
