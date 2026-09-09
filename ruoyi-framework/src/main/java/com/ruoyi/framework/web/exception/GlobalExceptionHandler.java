package com.ruoyi.framework.web.exception;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.DemoModeException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${feishu.error-notification.webhook:}")
    private String feishuErrorNotificationWebhook;

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
            HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return AjaxResult.error(e.getMessage());
    }
    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error(message);
    }
    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult handleBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }
    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e, HttpServletRequest request)
    {
        log.warn(e.getMessage(), e);
        Integer code = e.getCode();
        return StringUtils.isNotNull(code) ? AjaxResult.error(code, e.getMessage()) : AjaxResult.error(e.getMessage());
    }



    @ExceptionHandler(BaseException.class)
    public AjaxResult handleBaseException(BaseException e, HttpServletRequest request) {
        log.warn("请求地址'{}',发生未知异常.", request.getRequestURI(), e);
        return AjaxResult.error(e.getMessage(), e.getCode());
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult handleDemoModeException(DemoModeException e)
    {
        return AjaxResult.error("演示模式，不允许操作");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        sendFeishuErrorNotification(e, request);
        return AjaxResult.error(e.getMessage());
    }
    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        sendFeishuErrorNotification(e, request);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 向飞书机器人发送未处理异常通知。
     *
     * @param e 未处理的异常
     * @param request 当前请求
     * @return 无返回值
     */
    private void sendFeishuErrorNotification(Exception e, HttpServletRequest request)
    {
        if (StringUtils.isBlank(feishuErrorNotificationWebhook))
        {
            return;
        }
        try
        {
            String message = StringUtils.defaultIfBlank(e.getMessage(), e.getClass().getName());
            Map<String, Object> content = new HashMap<>();
            content.put("text", String.format("【商城系统异常】\n请求：%s %s\n异常：%s\n信息：%s",
                    request.getMethod(), request.getRequestURI(), e.getClass().getName(), StringUtils.abbreviate(message, 1000)));
            Map<String, Object> payload = new HashMap<>();
            payload.put("msg_type", "text");
            payload.put("content", content);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            restTemplate.postForObject(feishuErrorNotificationWebhook,
                    new org.springframework.http.HttpEntity<>(payload, headers), String.class);
        }
        catch (Exception notificationException)
        {
            log.error("飞书异常通知发送失败", notificationException);
        }
    }
}
