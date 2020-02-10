package com.slowvm

import com.slowvm.bytecode.instructions.basic.Instruction

import scala.annotation.tailrec

/**
  *
  */
object VM {
  val initial = new VM
  def execute(program: Seq[Instruction]): VM = {

    @tailrec
    def exec0(vm: VM, program: List[Instruction]): VM = {
      program match {
        case Nil          => vm
        case head :: tail => exec0(head.execute(vm), tail)
      }
    }

    exec0(VM.initial, program.toList)
  }

}

case class VM(stack: AnyRef = null,
              localVariableArray: AnyRef = null,
              operandStack: Seq[Any] = Seq.empty[Any])
