package sum;

//链表类 循环双向链表
//      为方便操作 链表的表头和表尾不储存有效数据
public class List {
    //构造函数  初始化一个链表
    public List(String num) throws ParseStringException{
        this.init();
        String[] nums = num.split(",");
        if(!this.isEffective(nums))
            throw new ParseStringException("未输入合法数字!");
        if(nums.length == 0)
            return;
        for(int i = nums.length - 1; i >= 0; i--){
            ListNode newNode = new ListNode(nums[i]);
            this.size++;
            newNode.setNext(this.head.getNext());
            this.head.setNext(newNode);
            newNode.getNext().setLast(newNode);
            newNode.setLast(this.head);
        }
        this.minus = this.head.getNext().getVal_s().charAt(0) == '-';
        this.head.getNext().fixedVal_sIfHead();
        for(ListNode tmp = this.head.getNext(); tmp != this.tail; tmp = tmp.getNext()) {
            this.length += tmp.getNumLength();
        }
    }

    //两数相加
    public List add(List anthor) throws ParseStringException{
        String str = "";
        if(this.minus && anthor.minus){
            str = "-" + this.twoStringNumberAdd(this.abs().ToString(), anthor.abs().ToString());
        }else if(this.minus || anthor.minus){
            if(this.minus){
                str = this.firstSubSecond(anthor.abs().ToString(), this.abs().ToString());
            }else {
                str = this.firstSubSecond(this.abs().ToString(), anthor.abs().ToString());
            }
        }else{
            str = this.twoStringNumberAdd(this.abs().ToString(), anthor.abs().ToString());
        }
        StringBuffer sb = new StringBuffer(str);
        while(sb.length() > 1 && sb.charAt(0) == '0'){
            sb.delete(0, 1);
        }
        str = sb.toString();
        sb = new StringBuffer("");
        int cnt = 0;
        for(int i = str.length() - 1; i >= 0; i--){
            if(cnt == 4){
                sb.insert(0,',');
                cnt = 0;
            }
            sb.insert(0, str.charAt(i));
            cnt++;
        }
        return new List(sb.toString());
    }

    //两数相减
    public List sub(List anthor) throws ParseStringException{
        StringBuffer sb = new StringBuffer(anthor.toString());
        if(sb.charAt(0) == '-') {
            sb.delete(0, 1);
        }else{
            sb.insert(0, '-');
        }
        return this.add(new List(sb.toString()));
    }

    //获得绝对值
    public List abs() throws ParseStringException{
        if(this.minus){
            StringBuffer sb = new StringBuffer(this.toString());
            sb.delete(0, 1);
            return new List(sb.toString());
        }else{
            return new List(this.toString());
        }
    }

    //两个正整数相加
    private String twoStringNumberAdd(String s1, String s2){
        StringBuffer sb = new StringBuffer("");
        int cntS1 = s1.length() - 1;
        int cntS2 = s2.length() - 1;
        boolean flag = false;
        while(cntS1 >= 0 && cntS2 >= 0){
            String tmp = this.twoCharAdd(s1.charAt(cntS1), s2.charAt(cntS2), flag);
            flag = false;
            cntS1--;
            cntS2--;
            if(tmp.length() == 1){
                sb.insert(0, tmp.charAt(0));
            }else{
                sb.insert(0, tmp.charAt(1));
                flag = true;
            }
        }
        while(cntS1 >= 0){
            String tmp = this.twoCharAdd(s1.charAt(cntS1), '0', flag);
            flag = false;
            cntS1--;
            if(tmp.length() == 1){
                sb.insert(0, tmp.charAt(0));
            }else{
                sb.insert(0, tmp.charAt(1));
                flag = true;
            }
        }
        while(cntS2 >= 0){
            String tmp = this.twoCharAdd('0', s2.charAt(cntS2), flag);
            flag = false;
            cntS2--;
            if(tmp.length() == 1){
                sb.insert(0, tmp.charAt(0));
            }else{
                sb.insert(0, tmp.charAt(1));
                flag = true;
            }
        }
        if(flag){
            sb.insert(0, '1');
            flag = false;
        }
        return sb.toString();
    }

