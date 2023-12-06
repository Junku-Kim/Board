package com.jk.board.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jk.board.dto.BoardFileDTO;
import com.jk.board.dto.BoardRequest;
import com.jk.board.entity.BoardFile;
import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;
import com.jk.board.repository.BoardFileRepository;
import com.jk.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardFileService {
	
	@Value("${upload.path}")
	private String uploadDir;

	private final BoardFileRepository boardFileRepository;
	private final BoardRepository boardRepository;

	public Map<String, Object> saveFiles(final Long boardId, final BoardRequest boardRequest) throws Exception {
		List<MultipartFile> multipartFiles = boardRequest.getMultipartFiles();
		
		Map<String, Object> result = new HashMap<>();
		
		List<Long> fileIds = new ArrayList<>();
		
		try {
			if (multipartFiles != null) {
				if (multipartFiles.size() > 0 && !multipartFiles.get(0).getOriginalFilename().equals("")) {
					for (MultipartFile file : multipartFiles) {
						String originalFileName = file.getOriginalFilename();
						String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
						String savedFileName = UUID.randomUUID() + extension;
						
						File targetFile = new File(uploadDir + savedFileName);
						
						result.put("result", "FAIL");
						
						BoardFileDTO boardFileDto = BoardFileDTO.builder()
								.originalName(originalFileName)
								.savedName(savedFileName)
								.uploadDir(uploadDir)
								.extension(extension)
								.size(file.getSize())
								.contentType(file.getContentType())
								.board(boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND)))
								.build();
						
						Long fileId = boardFileRepository.save(boardFileDto.toEntity()).getId();
						
						try {
							InputStream fileStream = file.getInputStream();
							FileUtils.copyInputStreamToFile(fileStream, targetFile);
							fileIds.add(fileId);
							result.put("fileIdxs", fileIds.toString());
							result.put("result", "OK");
						} catch (Exception e) {
							FileUtils.deleteQuietly(targetFile);
							e.printStackTrace();
							result.put("result", "FAIL");
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 * 게시판 파일 삭제
	 */
	public Long deleteFile(final Long boardFileId) {
		BoardFile boardFile = boardFileRepository.findById(boardFileId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		
		boardFile.delete();
		return boardFileId;
	}
}
