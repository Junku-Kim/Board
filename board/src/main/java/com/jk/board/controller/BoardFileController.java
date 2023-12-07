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
public class BoardFileController {
	
	private final BoardFileRepository boardFileRepository;
	
	@GetMapping("/fileDownload/{fileIdx}")
	@ResponseBody
	public void downloadFile(HttpServletResponse res, @PathVariable Long fileIdx) throws UnsupportedEncodingException {
		
		//파일 조회
		BoardFile boardFile = boardFileRepository.findById(fileIdx).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		
		//파일 경로
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

	//파일 헤더 설정
	private void setFileHeader(HttpServletResponse res, BoardFile boardFile) throws UnsupportedEncodingException {
		res.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String) boardFile.getOriginalName(), "UTF-8") + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setHeader("Content-Type", "application/download; utf-8");
		res.setHeader("Pragma", "no-cache;");
		res.setHeader("Expires", "-1");
	}
	
	//파일 header 설정
	private void copyFile(HttpServletResponse res, Path savedFilePath) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(savedFilePath.toFile());
			FileCopyUtils.copy(fis, res.getOutputStream());
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
