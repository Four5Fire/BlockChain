package com.testin.utils.wrap;

/**
 * 包装辅助类.
 * 
 * @author zhukaiyun
 */
public class WrapMapper {

    /**
     * Instantiates a new wrap mapper.
     */
    private WrapMapper() {
    }

    /**
     * Wrap.
     * 
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @param message
     *            the message
     * @param o
     *            the o
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper<E>(code, message,o);
    }
    
    /**
     * Wrap.
     * 
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @param message
     *            the message
     * @param o
     *            the o
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code, String message) {
        return new Wrapper<E>(code, message,(E)"");
    }

    /**
     * Wrap.
     * 
     * @param <E>
     *            the element type
     * @param code
     *            the code
     * @param message
     *            the message
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code,"", (E)"");
    }


    /**
     * Wrap.
     * 
     * @param <E>
     *            the element type
     * @param e
     *            the e
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper<E>(Wrapper.ERROR_CODE);
    }

    /**
     * Un wrapper.
     * 
     * @param <E>
     *            the element type
     * @param wrapper
     *            the wrapper
     * @return the e
     */
    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getData();
    }

//    /**
//     * Wrap ERROR. code=100
//     * 
//     * @param <E>
//     *            the element type
//     * @return the wrapper< e>
//     */
//    public static <E> Wrapper<E> illegalArgument() {
//        return wrap(Wrapper.ILLEGAL_ARGUMENT_CODE_);
//    }
//    
//    /**
//     * Wrap ERROR. code=10001
//     * 
//     * @param <E>
//     *            the element type
//     * @return the wrapper< e>
//     */
//    public static <E> Wrapper<E> nullResult() {
//        return wrap(Wrapper.NULL_RESULT_CODE_);
//    }

    /**
     * Wrap ERROR. code=500
     * 
     * @param <E>
     *            the element type
     * @return the wrapper< e>
     */
    public static <E> Wrapper<E> error() {
        return wrap(Wrapper.ERROR_CODE,Wrapper.ERROR_MESSAGE);
    }

    /**
     * Wrap SUCCESS. code=200
     * 
     * @param <E>
     *            the element type
     * @return the wrapper< e>
     */
    public static <E> Wrapper<E> ok() {
        return new Wrapper<E>();
    }
    
    public static <E> Wrapper<E> permissionsLimit(){
    	return wrap(Wrapper.PERMISSIONS_LIMIT_CODE,Wrapper.PERMISSIONS_LIMIT_MESSAGE);
    }
}
