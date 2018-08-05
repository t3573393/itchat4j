package org.fartpig;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fartpig.wechat.WechatHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.WechatTools;

@Controller
public class IndexController {


	@RequestMapping(value = { "/login/img" }, method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String loadimg(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		// img为图片的二进制流
		if (WechatTools.getWechatStatus() == false) {
			httpServletResponse.setContentType("image/jpg");
			OutputStream os;
			try {
				os = httpServletResponse.getOutputStream();
				Wechat wechat = new Wechat(WechatHandler.getInstance(), os, true);
				wechat.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	@RequestMapping(value = { "/relogin/img" }, method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.OPTIONS })
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String relogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (WechatTools.getWechatStatus() == true) {
			WechatTools.logout();
		}

		// img为图片的二进制流
		if (WechatTools.getWechatStatus() == false) {
			httpServletResponse.setContentType("image/jpg");
			OutputStream os;
			try {
				os = httpServletResponse.getOutputStream();
				Wechat wechat = new Wechat(WechatHandler.getInstance(), os, true);
				wechat.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	@RequestMapping(value = { "/wechat_info" }, method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.OPTIONS })
	public String wechatInfo(ModelMap map) {
		String info = "wechatStatus:" + WechatTools.getWechatStatus() + ": contactList" + WechatTools.getContactList();
		map.addAttribute("info", info);
		return "wechat";
	}
}