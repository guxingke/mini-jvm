class InstanceArray {
  public static void main(String[] args) {
    InstanceArrayObj[] arr = new InstanceArrayObj[1];
    System.out.println(arr.length);

    arr[0] = new InstanceArrayObj();
    arr[0].val = 2;
    System.out.println(arr[0].val);
  }
}

class InstanceArrayObj {
  int val;
}
