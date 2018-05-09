package com.hjga.crj.web.wx;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hjga.crj.constant.WXApiURL;
import com.hjga.crj.task.WXAccessTokenTask;
import com.hjga.crj.wxmodel.SceneReturn;

@RestController
@RequestMapping("/wx/scene")
public class SceneController {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("createLimit")
	public String createLimit(@RequestParam(name = "param", defaultValue = "") String param){
		Map<String, Object> map = createPostParam(true, true, param);

		String url =  WXApiURL.CREATE_SCENE_URL.replace("TOKEN", WXAccessTokenTask.getAccessToken());
		SceneReturn re = restTemplate.postForObject(url,  map, SceneReturn.class);
		System.out.println(re.getErrcode());
		if(re.getTicket()!=null){
			String imgurl= WXApiURL.SCENE_PIC_URL.replace("TICKET", URLEncoder.encode(re.getTicket()));
			return imgurl;
		}
		return "";
	}
	
	private Map<String,Object> createPostParam(boolean islimit, boolean isstr, Object param){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> scene_str_id = new HashMap<String,Object>();
		if(isstr){
			if(islimit)
				map.put("action_name", "QR_LIMIT_STR_SCENE");
			else
				 map.put("action_name", "QR_STR_SCENE ");
			scene_str_id.put("scene_str", param);
		}
		else {
			if(islimit)
				map.put("action_name", "QR_LIMIT_SCENE");
			else
				 map.put("action_name", "QR_SCENE");
			scene_str_id.put("scene_id", param);
		}
		
		Map<String, Map<String,Object>> scene = new HashMap<String,  Map<String,Object>>();
		scene.put("scene", scene_str_id);
		
		map.put("action_info", scene);
		return map;
		
	}
}
