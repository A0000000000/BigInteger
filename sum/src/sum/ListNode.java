package sum;

//链表节点
//      每个节点储存四个有效数字
//      数字的储存包括整形和字符串形式
public class ListNode {

    //构造方法: 用一个整形的数字初始化节点
    public ListNode(int val){
        this.val = val;
        this.val_s = String.valueOf(val);
        this.next = null;
        this.last = null;
        this.dealVal_s();
    }

    //构造方法: 用一个字符串初始化节点
    public ListNode(String val_s){
        this.val = Integer.parseInt(val_s);
        this.val_s = val_s;
        this.next = null;
        this.last = null;
        this.dealVal_s();
    }

    //获取该位置有几个数字
    int getNumLength(){
        if(this.val < 0){
            return this.val_s.length() - 1;
        } else {
            return this.val_s.length();
        }
    }

    //防止使用数字初始化时位数不足4位
    private void dealVal_s(){
        if(this.val_s.charAt(0) == '-'){
            int len = this.val_s.length();
            if(len < 5) {
                StringBuffer sb = new StringBuffer(this.val_s);
                for (int i = len; i < 5; i++) {
                    sb.insert(1,'0');
                }
                this.val_s = sb.toString();
            }
        }else{
            int len = this.val_s.length();
            if(len < 4){
                StringBuffer sb = new StringBuffer(this.val_s);
                for (int i = len; i < 4; i++) {
                    sb.insert(0,'0');
                }
                this.val_s = sb.toString();
            }
        }
    }

    //如果该节点是链表的表头 则不需要进行位数修正
    public void fixedVal_sIfHead(){
        this.val_s = String.valueOf(this.val);
    }

    //get方法
    public int getVal() {
        return val;
    }

    public String getVal_s() {
        return val_s;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode getLast() {
        return last;
    }

    //set方法

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void setLast(ListNode last) {
        this.last = last;
    }

    private int val;        //储存的数值
    private String val_s;   //储存数值的等效字符串
    private ListNode next;  //链表的下一个节点
    private ListNode last;  //链表的上一个节点
}
