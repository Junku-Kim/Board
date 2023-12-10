package com.jk.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.board.entity.BoardFile;
import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;
import com.jk.board.repository.BoardFileRepository;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
/*
 * BoardFile Controller입니다.
 * 파일을 다운로드 할 때 API라고 보기 에매하고 URL의 형식이 다른 느낌이므로
 * API Controller와 분리했습니다.
 */
public class BoardFileController {
	
	private final BoardFileRepository boardFileRepository;
	
	@GetMapping("/fileDownload/{fileIdx}")
	@ResponseBody
	/*
	 * 파일 다운로드 메서드
	 * 
	 * Description:
	 *  게시글 상세 페이지에서 파일 이름 클릭 시 작동하는 메서드입니다.
	 *  
	 */
	public void downloadFile(HttpServletResponse res, @PathVariable Long fileIdx) throws UnsupportedEncodingException {
		
		//파일 조회
		BoardFile boardFile = boardFileRepository.findById(fileIdx).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		
		//파일 경로 저장
		// File.separator를 통해 OS에 종속되지 않고 저장이 가능합니다.
		Path savedFilePath = Path.of(boardFile.getUploadDir() + File.separator + boardFile.getSavedName());
		
		//해당 경로에 파일이 없을 때
		if (!savedFilePath.toFile().exists()) {
			throw new RuntimeException("File not found");
		}
		
		//파일 헤더 설정
		setFileHeader(res, boardFile);
		
		//파일 복사
		copyFile(res, savedFilePath);
	}

	//파일 헤더 설정 메서드
	private void setFileHeader(HttpServletResponse res, BoardFile boardFile) throws UnsupportedEncodingException {
		/*
		 * Content-Disposition 헤더는 클라이언트에게 전송된 데이터가 어떻게 처리되어야 하는지를 나타냅니다.
		 * attachment는 로컬 컴퓨터에 저장하라는 의미이고, filename는 해당 이름으로 저장하라는 것을 의미합니다.
		 * URLEncoder.encode를 사용하여 파일 이름을 UTF-8 형식으로 인코딩하여 특수 문자나 공백과 같은 문자를 안전하게 처리합니다.
		 */
		res.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String) boardFile.getOriginalName(), "UTF-8") + "\";");
		// 이진 파일로 데이터를 전송하는 것을 의미합니다.
		res.setHeader("Content-Transfer-Encoding", "binary");
		// 전송되는 데이터의 MIME 타입을 의미합니다.
		res.setHeader("Content-Type", "application/download; utf-8");
		/*
		 * 중간 프록시나 클라이언트가 캐시 응답을 하지 않게하는 것을 의미합니다.
		 * 이에 따른 장점으로
		 *  - 서버에서 항상 최신 데이터를 가져올 수 있습니다.
		 *  - 즉, 동적이고 빈번하게 업데이트되는 컨텐츠의 경우 유리합니다.
		 */
		res.setHeader("Pragma", "no-cache;");
		// 유효기간을 나타냅니다.
		res.setHeader("Expires", "-1");
	}
	
	//파일 복사 메서드
	private void copyFile(HttpServletResponse res, Path savedFilePath) {
		FileInputStream fis = null;
		
		try {
			// 입력 스트림 생성
			fis = new FileInputStream(savedFilePath.toFile());
			FileCopyUtils.copy(fis, res.getOutputStream());
			// 응답 스트림을 플러시해 모든 데이터를 클라이언트로 전송합니다.
			res.getOutputStream().flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
