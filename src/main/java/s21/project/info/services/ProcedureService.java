package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.ProcedureDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProcedureService {

    @Autowired
    private ProcedureDAO procedureDAO;
    private final Map<Integer, String> procedureNames = new HashMap<>();
    {
        procedureNames.put(1, "select * from fnc_transferredpoints()");
        procedureNames.put(2, "select * from fnc_task_successful()");
        procedureNames.put(3, "select * from fnc_full_day(?::date)");
        procedureNames.put(4, "call pr_percent_successful(?::refcursor)");
        procedureNames.put(5, "call pr_transferred_points(?::refcursor)");
        procedureNames.put(6, "call pr_count_points(?::refcursor)");
        procedureNames.put(7, "call pr_the_most_checked_task(?::refcursor)");
        procedureNames.put(8, "call pr_duration_last_p2p(?::refcursor)");
        procedureNames.put(9, "call pr_peers_ending_block(?::refcursor, ?::varchar)");
        procedureNames.put(10, "call pr_peers_recommendations(?::refcursor)");
        procedureNames.put(11, "call pr_peers_who_start_block(?::refcursor, ?::varchar, ?::varchar)");
        procedureNames.put(12, "call pr_count_friend(?::refcursor)");
        procedureNames.put(13, "call pr_percent_successful_birthday(?::refcursor)");
        procedureNames.put(14, "call pr_max_xp(?::refcursor)");
        procedureNames.put(15, "call pr_find_completed_tasks(?::refcursor, ?::varchar, ?::varchar, ?::varchar)");
        procedureNames.put(16, "call prev_tasks(?::refcursor)");
        procedureNames.put(17, "call pr_success_checks(?::refcursor, ?::int)");
        procedureNames.put(18, "call pr_count_project(?::refcursor)");
        procedureNames.put(19, "call pr_count_xp(?::refcursor)");
        procedureNames.put(20, "call pr_max_by_time(?::refcursor, ?::date)");
        procedureNames.put(21, "call pr_entry_time(?::refcursor, ?::time, ?::int)");
        procedureNames.put(22, "call pr_peer_who_exit(?::refcursor, ?::date, ?::int, ?::int)");
        procedureNames.put(23, "call pr_last_peer(?::refcursor)");
        procedureNames.put(24, "call pr_exit_time(?::refcursor, ?::int)");
        procedureNames.put(25, "call pr_early_came_percent(?::refcursor)");
    }

    public String getQueryByID(int id) {
        return procedureNames.get(id);
    }

    public Map<String, Object> executeQuery(String query, Object... args) throws SQLException {
        return query.contains("call") ? procedureDAO.callProcedure(query, args) : procedureDAO.executeQuery(query, args);
    }


}
