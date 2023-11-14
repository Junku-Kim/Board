package com.jk.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.dto.CommentRequest;
import com.jk.board.dto.CommentResponse;
import com.jk.board.entity.Board;
import com.jk.board.entity.Comment;
import com.jk.board.exception.CustomException;
import com.jk.board.exception.ErrorCode;
import com.jk.board.repository.BoardRepository;
import com.jk.board.repository.CommentRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;

	public CommentService(final CommentRepository commentRepository, final BoardRepository boardRepository) {
		this.commentRepository = commentRepository;
		this.boardRepository = boardRepository;
	}
	
	/*
	 * 댓글 생성
	 */
	@Transactional
	public Long writeComment(final Long boardId, final CommentRequest commentRequest) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

		Comment comment = commentRequest.toEntity();
		comment.setBoard(board);
		Comment savedComment = commentRepository.save(comment);

		return savedComment.getId();
	}
	
	/*
	 * 댓글 수정
	 */
	@Transactional
	public Long updateComment(final Long id, final CommentRequest commentRequest) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		comment.update(commentRequest.getComment(), commentRequest.getWriter());
		
		return id;
	}
	
	/*
	 * 댓글 삭제
	 */
	@Transactional
	public Long deleteComment(final Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		comment.delete();
		
		return id;
	}
	
	/*
	 * 댓글 리스트 조회
	 */
	public Page<CommentResponse> findAllComments(final Long boardId, final Pageable pageable) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		
		return commentRepository.findAllByBoardAndIsDeleted(board, false, pageable).map(CommentResponse::new);
	}
	
	/*
	 * 댓글 상세정보 조회
	 */
	@Transactional
	public CommentResponse findCommentById(final Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		
		return new CommentResponse(comment); 
	}
}
