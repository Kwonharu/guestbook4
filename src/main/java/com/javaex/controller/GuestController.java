package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
public class GuestController {
	@RequestMapping(value="/addList", method={RequestMethod.POST, RequestMethod.GET})
	public String addList(Model model) {
		
		System.out.println("GuestController.addList()");
			
		//리스트 가져오기
		GuestDao guestDao = new GuestDao();
		List<GuestVo> guestList = guestDao.guestSelect("");
		
		//model 주소를 받고 메소드를 이용해서 담는다
		// --> DS request.setAttribute("guestList", guestList)
		model.addAttribute("guestList", guestList);
		
		return "/WEB-INF/addList.jsp";
		
		//request의 attribute에 넣는다
		//list.jsp에 포워드
	}
}
