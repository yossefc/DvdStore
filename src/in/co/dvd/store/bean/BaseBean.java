package in.co.dvd.store.bean;

public abstract class BaseBean implements IdListBean, Comparable<BaseBean>{
	/**
	 * Generic Attribute Id For All Child
	 */
	protected long id;

	

	/**
	 * @return ID of Child
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param Id
	 *            To set Child ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	

	public int compareTo(BaseBean next) {
		// TODO Auto-generated method stub
		return getValue().compareTo(next.getValue());
	}
}


