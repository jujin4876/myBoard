package boradexample.myboard.myboard.domain.member.repository;

import boradexample.myboard.myboard.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
    @Query(value = "SELECT m FROM Member AS m WHERE m.username= :username")
    Member searchByUserName(@Param("username") String username);

}
