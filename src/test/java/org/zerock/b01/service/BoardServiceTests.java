package org.zerock.b01.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

  @Autowired
  private BoardService boardService;

  @Test
  public void testRegister() {
    log.info(boardService.getClass().getName());

    BoardDTO boardDTO = BoardDTO.builder()
            .title("샘플 타이틀...")
            .content("샘플 내용...")
            .writer("user00")
            .build();
    Long bno = boardService.register(boardDTO);

    log.info("bno: " + bno);
  }

  @Test
  public void testModify(){

    BoardDTO boardDTO = BoardDTO.builder()
            .bno(101L)
            .title("Updated....101")
            .content("Updated content 101...")
            .build();
    boardService.modify(boardDTO);
  }

  @Test
  public void testList(){
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .type("tcw")
            .keyword("1")
            .page(1)
            .size(10)
            .build();
    PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

    log.info("responseDTO: " + responseDTO);
  }
}
