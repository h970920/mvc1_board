package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JdbcUtil;

public class BoardDAO {

    private JdbcUtil ju;

    public BoardDAO() {
        ju = JdbcUtil.getInstance();
    }

    // 게시물 삽입(C)
    public int insert(BoardVO vo) {
        String query = "INSERT INTO board(num, title, writer, content, regdate, cnt) VALUES(board_seq.nextval, ?, ?, ?, sysdate, 0)";
        try (Connection con = ju.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getWriter());
            pstmt.setString(3, vo.getContent());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 모든 게시물 조회(R)
    public List<BoardVO> selectAll() {
        String query = "SELECT num, title, writer, content, regdate, cnt FROM board";
        List<BoardVO> boardList = new ArrayList<>();

        try (Connection con = ju.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BoardVO vo = new BoardVO(
                        rs.getInt("num"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("content"),
                        new Date(rs.getDate("regdate").getTime()),
                        rs.getInt("cnt")
                );
                boardList.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardList;
    }

    // 단일 게시물 조회(R)
    public BoardVO selectOne(int num) {
        String query = "SELECT num, title, writer, content, regdate, cnt FROM board WHERE num = ?";
        try (Connection con = ju.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, num);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    updateCnt(num); // 조회수 증가
                    return new BoardVO(
                            rs.getInt("num"),
                            rs.getString("title"),
                            rs.getString("writer"),
                            rs.getString("content"),
                            new Date(rs.getDate("regdate").getTime()),
                            rs.getInt("cnt")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 수정(U)
    public int update(BoardVO vo) {
        String query = "UPDATE board SET title=?, content=? WHERE num=?";
        try (Connection con = ju.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getNum());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 삭제(D)	
    public int delete(int num) {
        String query = "DELETE FROM board WHERE num=?";
        try (Connection con = ju.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, num);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 조회수 증가
    public int updateCnt(int num) {
        String query = "UPDATE board SET cnt=cnt+1 WHERE num=?";
        try (Connection con = ju.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, num);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
