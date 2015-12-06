package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import models._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello play!"))
  }

  val userForm = Form(
    mapping("name" -> text, "age" -> number)(User.apply)(User.unapply))

  def entryInit = Action {
    val filledForm = userForm.fill(User("user name", 0))
    Ok(views.html.user.entry(filledForm))
  }

  def entrySubmit = Action { implicit request =>
    userForm.bindFromRequest.fold(
      errors => {
        println("Error!")
        BadRequest(views.html.user.entry(errors))
      },
      success => {
        println("Success!")
        Ok(views.html.user.entrySubmit(success))
      }
    )
  }
}
