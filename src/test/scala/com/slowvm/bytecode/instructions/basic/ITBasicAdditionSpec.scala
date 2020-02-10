package com.slowvm.bytecode.instructions.basic

import com.slowvm.VM
import org.scalatest.{FlatSpec, Matchers}

import scala.annotation.tailrec

class ITBasicAdditionSpec extends FlatSpec with Matchers {

  behavior of "Addition (IADD)"

  it should "Load 1" in {
    val program = Seq(ICONST_1)

    VM.execute(program) shouldBe VM().copy(operandStack = Seq(1))

  }
  ignore should "Load 1 and 3 and add them" in {
    val program = Seq(
      ICONST_1,
      ISTORE(1),
      ICONST_3,
      ISTORE(2),
      ILOAD(1),
      ILOAD(2),
      IADD,
      ISTORE(3),
    )

    VM.execute(program) shouldBe VM()

  }
}
