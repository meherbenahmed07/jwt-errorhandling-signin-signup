package services.Exception

object ErrorStatusCode {
sealed trait exception
  case object BadRequest extends exception
}
