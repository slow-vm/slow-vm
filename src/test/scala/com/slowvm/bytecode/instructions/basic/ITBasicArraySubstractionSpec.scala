package com.slowvm.bytecode.instructions.basic

import com.slowvm.VM
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
class ITBasicArraySubstractionSpec extends FlatSpec with Matchers {

  behavior of "Addition (IADD)"

  it should "Subtract two integers from an int[]" in {
    val program = Seq(
      BIPUSH(7),
      NEWARRAY("T_INT"),
      ASTORE(1),
      ALOAD(1),
      ICONST_0,
      ICONST_4,
      IASTORE,
      ALOAD(1),
      ICONST_1,
      ICONST_5,
      IASTORE,
      ALOAD(1),
      ICONST_2,
      BIPUSH(6),
      IASTORE,
      ALOAD(1),
      ICONST_3,
      BIPUSH(7),
      IASTORE,
      ALOAD(1),
      ICONST_3,
      IALOAD,
      ALOAD(1),
      ICONST_1,
      IALOAD,
      ISUB,
      ISTORE(2)
    )

    VM.execute(program).localVariableArray.last shouldBe 8

  }
}
