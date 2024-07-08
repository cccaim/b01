package org.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
  public BoardSearchImpl() {
    super(Board.class); // 쿼리DSL로 Board연동
  }

  @Override
  public Page<Board> search1(Pageable pageable) {
    // QueryDSL 객체를 사용
    QBoard board = QBoard.board;
    // board 테이블 사용
    JPQLQuery<Board> query = from(board);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    booleanBuilder.or(board.title.contains("11"));    //title like...
    booleanBuilder.or(board.content.contains("11"));  //content like ...

    //where 절
    query.where(booleanBuilder);
    query.where(board.bno.gt(0L));

    this.getQuerydsl().applyPagination(pageable, query);


    List<Board> boards = query.fetch();
    // 검색 결과 갯수
    long count = query.fetchCount();

    return  null;
  }
}

