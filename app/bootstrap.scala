package object myApp {
  
  import play.api.Play._
  
  val MyApp = if (isDev) {
    new controllers.MyApp with controllers.MockSecured
  } else {
    new controllers.MyApp with controllers.Secured
  }
}