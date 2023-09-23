package center.unit.letter.presentation.auth;

import center.unit.letter.infrastructure.kakao.KakaoAuthService;
import center.unit.letter.presentation.auth.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth/kakao")
@RestController
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    @GetMapping("/login")
    public AccessTokenResponse login(@RequestParam("code") String code) {
        return kakaoAuthService.requestToken(code);
    }
}
