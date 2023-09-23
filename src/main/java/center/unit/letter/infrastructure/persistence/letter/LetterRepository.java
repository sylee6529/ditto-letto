package center.unit.letter.infrastructure.persistence.letter;

import center.unit.letter.domain.letter.Letter;
import center.unit.letter.domain.user.User;
import center.unit.letter.presentation.letter.dto.response.MyLetterResponse;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LetterRepository extends CrudRepository<Letter, Long> {

    @Query("select count(l) from Letter l where l.from.id = :fromId and l.to.id = :toId")
    int countByFromAndTo(Long fromId, Long toId);

    @Query("select l from Letter  l where l.from.id = :fromId and l.to.id = :toId and l.arriveAt <= CURRENT_TIMESTAMP")
    List<Letter> findByFromAndTo(Long fromId, Long toId);

    @Query(nativeQuery = true, value="SELECT u.phone_number AS phone_number, l.type, l.medium_type, " +
            "    CASE WHEN l.arrive_at <= NOW() THEN 7 " +
            "        ELSE ROUND(EXTRACT(EPOCH FROM (NOW() - l.created_at)) / EXTRACT(EPOCH FROM (l.arrive_at - l.created_at)) * 7) " +
            "    END AS progress_level, " +
            "    SUBSTRING(l.text FROM 1 FOR 25) AS preview_text, " +
            "    CASE WHEN l.from_id = :userId THEN 'OUT' " +
            "        WHEN l.to_id = :userId THEN 'IN' " +
            "        ELSE NULL " +
            "    END AS direction, l.arrive_at " +
            "FROM tbl_letter l " +
            "JOIN tbl_user u ON u.id = l.from_id " +
            "WHERE l.from_id = :userId OR l.to_id = :userId ORDER BY l.arrive_at DESC")
    List<Object[]> findAllByUserId(@Param("userId") Long userId);
}
