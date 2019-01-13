package services.Services

import Entity.User
import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import javax.inject.Inject
import org.joda.time.DateTime

class TokenService {
  @Inject
  var configuration: play.api.Configuration = _
  private def generateToken(private_claims: Map[String, String], secret_key: String) = {
    val header = JwtHeader("HS256")
    var public_claims: Map[String, String] = Map()
    public_claims += ("iat" -> DateTime.now().getMillis.toString)
    val claims = public_claims ++ private_claims
    val claimsSet = JwtClaimsSet(claims)
    JsonWebToken(header, claimsSet, secret_key)
  }

  def generateAuthToken(private_claims: Map[String, String] = Map())(implicit me: User): String = {
    var claims: Map[String, String] = Map()
    claims += ("userID" -> me.id)
    val secret_key = Option(me.passwordHashCode).getOrElse(configuration.getString("application.secret").getOrElse("Store_default_secret_key"))
    generateToken(claims, secret_key)
  }

}
