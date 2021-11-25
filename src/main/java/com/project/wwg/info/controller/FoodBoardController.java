package com.project.wwg.info.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

import com.project.wwg.info.dto.FoodBoard;
import com.project.wwg.info.service.FoodBoardService;
import com.project.wwg.info.service.PagingPgm;

@Controller
public class FoodBoardController {

	@Autowired
	private FoodBoardService service;
	
	// 글작성 폼
	@RequestMapping("foodform.do")
	public String foodform() {
		return "info/foodform";
	}
	
	// 글작성
	@RequestMapping("foodwrite.do")
	public String foodwrite(FoodBoard foodboard, Model model, HttpServletRequest request) {
		System.out.println("in");
		System.out.println("food_title:"+foodboard.getFood_title());
		
		int food_no = foodboard.getFood_no();
		int number = service.getMaxNum();
		foodboard.setFood_no(number);
		int result = service.insert(foodboard);
		model.addAttribute("result", result);
		
		return "info/insertresult";
	}
	
	// 글목록 첫페이지 실행
	@RequestMapping("foodlist.do")
	public String initList() {
		return "redirect:foodlist.do/pageNum/1";
	}
	
	// 글목록
	@RequestMapping("foodlist.do/pageNum/{pageNum}")
	public String foodlist(@PathVariable String pageNum, FoodBoard foodboard, Model model) {
		final int rowPerPage = 10;
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		// 총 데이터 갯수
		int currentPage = Integer.parseInt(pageNum);
		
		int total = service.getTotal(foodboard);	// 검색
		int startRow = (currentPage - 1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
		PagingPgm pp = new PagingPgm(total, rowPerPage, currentPage);
		foodboard.setStartRow(startRow);
		foodboard.setEndRow(endRow);
		
		int no = total - startRow + 1;	// 화면 출력 번호
		List<FoodBoard> foodlist = service.foodlist(foodboard);
		model.addAttribute("foodlist", foodlist);
		model.addAttribute("no", no);
		model.addAttribute("pp", pp);
		// 검색
		model.addAttribute("search", foodboard.getSearch());
		model.addAttribute("keyword", foodboard.getKeyword());
		
		return "info/foodlist";
	}
	
	// 상세페이지
	@RequestMapping("foodcontent.do/food_no/{food_no}/pageNum/{pageNum}")
	public String foodcontent(@PathVariable int food_no, @PathVariable String pageNum, Model model) {
		service.selectUpdate(food_no);					// 조회수 1증가
		FoodBoard foodboard = service.select(food_no);	// 상세 정보 구하기
		model.addAttribute("foodboard", foodboard);
		model.addAttribute("pageNum", pageNum);
		return "info/foodcontent";
	}
	
	// 글수정 폼
	@RequestMapping("foodupdateform.do/food_no/{food_no}/pageNum/{pageNum}")
	public String foodupdateform(@PathVariable int food_no, @PathVariable String pageNum, Model model) {
		FoodBoard foodboard = service.select(food_no);
		model.addAttribute("foodboard", foodboard);
		model.addAttribute("pageNum", pageNum);
		return "info/foodupdateform";
	}
	
	//글수정
	@RequestMapping("foodupdate.do/pageNum/{pageNum}")
	public String foodupdate(FoodBoard foodboard, @PathVariable String pageNum, Model model) {
		int result = service.update(foodboard);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/foodupdate";
	}
	
	// 글삭제 폼
	@RequestMapping("fooddeleteform.do")
//	public String fooddeleteform(@PathVariable int food_no, @PathVariable String pageNum, Model model) {
	public String fooddeleteform() {
//		int result = service.delete(food_no);
//		model.addAttribute("result", result);
//		model.addAttribute("pageNum", pageNum);
		return "info/fooddeleteform";
	}

	// 글삭제
	@PostMapping("fooddelete.do")
	public String fooddelete(@RequestParam int food_no, @RequestParam String pageNum, Model model) {
		System.out.println("food_no:"+food_no);
		int result = service.delete(food_no);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "info/fooddelete";
	}
	
	// 좋아요
    @RequestMapping("foodlike.do")
    public String recommend (@RequestParam int food_no, Model model) {
		service.like(food_no);					// 좋아요 1증가
        model.addAttribute("food_no", food_no);
        return "redirect:foodlist.do";
    }

    // 이미지 업로드
    @RequestMapping(value="food/imageUpload.do", method = RequestMethod.POST)
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
    		String path = "C:\\Users\\wowo1\\Pictures\\Saved Pictures" + "ckImage/";// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
    		String ckUploadPath = path + uid + "_" + fileName;
    		File folder = new File(path);
    		System.out.println("path:"+path);
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
    	String fileUrl = "/food/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName; // 작성화면
    	
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
    @RequestMapping(value="/food/ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value="uid") String uid
    		, @RequestParam(value="fileName") String fileName
    		, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	
    	//서버에 저장된 이미지 경로
    	String path = "C:\\Users\\wowo1\\Pictures\\Saved Pictures" + "ckImage/";	// 이미지 
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
