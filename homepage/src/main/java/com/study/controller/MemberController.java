package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.member.MemberMapper;
import com.study.member.MemberVO;
import com.study.member.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	@Qualifier("com.study.member.MemberServiceImpl")
	private MemberService service;
	
	@PostMapping("/member/updatePw")
	public String updatePw(MemberVO vo) {
		Map map = new HashMap();
		map.put("passwd", vo.getPasswd());
		
		int pcnt = mapper.passwd(map);
		int cnt=0;
		if(pcnt==1) {
			cnt = mapper.updatePw(vo);
		}
		if(pcnt!=1) {
			return "passwdError"; 
		}else if(cnt==1) {
			return "redirect:./read";
		}else {
			return "error";
		}
	}
	
	@GetMapping("/member/updatePw")
	public String updatePw(String id, Model model) {
		
		model.addAttribute("memberVO", mapper.read(id));
		return "/member/updatePw";
	}
	
	@GetMapping("/member/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
			ServletContext ctx = request.getSession().getServletContext();
			//??????????????? ??????????????? ??? ??? ??????
			String dir = ctx.getRealPath(request.getParameter("dir"));
			String filename = request.getParameter("filename");
			byte[] files = FileUtils.readFileToByteArray(new File(dir,filename));
			response.setHeader("Content-disposition", "attachment; fileName=\""+
				    URLEncoder.encode(filename,"UTF-8")+"\";");
			  //Content-Transfer-Encoding : ?????? ???????????? body??? ???????????? ????????? ?????????.
		    response.setHeader("Content-Transfer-Encoding", "binary");
		    /**
		     * Content-Disposition??? attachment??? ?????? ?????????????????? 
		     * 'Save As'??? ????????? ??????????????? ????????? ?????? ??????????????? ????????????.
		     */
		    response.setContentType("application/octet-stream");
		    response.setContentLength(files.length);
		    response.getOutputStream().write(files);
		    response.getOutputStream().flush();
		    response.getOutputStream().close();
	}
	
	@PostMapping("/member/updateFile")
	public String updateFile(MultipartFile fnameMF,
			String oldfile,
			HttpSession session,
			HttpServletRequest request) throws IOException {
		//String basePath = request.getRealPath("/static/storage");
		String basePath = new ClassPathResource("/static/storage").getFile().getAbsolutePath();
		if(oldfile != null && !oldfile.equals("member.jpg")) { //???????????? ??????
			Utility.deleteFile(basePath, oldfile);
		}
		
		//storage??? ??????????????????
		Map map = new HashMap();
		map.put("id", session.getAttribute("id"));
		map.put("fname", Utility.saveFileSpring(fnameMF, basePath));
		
		// DB??? ????????? ??????
		int cnt = mapper.updateFile(map);
		
		if(cnt==1) {
			return "redirect:./read";
		}else {
			return "./error/";
		}
		
	}
	
	@GetMapping("/member/updateFile")
	public String updateFileForm() {
		return "/member/updateFile";
	}
	
	@RequestMapping("/admin/list")
	public String list(HttpServletRequest request) {
		// ????????????------------------------
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}

		// ???????????????-----------------------
		int nowPage = 1;// ?????? ???????????? ?????????
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 3;// ??????????????? ????????? ???????????????

		// DB?????? ????????? ??????-----------------
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = mapper.total(map);

		
		List<MemberVO> list = mapper.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request??? Model?????? ?????? ?????????
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);
		
		
		return "/member/list";

	}
	
	
	@PostMapping("/member/update")
	public String update(MemberVO vo, Model model) {
		int cnt = mapper.update(vo);
		
		if(cnt==1) {
			model.addAttribute("id", vo.getId());
			return "redirect:./read";
		}else {
			return "error";
		}
	}
	
	@GetMapping("/member/update")
	public String update(String id, HttpSession session, Model model) {
		
		if(id==null) {
			id = (String)session.getAttribute("id");
		}
		MemberVO vo = mapper.read(id);
		
		model.addAttribute("memberVO", vo);
		return "/member/update";
	}
	
	@GetMapping("/member/read")
	public String read(String id, Model model, HttpSession session) {
		
		if(id == null) {
			id =(String) session.getAttribute("id");
		}
		MemberVO vo = mapper.read(id);
		
		model.addAttribute("memberVO", vo);
		return "/member/read";
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		//session.removeAttribute("id");
		//session.removeAttribute("grade");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@PostMapping("/member/login")
	public String login(@RequestParam Map<String, String> map, 
			HttpSession session,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		int cnt = mapper.loginCheck(map);
		
		if(cnt > 0) { //????????????.
			String grade = mapper.getGrade(map.get("id"));
			session.setAttribute("id", map.get("id"));
			session.setAttribute("grade", grade);
			//Cookie ??????,id???????????? ??? id
			Cookie cookie = null;
			String c_id = request.getParameter("c_id");
			if(c_id != null) {
				cookie = new Cookie("c_id", "Y");
				cookie.setMaxAge(60 * 60 * 24 * 365);//1????????? ?????? ???????????????
				response.addCookie(cookie); //??????????????????-> ?????????(client:???????????????)
				
				cookie = new Cookie("c_id_val", map.get("id"));
				cookie.setMaxAge(60 * 60 * 24 * 365);//1????????? ?????? ???????????????
				response.addCookie(cookie); //??????????????????-> ?????????(client:???????????????)
			}else {
				cookie = new Cookie("c_id", ""); //????????????
				cookie.setMaxAge(0);
				response.addCookie(cookie); 
				
				cookie = new Cookie("c_id_val", ""); //????????????
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
		}//if cnt>0 end
		
		if(cnt>0) {
			
			if(map.get("rurl") != null && !map.get("rurl").equals("")) {
				model.addAttribute("bbsno", map.get("bbsno"));
				model.addAttribute("nPage", map.get("nPage"));
				model.addAttribute("nowPage", map.get("nowPage"));
				model.addAttribute("col", map.get("col"));
				model.addAttribute("word", map.get("word"));
				
				return "redirect:"+map.get("rurl");
			}else {
				
				return "redirect:/";
			}
						
		}else {
			model.addAttribute("msg", "????????? ?????? ??????????????? ?????? ??????????????? <br> ????????? ????????????. ???????????? ?????????");
			return "/member/errorMsg";
		}
	}
	
	@GetMapping("/member/login")
	public String login(HttpServletRequest request) {
		/*----???????????? ????????????----------------------------*/
		String c_id = "";     // ID ?????? ????????? ???????????? ??????, Y
		String c_id_val = ""; // ID ???
		 
		Cookie[] cookies = request.getCookies(); 
		Cookie cookie=null; 
		 
		if (cookies != null){ 
		 for (int i = 0; i < cookies.length; i++) { 
		   cookie = cookies[i]; 
		 
		   if (cookie.getName().equals("c_id")){ 
		     c_id = cookie.getValue();     // Y 
		   }else if(cookie.getName().equals("c_id_val")){ 
		     c_id_val = cookie.getValue(); // user1... 
		   } 
		 } 
		} 
		/*----???????????? ?????? ???----------------------------*/
		
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);
		
		return "/member/login";
	}
	
	
	@PostMapping("/member/create")
	public String create(MemberVO vo, HttpServletRequest request) throws IOException {
		//String upDir = request.getRealPath("/static/storage");
		String upDir = new ClassPathResource("/static/storage").getFile().getAbsolutePath();
		String fname = Utility.saveFileSpring(vo.getFnameMF(), upDir);
		int size = (int) vo.getFnameMF().getSize();
		if(size>0) {
			vo.setFname(fname);
		}else {
			vo.setFname("member.jpg");
		}
		if(mapper.create(vo)>0) {
			return "redirect:/";
		}else {
			return "error";
		}
	}
	
	
	@GetMapping(value="/member/emailcheck", produces="application/json;charset=utf-8")
	@ResponseBody    
	public Map<String,String> emailcheck(String email){
		int cnt = mapper.duplicatedEmail(email);
		
		Map<String, String> map = new HashMap<String, String>();
		if(cnt>0) {
			map.put("str", email+"??? ??????????????? ????????? ??? ????????????");
		}else {
			map.put("str", email+"??? ????????????, ?????????????????????");
		}
		return map;
	}
	
	@GetMapping(value="/member/idcheck", produces="application/json;charset=utf-8")
	@ResponseBody    
	public Map<String,String> idcheck(String id){
		int cnt = mapper.duplicatedId(id);
		
		Map<String, String> map = new HashMap<String, String>();
		if(cnt>0) {
			map.put("str", id+"??? ??????????????? ????????? ??? ????????????");
		}else {
			map.put("str", id+"??? ????????????, ?????????????????????");
		}
		return map;
	}
	
	@GetMapping("/member/agree")
	public String agree() {
		return "/member/agree";
	}
	
	@PostMapping("/member/createForm")
	public  String create() {
		return "/member/create";
	}
}
