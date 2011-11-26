package controllers

import play.api._
import play.api.mvc.Controller
import play.api.data._

trait MyApp extends Controller {
  this: Security =>
  
  val redirectToIndex = Redirect(myApp.routes.MyApp.index)
  
  def index = Authenticated {
    Action {
      Ok(views.html.index())
    }
  }
  
  def login = Action { implicit request =>
    Form("username" -> requiredText).bindFromRequest.fold(
        f => redirectToIndex,
        username => Redirect(request.headers.get("referer").getOrElse(myApp.routes.MyApp.index.url)).withSession("username" -> username)
    )
  }
  
  def logout = Action {
    redirectToIndex.withNewSession
  }
}
