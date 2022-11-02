package com.shopping.wx.entity.easyui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI treegrid模型
 */
public class Treegrid implements Serializable {
	private static final long serialVersionUID = 2440826064544601672L;

	private String id; // 节点的ID
	private String text; // 节点显示的文字
	private String state = "open"; // 默认open,当为‘closed’时说明此节点下有子节点，否则此节点为叶子节点
	private boolean checked = false; // 指示节点是否被选中
	private String iconCls; // 定义该节点的样式
	private String _parent; // 定义该节点的父节点
	private Integer order_id; // 树的排序
	private Object attribute; // 给一个节点追加的自定义属性
	private List<Treegrid> children = new ArrayList<Treegrid>(); // 定义了一些子节点的节点数组

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 * the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 * the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked
	 * the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the attribute
	 */
	public Object getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute
	 * the attributes to set
	 */
	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls
	 * the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return the _parent
	 */
	public String getParent() {
		return _parent;
	}

	/**
	 * @param _parent
	 * the _parent to set
	 */
	public void setParent(String _parent) {
		this._parent = _parent;
	}

	/**
	 * @return the order_id
	 */
	public Integer getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id
	 * the order_id to set
	 */
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the children
	 */
	public List<Treegrid> getChildren() {
		return children;
	}

	/**
	 * @param children
	 * the children to set
	 */
	public void setChildren(List<Treegrid> children) {
		this.children = children;
	}

	// 递归遍历子节点
	private static void getChildrenNodes(List<Treegrid> nodes, Treegrid root) {
		for (Treegrid node : nodes) {
			// 在根节点中下寻找它的子节点
			if (node.getParent().equals(root.getId())) {
				// 如果找到root的子结点,在父节点下添加子节点
				root.getChildren().add(node);
				// 寻找子节点的子节点
				getChildrenNodes(nodes, node);
			}
		}
	}

	// 构建树
	public static List<Treegrid> formatTree(List<Treegrid> list) {
		Treegrid root = new Treegrid();
		List<Treegrid> treelist = new ArrayList<Treegrid>(); // 拼凑好的json格式的数据
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 如果该节点没有父节点那么它就是根（root）节点
				if ("".equals(list.get(i).getParent())) {
					root = list.get(i);
					// 获取该根节点的子节点
					getChildrenNodes(list, root);
					treelist.add(root);
				}
			}
		}
		return treelist;
	}

}