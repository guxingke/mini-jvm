package com.gxk.jvm.rtda;

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
    this.stack.push(frame);
  }

  public Frame popFrame() {
    Frame pop = this.stack.pop();
    if (pop.getOnPop() != null) {
      pop.getOnPop().run();
    }
    return pop;
  }

  public Frame currentFrame() {
    return this.stack.peek();
  }

  public boolean empty() {
    return this.stack.empty();
  }
}
