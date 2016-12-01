package generator;

import utils.Node;
import utils.VariableHashmap;

import java.io.PrintWriter;
import java.util.Vector;

/**
 * Created by zachncst on 11/30/16.
 */
public class IntraFunctionCodePrinter {
    /**
     * Prints the node to file
     *
     * @param pw    -> printWriter to write to file
     * @param vHMap -> Hashmap containing ids
     */
    public static void print(Node start, PrintWriter pw, VariableHashmap vHMap) {
        //leaf node
        if (start.isLeaf()) {
            //print value
            if (start.getValue().contains("\n"))
                pw.print(start.getValue());
            else if (!start.getValue().equals(""))
                pw.print(start.getValue() + " ");
        }
        //data declaration node
        else if (start.isDataDecl()) {
            //get total number of variables for function
            int num = vHMap.getNumVariables(start.getValue());
            if (num > 0) {
                if (start.getValue().equals("global")) {
                    //int global[num];
                    pw.print("int global[" + num + "];\n");
                } else {
                    //int local[num];
                    pw.print("int local[" + num + "];\n");
                    Vector<String> paramsList = vHMap.getParameterList(start.getValue());
                    for (int i = 0; i < paramsList.size(); i++) {
                        int index = vHMap.get(start.getValue(), paramsList.elementAt(i));
                        pw.print("local[" + index + "] = " + paramsList.elementAt(i) + ";\n");
                    }
                }
            }
        } else {
            //non-leaf node
            for (int i = 0; i < start.getChildren().size(); i++) {
                //print children
                print(start.getChildren().get(i), pw, vHMap);
            }
        }
    }
}
