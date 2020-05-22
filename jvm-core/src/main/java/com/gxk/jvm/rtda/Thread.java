package com.gxk.jvm.rtda;

public class Thread {

  private String name;
  private Integer pc;
  private Stack<Frame> stack;

  public Thread(String name, Integer maxStackSize) {
    this.name = name;
    this.pc = 0;
    this.stack = new Stack<>(maxStackSize);
    Threads.register(this.name, this);
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

  public String getName() {
    return this.name;
  }
}
