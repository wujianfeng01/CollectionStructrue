package DataStructure.LinkedList;

public class LinkedNodeStructure {
    int val;
    LinkedNodeStructure next;
    LinkedNodeStructure(int val){
        this.val = val;
    }
    LinkedNodeStructure(int val, LinkedNodeStructure post){
        next = post;
    }
}
