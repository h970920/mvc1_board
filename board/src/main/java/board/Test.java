package board;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        BoardDAO dao = new BoardDAO();
        List<BoardVO> boardList = dao.selectAll();
        
        // 출력
        printBoardList(boardList);
    }
    
    private static void printBoardList(List<BoardVO> boardList) {
        if (boardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            System.out.println("게시글 수: " + boardList.size());
            System.out.println("----------------------");
            for (BoardVO vo : boardList) {
                System.out.println("번호: " + vo.getNum());
                System.out.println("제목: " + vo.getTitle());
                System.out.println("작성자: " + vo.getWriter());
                System.out.println("내용: " + vo.getContent());
                System.out.println("등록일: " + vo.getRegdate());
                System.out.println("조회수: " + vo.getCnt());
                System.out.println("----------------------");
            }
        }
    }
}
