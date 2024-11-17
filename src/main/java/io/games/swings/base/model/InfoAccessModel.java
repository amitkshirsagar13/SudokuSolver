package io.games.swings.base.model;

/**
 * ProjectName: SwingsStatusPanel
 * @author amit_kshirsagar
 * @date Jan 17, 2014
 */

import org.apache.log4j.Logger;

public class InfoAccessModel {
	static Logger log = Logger.getLogger(InfoAccessModel.class.getName());

	private String name = null;
	private String role = null;
	private String index = null;
	private String access = null;

	public InfoAccessModel(String name, String role, String index, String access) {
		setName(name);
		setRole(role);
		setIndex(index);
		setAccess(access);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * @param access
	 *            the access to set
	 */
	public void setAccess(String access) {
		this.access = access;
	}

}
