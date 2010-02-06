package spde.core;

trait Colors {
  type Color = Int

  implicit def color2RichColor(color: Color) = new RichColor(color)
  
  class RichColor(color: Color) {
    def r = color >> 16 & 0xFF
    def g = color >> 8 & 0xFF
    def b = color & 0xFF
  }
}

trait Randoms {
  import scala.util.Random
  private val rand = new Random
  implicit def seq2RichRandom[K](seq: Seq[K]) = new RichRandom(seq)
  
  protected class RichRandom[K](seq: Seq[K]) {
    def random: K = seq((rand.nextFloat * seq.length).toInt)
  }
  
  
  class Bet[K](val weight: Int, val out: () => K)
  class BetSum[K](val bet: Bet[K], val sum: Int)
  
  def play[K](odds: Bet[K]*) = {
    def adder(acc:Int, in: Seq[Bet[K]], out: List[BetSum[K]]): List[BetSum[K]] = in match  {
      case Seq() => out
      case Seq(cur, rest @ _*) => adder(acc + cur.weight, rest, new BetSum(cur, acc) :: out)
    }
    val sl = adder(0, odds, Nil)
    val score = rand.nextDouble * (sl.head.sum + sl.head.bet.weight)
    sl.find(_.sum < score).get.bet.out()
  }
  
  implicit def int2RichBetter(weight: Int) = new RichBetter(weight)
  
  protected class RichBetter(weight: Int) {
    def %>[K](out: => K) = new Bet(weight, () => out)
  }
}

trait Maths {
  implicit def double2float(d: Double) = d.toFloat
  
  implicit def int2RicherInt(num: Int) = new RicherInt(num)
  
  protected class RicherInt(num: Int) {
    private def pow(exp: Int, acc: Int): Int = 
      if (exp == 1) acc
      else pow(exp - 1, num * acc)
    def *^ (exp: Int) = 
      if (exp > 0) pow(exp, num)
      else if (exp == 0) 1
      else error("^^ for Int returns Int; negative exponents are not allowed")
  }
}

trait Timeout {
  /** A timeout function like JavaScript's but curried. */
  def setTimeout(ms: Int)(thunk: => Unit) {
    actors.Actor.actor {
      Thread.sleep(ms)
      thunk
    }
  }
}

trait Futures extends dispatch.futures.AvailableFutures {
  /** Override to use any Futures implementation */
  def spde_future: dispatch.futures.Futures = dispatch.futures.DefaultFuture
  def future[T](result: => T) = spde_future.future(result)
}