package com.slowvm.bytecode.instructions.basic

import com.slowvm.VM
import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class ITBasicAdditionSpec extends FlatSpec with Matchers {

  behavior of "Addition (IADD)"

  it should "Load CONST_1 as operand" in {
    val program = Seq(ICONST_1)
    VM.execute(program) shouldBe VM().copy(operandStack = mutable.Stack(1))
  }

  it should "takes the top item from the operand stack and pushes it into position 1 of the local variable array" in {
    // given a VM with ICONST_3 on the operand stack
    val vm0 = VM.execute(Seq(ICONST_3))
    val program = Seq(ISTORE(1))
    vm0.execute(program) shouldBe VM().copy(localVariableArray = ArrayBuffer(3))
  }

  it should "store multiple values in different positions of the local variable array" in {
    // given a VM with ICONST_3 and  ICONST_5 on the operand stack
    val vm0 = VM.execute(Seq(ICONST_5, ICONST_3))
    val program = Seq(ISTORE(1), ISTORE(2))
    vm0.execute(program) shouldBe VM().copy(
      localVariableArray = ArrayBuffer(3, 5)
    )
  }
  it should "load store multiple values into the operand stack" in {
    val vm0 = VM.execute(Seq(ICONST_5, ICONST_3, ISTORE(1), ISTORE(2)))
    val program = Seq(ILOAD(1), ILOAD(2))
    vm0.execute(program) shouldBe VM().copy(
      operandStack = mutable.Stack(5, 3),
      localVariableArray = ArrayBuffer(3, 5)
    )
  }

  it should "Load 1 and 3 and add them" in {
    val program = Seq(
      ICONST_5,
      ISTORE(1),
      ICONST_3,
      ISTORE(2),
      ILOAD(1),
      ILOAD(2),
      IADD,
      ISTORE(3),
    )

    VM.execute(program).localVariableArray.last shouldBe 8

  }
}
