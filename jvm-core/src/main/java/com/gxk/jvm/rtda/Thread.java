package com.gxk.jvm.rtda;


/**
 * 内部线程表示，1. 关联下层(宿主虚拟机) thread . 2, 关联上层(业务) thread.
 */
public class Thread {

  public int top;
  public Frame[] frames;

  public Thread(Integer maxStackSize) {
    this.frames = new Frame[maxStackSize];
  }


  public void pushFrame(Frame frame) {

    if (top >= this.frames.length) {
      throw new IllegalStateException("stackoverflow");
    }
    frames[top++] = frame;
  }

  public Frame popFrame() {
    if (top < 1) {
      throw new IllegalStateException("empty stack");
    }
    Frame frame = frames[--top];
    if (frame.getOnPop() != null) {
      frame.getOnPop().run();
    }
    frames[top] = null; // 从数组中移除引用。
    return frame;
  }

  public boolean empty() {
    return this.top == 0;
  }

  public int size() {
    return this.top;
  }

  // 当前栈顶栈帧
  public Frame topFrame() {
    return this.frames[top - 1];
  }

  public Frame callerFrame() {
    return this.frames[top - 2];
  }
}
