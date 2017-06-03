package team.sevendwarfs.common;

/**
 * 响应状态码
 *  200 正常
 *  404 资源不存在
 *  500 发生错误 错误信息在 info中
 * Created by deng on 2017/6/3.
 */
public class ResponseState {
    public static String SUCCESS = "200";
    public static String UNFOUND = "404";
    public static String ERROR = "500";

    private String stateCode;
    private String info;

    public ResponseState() {}

    public ResponseState(String stateCode) {
        this.stateCode = stateCode;
    }

    public ResponseState(String stateCode, String info) {
        this.stateCode = stateCode;
        this.info = info;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static void setSUCCESS(String SUCCESS) {
        ResponseState.SUCCESS = SUCCESS;
    }

    public static String getUNFOUND() {
        return UNFOUND;
    }

    public static void setUNFOUND(String UNFOUND) {
        ResponseState.UNFOUND = UNFOUND;
    }

    public static String getERROR() {
        return ERROR;
    }

    public static void setERROR(String ERROR) {
        ResponseState.ERROR = ERROR;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResponseState{" +
                "stateCode='" + stateCode + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
