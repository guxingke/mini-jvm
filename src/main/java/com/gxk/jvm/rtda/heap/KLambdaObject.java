package com.gxk.jvm.rtda.heap;

import com.gxk.jvm.rtda.LocalVars;

public class KLambdaObject extends KObject{
  public final LocalVars localVars;

  public KLambdaObject(KClass clazz, LocalVars localVars) {
    super(clazz);
    this.localVars = localVars;
  }
}
