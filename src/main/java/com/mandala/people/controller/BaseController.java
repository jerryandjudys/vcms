/**
The MIT License (MIT) * Copyright (c) 2015 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.mandala.people.controller;

import javax.servlet.http.HttpServletRequest;

import com.mandala.people.constant.Const;
import com.mandala.base.constant.SessionConst;
import com.mandala.people.entity.PeopleEntity;

/***
 * 
 * 用户模块基础控制层
 * @author 史爱华
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015-1-31<br/>
 * 历史修订：<br/>
 */
public class BaseController extends com.mandala.basic.controller.BaseController {
	/**
	 * 读取国际化资源文件
	 * 
	 * @param key 键值
	 * @return字符串
	 */
	protected String getResString(String key) {
		return super.getResString(key, Const.RESOURCES);
	}
	
	/**
	 * 读取国际化资源文件
	 * 
	 * @param key 键值
	 * @param fullStrs  需填充的值
	 * @return 字符串
	 */
	protected String getResString(String key, String... fullStrs) {
		return super.getResString(key, Const.RESOURCES,fullStrs);
	}
	
	/**
	 * 获取用户session.没有返回null
	 */
	protected PeopleEntity getPeopleBySession(HttpServletRequest request) {
		Object obj = this.getSession(request, SessionConst.PEOPLE_SESSION);
		if (obj!=null) {
			return (PeopleEntity)obj;
		} 
		return null;
	}
}