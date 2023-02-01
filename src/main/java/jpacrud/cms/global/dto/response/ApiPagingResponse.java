package jpacrud.cms.global.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiPagingResponse<T> {

    private ApiHeaderResponse header;
    private PagingResponse<T> data;

    public ApiPagingResponse(PagingResponse<T> data){
        this.header = new ApiHeaderResponse();
        this.data = data;
    }
}
