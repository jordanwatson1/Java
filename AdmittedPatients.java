/**
 * Author: Jordan Watson
 * Date: 03/26/2018
 * Filename:AdmittedPatients.java
 */

/**
 * AdmittedPatients class adds patients to a database to keep track of
 * all the patients in the hospital. It can delete patients once they are
 * discharged and find the locations of where they are staying within the
 * hospital.
 */
 public class AdmittedPatients{
   /*
    * The root node is null if there are no patient records
    * in the tree.
    */
   protected TreeNode root;

   /*
    * Creates an empty BinarySearchTree, ready to admit patients.
    */
   public AdmittedPatients(){
     this.root = null;
   }

   /*
    * Enters the record of an admitted patient into the current
    * BinarySearchTree, maintaining the ordering of the tree.
    * Previous records in the tree are not moved from their positino.
    * The ordering is determined by the compareTo method of the
    * HospitalPatient class.
    * @param patient - the patient that has been admitted.
    * This method was aided by:
    * https://introcs.cs.princeton.edu/java/44st/IterativeBST.java.html
    */
    public void admit(HospitalPatient patient){
      TreeNode pNode = new TreeNode(patient, null, null);

      // If root is empty, admit the first patient.
      if(root == null){
        root = pNode;
        return;
      }
      TreeNode parent = null;
      TreeNode traveller = root;

      // While loop travels through each subtree and compares the new patient
      // with the parent node and then determines to go left or right or adds
      // the patient to the new node.
      while(traveller != null){
        parent = traveller;
        if(patient.compareTo(parent.item) < 0){
          traveller = traveller.left;
        }
        else if(patient.compareTo(parent.item) > 0){
          traveller = traveller.right;
        }
        else{
          traveller.item = patient;
          return;
        }

      }

      // Moving the parent node location to continue the traversal comparison.
      if(patient.compareTo(parent.item) < 0){
        parent.left = pNode;
      }
      else{
        parent.right = pNode;
      }
    }

    /*
     * Retrieves the information about a patient, given id number.
     * The patients record remains in its current location in the
     * tree.
     * @param id - The id of the patient
     * @returns the complete record of that patient or null if the
     * patient is not in hospital.
     */
    public HospitalPatient getPatientInfo(String id){
      return findNode(root, id);
    }

    /*
     * This helper method searches the BST and returns the node
     * with the corresponding id
     * @param tNode - the placeholder for the traversal through all
     *                the nodes until item found or doesnt exist
     * @returns the HospitalPatient
     */
    private HospitalPatient findNode(TreeNode tNode, String id){

      HospitalPatient foundNode;
      /****BASE_CASE_****/
      // If the root is empty, return null.
      if(tNode == null){
        foundNode = null;
      }
      else{
        HospitalPatient nodeItem = tNode.item;

        // If the id is the root of tree or subtree.
        if(id.compareTo(nodeItem.getId()) == 0){
          foundNode = tNode.item;
        }
      /****END_BASE_CASE****/
      /****RECURSIVE_CASE****/

        // If the id is to the left of root, search left subtree.
        else if(id.compareTo(nodeItem.getId()) < 0){
          foundNode = findNode(tNode.left, id);
        }
        // If the id is to the right of root, search right subtree.
        else{
          foundNode = findNode(tNode.right, id);
        }
      /****END_RECURSIVE_CASE****/
      }

      return foundNode;
    }


    /*
     * Removes a patient's record from the BinarySearchTree.
     * If the record is not there, nothing changes. If the record is
     * in a node with no children, then that node is removed from
     * the tree. If the record is in a node with one child, then
     * that child replaces the removed node in the tree (it becomes
     * the child of the removed node's parent) If the record is in
     * a node with two children, the the tree is adjusted as
     * described on page 609 of the textbook. Essentially, it is
     * replaced by it's inorder successor.
     * @param patient - The patient record to remove.
     */
    public void discharge(HospitalPatient patient){
      root = locateNode(root, patient);
    }

    private TreeNode locateNode(TreeNode tNode, HospitalPatient patient){
      TreeNode subTree;
      if(tNode == null){
        System.out.println("Item not found");
        return null;
      }
      else{
        HospitalPatient nItem = tNode.item;
        if(patient.getId().compareTo(nItem.getId()) == 0){
          tNode = delete(tNode);
        }
        else if(patient.getId().compareTo(nItem.getId()) < 0){
          subTree = locateNode(tNode.left, patient);
          tNode.left = subTree;
        }
        else{
          subTree = locateNode(tNode.right, patient);
          tNode.right = subTree;
        }
      }
      return tNode;
    }

    private TreeNode delete(TreeNode tNode){

      // If tree is empty
      if(tNode == null){
         return null;
      }

      // Case 1: if node is a leaf.
      if(tNode.left == null && tNode.right == null){
        return null;
      }
      // Case 2: if node has one child.
      else if(tNode.left == null){
        return tNode.right;
      }
      else if(tNode.right == null){
        return tNode.left;
      }
      // Case 3: if node has two children.
      else{
        HospitalPatient swap = findLeftMostSucc(tNode.right);
        tNode.item = swap;
        tNode.right = deleteLeftMostSucc(tNode.right);
        return tNode;
      }

    }

    /*
     * Traverses tree until left most inroder successor is found
     * in relation to the node that is being deleted.
     */
    private HospitalPatient findLeftMostSucc(TreeNode tNode){
      if(tNode.left == null){
        return tNode.item;
      }
      else{
        return findLeftMostSucc(tNode.left);
      }
    }

    /*
     *Takes the left most inorder successor and deletes it once it
     * has replaced the node to be deleted in method: delete().
     */
    private TreeNode deleteLeftMostSucc(TreeNode tNode){
      if(tNode.left == null){
        return tNode.right;
      }
      else{
        tNode.left = deleteLeftMostSucc(tNode.left);
        return tNode;
      }
    }

    /*
     * Prints out a list of patient locations in alphabetical order,
     * one entry per line. Formatting is defined by the getLocation
     * method of HospitalPatient.
     */
    public void printLocations(){
      findLocation(root);
    }

    /*
     * Completes an inorder traversal and prints out the location
     * of each patient.
     */
    private void findLocation(TreeNode tNode){

      // If tree is empty, print nothing.
      if(tNode == null){
        return;
      }

      // prints out in order all the locations of patients
      // goes through left side of some subtree then prints, then goes
      // through right side or tree and prints location.
      findLocation(tNode.left);
      System.out.println(tNode.item.getLocation());
      findLocation(tNode.right);
    }



    /*
     * Used for internal testing only.
     * @param args - not used.
     */
    public static void main(String[] args){
     AdmittedPatients admitted = new AdmittedPatients();
     HospitalPatient p1 = new HospitalPatient(
      new SimpleDate(2018,2,27),"Duck","Donald",'C',123);
     HospitalPatient p2 = new HospitalPatient(
      new SimpleDate(2018,1,15),"Mouse","Minnie",'B',307);
     HospitalPatient p3 = new HospitalPatient(
      new SimpleDate(2018,3,1),"Dog","Goofie",'A',422);
     HospitalPatient p4 = new HospitalPatient(
      new SimpleDate(2017,12,25),"Newman","Alfred",'X',111);
     admitted.admit(p1);
     admitted.admit(p4);
     admitted.admit(p3);
     admitted.admit(p2);
     admitted.printLocations();
     System.out.println(admitted.getPatientInfo("Duck_0"));
     System.out.println(admitted.getPatientInfo("Mouse_1"));
     System.out.println(admitted.getPatientInfo("Dog_2"));
     System.out.println(admitted.getPatientInfo("Newman_3"));
     System.out.println();
     //admitted.discharge(p1);
     //admitted.discharge(p2);
    // admitted.discharge(p3);
     admitted.discharge(p4);

    admitted.printLocations();

      // these two lines cause the tree to be viewed in a little
      // resizable window.
     ViewableTree dbt = new ViewableTree(admitted);
     dbt.showFrame();
    }

 }
