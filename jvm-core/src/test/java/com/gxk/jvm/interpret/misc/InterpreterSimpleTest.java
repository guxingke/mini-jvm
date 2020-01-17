package com.gxk.jvm.interpret.misc;

import com.gxk.jvm.instruction.BiPushInst;
import com.gxk.jvm.instruction.GotoInst;
import com.gxk.jvm.instruction.IAddInst;
import com.gxk.jvm.instruction.IConst0Inst;
import com.gxk.jvm.instruction.IConst1Inst;
import com.gxk.jvm.instruction.IIncInst;
import com.gxk.jvm.instruction.ILoad1Inst;
import com.gxk.jvm.instruction.ILoad2Inst;
import com.gxk.jvm.instruction.IReturnInst;
import com.gxk.jvm.instruction.IStore1Inst;
import com.gxk.jvm.instruction.IStore2Inst;
import com.gxk.jvm.instruction.IfICmpGtInst;
import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.interpret.Interpreter;
import com.gxk.jvm.rtda.heap.KMethod;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class InterpreterSimpleTest {

  @Test
  public void interpret() {
    Interpreter interpreter = new Interpreter();

    Map<Integer, Instruction> instructions = sum10Instructions();

    KMethod method = new KMethod(1, "sum10", "V()", 2, 3, instructions, null);

    interpreter.interpret(method);
  }

  private Map<Integer, Instruction> sum10Instructions() {
    Map<Integer, Instruction> map = new HashMap<>();
    map.put(0, new IConst0Inst());
    map.put(1, new IStore1Inst());
    map.put(2, new IConst1Inst());
    map.put(3, new IStore2Inst());
    map.put(4, new ILoad2Inst());
    map.put(5, new BiPushInst((byte) 10));
    map.put(7, new IfICmpGtInst((short) 13));
    map.put(10, new ILoad1Inst());
    map.put(11, new ILoad2Inst());
    map.put(12, new IAddInst());
    map.put(13, new IStore1Inst());
    map.put(14, new IIncInst(2, 1));
    map.put(17, new GotoInst((short) -13));
    map.put(20, new ILoad1Inst());
    map.put(21, new IReturnInst());
    return map;
  }
}
