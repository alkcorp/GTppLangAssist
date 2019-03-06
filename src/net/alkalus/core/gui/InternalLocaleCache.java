package net.alkalus.core.gui;

import net.alkalus.core.locale.LocaleCache;

public class InternalLocaleCache extends LocaleCache {

	public InternalLocaleCache() {
		super();
	}
	
	public InternalLocaleCache(String built1) {
		super(built1);
	}

	@Override
	public void log(String aMessage) {
		ProgramSelector.mLog.info(aMessage);
	}

}
