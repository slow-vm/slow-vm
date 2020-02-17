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

/** Puts <i> in the operand stack */
abstract class ICONST_n(n: Int) extends Instruction {
  override def execute(vm: VM): VM = vm.pushOperandStack(n)
}

case object ICONST_0 extends ICONST_n(0)
case object ICONST_1 extends ICONST_n(1)
case object ICONST_2 extends ICONST_n(2)
case object ICONST_3 extends ICONST_n(3)
case object ICONST_4 extends ICONST_n(4)
case object ICONST_5 extends ICONST_n(5)

// takes the int from the top of the stack and stores it into a local variable
// The index is an unsigned byte that must be an index into the local variable
// array  of  the  current  frame  (§2.6).  The  value  on  the  top of  the
// operand  stack  must  be  of  type  int.  It  is  popped  from  the operand
// stack,  and  the  value  of  the  local  variable  at  index  is  set to value.
case class ISTORE(override val index: Int) extends Indexed(index) {
  override def execute(vm: VM): VM = vm.store(index)
}

case class ILOAD(override val index: Int) extends Indexed(index) {
  override def execute(vm: VM): VM = vm.load(index)
}

case object IADD extends Instruction {
  override def execute(vm: VM): VM = {
    val res = vm.operandStack.take(2).map(_.asInstanceOf[Int]).sum
    vm.copy(operandStack = vm.operandStack.push(res))
  }
}

case object ISUB extends Instruction {
  override def execute(vm: VM): VM = ???
}

case class BIPUSH(n: Int) extends Instruction

case class NEWARRAY(theType: String) extends Instruction
case class ASTORE(index: Int) extends Instruction
case class ALOAD(index: Int) extends Instruction
case object IASTORE extends Instruction
case object IALOAD extends Instruction
