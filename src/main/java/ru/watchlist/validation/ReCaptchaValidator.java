package ru.watchlist.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ru.watchlist.dto.CaptchaResponseDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;

public class ReCaptchaValidator implements ConstraintValidator<ReCaptcha, Object> {

    @Value("${recaptcha.captcha-url}")
    private String captcha_url;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String url = String.format(captcha_url, secret, value);
        CaptchaResponseDTO response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
         return response.isSuccess();
    }
}
