package com.jk.board.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jk.board.repository.BoardRepository;

@Transactional
@SpringBootTest
public class BoardTest {

	@Autowired
	BoardRepository boardRepository;
	
	static Stream<Arguments> boardProvider() {
		return Stream.of(
				Board.builder()
				   .title("제목1")
				   .content("내용1")
				   .writer("김준일")
				   .hits(1)
				   .build(),
				   
				   Board.builder()
				   .title("제목2")
				   .content("내용2")
				   .writer("김준이")
				   .hits(2)
				   .build(),
				   
				   Board.builder()
				   .title("제목3")
				   .content("내용3")
				   .writer("김준삼")
				   .hits(3)
				   .build(),
				   
				   Board.builder()
				   .title("제목4")
				   .content("내용4")
				   .writer("김준사")
				   .hits(4)
				   .build(),

				   Board.builder()
				   .title("제목5")
				   .content("내용5")
				   .writer("김준오")
				   .hits(5)
				   .build()
				)
				.map(Arguments::of);
	}

	@AfterEach
	void afterEach() {
		boardRepository.deleteAll();
	}
	
	@ParameterizedTest
	@MethodSource("boardProvider")
	void 게시글_저장_후_확인(Board board) {
		boardRepository.save(board);
		
		List<Board> boards = boardRepository.findAll();
		assertThat(boards).isNotEmpty();
	}
}
