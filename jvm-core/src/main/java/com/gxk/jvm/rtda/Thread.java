package com.gxk.jvm.rtda;

public class Thread {
  private Stack<Frame> stack;

  public Thread(Integer maxStackSize) {
    this.stack = new Stack<>(maxStackSize);
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
