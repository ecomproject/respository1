package com.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ssm.model.Department;
import com.ssm.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	private static final String DEPARTEMNTMANAGE = "departmentManage.jsp";

	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	/**
	 * 获取部门管理页面.
	 * 
	 * @return
	 */
	@RequestMapping("/getPage.do")
	public String getUserManagePage() {
		return DEPARTEMNTMANAGE;
	}

	/**
	 * 获取部门树形列表
	 */
	@RequestMapping(value = "/getTree.do", method = RequestMethod.POST)
	@ResponseBody // 此注解表明返回值跳过视图处理部分，直接写入 http response body中
	public List<Department> getTree() {
		List<Department> root = departmentService.findById("0"); // 获取根节点（获取的值存到list中）
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(buildTree(root));
		System.out.println(jsonArray.toString());

		return buildTree(root);
	}

	public List<Department> buildTree(List<Department> root) {
		for (int i = 0; i < root.size(); i++) {
			List<Department> children = departmentService.findByPid(root.get(i).getId()); // 查询某节点的子节点（获取的是list）
			buildTree(children);
			root.get(i).setChildren(children);
		}
		return root;
	}

}