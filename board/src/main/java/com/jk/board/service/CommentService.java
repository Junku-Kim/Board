package com.jk.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		
		return comment.getId();
	}
	
	/*
	 * 게시글 리스트 조회
	 */
	public List<CommentResponse> findAllComments(final Long boardId) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
		Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
		
		List<Comment> list = commentRepository.findAllByBoardAndIsDeleted(board, false, sort);

		return list.stream().map(CommentResponse::new).toList();
	}
	
	/*
	 * 댓글 상세정보 조회
	 */
	@Transactional
	public Optional<CommentResponse> findCommentById(final Long id) {
		return commentRepository.findById(id).map(CommentResponse::new);
	}
}
