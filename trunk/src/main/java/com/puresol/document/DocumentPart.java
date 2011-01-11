package com.puresol.document;

import java.io.Serializable;

import com.puresol.trees.Tree;

/**
 * This interface is an extension of Tree<?> to bind the document tree together.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface DocumentPart extends Tree<DocumentPart>, Serializable {

}