    //第一个正整数减第二个正整数
    private String firstSubSecond(String s1, String s2){
        if(s1.compareTo(s2) == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer("");
        boolean isFirstBig = true;
        if(s1.length() < s2.length()){
            isFirstBig = false;
        }else if(s1.length() == s2.length()){
            isFirstBig = s1.compareTo(s2) > 0;
        }else{
            isFirstBig = true;
        }
        if(isFirstBig){
            int len1 = s1.length() - 1;
            int len2 = s2.length() - 1;
            boolean flag = false;
            while(len1 >= 0 && len2 >= 0){
                String tmp = this.firstSubSecond(s1.charAt(len1), s2.charAt(len2), flag);
                flag = false;
                len1--;
                len2--;
                if(tmp.length() == 1){
                    sb.insert(0, tmp.charAt(0));
                }else{
                    sb.insert(0, tmp.charAt(1));
                    flag = true;
                }
            }
            while(len1 >= 0){
                String tmp = this.firstSubSecond(s1.charAt(len1), '0', flag);
                flag = false;
                len1--;
                if(tmp.length() == 1){
                    sb.insert(0, tmp.charAt(0));
                }else{
                    sb.insert(0, tmp.charAt(1));
                    flag = true;
                }
            }
        }else{
            String t = s1;
            s1 = s2;
            s2 = t;
            int len1 = s1.length() - 1;
            int len2 = s2.length() - 1;
            boolean flag = false;
            while(len1 >= 0 && len2 >= 0){
                String tmp = this.firstSubSecond(s1.charAt(len1), s2.charAt(len2), flag);
                flag = false;
                len1--;
                len2--;
                if(tmp.length() == 1){
                    sb.insert(0, tmp.charAt(0));
                }else{
                    sb.insert(0, tmp.charAt(1));
                    flag = true;
                }
            }
            while(len1 >= 0){
                String tmp = this.firstSubSecond(s1.charAt(len1), '0', flag);
                flag = false;
                len1--;
                if(tmp.length() == 1){
                    sb.insert(0, tmp.charAt(0));
                }else{
                    sb.insert(0, tmp.charAt(1));
                    flag = true;
                }
            }
            sb.insert(0, '-');
        }
        return sb.toString();
    }

    //两个数字字符相加
    private String twoCharAdd(char ch1, char ch2, boolean flag){
        int num1 = (int)ch1 - (int)'0';
        int num2 = (int)ch2 - (int)'0';
        if(flag){
            return String.valueOf(num1 + num2 + 1);
        }else {
            return String.valueOf(num1 + num2);
        }
    }

    //第一个字符减第二个字符
    private String firstSubSecond(char ch1, char ch2, boolean flag){
        int num1 = (int)ch1 - (int)'0' - (flag ? 1 : 0);
        int num2 = (int)ch2 - (int)'0';
        int ret = num1 - num2;
        if(ret >= 0){
            return String.valueOf(ret);
        }else{
            if(ret == -10){
                return "-0";
            }else{
                return String.valueOf(-1 * (ret + 10));
            }
        }
    }

    //初始化每个成员
    private void init(){
        this.head = new ListNode(0);
        this.tail = new ListNode(0);
        this.head.setNext(this.tail);
        this.head.setLast(this.tail);
        this.tail.setLast(this.head);
        this.tail.setNext(this.head);
        this.minus = false;
        this.size = 0;
        this.length = 0;
    }

    private boolean isEffective(String[] nums){
        if(nums.length == 0)
            return true;
        if(nums[0].charAt(0) == '-'){
            if(nums[0].length() > 5)
                return false;
        } else {
            if(nums[0].length() > 4)
                return false;
        }
        for(int i = 1; i < nums.length; i++){
            if(nums[i].length() != 4)
                return false;
        }
        return true;
    }

    private String ToString(){
        String ret = "";
        for(ListNode tmp = this.head.getNext(); tmp != this.tail; tmp = tmp.getNext()) {
            ret = ret + tmp.getVal_s();
        }
        return ret;
    }

    //重载toString方便打印
    @Override
    public String toString(){
        String ret = "";
        for(ListNode tmp = this.head.getNext(); tmp != this.tail.getLast(); tmp = tmp.getNext()) {
            ret = ret + tmp.getVal_s() + ",";
        }
        ret = ret + this.tail.getLast().getVal_s();
        return ret;
    }

    private ListNode head;  //链表表头
    private ListNode tail;  //链表表尾

    //get方法  获取链表的属性 但属性不应该由类外修改 故不提供set方法
    public boolean isMinus() {
        return minus;
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return length;
    }

    private boolean minus;  //是否为负数
    private int size;       //节点个数
    private int length;     //数字的长度
}
