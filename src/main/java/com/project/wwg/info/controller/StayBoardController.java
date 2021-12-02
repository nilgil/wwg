package com.project.wwg.info.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.wwg.info.dto.StayBoard;
import com.project.wwg.info.service.StayBoardService;
import com.project.wwg.info.service.PagingPgm;

@Controller
public class StayBoardController {

	@Autowired
	private StayBoardService service;
	
	// 글작성 폼
	@RequestMapping("stayform.do")
	public String stayform(Principal principal, Model model) {
		
		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		 		
		return "info/stay/stayform";
	}
	
	// 글작성
	@RequestMapping("staywrite.do")
	public String staywrite(Principal principal, StayBoard stayboard, Model model, HttpServletRequest request) {
		System.out.println("in");
		System.out.println("stay_title:"+stayboard.getStay_title());

		String username = principal.getName(); // 로그인후 유저네임 넘기기
		System.out.println("username:"+username);
		 
		int stay_no = stayboard.getStay_no();
		int number = service.getMaxNum();
		
		stayboard.setStay_no(number);
		
		stayboard.setUsername(username); // 로그인후 유저네임 넘기기
		 		
		int result = service.insert(stayboard);
		model.addAttribute("result", result);
		/* model.addAttribute("username", username); */
		
		return "info/stay/stayinsertresult";
	}
	
	// 글목록 첫페이지 실행
	@RequestMapping("staylist.do")
	public String initList() {
		return "redirect:staylist.do/pageNum/1";
	}
	
	// 글목록
	@RequestMapping("staylist.do/pageNum/{pageNum}")
	public String staylist(@PathVariable String pageNum, StayBoard stayboard, Model model) {
		final int rowPerPage = 10;
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int stay_count = service.getStay_count();	// 게시물 갯수
		int currentPage = Integer.parseInt(pageNum);// 총 데이터 갯수
		int total = service.getTotal(stayboard);	// 검색
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		stayboard.setStartRow(startRow);
		stayboard.setEndRow(endRow);
		
		int no = total - startRow + 1;	// 화면 출력 번호
		List<StayBoard> staylist = service.staylist(stayboard);
		model.addAttribute("stay_count", stay_count);
		model.addAttribute("staylist", staylist);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", stayboard.getSearch());
		model.addAttribute("keyword", stayboard.getKeyword());
		
		return "info/stay/staylist";
	}
	
	// 상세페이지
	@RequestMapping("staycontent.do/stay_no/{stay_no}/pageNum/{pageNum}")
	public String staycontent(Principal principal, @PathVariable int stay_no, @PathVariable String pageNum, Model model) {
		service.selectUpdate(stay_no);					// 조회수 1증가
		
		String username = "guest";
		if(principal != null) {			// 비로그인시 상세정보 구하기
		username = principal.getName();
		}
		
		StayBoard stayboard = service.select(stay_no);	// 상세 정보 구하기
		
		model.addAttribute("username", username);
		model.addAttribute("stayboard", stayboard);
		model.addAttribute("pageNum", pageNum);
		return "info/stay/staycontent";
	}
	
	// 글수정 폼
	@RequestMapping("stayupdateform.do/stay_no/{stay_no}/pageNum/{pageNum}")
	public String stayupdateform(@PathVariable int stay_no, @PathVariable String pageNum, Model model) {
		StayBoard stayboard = service.select(stay_no);
		model.addAttribute("stayboard", stayboard);
		model.addAttribute("pageNum", pageNum);
		return "info/stay/stayupdateform";
	}
	
	//글수정
	@RequestMapping("stayupdate.do/pageNum/{pageNum}")
	public String stayupdate(StayBoard stayboard, @PathVariable String pageNum, Model model) {
		int result = service.update(stayboard);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/stay/stayupdate";
	}
	
	// 글삭제 폼
	@RequestMapping("staydeleteform.do")
//	public String staydeleteform(@PathVariable int stay_no, @PathVariable String pageNum, Model model) {
	public String staydeleteform() {
//		int result = service.delete(stay_no);
//		model.addAttribute("result", result);
//		model.addAttribute("pageNum", pageNum);
		return "info/stay/staydeleteform";
	}

