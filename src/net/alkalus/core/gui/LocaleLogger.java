package net.alkalus.core.gui;

import java.io.File;

import net.alkalus.api.objects.misc.AcLog;
import net.alkalus.core.util.data.FileUtils;

public class LocaleLogger extends AcLog {
	
	private static final File mLogFile;
	
	static {		
		mLogFile = new File(FileUtils.getWorkingDirectory(), "LangAssist_Log.log");
		FileUtils.wipeFileIfExists(mLogFile);
	}

	@Override
	public void info(String s) {
		super.info(s);
		logToFile(s);
	}

	@Override
	public void warn(String s) {
		super.warn(s);
		logToFile(s);
	}

	@Override
	public void error(String s) {
		super.error(s);
		logToFile(s);
	}

	public LocaleLogger() {
		super("Lang_Assist");
	}
	

	
	public static void logToFile(String aLogString) {
		FileUtils.appendLineToFile(mLogFile, aLogString);
		System.out.println(aLogString);
	}

}
