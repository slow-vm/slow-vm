package com.slowvm.bytecode.instructions.basic

import com.slowvm.VM
import org.scalatest.FlatSpec
import org.scalatest.Matchers


class ITBasicAdditionSpec extends FlatSpec with Matchers {

  behavior of "Addition (IADD)"

  it should "Load CONST_1 as operand " in {
    val program = Seq(ICONST_1)
    VM.execute(program) shouldBe VM().copy(operandStack = Seq(1))
  }

  it should "takes the top item from the operand stack and pushes it into position 1 of variable array " in {
    // given a VM with ICONST_3 on the operand stack
    val vm0 = VM.execute(Seq(ICONST_3))
    val program = Seq(ISTORE(1))
    vm0.execute(program) shouldBe VM().copy(localVariableArray = Seq(3))
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
