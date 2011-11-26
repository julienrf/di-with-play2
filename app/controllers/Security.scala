package controllers

import play.api.mvc.Action

trait Security {
  def Authenticated[A](action: Action[A]): Action[A]
}