package alkalus.core.gui;

public class LogicThread implements Runnable {
	
	public ProgramSelector mMainGuiInstance;	
	
	@Override
	public void run() {	
		ProgramSelector h;
		try {
			h = new ProgramSelector();	
		} catch (Exception e) {
			h = null;
			e.printStackTrace();
		}
		mMainGuiInstance = h;
	}		
}