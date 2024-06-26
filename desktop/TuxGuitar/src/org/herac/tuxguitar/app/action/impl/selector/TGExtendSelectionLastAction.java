package org.herac.tuxguitar.app.action.impl.selector;

import org.herac.tuxguitar.action.TGActionContext;
import org.herac.tuxguitar.action.TGActionManager;
import org.herac.tuxguitar.app.action.impl.measure.TGGoLastMeasureAction;
import org.herac.tuxguitar.app.action.impl.measure.TGGoNextMeasureAction;
import org.herac.tuxguitar.app.view.component.tab.Caret;
import org.herac.tuxguitar.app.view.component.tab.Selector;
import org.herac.tuxguitar.app.view.component.tab.Tablature;
import org.herac.tuxguitar.app.view.component.tab.TablatureEditor;
import org.herac.tuxguitar.document.TGDocumentContextAttributes;
import org.herac.tuxguitar.editor.action.TGActionBase;
import org.herac.tuxguitar.util.TGContext;

public class TGExtendSelectionLastAction extends TGActionBase {

	public static final String NAME = "action.selection.extend-last";

	public TGExtendSelectionLastAction(TGContext context) {
		super(context, NAME);
	}

	protected void processAction(TGActionContext context) {
		Tablature tablature = TablatureEditor.getInstance(getContext()).getTablature();
		Selector selector = tablature.getSelector();
		Caret caret = tablature.getCaret();
		if (!selector.isActive()) {
		    selector.initializeSelection(caret.getSelectedBeat());
		}
		context.setAttribute(TGDocumentContextAttributes.ATTRIBUTE_KEEP_SELECTION, true);
		TGActionManager tgActionManager = TGActionManager.getInstance(getContext());
		tgActionManager.execute(TGGoLastMeasureAction.NAME, context);
		tgActionManager.execute(TGGoNextMeasureAction.NAME, context);
		selector.updateSelection(caret.getSelectedBeat());
	}
}
