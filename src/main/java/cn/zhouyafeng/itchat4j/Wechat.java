package cn.zhouyafeng.itchat4j;

import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.controller.LoginController;
import cn.zhouyafeng.itchat4j.core.MsgCenter;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

public class Wechat {
	private static final Logger LOG = LoggerFactory.getLogger(Wechat.class);
	private IMsgHandlerFace msgHandler;
	private static volatile Executor executor;

	public Wechat(IMsgHandlerFace msgHandler, String qrPath) {
		System.setProperty("jsse.enableSNIExtension", "false"); // 防止SSL错误
		this.msgHandler = msgHandler;

		// 登陆
		LoginController login = new LoginController();
		login.login(qrPath);
	}

	public Wechat(IMsgHandlerFace msgHandler, OutputStream out) {
		this(msgHandler, out, false);
	}

	public Wechat(IMsgHandlerFace msgHandler, OutputStream out, boolean isAync) {
		System.setProperty("jsse.enableSNIExtension", "false"); // 防止SSL错误
		this.msgHandler = msgHandler;

		// 登陆
		LoginController login = new LoginController();
		if (isAync) {
			login.loginAsync(out);
		} else {
			login.login(out);
		}

	}

	public static Executor getExecutor() {
		if (executor == null) {
			synchronized (Wechat.class) {
				if (executor == null) {
					executor = Executors.newCachedThreadPool();
				}
			}
		}
		return executor;
	}

	public static void setExecutor(Executor executor) {
		Wechat.executor = executor;
	}

	public void start() {
		LOG.info("+++++++++++++++++++开始消息处理+++++++++++++++++++++");
		executor.execute(new Runnable() {
			@Override
			public void run() {
				MsgCenter.handleMsg(msgHandler);
			}
		});
	}

}
