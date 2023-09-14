package com.jk.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jk.board.domain.Board;
import com.jk.board.repository.BoardRepository;

@Transactional
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	// 게시글 작성 처리
	public void boardWrite(Board board, MultipartFile file) throws IllegalStateException, IOException {
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + file.getOriginalFilename();
		
		File savedFile = new File(projectPath, fileName);
		
		file.transferTo(savedFile);
		
		Board newBoard = board.withFileNameAndFilePath(fileName, "/files/" + fileName);
		
		boardRepository.save(newBoard);
	}
	
	// 게시글 리스트 처리
	public List<Board> boardList() {
		
		return boardRepository.findAll();
	}
	
	// 특정 게시글 불러오기
	public Optional<Board> boardView(Long id) {
		
		return boardRepository.findById(id);
	}
	
	// 특정 게시글 삭제
	public void boardDelete(Long id) {
		boardRepository.deleteById(id);
	}
}
