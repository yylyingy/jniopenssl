package demo.rsa.gkbn.rsademo;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(new JniDemo().getStringFromNative());
        Button rsa = (Button) findViewById(R.id.button);
        Button des = (Button) findViewById(R.id.button5);

        Button aes = (Button) findViewById(R.id.button6);
        Button md5 = (Button) findViewById(R.id.button7);
        Button base64 = (Button) findViewById(R.id.button8);
        Button test3des = (Button) findViewById(R.id.test3des);
        final JniDemo jniDemo = new JniDemo();


        test3des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, jniDemo.nativeEncrypt3DES("sadf"), Toast.LENGTH_SHORT).show();
                try {
                    String text = "aslkdfjlasf";
                    String javaEncrypt= "";
                    String nativeEncrypt = "";
                    byte[] bytesJava = encryptTextURL(text,"i^FgWOB8IsN47zja^^&eSBup").getBytes();
                    byte[]bytesNative= jniDemo.nativeEncryptDES(text).getBytes();
                    for (int i = 0;i < bytesJava.length;i ++){
                        javaEncrypt += Integer.toHexString(bytesJava[i]);
                    }
                    for (int i = 0;i < bytesNative.length;i ++){
                        nativeEncrypt += Integer.toHexString(bytesNative[i]);
                    }
                    Log.d("native1",nativeEncrypt);
                    Log.d("jav   a",javaEncrypt);
                    Log.d("native2",jniDemo.nativeEncrypt3DES(text));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        rsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tv.setText(new JniDemo().nativeDecryptRSA(new JniDemo().nativeEncryptRSA("RSA加密测试-RSA加密测试-RSA加密测试-RSA加密测试")));
            }
        });

        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(new JniDemo().nativeDecryptDES(new JniDemo().nativeEncryptDES("DES加密测试-DES加密测试-DES加密测试-DES加密测试")));
            }
        });

        aes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(new JniDemo().nativeDecodeAES(new JniDemo().nativeEncodeAES("AES加密测试-AES加密测试-AES加密测试-AES加密测试")));
            }
        });


        md5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(new JniDemo().nativeMD5("MD5加密测试—MD5加密测试-MD5加密测试-MD5加密测试"));
            }
        });

        base64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv.setText(new JniDemo().nativeDecryptBase64(new JniDemo().nativeEncryptBase64("BASE64加密测试—BASE64加密测试-BASE64加密测试-BASE64加密测试")));
            }
        });



    }

    public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
    // Encrypts string and encode in Base64
    public static String encryptTextURL(String plainText, String key) throws Exception {

        byte[] values = des3EncodeECB(key.getBytes(), plainText.getBytes("UTF-8"));
        return new String(Base64.encode(values, Base64.DEFAULT), "UTF-8");
    }


}
