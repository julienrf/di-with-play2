package controllers

import play.api.mvc._
import play.api.mvc.Results._

trait Secured extends Security {
  
  override def Authenticated[A](action: Action[A]): Action[A] = action.compose { (request, originalAction) =>
    (for {
      username <- request.session.get("username")
    } yield {
      originalAction(request)
    }) getOrElse {
      Unauthorized(views.html.unauthorized())
    }
  }
}