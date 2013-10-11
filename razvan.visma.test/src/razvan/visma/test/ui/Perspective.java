package razvan.visma.test.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import razvan.visma.test.ui.views.PriceCalculationView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		 layout.addView(PriceCalculationView.ID, IPageLayout.TOP,
			        IPageLayout.RATIO_MAX, IPageLayout.ID_EDITOR_AREA);
		
	}
}
