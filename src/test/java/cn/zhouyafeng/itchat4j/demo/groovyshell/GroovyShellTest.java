package cn.zhouyafeng.itchat4j.demo.groovyshell;

import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.demo.demo1.SimpleDemo;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyShellTest {

	public static void main(String[] args) {

		String qrPath = "D://itchat4j//login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
		IMsgHandlerFace msgHandler = new SimpleDemo(); // 实现IMsgHandlerFace接口的类
		Wechat wechat = new Wechat(msgHandler, qrPath); // 【注入】
		wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片

		ScriptEngineManager factory = new ScriptEngineManager(GroovyShellTest.class.getClassLoader());
		ScriptEngine scriptEngine = factory.getEngineByName("groovy");// 或者"Groovy" groovy版本要1.6以上的
		try {
			scriptEngine.put("WechatTools", WechatTools.class);
			scriptEngine.eval("println WechatTools;");
		} catch (javax.script.ScriptException e1) {
			e1.printStackTrace();
		}

		Binding bb = new Binding();
		bb.setProperty("WechatTools", WechatTools.class);
		GroovyShell gs = new GroovyShell(bb);

		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		do {
			try {

				gs.evaluate(line);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			line = s.nextLine();
		} while (!"end".equals(line));

	}

}
