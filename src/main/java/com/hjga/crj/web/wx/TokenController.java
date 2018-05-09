package com.hjga.crj.web.wx;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hjga.crj.prop.WXConfigProp;
import com.hjga.crj.util.HashKit;

@RestController
@RequestMapping("/wx")
public class TokenController {

	private static Logger log = LoggerFactory.getLogger(TokenController.class);
	
	@Autowired
	private WXConfigProp token;

	@RequestMapping(method = RequestMethod.GET)
	public String checkName(@RequestParam(name = "signature", defaultValue = "") String signature,
			@RequestParam(name = "timestamp", defaultValue = "") String timestamp,
			@RequestParam(name = "nonce", defaultValue = "") String nonce,
			@RequestParam(name = "echostr", defaultValue = "") String echostr) {
		if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
			log.info("签名信息为空！");
			return "";
		}
		String checktext = authentication(token.getToken(), timestamp, nonce);
		if (checktext != null && checktext.equals(signature.toUpperCase())) {
			log.info("签名验证成果！");
			return echostr;
		} else {
			log.info("签名验证失败！");
			return "";
		}
	}

	/**
	 * 将token、timestamp、nonce三个参数进行字典序排序 
	 * 将三个参数字符串拼接成一个字符串进行sha1加密 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	 private  String authentication(String token, String timestamp, String nonce) {
	        String[] strArray = {token, timestamp, nonce};
	        Arrays.sort(strArray);
	        String content = strArray[0].concat(strArray[1]).concat(strArray[2]);
	        
	        String checktext = HashKit.sha1(content);
//	   	 try {
//	            MessageDigest md = MessageDigest.getInstance("SHA-1");
//	            //对接后的字符串进行sha1加密
//	            byte[] digest = md.digest(content.toString().getBytes());
//	            checktext = byteToStr(digest);
//	        } catch (NoSuchAlgorithmException e){
//	            e.printStackTrace();
//	        }
	   	   return checktext;
	    }

	    /**
	     * 将字节数组转换为十六进制字符串
	     * 
	     * @param byteArray
	     * @return
	     */

	    private String byteToStr(byte[] byteArrays){
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
	    private String byteToHexStr(byte myByte) {
	        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	        char[] tampArr = new char[2];
	        tampArr[0] = Digit[(myByte >>> 4) & 0X0F];
	        tampArr[1] = Digit[myByte & 0X0F];
	        String str = new String(tampArr);
	        return str;
	    }
}
