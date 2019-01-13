package Repository

import com.google.inject.ImplementedBy
import Entity.User
import javax.inject.Inject
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import services.Services.UserService
@ImplementedBy(classOf[UserService])
@Repository
trait UserRepository extends JpaRepository[User, String]{
  @Query("select u FROM User u where u.email = :email")
  def findByEmail(@Param("email") email: String): User
}
