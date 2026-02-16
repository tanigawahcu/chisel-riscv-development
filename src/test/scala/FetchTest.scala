package fetch

import chisel3._

import org.scalatest.freespec.AnyFreeSpec
import chiseltest._

class HexTest extends AnyFreeSpec with ChiselScalatestTester {
    "mycpu should work through hex" in {
        test(new Top) { c =>
            c.clock.setTimeout(0)
            var i = 0;
            while( !c.io.exit.peek().litToBoolean && i < 10) {
                c.clock.step(1)
                i += 1;
            }

        }
    }

}