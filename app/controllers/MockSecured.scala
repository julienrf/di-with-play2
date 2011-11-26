package controllers

import play.api.mvc.Action

trait MockSecured extends Security {
  override def Authenticated[A](action: Action[A]) = action
}