package demo.rsa.gkbn.rsademo;

/**
 * Created by gzbd on 2016/12/7.
 */

public class JniDemo {
    public native String nativeMD5(String msg);

    public native String nativeEncodeAES(String msg);

    public native String nativeDecodeAES(String msg);


    public native String nativeEncryptDES(String msg);

    public native String nativeDecryptDES(String msg);

    public native String nativeDecryptRSA(String msg);

    public native String nativeEncryptRSA(String msg);

    public native String nativeEncryptBase64(String msg);

    public native String nativeDecryptBase64(String msg);
    public native String nativeEncrypt3DES(String string);
    public native String getStringFromNative();
    static {
        System.loadLibrary("native");
    }
}
