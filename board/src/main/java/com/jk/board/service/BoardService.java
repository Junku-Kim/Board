//package com.jk.board.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.jk.board.entity.Board;
//import com.jk.board.repository.BoardRepository;
//
//
//@Transactional
//@Service
//public class BoardService {
//
//	private final BoardRepository boardRepository;
//	
//	public BoardService(BoardRepository boardRepository) {
//		this.boardRepository = boardRepository;
//	}
//	
//	// 게시글 작성 처리
//	public void boardWrite(Board board) {
//		boardRepository.save(board);
//	}
//	
//	// 파일을 포함한 게시글 작성 처리
//	public void boardWrite(Board board, MultipartFile file) throws IllegalStateException, IOException {
//		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//		UUID uuid = UUID.randomUUID();
//		String fileName = uuid + "_" + file.getOriginalFilename();
//		File savedFile = new File(projectPath, fileName);
//		file.transferTo(savedFile);
//
//		Board newBoard = board.withFileNameAndFilePath(fileName, "/files/" + fileName);
//		boardRepository.save(newBoard);
//	}
//	
//	// 게시글 리스트 처리
//	public Page<Board> boardList(Pageable pageable) {
//		return boardRepository.findAll(pageable);
//	}
//	
//	// 제목을 통한 특정 게시글 검색
//	public Page<Board> boardSearchListWithTitle(String searchKeyword, Pageable pageable) {
//		return boardRepository.findByTitleContaining(searchKeyword, pageable);
//	}
//	
//	// 특정 게시글 불러오기
//	public Optional<Board> boardView(Long id) {
//		return boardRepository.findById(id);
//	}
//	
//	// 특정 게시글 삭제
//	public void boardDelete(Long id) {
//		Optional<Board> optionalBoard = boardRepository.findById(id);
//		
//		if (optionalBoard.isPresent()) {
//			Board board = optionalBoard.get();
//			if (board.getFileName() != null) {
//				String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//				File fileToDelete = new File(projectPath, board.getFileName());
//
//				if (fileToDelete.exists()) {
//					if (fileToDelete.delete()) {
//					}
//				}
//			}
//		}
//		
//		boardRepository.deleteById(id);
//	}
//}
