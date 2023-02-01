package jpacrud.cms.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Log4j2
@Component
@RequiredArgsConstructor
public class HttpInterCeptor extends HandlerInterceptorAdapter {
    private final ObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[REQ] ---> [METHOD] {} | [URL] {} | [qs] {} | [TOKEN] {} | reto {}",request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getHeader(HttpHeaders.AUTHORIZATION),request.getHeader("Authorization: Bearer"));

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[RES] ---> [STATUS] {}", response.getStatus());

        //log.info("request" + request.getParts());
       // log.info("requets" + request.get());

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    return " - ";
                }
            }
        }
        return " - ";
    }
}
