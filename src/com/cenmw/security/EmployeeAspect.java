package com.cenmw.security;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.opensymphony.xwork2.ActionContext;
import com.cenmw.admin.manager.service.UserManagerService;
import com.cenmw.admin.po.User;

/**
 * 运营商用户权限控制核心
 * 
 * @author LiangJiChao
 * 
 */
@Aspect
public class EmployeeAspect {
	private UserManagerService userManagerService;

	/**
	 * 切入点,拦截所有配置EmployeePermission注解的方法
	 */
	@Pointcut("@annotation(com.cenmw.security.EmployeePermission)")
	public void allEmployeePermission() {

	};

	/**
	 * 用户权限检查
	 * 
	 * @param employeePermission
	 *            权限注解
	 * @throws Throwable
	 */
	@Around("com.cenmw.security.EmployeeAspect.allEmployeePermission()&&@annotation(employeePermission)")
	public String checkPermission(ProceedingJoinPoint jp,
			EmployeePermission employeePermission) throws Throwable {
		User user = (User) ActionContext.getContext().getSession()
		.get("adminInfo");//得到用户信息
		
		if(user.getStatus()==1){
			return (String) jp.proceed();
		}
		
		boolean check = false;// 权限通过标识
		
		//权限验证
		Map actionMap=userManagerService.getUserActionMap(ActionContext.getContext().getSession(), user);
		Object obj[] = employeePermission.perm();// 获取指定权限数组
		if(actionMap!=null){
			for (Object o : obj) {
				String permName = o.toString();// 权限名称  得到方法允许的权限名称
				if(actionMap.get(permName)!=null){
					check=true;
				}
			}
		}
		
		if (!check) {
			return "employeenopermission";//无权限
		} else {
			return (String) jp.proceed();//权限 通过
		}
	}

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

}
