package services.Exception

import play.api.Logger

import scala.collection.mutable.Map

object ErrorCode {
  private val mapCodes: Map[String, String] = Map()
  final val BadRequest_Credentials = "badrequest_credentials"
  final val FormErrors = "form_errors"
  final val BadRequest_Email_Duplicated = "badrequest_email_duplicated"
  def getErrorMessage(code: String): String = {
    if (mapCodes.isEmpty) {
      mapCodes += (BadRequest_Email_Duplicated -> "This email is already used")
      mapCodes += (BadRequest_Credentials -> "wrong credentials!")
      mapCodes += (FormErrors -> "Errors while processing form data!")
    }
    mapCodes.get(code) match {
      case Some(value) => value
      case None => {
        Logger.error(s"The error code (( $code )) is missing!")
        ""
      }
    }
  }
}