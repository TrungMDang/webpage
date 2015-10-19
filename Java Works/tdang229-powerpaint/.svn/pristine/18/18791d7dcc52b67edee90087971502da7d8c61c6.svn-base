/**
 * 
 */
package gui;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * @author Trung
 *
 */
public class ToolBar extends JToolBar {
    
    /***/
    private final ButtonGroup myButtonGroup;

    /**
     * 
     */
    public ToolBar() {
        super();
        myButtonGroup = new ButtonGroup();
    }
    
    public void createToolBarButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        myButtonGroup.add(toggleButton);
        if (theAction.getClass().getSimpleName().equals("Pencil")) {
            toggleButton.setSelected(true);
        }
        toggleButton.setMnemonic(theAction.getClass().getSimpleName().charAt(0));
        add(toggleButton);
    }
}
