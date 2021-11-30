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

import com.project.wwg.info.dto.TourBoard;
import com.project.wwg.info.service.TourBoardService;
import com.project.wwg.info.service.PagingPgm;

@Controller
public class TourBoardController {

	@Autowired
	private TourBoardService service;
	
	// 글작성 폼
	@RequestMapping("tourform.do")
	public String tourform(Principal principal, Model model) {
		
		/*
		 * String username = principal.getName(); // 로그인후 유저네임 넘기기
		 * model.addAttribute("username", username); // 로그인후 유저네임 넘기기
		 */		 		
		return "info/tour/tourform";
	}
	
	// 글작성
	@RequestMapping("tourwrite.do")
	public String tourwrite(Principal principal, TourBoard tourboard, Model model, HttpServletRequest request) {
		System.out.println("in");
		System.out.println("tour_title:"+tourboard.getTour_title());

		String username = principal.getName(); // 로그인후 유저네임 넘기기
		System.out.println("username:"+username);
		 
		int tour_no = tourboard.getTour_no();
		int number = service.getMaxNum();
		
		tourboard.setTour_no(number);
		
		tourboard.setUsername(username); // 로그인후 유저네임 넘기기
		 		
		int result = service.insert(tourboard);
		model.addAttribute("result", result);
		/* model.addAttribute("username", username); */
		
		return "info/tour/tourinsertresult";
	}
	
	// 글목록 첫페이지 실행
	@RequestMapping("tourlist.do")
	public String initList() {
		return "redirect:tourlist.do/pageNum/1";
	}
	
	// 글목록
	@RequestMapping("tourlist.do/pageNum/{pageNum}")
	public String tourlist(@PathVariable String pageNum, TourBoard tourboard, Model model) {
		final int rowPerPage = 10;
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		// 총 데이터 갯수
		int currentPage = Integer.parseInt(pageNum);
		
		int total = service.getTotal(tourboard);	// 검색
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		tourboard.setStartRow(startRow);
		tourboard.setEndRow(endRow);
		
		int no = total - startRow + 1;	// 화면 출력 번호
		List<TourBoard> tourlist = service.tourlist(tourboard);
		model.addAttribute("tourlist", tourlist);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", tourboard.getSearch());
		model.addAttribute("keyword", tourboard.getKeyword());
		
		return "info/tour/tourlist";
	}
	
	// 상세페이지
	@RequestMapping("tourcontent.do/tour_no/{tour_no}/pageNum/{pageNum}")
	public String tourcontent(@PathVariable int tour_no, @PathVariable String pageNum, Model model) {
		service.selectUpdate(tour_no);					// 조회수 1증가
		TourBoard tourboard = service.select(tour_no);	// 상세 정보 구하기
		model.addAttribute("tourboard", tourboard);
		model.addAttribute("pageNum", pageNum);
		return "info/tour/tourcontent";
	}
	
	// 글수정 폼
	@RequestMapping("tourupdateform.do/tour_no/{tour_no}/pageNum/{pageNum}")
	public String tourupdateform(@PathVariable int tour_no, @PathVariable String pageNum, Model model) {
		TourBoard tourboard = service.select(tour_no);
		model.addAttribute("tourboard", tourboard);
		model.addAttribute("pageNum", pageNum);
		return "info/tour/tourupdateform";
	}
	
	//글수정
	@RequestMapping("tourupdate.do/pageNum/{pageNum}")
	public String tourupdate(TourBoard tourboard, @PathVariable String pageNum, Model model) {
		int result = service.update(tourboard);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/tour/tourupdate";
	}
	
	// 글삭제 폼
	@RequestMapping("tourdeleteform.do")
//	public String tourdeleteform(@PathVariable int tour_no, @PathVariable String pageNum, Model model) {
	public String tourdeleteform() {
//		int result = service.delete(tour_no);
//		model.addAttribute("result", result);
//		model.addAttribute("pageNum", pageNum);
		return "info/tour/tourdeleteform";
	}

	// 글삭제
	@PostMapping("tourdelete.do")
	public String tourdelete(@RequestParam int tour_no, @RequestParam String pageNum, Model model) {
		System.out.println("tour_no:"+tour_no);
		int result = service.delete(tour_no);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/tour/tourdelete";
	}
	
	// 좋아요
    @RequestMapping("tourlike.do")
    public String recommend (@RequestParam int tour_no, Model model) {
		service.like(tour_no);					// 좋아요 1증가
        model.addAttribute("tour_no", tour_no);
        return "redirect:tourlist.do";
    }

    // 이미지 업로드
    @RequestMapping(value="tour/imageUpload.do", method = RequestMethod.POST)
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
    	String fileUrl = "/tour/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName; // 작성화면
    	
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
    @RequestMapping(value="/tour/ckImgSubmit.do")
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
