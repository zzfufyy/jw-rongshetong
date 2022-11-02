package com.shopping.wx.entity.easyui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI treegrid模型
 */
public class SourceTreegrid implements Serializable {
	private String tablename;
	private String deptid;
	private String sourcename;
	private String deptsourceid;
	private String pid;
	private String infotype;
	private String unittype;
	private String ispublic;
	private String theredlist;
	//private String id; // 节点的ID
	private String text; // 节点显示的文字
	private String state = "open"; // 默认open,当为‘closed’时说明此节点下有子节点，否则此节点为叶子节点
	private boolean checked = false; // 指示节点是否被选中
	private String iconCls; // 定义该节点的样式
	private String _parent; // 定义该节点的父节点
	private Integer order_id; // 树的排序
	private Object attribute; // 给一个节点追加的自定义属性
	private List<SourceTreegrid> children = new ArrayList<SourceTreegrid>(); // 定义了一些子节点的节点数组
	
	
	public String getTheredlist() {
		return theredlist;
	}
	public void setTheredlist(String theredlist) {
		this.theredlist = theredlist;
	}
	public String getIspublic() {
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	public String getInfotype() {
		return infotype;
	}
	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}
	public String getUnittype() {
		return unittype;
	}
	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}
	public String getDeptsourceid() {
		return deptsourceid;
	}
	public void setDeptsourceid(String deptsourceid) {
		this.deptsourceid = deptsourceid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<SourceTreegrid> getChildren() {
		return children;
	}
	public void setChildren(List<SourceTreegrid> children) {
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String get_parent() {
		return _parent;
	}
	public void set_parent(String _parent) {
		this._parent = _parent;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Object getAttribute() {
		return attribute;
	}
	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
}