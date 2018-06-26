package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class PizzaControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "PizzaController GET" should {

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK

    }
    "respond to the /welcome url" in {
      // Need to specify Host header to get through AllowedHostsFilter
      val request = FakeRequest(GET, "/welcome").withHeaders("Host" -> "localhost")
      val home = route(app, request).get
      status(home) mustBe OK
    }
    "return some html" in {
      val controller = inject[PizzaController]
      val result = controller.welcome().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
    }
    "say hello and have a title" in {
      val controller =  inject[PizzaController]
      val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
      contentAsString(result) must include ("<h1>Hello!</h1>")
      contentAsString(result) must include ("<title>Welcome!</title>")
    }

  }
}