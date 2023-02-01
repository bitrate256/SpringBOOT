package jpacrud.cms.global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor()
@AllArgsConstructor
@Setter
public class ApiListResponse<T> {

    private ApiHeaderResponse header;

    private List<T> body;


    public ApiListResponse(List<T> data){
        this.header = new ApiHeaderResponse();
        this.body = data;

    }
}
