package com.gxk.jvm.rtda;

import com.gxk.jvm.util.Logger;

public class Thread {

  private Integer pc;
  private Stack<Frame> stack;

  public Thread(Integer maxStackSize) {
    this.pc = 0;
    this.stack = new Stack<>(maxStackSize);
  }

  public void setPc(Integer pc) {
    this.pc = pc;
  }

  public Integer getPc() {
    return pc;
  }

  public void pushFrame(Frame frame) {
//    Logger.trace("push frame " + frame.method.clazz.name + "#" + frame.method.name);
    this.stack.push(frame);
  }

  public Frame popFrame() {
    Frame pop = this.stack.pop();
//    Logger.trace("pop frame " + pop.method.clazz.name + "#" + pop.method.name);
    if (pop.getOnPop() != null) {
      pop.getOnPop().run();
    }
    if (!this.empty()) {
      this.pc = this.stack.peek().getPc();
    }
    return pop;
  }

  public Frame currentFrame() {
    return this.stack.peek();
  }

  public Frame callerFrame() {
    return this.stack.get(this.stack.size() - 2);
  }

  public boolean empty() {
    return this.stack.empty();
  }

  public int size() {
    return this.stack.size();
  }
}
