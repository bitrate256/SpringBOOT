package jpacrud.cms.global.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CE {

    private  int status;
    private  String code;
    private  String message;


    public CE(final int status, final String code, final String message){
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
