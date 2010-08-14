package com.puresol.uhura.grammar;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This class stores the
 * 
 * @author ludwig
 * 
 */
public class NameReferenceTable {

	private final ConcurrentMap<String, Integer> name2Integer = new ConcurrentHashMap<String, Integer>();
	private final ConcurrentMap<Integer, String> integer2Name = new ConcurrentHashMap<Integer, String>();
	private final ConcurrentMap<Integer, Boolean> defined = new ConcurrentHashMap<Integer, Boolean>();
	private final ConcurrentMap<Integer, Boolean> token = new ConcurrentHashMap<Integer, Boolean>();

	private int counter = 0;

	private int addNameAndGetId(String name) {
		int id = counter;
		counter++;
		name2Integer.put(name, id);
		integer2Name.put(id, name);
		return id;
	}

	public int defineTokenAndGetId(String name) throws GrammarException {
		if (name2Integer.containsKey(name)) {
			int id = name2Integer.get(name);
			if (!defined.get(id)) {
				defined.put(id, true);
				token.put(id, true);
				return id;
			} else if (token.get(id)) {
				// alternative was defined...
				return id;
			}
			throw new GrammarException("Element name '" + name
					+ "' was double defined!");
		} else {
			int id = addNameAndGetId(name);
			defined.put(id, true);
			token.put(id, true);
			return id;
		}
	}

	public int defineProductionAndGetId(String name) throws GrammarException {
		if (name2Integer.containsKey(name)) {
			int id = name2Integer.get(name);
			if (!defined.get(id)) {
				defined.put(id, true);
				token.put(id, false);
				return id;
			} else if (!token.get(id)) {
				// alternative was defined...
				return id;
			}
			throw new GrammarException("Element name '" + name
					+ "' was double defined!");
		} else {
			int id = addNameAndGetId(name);
			defined.put(id, true);
			token.put(id, false);
			return id;
		}
	}

	public String getToken(int id) throws GrammarException {
		if (!integer2Name.containsKey(id)) {
			throw new GrammarException("Id '" + id + "' is not present!");
		}
		String name = integer2Name.get(id);
		if (!token.get(id)) {
			throw new GrammarException("Id '" + id + "' with name '" + name
					+ "' is not a token!");
		}
		return name;
	}

	public int getToken(String name) throws GrammarException {
		if (!name2Integer.containsKey(name)) {
			int id = addNameAndGetId(name);
			token.put(id, true);
			defined.put(id, false);
			return id;
		} else {
			int id = name2Integer.get(name);
			if (!token.get(id)) {
				throw new GrammarException("Id '" + id + "' with name '" + name
						+ "' is not a token!");
			}
			return id;
		}
	}

	public String getProduction(int id) throws GrammarException {
		if (!integer2Name.containsKey(id)) {
			throw new GrammarException("Id '" + id + "' is not present!");
		}
		String name = integer2Name.get(id);
		if (token.get(id)) {
			throw new GrammarException("Id '" + id + "' with name '" + name
					+ "' is not a production!");
		}
		return name;
	}

	public int getProduction(String name) throws GrammarException {
		if (!name2Integer.containsKey(name)) {
			int id = addNameAndGetId(name);
			token.put(id, false);
			defined.put(id, false);
			return id;
		} else {
			int id = name2Integer.get(name);
			if (token.get(id)) {
				throw new GrammarException("Id '" + id + "' with name '" + name
						+ "' is not a production!");
			}
			return id;
		}
	}

	public String getName(int id) {
		return integer2Name.get(id);
	}
}
