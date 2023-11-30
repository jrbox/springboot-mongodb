package com.jrbox.springbootmongodb.config;

import com.jrbox.springbootmongodb.domain.BusinessErrorMessage;
import com.jrbox.springbootmongodb.exception.BusinessErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;

/**
 * Gestionnaire des erreurs métier.
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.module.name}")
    private String moduleName;

    @Autowired
    private BusinessErrorProperties businessErrorProperties;

    @ExceptionHandler(BusinessErrorException.class)
    public ResponseEntity<BusinessErrorMessage> handlerBusinessError(BusinessErrorException ex) {
        var code = ex.getBusinessError().getCode();
        var reason = String.format(businessErrorProperties.getErrorByCode(code), ex.getParameters());
        var businessErrorMessage = BusinessErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .service(getInfoService())
                .severity("Erreur").code(code)
                .reason(reason).build();
        return new ResponseEntity<>(businessErrorMessage, ex.getHttpStatus());
    }

    /**
     * Implémentation pour la Servlet Stack.
     * @return le message d'erreur.
     */
    protected String getInfoService() {
        String httpMethod = "?";
        String path = "?";
        ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequest != null) {
            httpMethod = servletRequest.getRequest().getMethod();
            path = servletRequest.getRequest().getRequestURI();
        }
        return String.format("[%s] [%s] [%s] - %s", this.moduleName, this.appName, httpMethod, path);
    }

    /**
     * Implémentation pour la Reactive Stack.
     * @param serverWebExchange l'échange HTTP.
     * @return le message d'erreur.
     */
    protected String buildService(ServerWebExchange serverWebExchange) {
        var httpMethod = serverWebExchange.getRequest().getMethod();
        var path = serverWebExchange.getRequest().getPath();
        return String.format("[%s] [%s] [%s] - %s", this.moduleName, this.appName, httpMethod, path);

    }
}
