package center.unit.letter.shared.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CommonResponse {
    String code;
    String message;

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> SingleCommonResponse<T> ok(T data) {
        return new SingleCommonResponse<>(
                "200",
                "요청에 성공하였습니다.",
                data
        );
    }

    public static <T> ListCommonResponse<T> ok(List<T> dataList) {
        return new ListCommonResponse<>(
                "200",
                "요청에 성공하였습니다.",
                dataList
        );
    }
}
