package registration_check.registration_check.model;

/**
 * Store various information about stuff
 * 
 * @author Tu Anh Vu
 *
 */
public class Stuff {
	private String Bar;
	private String Foo;
	private String Bazz;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stuff [Bar=" + Bar + ", Foo=" + Foo + ", Bazz=" + Bazz + "]";
	}

	/**
	 * @return the bar
	 */
	public String getBar() {
		return Bar;
	}
	
	/**
	 * @param bar the bar to set
	 */
	public void setBar(String bar) {
		Bar = bar;
	}
	
	/**
	 * @return the foo
	 */
	public String getFoo() {
		return Foo;
	}
	
	/**
	 * @param foo the foo to set
	 */
	public void setFoo(String foo) {
		Foo = foo;
	}
	
	/**
	 * @return the bazz
	 */
	public String getBazz() {
		return Bazz;
	}
	
	/**
	 * @param bazz the bazz to set
	 */
	public void setBazz(String bazz) {
		Bazz = bazz;
	}
}
