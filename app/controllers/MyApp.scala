package controllers

import play.api._
import play.api.mvc.{Controller, Action}
import play.api.data._
import play.api.data.Forms._

trait MyApp extends Controller {
  this: Security =>
  
  val redirectToIndex = Redirect(myApp.routes.MyApp.index)
  
  def index = Authenticated {
    Action {
      Ok(views.html.index())
    }
  }
  
  def login = Action { implicit request =>
    Form(mapping("username" -> nonEmptyText)(identity)(Some(_))).bindFromRequest.fold(
        noUser => redirectToIndex,
        username => Redirect(request.headers.get("referer").getOrElse(myApp.routes.MyApp.index.url)).withSession("username" -> username)
    )
  }
  
  def logout = Action {
    redirectToIndex.withNewSession
  }
}
