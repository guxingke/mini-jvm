public class ArrayTest2 {
  public static void main(String[] args) {
    AtObj[] arr = new AtObj[1];
    arr[0] = new AtObj();
    arr[0].i = 2;
    System.out.println(arr[0].i);

    System.out.println(arr.length);
  }
}

class AtObj {
  int i;
}