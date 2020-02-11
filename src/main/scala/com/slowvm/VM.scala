package com.slowvm

import com.slowvm.bytecode.instructions.basic.Instruction

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 *
 */
object VM {
  def empty = new VM()

  def execute(program: Seq[Instruction]): VM = VM.empty.execute(program)
}

case class VM(stack: AnyRef = null,
              localVariableArray: ArrayBuffer[Int] = ArrayBuffer(),
              operandStack: mutable.Stack[Any] = mutable.Stack.empty[Any]) {
  // TODO: implement so it doesn't mutate
  def store(index: Int): VM = {
    val (top, bottom) = (operandStack.top, operandStack.drop(1))
    println(operandStack)
    val newLocalVars = localVariableArray
    newLocalVars.size match {
      case s if s > index - 1 =>
        newLocalVars.update(index - 1, top.asInstanceOf[Int])
      case s if s == index - 1 =>
        newLocalVars += top.asInstanceOf[Int]
    }
    copy(operandStack = bottom, localVariableArray = newLocalVars)
  }

  def pushOperandStack(value: Int): VM = {
    copy(operandStack = operandStack.push(value))
  }

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
