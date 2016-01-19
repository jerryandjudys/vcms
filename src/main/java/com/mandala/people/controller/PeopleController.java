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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mandala.base.constant.ModelCode;
import com.mandala.people.biz.IPeopleBiz;
import com.mandala.people.constant.e.PeopleEnum;
import com.mandala.people.entity.PeopleEntity;

/**
 * 
 * <p>
 * <b>铭飞科技-会员系统</b>
 * </p>  
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 *
 * @author 成卫雄
 *                QQ:330216230
 *
 * <p>
 * Comments:会员基础信息控制层
 * </p>
 *
 * <p>
 * Create Date:2014-10-31
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller
@RequestMapping("/manager/people")
public class PeopleController extends BaseController {
	
	/**
	 * 注入用户控制层
	 */
	@Autowired
	private IPeopleBiz peopleBiz;
	
	/**
	 * 更新用户状态
	 * @param people 用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateState")
	public void updateState(@ModelAttribute PeopleEntity people,HttpServletRequest request,HttpServletResponse response){
		if(people == null){
			this.outJson(response, ModelCode.PEOPLE,false);
			return ;
		}
		
		if(people.getPeopleState() == PeopleEnum.STATE_CHECK.toInt()){
			people.setPeopleState(PeopleEnum.STATE_NOT_CHECK);
		}else{
			people.setPeopleState(PeopleEnum.STATE_CHECK);
		}
		this.peopleBiz.updateEntity(people);
		this.outJson(response, ModelCode.PEOPLE,true,Integer.toString(people.getPeopleState()));
	}
}