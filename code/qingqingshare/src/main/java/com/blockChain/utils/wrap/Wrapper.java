package com.testin.utils.wrap;

import java.io.Serializable;

/**
 * 包装类.
 * 
 * @param <T>
 *            the generic type
 * @author zhukaiyun
 */
public class Wrapper<T> implements Serializable {
    
    /** 序列化标识 */
    private static final long  serialVersionUID          = 4893280118017319089L;
    
    /** 成功码. */
    public static final int    SUCCESS_NO                = 0;
    
    /** 成功信息. */
    public static final String SUCCESS_MESSAGE           = "SUCCESS";
    
    /** 错误码. */
    public static final int    ERROR_CODE                = 500;
    /** 没有权限 */
    public static final int    PERMISSIONS_LIMIT_CODE    = 20101;
    /** 数据已经存在 */
    public static final int    DATA_EXIST                = 10004;
    
    /** 错误信息. */
    public static final String ERROR_MESSAGE             = "内部异常";
    
    /** 错误信息：参数非法 */
    public static final String ILLEGAL_ARGUMENT_MESSAGE  = "参数非法";
    
    public static final String PERMISSIONS_LIMIT_MESSAGE = "权限限制";
    /** 请创建版本 **/
    public static final int    ERROR_CREATE_VERSION      = 20100;
    /** 请创建指标 **/
    public static final int    ERROR_CREATE_METRIC       = 20107;
    
    /** 请创建变量 **/
    public static final int    ERROR_CREATE_VARIABLE     = 20102;
    /** 账户失效 **/
    public static final int    ERROR_EXPIRED_USER        = 20103;
    
    /**
     * 正则表达式含有非法变量
     */
    public static final int    ERROR_METRIC_EXPRESSION   = 20104;
    
    /**
     * 正则表达式含有非法变量
     */
    public static final int    ERROR_IN_PARAM            = 20105;
    /**
     * 不允许进行该操作
     */
    public static final int    ERROR_NOT_ALLOWED         = 20106;
    
    /**
     * 不允许进行该操作
     */
    public static final int    ERROR_FOR_RUNNING       = 20107;
    
    
    /**
       * 登录失败
       */
    public static final int ERROR_LOGIN=1000;
      
    /** 编号. */
    private int                errNo;
    
    /** 结果数据 */
    private T                  data;
    
    /**
     * 响应状态描述
     */
    private String             message;
    
    /**
     * Instantiates a new wrapper. default code=200
     */
    public Wrapper() {
        this(SUCCESS_NO,
             SUCCESS_MESSAGE);
    }
    
    /**
     * Instantiates a new wrapper.
     * 
     * @param code
     *            the code
     * @param message
     *            the message
     */
    public Wrapper(int code) {
        this(code,
             "",
             (T) "");
    }
    
    /**
     * Instantiates a new wrapper.
     * 
     * @param code
     *            the code
     * @param message
     *            the message
     */
    public Wrapper(int code,
                   String message) {
        this(code,
             message,
             (T) "");
    }
    
    /**
     * Instantiates a new wrapper.
     * 
     * @param code
     *            the code
     * @param message
     *            the message
     * @param result
     *            the result
     */
    public Wrapper(int code,
                   String message,
                   T result) {
        super();
        this.code(code)
            .message(message)
            .result(result);
    }
    
    /**
     * Gets the 编号.
     * 
     * @return the 编号
     */
    public int getErrNo() {
        return errNo;
    }
    
    /**
     * Sets the 编号.
     * 
     * @param code
     *            the new 编号
     */
    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Gets the 结果数据.
     * 
     * @return the 结果数据
     */
    public T getData() {
        return data;
    }
    
    /**
     * Sets the 结果数据.
     * 
     * @param result
     *            the new 结果数据
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Sets the 编号 ，返回自身的引用.
     * 
     * @param errNo
     *            the new 编号
     * @return the wrapper
     */
    public Wrapper<T> code(int errNo) {
        this.setErrNo(errNo);
        return this;
    }
    
    /**
     * Sets the 结果数据 ，返回自身的引用.
     * 
     * @param result
     *            the new 结果数据
     * @return the wrapper
     */
    public Wrapper<T> result(T data) {
        this.setData(data);
        return this;
    }
    
    /**
     * Sets the 结果数据 ，返回自身的引用.
     * 
     * @param result
     *            the new 结果数据
     * @return the wrapper
     */
    public Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }
    
    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE == this.code
     * 
     * @return code=200,true;否则 false.
     */
    public boolean isSuccess() {
        return Wrapper.SUCCESS_NO == this.errNo;
    }
    
}
