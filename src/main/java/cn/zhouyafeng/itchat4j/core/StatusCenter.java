package cn.zhouyafeng.itchat4j.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhouyafeng.itchat4j.face.IStatusHandlerFace;

public class StatusCenter {

	private static Logger LOG = LoggerFactory.getLogger(StatusCenter.class);

	private static IStatusHandlerFace statusHandler = null;

	public static void login() {
		Core.getInstance().setAlive(true);
		if (statusHandler != null) {
			statusHandler.loginHandle();
		}
	}

	public static void logout() {
		Core.getInstance().setAlive(false);
		if (statusHandler != null) {
			statusHandler.logoutHandle();
		}
	}

	public static void sysException() {
		Core.getInstance().setAlive(false);
		if (statusHandler != null) {
			statusHandler.sysExceptionHandle();
		}
	}

	public static void handleStatus(IStatusHandlerFace statusHandler) {
		StatusCenter.statusHandler = statusHandler;
	}

}