	// 글삭제
	@PostMapping("staydelete.do")
	public String staydelete(@RequestParam int stay_no, @RequestParam String pageNum, Model model) {
		System.out.println("stay_no:"+stay_no);
		int result = service.delete(stay_no);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/stay/staydelete";
	}
	
	// 좋아요
    @RequestMapping("staylike.do")
    public String recommend (@RequestParam int stay_no, Model model) {
		service.like(stay_no);					// 좋아요 1증가
        model.addAttribute("stay_no", stay_no);
        return "redirect:staylist.do";
    }

    // 이미지 업로드
    @RequestMapping(value="stay/imageUpload.do", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request,
    		HttpServletResponse response, MultipartHttpServletRequest multiFile
    		, @RequestParam MultipartFile upload) throws Exception{
    	// 랜덤 문자 생성
    	UUID uid = UUID.randomUUID();
    	
    	OutputStream out = null;
    	PrintWriter printWriter = null;
    	
    	//인코딩
    	response.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	try{
    		//파일 이름 가져오기
    		String fileName = upload.getOriginalFilename();
    		byte[] bytes = upload.getBytes();
    		
    		//이미지 경로 생성
    		String path = "C:\\Users\\wowo1\\Pictures\\Saved Pictures" + "ckImage/";	// 이미지 경로 설정(폴더 자동 생성)
    		String ckUploadPath = path + uid + "_" + fileName;
    		File folder = new File(path);
    		System.out.println("path:"+path);	// 이미지 저장경로 console에 확인
    		//해당 디렉토리 확인
    		if(!folder.exists()){
    			try{
    				folder.mkdirs(); // 폴더 생성
    		}catch(Exception e){
    			e.getStackTrace();
    		}
    	}
    	
    	out = new FileOutputStream(new File(ckUploadPath));
    	out.write(bytes);
    	out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
    	
    	String callback = request.getParameter("CKEditorFuncNum");
    	printWriter = response.getWriter();
    	String fileUrl = "/stay/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName; // 작성화면
    	
    	// 업로드시 메시지 출력
    	printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
    	printWriter.flush();
    	
    	}catch(IOException e){
    		e.printStackTrace();
    	} finally {
    		try {
    		if(out != null) { out.close(); }
    		if(printWriter != null) { printWriter.close(); }
    	} catch(IOException e) { e.printStackTrace(); }
    	}
    	return;
    }

    // 서버로 전송된 이미지 뿌려주기
    @RequestMapping(value="/stay/ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value="uid") String uid
    		, @RequestParam(value="fileName") String fileName
    		, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	
    	//서버에 저장된 이미지 경로
    	String path = "C:\\Users\\wowo1\\Pictures\\Saved Pictures" + "ckImage/";	// 저장된 이미지 경로
    	System.out.println("path:"+path);
    	String sDirPath = path + uid + "_" + fileName;
    	
    	File imgFile = new File(sDirPath);
    	
    	//사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
    	if(imgFile.isFile()){
    		byte[] buf = new byte[1024];
    		int readByte = 0;
    		int length = 0;
    		byte[] imgBuf = null;
    		
    		FileInputStream fileInputStream = null;
    		ByteArrayOutputStream outputStream = null;
    		ServletOutputStream out = null;
    		
    		try{
    			fileInputStream = new FileInputStream(imgFile);
    			outputStream = new ByteArrayOutputStream();
    			out = response.getOutputStream();
    			
    			while((readByte = fileInputStream.read(buf)) != -1){
    				outputStream.write(buf, 0, readByte); 
    			}
    			
    			imgBuf = outputStream.toByteArray();
    			length = imgBuf.length;
    			out.write(imgBuf, 0, length);
    			out.flush();
    			
    		}catch(IOException e){
    			e.printStackTrace();
    		}finally {
    			outputStream.close();
    			fileInputStream.close();
    			out.close();
    			}
    		}
    }

	
}
