package com.ssm.model;

import java.util.List;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Department {
	private String id;          //部门id   
    private String pid;         //父id  
    private String text;        //部门名称  
    private List<Department> children;   //用于存储子节点  
    
    @Id  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getPid() {  
        return pid;  
    }  
    public void setPid(String pid) {  
        this.pid = pid;  
    }  
      
    public String getText() {  
        return text;  
    }  
    public void setText(String text) {  
        this.text = text;  
    }  
    @OneToMany  
    public List<Department> getChildren() {  
        return children;  
    }  
    public void setChildren(List<Department> children) {  
        this.children = children;  
    }  

}
