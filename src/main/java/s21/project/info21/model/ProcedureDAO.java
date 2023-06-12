package s21.project.info21.model;

import lombok.RequiredArgsConstructor;
import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class ProcedureDAO {

//    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProcedureDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> callProcedure(String query, Object... args) throws SQLException {
        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
        CallableStatement stm = connection.prepareCall(query);

        connection.setAutoCommit(false);

        int index = 1;
        stm.setObject(index++, null);

        if (args != null) {
            for (Object ob : args) {
                stm.setObject(index++, ob);
            }
        }

        stm.registerOutParameter(1, Types.REF_CURSOR);
        stm.execute();

        ResultSet rs = (ResultSet) stm.getObject(1);
        return unpackResultSet(rs);
    }

    public Map<String, Object> executeQuery(String query, Object... args) throws SQLException {
        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();

        CallableStatement stm = connection.prepareCall(query);

        int index = 1;

        if (args != null) {
            for (Object ob : args) {
                stm.setObject(index++, ob);
            }
        }

        ResultSet rs = stm.executeQuery();

        return unpackResultSet(rs);
    }

    public Map<String, Object> unpackResultSet(ResultSet rs) throws SQLException {
        Map <String, Object> map = new LinkedHashMap<>();
        ResultSetMetaData mtd = rs.getMetaData();
        int columnCount = mtd.getColumnCount();
        List<String> columnName = new ArrayList<>();
        List<List<Object>> allRows = new ArrayList<>();

        if (!rs.next()) {
            return map;
        }

        for (int i = 0; i < columnCount; ++i) {
            columnName.add(mtd.getColumnName(i + 1));
            List<Object> tmp = new ArrayList<>();
            tmp.add(rs.getObject(i + 1));
            allRows.add(tmp);
        }
        while (rs.next()) {
            for (int i = 0; i < columnCount; ++i) {
                allRows.get(i).add(rs.getObject(i + 1));
            }
        }
        for (int i = 0; i < columnCount; ++i) {
            map.put(columnName.get(i), allRows.get(i));
        }

        for (var a : map.entrySet()) {
            System.out.println(a.getKey() + "  " + a.getValue());
        }

        return map;
    }
}
