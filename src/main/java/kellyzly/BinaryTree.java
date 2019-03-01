package kellyzly;


import java.util.*;

public class BinaryTree {


   //任何递归实现的程序都可以改用栈实现。比如，先序遍历时用栈维护待访问结点；先将根结点入栈，再将右孩子结点入栈、左孩子结点入栈。Java实现如下：

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
   
    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();

        TreeNode node0  = binaryTree.construct();

//        int depth = binaryTree.max_depth(node0);
//
//        System.out.println("depth:"+depth);

        binaryTree.dfs_inOrder(node0);

    }

    
    public TreeNode construct(){
        TreeNode node3 =new
                TreeNode(3, null, null);
        TreeNode node4 =new
                TreeNode(4, null, null);
        TreeNode node1 =new
                TreeNode(1, node3, node4);

        TreeNode node5 =new
                TreeNode(5, null, null);
        TreeNode node6 = new BinaryTree().new
                TreeNode(6, null, null);
        TreeNode node2 =new
                TreeNode(2, node5, node6);
        TreeNode node0 =new
                TreeNode(0, node1, node2);


        return node0;
    }

    public static void main1(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        
        TreeNode node0  = binaryTree.construct();

        // dfs_inOrder() dfs_postOrder() dfs_preOrder 深度遍历
        binaryTree.dfs_inOrder(node0);
//        binaryTree.dfs_postorder(node0);
//        binaryTree.dfs_preOrder(node0);


        // 广度遍历
      //  binaryTree.levelOrder(node0);
        

    }

     public int max_depth(TreeNode node0){
         if( node0.left == null && node0.right == null){
             return 1;
         }else{
             // left node depth
             int left = 0;
             if( node0.left!= null) {
                 left = max_depth(node0.left);
             }
              // right node depth
             int right = 0;

             if( node0.right!= null) {
                 right = max_depth(node0.right);

             }
             
             return Math.max(left,right)+1;
              
              
         }
     }

    // new tempLevelList store next level element
    // new queue store current level element
    // foreach element in current level in queue to visit next level
    // if there is no more element in current level (queue.isEmpty). directly jump to next level to foreach
    void levelOrder(TreeNode node0) {
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(node0);
        List tempLevelList = new ArrayList();
        int level = 0;
        while (!queue.isEmpty()) {
            TreeNode curLevel = queue.poll();
            if (curLevel.left != null) {
                tempLevelList.add(curLevel.left);
            }
            if (curLevel.right != null) {
                tempLevelList.add(curLevel.right);
            }

            System.out.println(String.format("%s :%d", level, curLevel.val));
            if (queue.isEmpty()) {
                level++;
                queue.addAll(tempLevelList);
                tempLevelList.clear();
            }
        }

    }


    //         0
    //     1      2
    //  3     4  5  6
    //
    //  0 1 3 4 2 5 6  先序tranverse
    // 先序： 1. push root
   //        2 .if contain left and right, directly push right and left and pop top


    void dfs_preOrder(TreeNode node0){
       

        Stack<TreeNode> stack = new Stack();
        stack.push(node0);

        while(!stack.empty()){
            TreeNode topNode = stack.pop();
            System.out.println(topNode.val+" ");
            if(topNode.left!= null && topNode.right!=null) {
                stack.push(topNode.right);
                stack.push(topNode.left);
            }

        }



    }



    //         0
    //     1      2
    //  3     4  5  6
    //
    //   3  1 4 0  5 2 6  中序tranverse
    // 中序： 1. push root
//               2. stack get top ,
//               3 .if contain left , continue push left,（mark left is null (visited))
//               3. if contain right, pop 出来 top, mark right is null (visited), push right
    //  2019.2.28 再温习， 中序遍历的思路： 用stack 实现， 如果有左子树，继续压入左子树
    // 如果没有左子树，弹出top， 继续压入非空右子树
    void dfs_inOrder(TreeNode node0){
          Stack<TreeNode> stack = new Stack();
          stack.push(node0);
          while(!stack.empty()){
                TreeNode topNode = stack.peek();

                if(topNode.left!= null){
                    stack.push(topNode.left);
                    topNode.left = null;  // mark left is visited
                }else{
                    // result.add(topNode.val);
                    topNode = stack.pop();
                    System.out.println(topNode.val+" ");
                     if( topNode.right != null) {
                         stack.push(topNode.right);
                         topNode.right = null;
                     }
                }

          }



    }

    //         0
    //     1      2
    //  3     4  5  6
    //
    //   3  1 4  5  0  5  2  6   post tranverse
    //  post序： 1. push root
//               2. stack get top , if left== null && right==null, return this ele and pop
//               3 .if contain left , continue push left,（mark left is null (visited))
//               3. if contain right, continue push right( mark right is null visited))
//   感觉要 topNode left right 都标注成 null ，才能pop 出来 parentNode

    void dfs_postorder(TreeNode node0){
      
        Stack<TreeNode> stack = new Stack();
        stack.push(node0);
        List<Integer> result = new ArrayList();
        while(!stack.empty()){
            TreeNode topNode = stack.peek();
            if( topNode.left == null && topNode.right != null){
                result.add(topNode.val);
                stack.pop();
            }
            if(topNode.left!= null){
                stack.push(topNode.left);
                topNode.left = null;
            }else if( topNode.right!= null){
                stack.push(topNode.right);
                topNode.right = null;
            }

        }


        for(Integer i: result){
            System.out.println(i);
        }

    }


}
