package com.slowvm.bytecode.instructions.basic

import com.slowvm.VM

/**
  *
  */
trait Instruction {
  def execute(vm: VM): VM = ???
}

// TODO: index should be an unsigned byte
abstract class Indexed(val index: Int) extends Instruction {
  require(index > 0)
  // TODO: validate index is < MAXLOCALS
}

// Puts <i> in the operand stack
case object ICONST_1 extends Instruction {
  override def execute(vm: VM): VM =
    vm.copy(operandStack = Seq(1) ++ vm.operandStack)
}
case object ICONST_3 extends Instruction

// takes the int from the top of the stack and stores it into a local variable
// The index is an unsigned byte that must be an index into the local variable
// array  of  the  current  frame  (§2.6).  The  value  on  the  top of  the
// operand  stack  must  be  of  type  int.  It  is  popped  from  the operand
// stack,  and  the  value  of  the  local  variable  at  index  is  set to value.
case class ISTORE(override val index: Int) extends Indexed(index)
case class ILOAD(override val index: Int) extends Indexed(index)

case object IADD extends Instruction
