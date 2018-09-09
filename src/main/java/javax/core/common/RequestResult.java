package javax.core.common;

import java.io.Serializable;

/**
 * Demo class
 *
 * @author dubber
 * @date 2018/9/8
 */
public class RequestResult<T> implements Serializable {

    /**
     * 请求结果状态码
     */
    private int resultCode;
    /**
     * 请求结果状态值
     */
    private String resultMsg;

    /**
     * 输入任意对象
     */
    private T data;

    public RequestResult() {
    }

    public RequestResult(int resultCode) {
        this.resultCode = resultCode;
    }

    public RequestResult(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public RequestResult(int resultCode, T data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public RequestResult(int resultCode, String resultMsg, T data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
