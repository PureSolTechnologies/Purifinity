package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraph;

import io.reactivex.functions.Consumer;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class MethodProfileViewer extends AbstractViewer implements Consumer<CodeGraph> {

    private static final long serialVersionUID = 4144855423662815232L;

    private BorderPane borderPane;

    public MethodProfileViewer() {
	super("Method Profile", PartOpenMode.AUTO_AND_MANUAL);
	ReactiveFX.getStore().subscribe("code.graph", this);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();
    }

    @Override
    public void close() {
	// TODO Auto-generated method stub

    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    @Override
    public void accept(CodeGraph codeGraph) throws Exception {

    }

    public void setMethod(String className, String method) {
	// TODO Auto-generated method stub

    }

}
