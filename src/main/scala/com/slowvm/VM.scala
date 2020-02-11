package com.slowvm

import com.slowvm.bytecode.instructions.basic.Instruction

import scala.annotation.tailrec

/**
 *
 */
object VM {
  val initial = new VM

  def execute(program: Seq[Instruction]): VM = VM.initial.execute(program)
}

case class VM(stack: AnyRef = null,
              localVariableArray: AnyRef = null,
              operandStack: Seq[Any] = Seq.empty[Any]) {
  def pushOperandStack(value: Int): VM =
    copy(operandStack = Seq(value) ++ operandStack)

  def execute(program: Seq[Instruction]): VM = {
    @tailrec
    def exec0(vm: VM, program: List[Instruction]): VM = {
      program match {
        case Nil => vm
        case head :: tail => exec0(head.execute(vm), tail)
      }
    }

    exec0(this, program.toList)
  }


}
