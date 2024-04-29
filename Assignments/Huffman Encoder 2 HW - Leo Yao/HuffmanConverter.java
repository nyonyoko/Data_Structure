import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter{

        // ASCII number of characters
        public static final int NUMBER_OF_CHARACTERS = 256;

        private String contents;
        private HuffmanTree huffmanTree;
        private int count[];
        private String code[];

        // Construct using an input string.
        // Initialize count and code.
        public HuffmanConverter(String input) {
          this.contents = input;
          this.count = new int[NUMBER_OF_CHARACTERS];
          this.code = new String[NUMBER_OF_CHARACTERS];
        }

        // Record how often each character occurs and store in count.
        public void recordFrequencies() {
            // calculate and store the frequencies of the characters in an integer array
            for (int i = 0; i < contents.length(); i++) {
                count[(int) contents.charAt(i)]++;
            }
            // print the frequencies
        }

        // Construct a Huffman tree from count and 
        // store the tree in huffmanTree.
        public void frequenciesToTree() {
            // create a binary heap using count
            BinaryHeap heap = new BinaryHeap();
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    heap.insert(new HuffmanNode(Character.toString((char) i), (double) count[i]));
                }
            }
            heap.printHeap();
            // create a huffman tree from the heap
            huffmanTree = HuffmanTree.createFromHeap(heap);
        }

        // Construct code from huffmanTree.
        public void treeToCode() {
            // store the code in a string array attribute called code such that code[(int)c]= the Huffman encoding of character c
            for (int i = 0; i < code.length; i++) {
                code[i] = "";
            }
            // call treeToCode at the root of the Huffman tree
            treeToCode(huffmanTree.root, "");
            // print the code
            huffmanTree.printLegend();
        }

        private void treeToCode(HuffmanNode t, String encoding) {
            // node is NOT a leaf node
            if (t.letter.length() > 1) {
                if (t.left != null){
                    treeToCode(t.right, encoding + "1");                    
                }
                if (t.right != null){
                    treeToCode(t.left, encoding + "0");                   
                }
            // node is a leaf node
            } else {
                code[(int) t.letter.charAt(0)] = encoding;
            }
        }

        // Encode content using code.
        public String encodeMessage() {
            // encode contents into a string of bits
            String encoded = "";
            for (int i = 0; i < contents.length(); i++) {
                encoded += code[(int) contents.charAt(i)];
            }
            return encoded;
        }
        
        // Decode a Huffman encoding.
        public String decodeMessage(String encodedStr) {
            // decode a given bit string using huffmanTree
            // take in one bit at a time to navigate through the huffmanTree
            // 0 means go left, 1 means go right
            // upon reaching a leaf, store the character at the leaf and return to the root
            String decoded = "";
            HuffmanNode current = huffmanTree.root;
            for (int i = 0; i < encodedStr.length(); i++) {
                if (encodedStr.charAt(i) == '0') {
                    current = current.left;
                } else {
                    current = current.right;
                }
                if (current.letter.length() == 1) {
                    decoded += current.letter;
                    current = huffmanTree.root;
                }
            }
            return decoded;
        }

        // Read an input file.
        public static String readContents(String filename) {
            String temp = "";
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
        }

        public static void main(String args[]) {
                String input = HuffmanConverter.readContents(args[0]);
                HuffmanConverter h = new HuffmanConverter(input);
                h.recordFrequencies();
                // Print a list of characters and frequencies here!
                h.frequenciesToTree();
                h.treeToCode();
                // Print the Huffman encoding here!
                String encoded = h.encodeMessage();
                System.out.println(encoded+"\n");
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded)); 
        }         
}
