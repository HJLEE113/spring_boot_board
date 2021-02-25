package edu.bit.ex;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.bit.ex.mapper.BoardMapper;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) // 스프링4버전. 이게 들어가줘야함
//@RunWith~~~ //예전에는 이렇게 가져옴
@SpringBootTest
class BoardTests {

	@Autowired
	private BoardMapper mapper;

	@Autowired
	private DataSource ds;

	@Test // juit5으로 가져와야함 4로가져오면 오류남
	public void testDataSource() {
		System.out.println("DS=" + ds);
		try (Connection conn = ds.getConnection()) {
			System.out.println("Cooooooooonn=" + conn);

			assertThat(conn).isInstanceOf(Connection.class);
			assertEquals(100, getLong(conn, "select 100 from dual"));
			// assertTrue(0 < getLong(conn, "select count(*) from User"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void assertEquals(int i, long long1) {
		//이거없었는데...	
	}

	private long getLong(Connection conn, String sql) {
		long result = 0;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Test
	public void testGetList() {

		System.out.println(mapper);
		System.out.println(mapper.getList().size());

		for (BoardVO vo : mapper.getList()) {
			log.info(vo.getbName());
			System.out.println(vo.getbName());
		}

	}

}
