package controllers

import play.api.mvc._
import play.api.mvc.Results._

trait Secured extends Security {
  
  override def Authenticated[A](action: Action[A]): Action[A] = Action(action.parser) { request =>
    request.session.get("username") match {
      case Some(user) => action(request)
      case None => Unauthorized(views.html.unauthorized())
    }
  }
}