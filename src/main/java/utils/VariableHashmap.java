package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Kannan
 *
 */
public class VariableHashmap {

	// Variable Hashmap
	// Hashmap mapping variable name
	// and function name to local
	// array(global if function
	// name=global) index
	Map<HKey, Integer> variableMap;

	// Function Hashmap
	// Hashmap mapping function name
	// to number of variables
	Map<String, Integer> functionMap;

	Map<String, Node> functionNodeMap;

	// Parameter hashmap
	// Hashmap mapping
	// function name to list
	// of parameter names
	Map<String, Vector<String>> paramMap;

	public VariableHashmap() {
		variableMap = new HashMap<>();
		functionMap = new HashMap<>();
		paramMap = new HashMap<>();
		functionNodeMap = new HashMap<>();
	}

	/**
	 * Adding a new function, id to variable hashmap
	 *
	 * @param fName
	 *            = functionName
	 * @param iName
	 *            = idName
	 */
	public void put(String fName, String iName) {
		int index = 0;
		// get current total number of local array elements
		if (functionMap.containsKey(fName)) {
			index = functionMap.get(fName);
		}
		// add id to variable hashmap
		HKey k = new HKey(fName, iName);
		variableMap.put(k, index);
		// increment number of local array elements
		// add to function hashmap
		index++;

		functionMap.put(fName, index);
	}

	/**
	 * Get the local array index for function,id
	 * @param fName = functionName
	 * @param iName = idName
	 * @return array index
	 */
	public int get(String fName, String iName) {
		HKey k = new HKey(fName, iName);
		return variableMap.get(k);
	}

	/**
	 * Checks if id has been hashed
	 * @param fName
	 * @param iName
	 * @return boolean
	 */
	public boolean containsKey(String fName, String iName) {
		HKey k = new HKey(fName, iName);
		return variableMap.containsKey(k);
	}

	/**
	 * Get variable count for a function
	 * @param fName = function name
	 * @return variable count
	 */
	public int getNumVariables(String fName) {
		if (functionMap.containsKey(fName))
			return functionMap.get(fName);
		else
			return 0;
	}

	/**
	 * Increment variable count
	 * @param fname
	 * @param num = value to be incremented by
	 */
	public void incrementNumVariables(String fname, int num) {
		int index = 0;
		if (functionMap.containsKey(fname)) {
			index = functionMap.get(fname);
		}
		functionMap.put(fname, index + num);
	}

	/**
	 *
	 * @param fName = function name
	 * @return Parameter list
	 */
	public Vector<String> getParameterList(String fName) {
		if (paramMap.containsKey(fName))
			return paramMap.get(fName);
		else
			return new Vector<String>();
	}

	/**
	 * Add parameter to list
	 * @param fName = function name
	 * @param iName = parameter name
	 */
	public void putParameter(String fName, String iName) {
		Vector<String> paramList;
		if (paramMap.containsKey(fName))
			paramList = paramMap.get(fName);
		else
			paramList = new Vector<String>();
		paramList.add(iName);
		paramMap.put(fName, paramList);
	}

	public void putFunction(String fName, Node node) {
	    functionNodeMap.put(fName, node);
    }

	public Map<HKey, Integer> getVariableMap() {
		return variableMap;
	}

	public Map<String, Integer> getFunctionMap() {
		return functionMap;
	}

	public Map<String, Vector<String>> getParamMap() {
		return paramMap;
	}

    public Map<String, Node> getFunctionNodeMap() {
        return functionNodeMap;
    }
}

/**
 * Key for variable Hashmap
 * @author Kannan
 *
 */
class HKey {
	String fName, iName;

	public HKey(String fName, String iName) {
		this.fName = fName;
		this.iName = iName;
	}

	@Override
	public boolean equals(Object o) {
		HKey n = (HKey) o;
		if (n.fName.equals(this.fName) && n.iName.equals(this.iName))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return fName.hashCode() + iName.hashCode();
	}
}
