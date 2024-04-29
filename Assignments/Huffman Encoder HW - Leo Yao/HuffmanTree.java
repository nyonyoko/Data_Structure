public class HuffmanTree {
    HuffmanNode root;
    
    public HuffmanTree(HuffmanNode huff) {
        this.root = huff;
    }

    public void printLegend() {
        printLegend(root, "");
    }

    private void printLegend(HuffmanNode node, String code) {
        // node contains multiple characters, NOT a leaf node
        if (node.letter.length() > 1) {
            if (node.left != null){
                printLegend(node.left, code + "0");
            }
            if (node.right != null){
                printLegend(node.right, code + "1");
            }
        // node is a leaf node
        } else {
            System.out.println(node.letter + "=" + code);
        }
    }

    public static BinaryHeap legendToHeap(String legend) {
        BinaryHeap heap = new BinaryHeap();
        String[] pairs = legend.split("\\s+"); 
    
        for (int i = 0; i < pairs.length; i += 2) {
            String letter = pairs[i];
            Double frequency = Double.parseDouble(pairs[i + 1]);
            heap.insert(new HuffmanNode(letter, frequency));
        }
    
        return heap;
    }
    


    public static HuffmanTree createFromHeap (BinaryHeap b) {
        while (b.getSize() > 1) {
            HuffmanNode left = (HuffmanNode) b.deleteMin();
            HuffmanNode right = (HuffmanNode) b.deleteMin();
            HuffmanNode parent = new HuffmanNode(left, right);
            b.insert(parent);
        }
        return new HuffmanTree((HuffmanNode) b.deleteMin());
    }

    public static void main(String[] args) {
        String legend = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
        BinaryHeap heap = legendToHeap(legend);
        heap.printHeap();
        HuffmanTree huffmantree = createFromHeap(heap);
        huffmantree.printLegend();
    }
}