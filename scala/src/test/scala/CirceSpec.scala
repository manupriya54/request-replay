import com.stripe.interview.{Main}
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.prop.Checkers

class CirceSpec extends WordSpec with Matchers with Checkers {

  "circe" should {
    "parse to json" in {
      Main.parseJsonWithCirce("hello!").head shouldBe "hello!"
    }
  }
}
