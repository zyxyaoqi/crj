package com.hjga.crj.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/")
public class WXController {

	@Value("${wx.token}")
	private String token;
	
	@RequestMapping(value = "/",method=RequestMethod.GET)
    public String checkName(@RequestParam(name="signature")String signature,
                            @RequestParam(name="timestamp")String timestamp,
                            @RequestParam(name="nonce")String nonce,
                            @RequestParam(name="echostr")String echostr){
        System.out.println("-----------------------开始校验------------------------");
        //排序
        String sortString = sort(token, timestamp, nonce);
        //加密
        String myString = sha1(sortString);
        //校验
        if (myString != null && myString != "" && myString.equals(signature)) {
            System.out.println("签名校验通过");
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else {
            System.out.println("签名校验失败");
            return "";
        }
    }
	/**
     * 排序方法
     */
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        String content = strArray[0].concat(strArray[1]).concat(strArray[2]);
        return content;
    }

    /**
     * 将字符串进行sha1加密
     *
     * @param str 需要加密的字符串
     * @return 加密后的内容
     */
    public String sha1(String str) {
    	 String checktext = null;
    	 try {
             MessageDigest md = MessageDigest.getInstance("SHA-1");
             //对接后的字符串进行sha1加密
             byte[] digest = md.digest(str.toString().getBytes());
             checktext = byteToStr(digest);
         } catch (NoSuchAlgorithmException e){
             e.printStackTrace();
         }
    	//将加密后的字符串与signature进行对比
         return checktext;
     }
    
    private static String byteToStr(byte[] byteArrays){
        String str = "";
        for (int i = 0; i < byteArrays.length; i++) {
            str += byteToHexStr(byteArrays[i]);
        }
        return str;
    }
    
    /**
     *  将字节转化为十六进制字符串
     * @param myByte 字节
     * @return 字符串
     */
    private static String byteToHexStr(byte myByte) {
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tampArr = new char[2];
        tampArr[0] = Digit[(myByte >>> 4) & 0X0F];
        tampArr[1] = Digit[myByte & 0X0F];
        String str = new String(tampArr);
        return str;
    }

}
