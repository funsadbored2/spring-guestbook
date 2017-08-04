package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao dao;
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {

		 dao.add(vo);

		 return "redirect:/list";
	}
	
	@RequestMapping("deleteform")
	public String deleteform() {

		// delete전 비밀번호 확인하는 부분으로 사용자 입력 값 가져오기
		System.out.println("deleteform");
		
		return "deleteform";

	}  
	
	@RequestMapping("delete")
	public String delete(GuestbookVo vo) {
		
		dao.delete(vo.getPassword(), vo.getNo());

		 return "redirect:/list";

	} 
	
	@RequestMapping("updateform")
	public String updateform() {

		System.out.println("updateform");
		
		return "updateform";

	}
	
	@RequestMapping("update")
	public String update(GuestbookVo vo) {

		System.out.println("update");

		dao.update(vo);
		
		return "redirect:/list";

	} 
	
	@RequestMapping("/list")
	public String list(Model model) {

		System.out.println("리스트로");

		List<GuestbookVo> list = dao.getList();

		model.addAttribute("list", list);

		return "list";

	}
	
	
}
