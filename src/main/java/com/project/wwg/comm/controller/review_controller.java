package com.project.wwg.comm.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.wwg.comm.model.review;
import com.project.wwg.comm.service.review_PagingPgm;
import com.project.wwg.comm.service.review_service;

@Controller
public class review_controller {

	@Autowired
	private review_service rs;
	
	// 글목록 첫페이지 실행
		@RequestMapping("reviewlist")
		public String initList() {
			return "redirect:reviewlist/pageNum/1";
		}

	@RequestMapping("/reviewlist/pageNum/{pageNum}")
	public String list(@PathVariable String pageNum, review review, Model model) {
		
		final int rowPerPage = 10;	// 화면에 출력할 데이터 갯수
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지 번호
		
		// int total = bs.getTotal();
		int total = rs.getTotal(review); // 검색 (데이터 갯수)
		
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		
		review_PagingPgm pp = new review_PagingPgm(total, rowPerPage, currentPage);
		review.setStartRow(startRow);
		review.setEndRow(endRow);
		// List<Board> list = bs.list(startRow, endRow);
		int no = total - startRow + 1;		// 화면 출력 번호
		List<review> list = rs.list(review);
		
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", review.getSearch());
		model.addAttribute("keyword", review.getKeyword());
		
		return "comm/review/review_list";
	}

	// 글작성 폼
		@RequestMapping("/comm_reviewinsertform")
		public String review_insertform() {
			return "comm/review/review_insertform";
		}
		
	// 글작성
		@RequestMapping("/comm_reviewinsert")
		public String reviewinsert(Principal principal, review review, Model model) {

			System.out.println("제목:"+review.getReview_title());
			System.out.println("내용:"+review.getReview_content());
			
			String username = principal.getName(); // 로그인후 유저네임 넘기기
			System.out.println("username:"+username);
			review.setMember_id(username); // 로그인후 유저네임 넘기기
			
			int review_no = review.getReview_no();
			int number = rs.getMaxNum();
			review.setReview_no(number);
			int result = rs.insert(review);
			
			model.addAttribute("result", result);
			
			return "comm/review/review_insertalert";
		}
	// 상세페이지	
		@RequestMapping("/reviewview/review_no/{review_no}/pageNum/{pageNum}")
		public String view(@PathVariable int review_no, @PathVariable String pageNum, Model model) {
			rs.selectUpdate(review_no);
			review review = rs.select(review_no);
			model.addAttribute("review", review);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_view";
		}	
	// 수정폼	
		@RequestMapping("/reviewupdateform/review_no/{review_no}/pageNum/{pageNum}")
		public String updateForm(@PathVariable int review_no, @PathVariable String pageNum, Model model) {
			review review = rs.select(review_no);
			model.addAttribute("review", review);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_updateform";
		}
	// 수정	
		@RequestMapping("/reviewupdate/pageNum/{pageNum}")
		public String update(review review, @PathVariable String pageNum, Model model) {
			int result = rs.update(review);
			model.addAttribute("result", result);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_update";
		}
		
	// 삭제폼	
//		@RequestMapping("/reviewdeleteForm/review_no/{review_no}/pageNum/{pageNum}")
//		public String deleteForm(@PathVariable int review_no, @PathVariable String pageNum, Model model) {
//			review review = ns.select(review_no);
//			model.addAttribute("review", review);
//			model.addAttribute("pageNum", pageNum);
//			return "comm/comm_reviewdeleteform";
//		}
		
    // 삭제
		@RequestMapping("/reviewdelete/review_no/{review_no}/pageNum/{pageNum}")
		public String delete(@PathVariable int review_no, String pageNum, Model model) {
			int result = rs.delete(review_no);
			model.addAttribute("result", result);
			model.addAttribute("pageNum", pageNum);
			return "comm/review/review_delete";
		}
		
		// 좋아요
	    @RequestMapping("revlike")
	    public String recommend (@RequestParam int review_no, Model model) {
			rs.like(review_no);					// 좋아요 1증가
	        model.addAttribute("review_no", review_no);
	        return "redirect:reviewlist/pageNum/1";
	    }
	    
	 // 이미지 업로드
	    @RequestMapping(value="comm/imageupload", method = RequestMethod.POST)
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
	    		String path = "D:\\ryu\\Pictures\\Saved Pictures" + "ckImage/";	// 이미지 경로 설정(폴더 자동 생성)
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
	    	String fileUrl = "/comm/ckImgSubmit?uid=" + uid + "&fileName=" + fileName; // 작성화면
	    	
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
	    @RequestMapping(value="/comm/ckImgSubmit")
	    public void ckSubmit(@RequestParam(value="uid") String uid
	    		, @RequestParam(value="fileName") String fileName
	    		, HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException{
	    	
	    	//서버에 저장된 이미지 경로
	    	String path = "D:\\ryu\\Pictures\\Saved Pictures" + "ckImage/";	// 저장된 이미지 경로
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
