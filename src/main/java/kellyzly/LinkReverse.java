package kellyzly;

public class LinkReverse {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public void print() {

            ListNode p = this;

            while (p != null) {
                System.out.println(p.val + "->");
                p = p.next;
            }
        }
    }


    public static void main(String[] args) {
        LinkReverse app = new LinkReverse();
        ListNode head = app.testcase1();
        ListNode newHead = app.reverseList(head);

        newHead.print();
    }

    public ListNode testcase1() {

        ListNode p3 = new ListNode(3);
        ListNode p2 = new ListNode(2);
        ListNode p1 = new ListNode(1);

        p1.next = p2;
        p2.next = p3;
        p3.next = null;
        return p1;
    }

    // 翻转就是开3个变量 pre, cur, next
    // 注意 1.原来的head.next = null
    //      2.返回新的head
    //      3. 如果只有一个元素情况，不需要翻转
    // https://leetcode-cn.com/problems/reverse-linked-list/submissions/
    public ListNode reverseList(ListNode head) {

        ListNode pre = head;
        ListNode cur = pre.next;
        ListNode next = null;

        while (cur != null) {
            // System.out.println("cur.value:"+cur.val);
            next = cur.next; // 1->2->3   pre=1, cur=2, next=3   2.next=1
            cur.next = pre;
            pre = cur;
            cur = next;

        }

        head.next = null;
        head = pre;
        return head;

    }
}
