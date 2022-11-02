package com.shopping.wx.entity.easyui;

import java.util.*;

/**
 * EasyUI tree模型
 */
public class Tree implements java.io.Serializable {
    private static final long serialVersionUID = 980682543891282923L;
    
    private String id;
    private String text;
    private String state = "open";// open,closed
    private boolean checked = false;
    private Object attributes;
    private List<Tree> children = new ArrayList<Tree>();
    private String iconCls;
    private String pid;
    private String depid;//部门ID
    private String name;
    private String remark;
    private String status;
    private String code; // 编码
    private Integer setorder;//排序
    private String url;
    private String icon;
    
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSetorder() {
		return setorder;
	}

	public void setSetorder(Integer setorder) {
		this.setorder = setorder;
	}

	/**
     * ajax,iframe,
     */
    private String openMode;
    
    public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }
    
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// 递归遍历子节点
	private static void getChildrenNodes(List<Tree> nodes, Tree root) {
		for (Tree node : nodes) {
			// 在根节点中下寻找它的子节点
			if (node.getPid().equals(root.getId())) {
				// 如果找到root的子结点,在父节点下添加子节点
				root.getChildren().add(node);
				// 寻找子节点的子节点
				getChildrenNodes(nodes, node);
			}
		}
	}

	// 构建树
	public static List<Tree> formatTree(List<Tree> list) {
		Tree root = new Tree();
		List<Tree> treelist = new ArrayList<Tree>(); // 拼凑好的json格式的数据
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 如果该节点没有父节点那么它就是根（root）节点
				if ("".equals(list.get(i).getPid())) {
					root = list.get(i);
					// 获取该根节点的子节点
					getChildrenNodes(list, root);
					treelist.add(root);
				}
			}
		}
		return treelist;
	}
	
	// 构建多叉树
	public static List<Tree> formatMultipleTree(List<Tree> list) {
		List<Tree> treeList = new ArrayList<Tree>(); // 拼凑好的json格式的数据
		
		// 节点列表（散列表，用于临时存储节点对象）
		Map<String, Tree> treeMap = new LinkedHashMap<String, Tree>();
		// 根据结果集构造节点列表（存入散列表）
		for (Iterator<Tree> iterator = list.iterator(); iterator.hasNext();) {
			Tree tree = (Tree) iterator.next();
			
			treeMap.put(tree.getId(), tree);
		}
		// 构造无序的多叉树 
		Iterator<String> treeIterator = treeMap.keySet().iterator();
		while (treeIterator.hasNext()) {
			String key = treeIterator.next();
			Tree tree = treeMap.get(key);
			Tree root = null; // 根节点
			if (null == tree.getPid() || "".equals(tree.getPid())) {
				root = tree;
				treeList.add(root);
			} else {
				try {
					((Tree)treeMap.get(tree.getPid())).getChildren().add(tree);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		}
		return treeList;
	}

}