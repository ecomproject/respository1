package com.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.model.User;
import com.ssm.service.IUserService;
import com.ssm.util.DoField;
import com.ssm.util.ExportUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	/**
	 * 用户平均年龄
	 * @return
	 */
	@RequestMapping(value = "/avg_age", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView functionAvg(){
		ModelAndView mav = new ModelAndView("avg_age");
		mav.addObject("avgAge","平均年龄"); //
		return mav;
	}
	
	@RequestMapping(value="/count", method = RequestMethod.POST)
	public Map<String,Object> userAccess(String id,String userName, String beginDate,String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", id);
		params.put("userName", userName);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		
		
		return null;
	}
	
	/**
	 * 通过 ID 获取一个对象
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public String findUser(Model model, @RequestParam("id") Integer id) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "/detail";
	}
ExportUtil t = new ExportUtil();
	/**
	 * 获取所有对象
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public String findAllUsers(Model model) {
		// 导出功能
//		t.exportBaseInfo(null,null);
		
		List<User> list = userService.getAllUsers();
		User u = list.get(0);
		
		String str = "";
		
		
		// 处理集合
		DoField d = new DoField();
		// List l = d.getFiledsInfo(list.get(0));
		String[] l = d.getFiledName(list.get(0));
		for (int i = 0; i < l.length; i++) {
			System.out.println("===================");
			Object fieldName = l[i];
			System.out.println("fieldName:" + fieldName);
			Object valueName = d.getFieldValueByName(fieldName.toString(), u);
			System.out.println("value:" + valueName);
			
			str = "\"" + fieldName+"\""+":";
			
		}
		System.out.println(str);
		// System.out.println("========="+l);

		model.addAttribute("list", list);
		return "/jsp/list";
	}

	/**
	 * 添加一个对象
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(@RequestParam("name") String name, @RequestParam("age") Integer age) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		userService.insertUser(user);
		return "/success";
	}

	/**
	 * 更新一个对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("age") Integer age) {
		User user = userService.getUserById(1);
		user.setName(name);
		user.setAge(age);
		userService.updateUser(user);
		return "/success";
	}

	/**
	 * 删除一个对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestParam("id") Integer id) {
		System.out.println("delete..." + id);
		userService.deleteUser(id);
		return "/success";
	}
}
