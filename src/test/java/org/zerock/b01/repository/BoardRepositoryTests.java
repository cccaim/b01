package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b01.domain.Board;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user"+(i%10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        }
    }

    @Test
    public void testSelect(){
        Long bno = 1L;
        //Optional 타입은 없을 경우 null 값 에러 방지를 위함
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow(); //결과가 있을 경우에 가져오고 없으면 예외발생

        log.info(board.toString());
    }

    //update 기능 테스트
    @Test
    public void testUpdate(){
        Long bno = 100L;
        //Optional 타입은 없을 경우 null 값 에러 방지를 위함
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update..title 100", "update..content 100");
        boardRepository.save(board);//update 도 save 메서드로 사용함
        // 1. 새로운 객체 입력시 새로 등록됨 (Create)
        // 2. 같은 객체 입력시 수정된 내용이 업데이트 됨 (기준은 id 기준)
    }

    @Test
    public void testDelete(){
        long bno = 1L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testSearch1(){
        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
        boardRepository.search1(pageable);
    }
}
