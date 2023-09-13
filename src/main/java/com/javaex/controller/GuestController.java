package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//guest insert
	@RequestMapping(value="insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("GuestController.insert()");
		
		GuestDao guestDao = new GuestDao();
		int count = guestDao.guestInsert(guestVo);
		System.out.println("insert: "+count);
		
		return "redirect:/addList";
	}
	
	//deleteForm
	@RequestMapping(value="/deleteForm/{no}", method={RequestMethod.POST, RequestMethod.GET})
	public String updateform(Model model,
							 @PathVariable(value="no") int no) {
		
		System.out.println("PhoneController.deleteForm()");

		GuestDao guestDao = new GuestDao();
		GuestVo guestVo = guestDao.getGuest(no);
		
		model.addAttribute("guestVo", guestVo);
		
		return "/WEB-INF/deleteForm.jsp";

	}
	
	//guest delete
	@RequestMapping(value="delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("password") String password,
						 @RequestParam("no") int no) {
	
		System.out.println("GuestController.delete()");
		
		GuestDao guestDao = new GuestDao();
		GuestVo guestVo = guestDao.getGuest(no);
		
		if(guestVo.getPassword().equals(password)) {
			
			int count = guestDao.guestDelete(no);
			System.out.println("delete: "+count);
		
		}else {
			System.out.println("삭제 실패.");
		}
	
		return "redirect:/addList";
	}
}







